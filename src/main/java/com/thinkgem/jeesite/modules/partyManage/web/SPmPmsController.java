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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMrs;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPms;
import com.thinkgem.jeesite.modules.partyManage.service.SPmPmsService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是党员推荐汇总表Controller
 * @author one
 * @version 2017-04-17
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmPms")
public class SPmPmsController extends BaseController {

	@Autowired
	private SPmPmsService sPmPmsService;
	
	@ModelAttribute
	public SPmPms get(@RequestParam(required=false) String id) {
		SPmPms entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmPmsService.get(id);
		}
		if (entity == null){
			entity = new SPmPms();
		}
		return entity;
	}
	
	@RequestMapping(value = "list")
	public String list(SPmPms sPmPms, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmPms> page = sPmPmsService.findPage(new Page<SPmPms>(request, response), sPmPms); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmPmsList";
	}

	@RequestMapping(value = "form")
	public String form(String proId, SPmPms sPmPms, Model model) {
		User user = UserUtils.getUser();
		String userId = user.getId();
		if (userId != null && userId != "") {
			if (proId != null && proId != "") {
				SPmPms sPmPmss = sPmPmsService.getByproId(user.getProId());
				if(sPmPmss != null ){
					model.addAttribute("sPmPms", sPmPmss);
				    return "modules/partyManage/sPmPmsForm";
				}
			}
		}
		sPmPms.setProId(user.getProId());
		model.addAttribute("sPmPms", sPmPms);
		return "modules/partyManage/sPmPmsForm";
	}
	
	@RequestMapping(value = "detail")
	public String detail(String proId, SPmPms sPmPms, Model model) {
	   User user = UserUtils.getUser();
	   if (proId != null && proId != "") {
		   user.setProId(proId);
	   }
	   SPmPms sPmPmss = sPmPmsService.getByproId(user.getProId());
	   if (sPmPmss != null) {
		   //sPmPmsService.getForms(sPmPmss);
	       model.addAttribute("sPmPms", sPmPmss);
	       return "modules/partyManage/sPmPmsDetail";
	    }
	   sPmPms.setUploader(user.getName());
	   sPmPms.setUploadTime(new Date());
	   model.addAttribute("sPmPms", sPmPms);
	   return "modules/partyManage/sPmPmsDetail";
	}

	@RequestMapping(value = "save")
	public String save(SPmPms sPmPms, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmPms)) {
	      return form(user.getProId(), sPmPms, model);
	    }
	    String status = sPmPmsService.save(sPmPms, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmPms", sPmPms);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmPms/detail";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmPms/detail";
	    }
	}
	
	@RequestMapping(value = "delete")
	public String delete(SPmPms sPmPms, RedirectAttributes redirectAttributes) {
		sPmPmsService.delete(sPmPms);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmPms/list";
	}

}