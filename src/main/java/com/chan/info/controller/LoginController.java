package com.chan.info.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	//登录页(shiro配置需要两个/login 接口,一个是get用来获取登陆页面,一个用post用于登录,这是一个坑)
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login() {
    	System.out.println("LoginController.login  GET-------1");
        return "login";
    }
	
 // 登录提交地址和applicationontext-shiro.xml配置的loginurl一致。 (配置文件方式的说法)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Map<String, Object> map)
            throws Exception {
    	System.out.println("LoginController.login   POST-------2");
        // 登录失败从request中获取shiro处理的异常信息。shiroLoginFailure:就是shiro异常类的全类名.
        Object exception = request.getAttribute("shiroLoginFailure");
        String msg = "";
        if (exception != null){
            if (UnknownAccountException.class.getName().equals(exception)) {
                msg = "提示->账号不存在";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                msg = "提示->密码不正确";
            } else if (ExcessiveAttemptsException.class.getName().equals(exception)) {
            	msg = "ExcessiveAttemptsException -- > 登录失败次数过多：";
            } else {
                msg = "提示->未知错误";
            }
            map.put("msg", msg);
            return "login";
        }
        return "index";
    }

}
