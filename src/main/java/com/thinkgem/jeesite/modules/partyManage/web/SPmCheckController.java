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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmCheck;
import com.thinkgem.jeesite.modules.partyManage.service.SPmCheckService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是发展对象政审表Controller
 * @author one
 * @version 2017-04-21
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmCheck")
public class SPmCheckController extends BaseController {

	@Autowired
	private SPmCheckService sPmCheckService;
	
	@ModelAttribute
	public SPmCheck get(@RequestParam(required=false) String id) {
		SPmCheck entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmCheckService.get(id);
		}
		if (entity == null){
			entity = new SPmCheck();
		}
		return entity;
	}
	
	//@RequiresPermissions("partyManage:sPmCheck:view")
	@RequestMapping(value = "list")
	public String list(SPmCheck sPmCheck, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmCheck> page = sPmCheckService.findPage(new Page<SPmCheck>(request, response), sPmCheck); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmCheckList";
	}

	//@RequiresPermissions("partyManage:sPmCheck:view")
	@RequestMapping(value = "form")
	public String form(String proId, SPmCheck sPmCheck, Model model) {
		User user = UserUtils.getUser();
		String userId = user.getId();
		if (userId != null && userId != "") {
			if (proId != null && proId != "") {
				SPmCheck sPmChecks = sPmCheckService.getByproId(user.getProId());
				if(sPmChecks != null ){
					model.addAttribute("sPmCheck", sPmChecks);
				    return "modules/partyManage/sPmCheckForm";
				}
			}
		}
		sPmCheck.setProId(user.getProId());
		sPmCheck.setUploader(user.getName());
		sPmCheck.setUploadTime(new Date());
		model.addAttribute("sPmCheck", sPmCheck);
		return "modules/partyManage/sPmCheckForm";
	}

	@RequestMapping(value = "detail")
	public String detail(String proId, SPmCheck sPmCheck, Model model) {
	   User user = UserUtils.getUser();
	   if (proId != null && proId != "") {
		   user.setProId(proId);
	   }
	   SPmCheck sPmChecks = sPmCheckService.getByproId(user.getProId());
	   if (sPmChecks != null) {
		   //sPmCheckService.getForms(sPmChecks);
	       model.addAttribute("sPmCheck", sPmChecks);
	       return "modules/partyManage/sPmCheckDetail";
	    }
	   sPmCheck.setUploader(user.getName());
	   sPmCheck.setUploadTime(new Date());
	   model.addAttribute("sPmCheck", sPmCheck);
	   return "modules/partyManage/sPmCheckDetail";
	}
	
	//@RequiresPermissions("partyManage:sPmCheck:edit")
	@RequestMapping(value = "save")
	public String save(SPmCheck sPmCheck, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmCheck)) {
	      return form(user.getProId(), sPmCheck, model);
	    }
	    String status = sPmCheckService.save(sPmCheck, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmCheck", sPmCheck);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmCheck/detail";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmCheck/detail";
	    }
	}
	
	//@RequiresPermissions("partyManage:sPmCheck:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmCheck sPmCheck, RedirectAttributes redirectAttributes) {
		sPmCheckService.delete(sPmCheck);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmCheck/list";
	}

}