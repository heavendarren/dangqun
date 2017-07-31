/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import java.util.Date;

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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMinutesS;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMrs;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPb;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmTalkingS;
import com.thinkgem.jeesite.modules.partyManage.service.SPmPbService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是党总支会议记录表Controller
 * @author one
 * @version 2017-04-26
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmPb")
public class SPmPbController extends BaseController {

	@Autowired
	private SPmPbService sPmPbService;
	
	@ModelAttribute
	public SPmPb get(@RequestParam(required=false) String id) {
		SPmPb entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmPbService.get(id);
		}
		if (entity == null){
			entity = new SPmPb();
		}
		return entity;
	}
	
	//@RequiresPermissions("partyManage:sPmPb:view")
	@RequestMapping(value = "list")
	public String list(SPmPb sPmPb, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmPb> page = sPmPbService.findPage(new Page<SPmPb>(request, response), sPmPb); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmPbList";
	}

	//@RequiresPermissions("partyManage:sPmPb:view")
	@RequestMapping(value = "form")
	public String form(String proId, SPmPb sPmPb, Model model) {
		User user = UserUtils.getUser();
		String userId = user.getId();
		if (userId != null && userId != "") {
			if (proId != null && proId != "") {
				SPmPb sPmPbs = sPmPbService.getByproId(user.getProId());
				if(sPmPbs != null ){
					model.addAttribute("sPmPb", sPmPbs);
				    return "modules/partyManage/sPmPbForm";
				}
			}
		}
		sPmPb.setProId(user.getProId());
		model.addAttribute("sPmPb", sPmPb);
		return "modules/partyManage/sPmPbForm";
	}

	@RequestMapping(value = "detail")
	public String detail(String proId, SPmPb sPmPb, Model model) {
	   User user = UserUtils.getUser();
	   if (proId != null && proId != "") {
		   user.setProId(proId);
	   }
	   SPmPb sPmPbs = sPmPbService.getByproId(user.getProId());
	   if (sPmPbs != null) {
		   //sPmPbService.getForms(sPmPbs);
	       model.addAttribute("sPmPb", sPmPbs);
	       return "modules/partyManage/sPmPbDetail";
	    }
	   model.addAttribute("sPmPb", sPmPb);
	   return "modules/partyManage/sPmPbDetail";
	}
	
	//@RequiresPermissions("partyManage:sPmPb:edit")
	@RequestMapping(value = "save")
	public String save(SPmPb sPmPb, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmPb)) {
	      return form(user.getProId(), sPmPb, model);
	    }
	    String status = sPmPbService.save(sPmPb, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmPb", sPmPb);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmPb/detail";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmPb/detail";
	    }
	}
	
	//@RequiresPermissions("partyManage:sPmPb:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmPb sPmPb, RedirectAttributes redirectAttributes) {
		sPmPbService.delete(sPmPb);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmPb/list";
	}

}