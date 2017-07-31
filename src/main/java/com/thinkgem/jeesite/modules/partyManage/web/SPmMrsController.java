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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAppUpload;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmDevelopmentOption;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmExamineRecord;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMass;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMrs;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmMrsService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是群众推荐汇总表Controller
 * @author one
 * @version 2017-04-17
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmMrs")
public class SPmMrsController extends BaseController {

	@Autowired
	private SPmMrsService sPmMrsService;
	@Autowired
	private DQRecordService dqRecordService;
	
	@ModelAttribute
	public SPmMrs get(@RequestParam(required=false) String id) {
		SPmMrs entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmMrsService.get(id);
		}
		if (entity == null){
			entity = new SPmMrs();
		}
		return entity;
	}
	
	@RequestMapping(value = "list")
	public String list(SPmMrs sPmMrs, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmMrs> page = sPmMrsService.findPage(new Page<SPmMrs>(request, response), sPmMrs); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmMrsList";
	}

	@RequestMapping(value = "form")
	public String form(String proId,SPmMrs sPmMrs, Model model) {
		User user = UserUtils.getUser();
		String userId = user.getId();
		if (userId != null && userId != "") {
			if (proId != null && proId != "") {
				SPmMrs sPmMrss = sPmMrsService.getByproId(user.getProId());
				if(sPmMrss != null ){
					model.addAttribute("sPmMrs", sPmMrss);
				    return "modules/partyManage/sPmMrsForm";
				}
			}
		}
		sPmMrs.setProId(user.getProId());
		model.addAttribute("sPmMrs", sPmMrs);
		return "modules/partyManage/sPmMrsForm";
	}
	
	@RequestMapping(value = "active/detail")
	  public String detail(String proId, Model model) {
	    User user = UserUtils.getUser();
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	    }
	    return "modules/partyManage/sPmFormChange";
	 }
	
	@RequestMapping(value = "detail")
	public String detail(String proId, SPmMrs sPmMrs, Model model) {
	   User user = UserUtils.getUser();
	   if (proId != null && proId != "") {
		   user.setProId(proId);
	   }
	   SPmMrs sPmMrss = sPmMrsService.getByproId(user.getProId());
	   if (sPmMrss != null) {
		   sPmMrsService.getForms(sPmMrss);
	       model.addAttribute("sPmMrs", sPmMrss);
	       return "modules/partyManage/sPmMrsDetail";
	    }
	   sPmMrs.setRecTime(new Date());
	   sPmMrs.setUploader(user.getName());
	   sPmMrs.setUploadTime(new Date());
	   model.addAttribute("sPmMrs", sPmMrs);
	   return "modules/partyManage/sPmMrsDetail";
	}
	

	@RequestMapping(value = "save")
	public String save(SPmMrs sPmMrs, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmMrs)) {
	      return form(user.getProId(), sPmMrs, model);
	    }
	    String status = sPmMrsService.save(sPmMrs, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmMrs", sPmMrs);
	      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmMrs/form";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmMrs/form";
	    }
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(SPmMrs sPmMrs, RedirectAttributes redirectAttributes) {
		sPmMrsService.delete(sPmMrs);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmMrs/list";
	}

}