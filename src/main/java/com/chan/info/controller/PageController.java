package com.chan.info.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	
	@RequestMapping("/tagtestone")
	public String tagtestone() {
		return "tagtestone";
	}
	
	@RequestMapping("/add")
	//@RequiresPermissions("add")
	public String personAdd() {
		return "personAdd";
	}

	@RequestMapping("/person")
	@RequiresPermissions("person")
	public String person() {
		return "person";
	}

	@RequestMapping("/success")
	@RequiresPermissions("success")
	public String success() {
		return "success";
	}

	//主页
    @RequestMapping({"/","/index"})
    public String index(HttpServletRequest request,Model model){
    	System.out.println("PageController.index-------1");
        return "index";
    }
	
//    @RequiresRoles(value={"admin","vip","god"}, logical= Logical.OR)
//    @RequiresRoles(value={"admin","vip","god"}, logical= Logical.AND)
	@RequestMapping("/personOption")
	public String personOption() {
		System.out.println("PageController.personOption-------2");
		return "personOption";
	}
    
//    @RequiresRoles(value={"admin","vip","god"}, logical= Logical.AND)
	@RequestMapping("/roleNoPer")
	public String roleNoPer() {
		System.out.println("PageController.roleNoPer-------3");
		return "roleNoPer";
	}

	@RequestMapping("/everyone")
	public String everyone() {
		return "everyone";
	}

	@RequestMapping("/403")
	public String errorPage() {
		return "403";
	}
	
	@RequestMapping("/404")
	public String errorPageFour() {
		return "404";
	}

}
