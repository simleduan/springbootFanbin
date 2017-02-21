package com.chan.config.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
/**
 * 自定义授权过滤器
 * @author Administrator
 *
 */
public class MyFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		
		System.out.println("执行了自定义的授权过滤器");
		//得到subject
		Subject subject=getSubject(request, response);
		
		String []perms=(String [])mappedValue;//得到shiro配置文件中perms[]中的部分
		if(perms==null || perms.length==0){
			return true;
		}
		for(String p:perms){
			if(subject.isPermitted(p)){
				return true;
			}			
		}
		
		return false;
	}

}
