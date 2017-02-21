package com.chan.info.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiroMethodConroller {

	@RequestMapping("editAdd")
	@RequiresPermissions(value = {"weihu:add"})
	public String editAdd() {
		return "您正在访问--增加--人员信息的方法方法";
	}

	@RequestMapping("editDel")
	@RequiresPermissions(value = {"weihu:del"})
	public String editDel() {
		return "您正在访问--删除--人员信息的方法方法";
	}

	@RequestMapping("editUp")
	@RequiresPermissions(value = {"weihu:up"})
	public String editUp() {
		return "您正在访问--更新--人员信息的方法方法";
	}

	@RequestMapping("editSel")
	@RequiresPermissions(value = {"weihu:sel"})
	public String editSel() {
		return "您正在访问--查询--人员信息的方法方法";
	}
}
