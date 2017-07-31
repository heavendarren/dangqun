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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMinutes;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMinutesS;
import com.thinkgem.jeesite.modules.partyManage.service.SPmMinutesSService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是会议记录表Controller
 * @author one
 * @version 2017-04-26
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmMinutesS")
public class SPmMinutesSController extends BaseController {

	@Autowired
	private SPmMinutesSService sPmMinutesSService;
	
	@ModelAttribute
	public SPmMinutesS get(@RequestParam(required=false) String id) {
		SPmMinutesS entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmMinutesSService.get(id);
		}
		if (entity == null){
			entity = new SPmMinutesS();
		}
		return entity;
	}
	
	//@RequiresPermissions("partyManage:sPmMinutesS:view")
	@RequestMapping(value = "list")
	public String list(SPmMinutesS sPmMinutesS, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmMinutesS> page = sPmMinutesSService.findPage(new Page<SPmMinutesS>(request, response), sPmMinutesS); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmMinutesSList";
	}

	//@RequiresPermissions("partyManage:sPmMinutesS:view")
	@RequestMapping(value = "form")
	public String form(String proId, SPmMinutesS sPmMinutesS, Model model) {
		User user = UserUtils.getUser();
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	      SPmMinutesS sPmMinutes1 = sPmMinutesSService.getByproId(proId);
	      if (sPmMinutes1 != null) {
	        sPmMinutesSService.getForms(sPmMinutes1);
	        sPmMinutes1.setUploader(user.getName());
	        sPmMinutes1.setUploadTime(new Date());
	        model.addAttribute("sPmMinutesS", sPmMinutes1);
	        return "modules/partyManage/sPmMinutesSForm";
	      }
	    }
		sPmMinutesS.setProId(user.getProId());
	    sPmMinutesS.setUploader(user.getName());
	    sPmMinutesS.setUploadTime(new Date());
	    model.addAttribute("sPmMinutesS", sPmMinutesS);
	    return "modules/partyManage/sPmMinutesSForm";
	}

	@RequestMapping(value = "discuss/detail")
	public String detail(String proId, Model model) {
	    User user = UserUtils.getUser();
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	    }
	    return "modules/partyManage/sPmDiscussDetail";
	}
	
	@RequestMapping(value = "detail")
	public String detail(String proId, SPmMinutesS sPmMinutesS, Model model) {
	   User user = UserUtils.getUser();
	   if (proId != null && proId != "") {
		   user.setProId(proId);
	   }
	   SPmMinutesS sPmMinutesSs = sPmMinutesSService.getByproId(user.getProId());
	   if (sPmMinutesSs != null) {
	       model.addAttribute("sPmMinutesS", sPmMinutesSs);
	       return "modules/partyManage/sPmMinutesSDetail";
	    }
	   /*sPmMinutesS.setUploader(user.getName());
	   sPmMinutesS.setUploadTime(new Date());*/
	   model.addAttribute("sPmMinutesS", sPmMinutesS);
	   return "modules/partyManage/sPmMinutesSDetail";
	}
	
	
	//@RequiresPermissions("partyManage:sPmMinutesS:edit")
	@RequestMapping(value = "save")
	public String save(SPmMinutesS sPmMinutesS, Model model, RedirectAttributes redirectAttributes) {
		
		/*if (!beanValidator(model, sPmMinutesS)){
		
			return form(sPmMinutesS.getProId(), sPmMinutesS, model);
		}
		User user = UserUtils.getUser();
		sPmMinutesS.setProId(user.getProId());
		sPmMinutesSService.saveMss(sPmMinutesS);
		addMessage(redirectAttributes, "保存表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmMinutesS/form";*/
		
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmMinutesS)) {
	      return form(user.getProId(), sPmMinutesS, model);
	    }
	    String status = sPmMinutesSService.save(sPmMinutesS, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmMinutesS", sPmMinutesS);
	      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmMinutesS/form";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmMinutesS/form";
	    }
	}
	
	//@RequiresPermissions("partyManage:sPmMinutesS:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmMinutesS sPmMinutesS, RedirectAttributes redirectAttributes) {
		sPmMinutesSService.delete(sPmMinutesS);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmMinutesS/list";
	}

}