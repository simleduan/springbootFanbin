package com.chan.config.shiro;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @PackageName : com.xuezhijian.shiro
 * @author      : 樊斌
 * @date        : 2017年2月14日
 * @time        : 下午2:07:54
 * @version     : 1.0
 * @Description : TODO
 */
public class MyExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    	System.out.println("MyExceptionResolver.resolveException---------1");
        //如果是shiro无权操作，因为shiro 在操作auno等一部分不进行转发至无权限url
        if(ex instanceof UnauthorizedException){
            ModelAndView mv = new ModelAndView("404");
            return mv;
        }
        return null;
    }

}
