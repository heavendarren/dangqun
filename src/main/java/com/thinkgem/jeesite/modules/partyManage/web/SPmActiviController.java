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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmActivi;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMass;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMrs;
import com.thinkgem.jeesite.modules.partyManage.service.SPmActiviService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是入党积极分子备案表Controller
 * @author one
 * @version 2017-04-18
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmActivi")
public class SPmActiviController extends BaseController {

	@Autowired
	private SPmActiviService sPmActiviService;
	
	@ModelAttribute
	public SPmActivi get(@RequestParam(required=false) String id) {
		SPmActivi entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmActiviService.get(id);
		}
		if (entity == null){
			entity = new SPmActivi();
		}
		return entity;
	}
	
	@RequestMapping(value = "list")
	public String list(SPmActivi sPmActivi, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmActivi> page = sPmActiviService.findPage(new Page<SPmActivi>(request, response), sPmActivi); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmActiviList";
	}

	@RequestMapping(value = "form")
	public String form(String proId, SPmActivi sPmActivi, Model model) {
		
		User user = UserUtils.getUser();
		String userId = user.getId();
		if (userId != null && userId != "") {
			if (proId != null && proId != "") {
				SPmActivi sPmActivis = sPmActiviService.getByproId(user.getProId());
				if(sPmActivis != null ){
					model.addAttribute("sPmActivi", sPmActivis);
				    return "modules/partyManage/sPmActiviForm";
				}
			}
		}
		sPmActivi.setProId(user.getProId());
		model.addAttribute("sPmActivi", sPmActivi);
		return "modules/partyManage/sPmActiviForm";
	}
	
	@RequestMapping(value = "detail")
	public String detail(String proId, SPmActivi sPmActivi, Model model) {
	   User user = UserUtils.getUser();
	   if (proId != null && proId != "") {
		   user.setProId(proId);
	   }
	   SPmActivi sPmActivis = sPmActiviService.getByproId(user.getProId());
	   if (sPmActivis != null) {
		   //sPmActiviService.getForms(sPmActivis);
	       model.addAttribute("sPmActivi", sPmActivis);
	       return "modules/partyManage/sPmActiviDetail";
	    }
	   sPmActivi.setUploader(user.getName());
	   sPmActivi.setUploadTime(new Date());
	   model.addAttribute("sPmActivi", sPmActivi);
	   return "modules/partyManage/sPmActiviDetail";
	}
	

	@RequestMapping(value = "save")
	public String save(SPmActivi sPmActivi, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmActivi)) {
	      return form(user.getProId(), sPmActivi, model);
	    }
	    String status = sPmActiviService.save(sPmActivi, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmActivi", sPmActivi);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmActivi/detail";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmActivi/detail";
	    }
	}
	
	@RequestMapping(value = "delete")
	public String delete(SPmActivi sPmActivi, RedirectAttributes redirectAttributes) {
		sPmActiviService.delete(sPmActivi);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmActivi/list";
	}

}