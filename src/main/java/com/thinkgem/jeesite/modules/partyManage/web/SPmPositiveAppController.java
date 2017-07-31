/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPartyMen;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPositiveApp;
import com.thinkgem.jeesite.modules.partyManage.service.SPmPositiveAppService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是转正申请表Controller
 * @author one
 * @version 2017-04-28
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmPositiveApp")
public class SPmPositiveAppController extends BaseController {

	@Autowired
	private SPmPositiveAppService sPmPositiveAppService;
	
	@ModelAttribute
	public SPmPositiveApp get(@RequestParam(required=false) String id) {
		SPmPositiveApp entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmPositiveAppService.get(id);
		}
		if (entity == null){
			entity = new SPmPositiveApp();
		}
		return entity;
	}
	
	//@RequiresPermissions("partyManage:sPmPositiveApp:view")
	@RequestMapping(value = "list")
	public String list(String proId, SPmJionFiles sPmJionFiles, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		SPmPositiveApp sPmPositiveApp = new SPmPositiveApp();
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	    }
		Page<SPmJionFiles> page = sPmPositiveAppService.findJoinList(new Page<SPmJionFiles>(request, response), sPmJionFiles, user.getProId());
		sPmPositiveApp.setUploader(user.getName());
		sPmPositiveApp.setUploadTime(new Date());
	    model.addAttribute("page", page);
		return "modules/partyManage/sPmPositiveAppList";
	}

	//@RequiresPermissions("partyManage:sPmPositiveApp:view")
	@RequestMapping(value = "form")
	public String form(String proId, SPmPositiveApp sPmPositiveApp, Model model) {
		User user = UserUtils.getUser();
		if (proId != null && proId != "") {
			user.setProId(proId);
		}
		SPmPositiveApp sPmPositiveApps = sPmPositiveAppService.getByproId(user.getProId());
		if (sPmPositiveApps != null) {
			sPmPositiveAppService.findForms(sPmPositiveApps);
			model.addAttribute("sPmPositiveApp", sPmPositiveApps);
			return "modules/partyManage/sPmPositiveAppForm";
		}
		sPmPositiveApp.setUploader(user.getName());
		sPmPositiveApp.setUploadTime(new Date());
		model.addAttribute("sPmPositiveApp", sPmPositiveApp);
		return "modules/partyManage/sPmPositiveAppForm";
	}
	
	
	@RequestMapping(value = "detail")
	public String detail(String proId, SPmPositiveApp sPmPositiveApp, Model model) {
		User user = UserUtils.getUser();
		if (proId != null && proId != "") {
			user.setProId(proId);
		}
		SPmPositiveApp sPmPositiveApps = sPmPositiveAppService.getByproId(user.getProId());
		if (sPmPositiveApps != null) {
			sPmPositiveAppService.findForms(sPmPositiveApps);
			model.addAttribute("sPmPositiveApp", sPmPositiveApps);
			return "modules/partyManage/sPmPositiveAppDetail";
		}
		sPmPositiveApp.setUploader(user.getName());
		sPmPositiveApp.setUploadTime(new Date());
		model.addAttribute("sPmPositiveApp", sPmPositiveApp);
		return "modules/partyManage/sPmPositiveAppDetail";
	}

	//@RequiresPermissions("partyManage:sPmPositiveApp:edit")
	@RequestMapping(value = "save")
	public String save(SPmPositiveApp sPmPositiveApp, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		if (!beanValidator(model, sPmPositiveApp)) {
			return form(user.getProId(), sPmPositiveApp, model);
		}
		List<String> fileUrlList = sPmPositiveApp.getFileUrls();
		List<String> fileNameList = sPmPositiveApp.getFileNames();
		List<String> status = new ArrayList<String>();
		for (int i = 0; i < fileUrlList.size(); i++) {
			String statu = sPmPositiveAppService.save(sPmPositiveApp, user.getProId(), fileUrlList.get(i), fileNameList.get(i),"file");
			status.add(statu);
		}
		List<String> imgUrlList = sPmPositiveApp.getImgUrls();
		List<String> imgNameList = sPmPositiveApp.getImgNames();
		for (int i = 0; i < imgUrlList.size(); i++) {
			String statu = sPmPositiveAppService.save(sPmPositiveApp, user.getProId(), imgUrlList.get(i), imgNameList.get(i),"img");
			status.add(statu);
		}
		List<String> fileUrlsList = sPmPositiveApp.getFileUrlss();
		List<String> fileNamesList = sPmPositiveApp.getFileNamess();
		
		for (int i = 0; i < fileUrlsList.size(); i++) {
			String statu = sPmPositiveAppService.save(sPmPositiveApp, user.getProId(), fileUrlsList.get(i), fileNamesList.get(i),"files");
			status.add(statu);
		}
		 Map<String, String> sta = new HashMap<String, String>();
		    for (String statu : status) {
		      if (statu != "success") {
		        sta.put("statu", "保存失败");
		        break;
		      }
		      sta.put("statu", "保存成功");
		    }
		    addMessage(redirectAttributes, sta.get("statu"));
		sPmPositiveAppService.save(sPmPositiveApp);
		addMessage(redirectAttributes, "保存表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmPositiveApp/list";
	}
	
	//@RequiresPermissions("partyManage:sPmPositiveApp:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmPositiveApp sPmPositiveApp, RedirectAttributes redirectAttributes) {
		sPmPositiveAppService.delete(sPmPositiveApp);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmPositiveApp/?repage";
	}

}