/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAuto;
import com.thinkgem.jeesite.modules.partyManage.service.SPmAutoService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是自传表Controller
 * @author one
 * @version 2017-04-21
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmAuto")
public class SPmAutoController extends BaseController {

	@Autowired
	private SPmAutoService sPmAutoService;
	
	@ModelAttribute
	public SPmAuto get(@RequestParam(required=false) String id) {
		SPmAuto entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmAutoService.get(id);
		}
		if (entity == null){
			entity = new SPmAuto();
		}
		return entity;
	}
	
	//@RequiresPermissions("partyManage:sPmAuto:view")
	@RequestMapping(value = "list")
	public String list(SPmAuto sPmAuto, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmAuto> page = sPmAutoService.findPage(new Page<SPmAuto>(request, response), sPmAuto); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmAutoList";
	}

	//@RequiresPermissions("partyManage:sPmAuto:view")
	@RequestMapping(value = "form")
	public String form(String proId, SPmAuto sPmAuto, Model model) {
		User user = UserUtils.getUser();
		String userId = user.getId();
		if (userId != null && userId != "") {
			if (proId != null && proId != "") {
				SPmAuto sPmAutos = sPmAutoService.getByproId(user.getProId());
				if(sPmAutos != null ){
					model.addAttribute("sPmAuto", sPmAutos);
				    return "modules/partyManage/sPmAutoForm";
				}
			}
		}
		sPmAuto.setProId(user.getProId());
		model.addAttribute("sPmAuto", sPmAuto);
		return "modules/partyManage/sPmAutoForm";
	}
	
	@RequestMapping(value = "political/detail")
	  public String detail(String proId, Model model) {
	    User user = UserUtils.getUser();
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	    }
	    return "modules/partyManage/sPmPoliticalDetail";
	 }
	
	@RequestMapping(value = "detail")
	public String detail(String proId, SPmAuto sPmAuto, Model model) {
	   User user = UserUtils.getUser();
	   if (proId != null && proId != "") {
		   user.setProId(proId);
	   }
	   SPmAuto sPmAutos = sPmAutoService.getByproId(user.getProId());
	   if (sPmAutos != null) {
		   sPmAutoService.getForms(sPmAutos);
	       model.addAttribute("sPmAuto", sPmAutos);
	       return "modules/partyManage/sPmAutoDetail";
	    }
	   sPmAuto.setUploader(user.getName());
	   sPmAuto.setUploadTime(new Date());
	   model.addAttribute("sPmAuto", sPmAuto);
	   return "modules/partyManage/sPmAutoDetail";
	}

	//@RequiresPermissions("partyManage:sPmAuto:edit")
	@RequestMapping(value = "save")
	public String save(SPmAuto sPmAuto, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmAuto)) {
	      return form(user.getProId(), sPmAuto, model);
	    }
	    String status = sPmAutoService.save(sPmAuto, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmAuto", sPmAuto);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmAuto/detail";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmAuto/detail";
	    }
	}
	
	//@RequiresPermissions("partyManage:sPmAuto:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmAuto sPmAuto, RedirectAttributes redirectAttributes) {
		sPmAutoService.delete(sPmAuto);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmAuto/list";
	}

}