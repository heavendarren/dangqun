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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmOpinion;
import com.thinkgem.jeesite.modules.partyManage.service.SPmOpinionService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是党员意见表Controller
 * @author one
 * @version 2017-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmOpinion")
public class SPmOpinionController extends BaseController {

	@Autowired
	private SPmOpinionService sPmOpinionService;
	
	@ModelAttribute
	public SPmOpinion get(@RequestParam(required=false) String id) {
		SPmOpinion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmOpinionService.get(id);
		}
		if (entity == null){
			entity = new SPmOpinion();
		}
		return entity;
	}
	
	//@RequiresPermissions("partyManage:sPmOpinion:view")
	@RequestMapping(value = "list")
	public String list(SPmOpinion sPmOpinion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmOpinion> page = sPmOpinionService.findPage(new Page<SPmOpinion>(request, response), sPmOpinion); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmOpinionList";
	}

	@RequestMapping(value = "detail")
	public String detail(String proId, SPmOpinion sPmOpinion, Model model) {
	   User user = UserUtils.getUser();
	   if (proId != null && proId != "") {
		   user.setProId(proId);
	   }
	   SPmOpinion sPmOpinions = sPmOpinionService.getByproId(user.getProId());
	   if (sPmOpinions != null) {
	       model.addAttribute("sPmOpinion", sPmOpinions);
	       return "modules/partyManage/sPmOpinionDetail";
	    }
	   sPmOpinion.setStartTime(new Date());
	   sPmOpinion.setUploader(user.getName());
	   sPmOpinion.setUploadTime(new Date());
	   model.addAttribute("sPmOpinion", sPmOpinion);
	   return "modules/partyManage/sPmOpinionDetail";
	}
	
	//@RequiresPermissions("partyManage:sPmOpinion:view")
	@RequestMapping(value = "form")
	public String form(String proId, SPmOpinion sPmOpinion, Model model) {
		
		User user = UserUtils.getUser();
		String userId = user.getId();
		if (userId != null && userId != "") {
			if (proId != null && proId != "") {
				SPmOpinion sPmOpinions = sPmOpinionService.getByproId(user.getProId());
				if(sPmOpinions != null ){
					model.addAttribute("sPmOpinion", sPmOpinions);
				    return "modules/partyManage/sPmOpinionForm";
				}
			}
		}
		sPmOpinion.setProId(user.getProId());
		model.addAttribute("sPmOpinion", sPmOpinion);
		return "modules/partyManage/sPmOpinionForm";
	}

	//@RequiresPermissions("partyManage:sPmOpinion:edit")
	@RequestMapping(value = "save")
	public String save(SPmOpinion sPmOpinion, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmOpinion)) {
	      return form(user.getProId(), sPmOpinion, model);
	    }
	    String status = sPmOpinionService.save(sPmOpinion, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmOpinion", sPmOpinion);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmOpinion/detail";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmOpinion/detail";
	    }
	}
	
	//@RequiresPermissions("partyManage:sPmOpinion:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmOpinion sPmOpinion, RedirectAttributes redirectAttributes) {
		sPmOpinionService.delete(sPmOpinion);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmOpinion/list";
	}

}