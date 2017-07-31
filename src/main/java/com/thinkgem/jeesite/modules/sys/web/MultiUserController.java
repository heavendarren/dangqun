package com.thinkgem.jeesite.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

@Controller
@RequestMapping(value = "${adminPath}/sys/multiUser")
public class MultiUserController extends BaseController {
	@Autowired
	private SystemService systemService;

	@RequiresPermissions("user")
	@RequestMapping(value = { "demo" })
	public String demo(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "jeesite/test/multiUser";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "userList")
	public String selectList(User user, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<User> page = systemService.findUser(new Page<User>(request,
				response), user);
		model.addAttribute("page", page);
		model.addAttribute("flag", request.getParameter("flag"));
		return "modules/sys/userList-mlt";
	}
}
