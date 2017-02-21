package com.chan.config.shiro;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.chan.info.model.UserInfo;
import com.chan.info.service.GetRoleAndPerService;
import com.chan.info.service.UserInfoService;

/**
 * 
 * @PackageName : com.chan.config.shiro
 * @author      : 樊斌
 * @date        : 2017年2月15日
 * @time        : 下午1:25:58
 * @version     : 1.0
 * @Description : TODO
 */
public class FanBinRealm extends AuthorizingRealm {
	@Resource
	private UserInfoService userInfoService;

	@Resource
	private GetRoleAndPerService getRoleAndPerService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("FanBinRealm.doGetAuthorizationInfo权限配置处理---------5");
		UserInfo userinfo = (UserInfo) principalCollection.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		String username = userinfo.getUsername();
		List<Map<String, String>> list = getRoleAndPerService.getRoleByUsername(username);
//		System.out.println("我获取到的角色信息值是" + list.toString());
		if(list!=null){
			for (Map<String, String> map : list) {
				if(map!=null){
					info.addRole(map.get("role"));
				}
				
			}
		}

		List<Map<String,String>> list2 = getRoleAndPerService.getPermissionByUsername(username);
//		System.out.println("我获取到的权限信息值是" + list2.toString());
		if(list2!=null){
			for (Map<String, String> map : list2) {
				if(map!=null){
					info.addStringPermission(map.get("permission"));
				}
			}
		}
		return info;
	}

	/** 登录认证 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		System.out.println("FanBinRealm.doGetAuthenticationInfo登陆认证处理---------4");
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        UserInfo userInfo = userInfoService.queryUserInfo(username);// 判断账号是否存在

        if(userInfo == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        		userInfo, //用户实体类
        		userInfo.getPassword(), //密码
                getName()  //realm name
        );
        return authenticationInfo;
	}
}
