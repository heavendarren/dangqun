/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMass;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmTalking;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmTalkingS;
import com.thinkgem.jeesite.modules.partyManage.service.SPmTalkingSService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这还算预备党员谈话纪实表Controller
 * @author one
 * @version 2017-04-26
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmTalkingS")
public class SPmTalkingSController extends BaseController {

	@Autowired
	private SPmTalkingSService sPmTalkingSService;
	
	@ModelAttribute
	public SPmTalkingS get(@RequestParam(required=false) String id) {
		SPmTalkingS entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmTalkingSService.get(id);
		}
		if (entity == null){
			entity = new SPmTalkingS();
		}
		return entity;
	}
	
	//@RequiresPermissions("partyManage:sPmTalkingS:view")
	@RequestMapping(value = "list")
	public String list(SPmTalkingS sPmTalkingS, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmTalkingS> page = sPmTalkingSService.findPage(new Page<SPmTalkingS>(request, response), sPmTalkingS); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmTalkingSList";
	}

	//@RequiresPermissions("partyManage:sPmTalkingS:view")
	@RequestMapping(value = "form")
	public String form(String proId, SPmTalkingS sPmTalkingS, Model model) {
		User user = UserUtils.getUser();
		String userId = user.getId();
		if (userId != null && userId != "") {
			if (proId != null && proId != "") {
				SPmTalkingS sPmTalkingSs = sPmTalkingSService.getByproId(user.getProId());
				if(sPmTalkingSs != null ){
					model.addAttribute("sPmTalkingS", sPmTalkingSs);
				    return "modules/partyManage/sPmTalkingSForm";
				}
			}
		}
		sPmTalkingS.setProId(user.getProId());
		model.addAttribute("sPmTalkingS", sPmTalkingS);
		return "modules/partyManage/sPmTalkingSForm";
	}

	@RequestMapping(value = "detail")
	public String detail(String proId, SPmTalkingS sPmTalkingS, Model model) {
		User user = UserUtils.getUser();
		if (proId != null && proId != "") {
			user.setProId(proId);
		}
		SPmTalkingS sPmTalkingSs = sPmTalkingSService.getByproId(user.getProId());
		if (sPmTalkingSs != null) {
			model.addAttribute("sPmTalkingS", sPmTalkingSs);
			return "modules/partyManage/sPmTalkingSDetail";
		}
		model.addAttribute("sPmTalkingS", sPmTalkingS);
		return "modules/partyManage/sPmTalkingSDetail";
	}
	
	//@RequiresPermissions("partyManage:sPmTalkingS:edit")
	@RequestMapping(value = "save")
	public String save(SPmTalkingS sPmTalkingS, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmTalkingS)) {
	      return form(user.getProId(), sPmTalkingS, model);
	    }
	    String status = sPmTalkingSService.save(sPmTalkingS, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmTalkingS", sPmTalkingS);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmTalkingS/detail";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmTalkingS/detail";
	    }
	}
	
	//@RequiresPermissions("partyManage:sPmTalkingS:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmTalkingS sPmTalkingS, RedirectAttributes redirectAttributes) {
		sPmTalkingSService.delete(sPmTalkingS);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmTalkingS/list";
	}

}