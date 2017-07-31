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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAuto;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMass;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmProve;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmReport;
import com.thinkgem.jeesite.modules.partyManage.service.SPmReportService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是政审报告表Controller
 * @author one
 * @version 2017-04-21
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmReport")
public class SPmReportController extends BaseController {

	@Autowired
	private SPmReportService sPmReportService;
	
	@ModelAttribute
	public SPmReport get(@RequestParam(required=false) String id) {
		SPmReport entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmReportService.get(id);
		}
		if (entity == null){
			entity = new SPmReport();
		}
		return entity;
	}
	
	//@RequiresPermissions("partyManage:sPmReport:view")
	@RequestMapping(value = "list")
	public String list(SPmReport sPmReport, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmReport> page = sPmReportService.findPage(new Page<SPmReport>(request, response), sPmReport); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmReportList";
	}

	//@RequiresPermissions("partyManage:sPmReport:view")
	@RequestMapping(value = "form")
	public String form(String proId, SPmReport sPmReport, Model model) {
		User user = UserUtils.getUser();
		String userId = user.getId();
		if (userId != null && userId != "") {
			if (proId != null && proId != "") {
				SPmReport sPmReports = sPmReportService.getByproId(user.getProId());
				if(sPmReports != null ){
					model.addAttribute("sPmReport", sPmReports);
				    return "modules/partyManage/sPmReportForm";
				}
			}
		}
		sPmReport.setProId(user.getProId());
		model.addAttribute("sPmReport", sPmReport);
		return "modules/partyManage/sPmReportForm";
	}

	@RequestMapping(value = "detail")
	public String detail(String proId, SPmReport sPmReport, Model model) {
	   User user = UserUtils.getUser();
	   if (proId != null && proId != "") {
		   user.setProId(proId);
	   }
	   SPmReport sPmReports = sPmReportService.getByproId(user.getProId());
	   if (sPmReports != null) {
		   sPmReportService.getForms(sPmReports);
	       model.addAttribute("sPmReport", sPmReports);
	       return "modules/partyManage/sPmReportDetail";
	    }
	   sPmReport.setUploader(user.getName());
	   sPmReport.setUploadTime(new Date());
	   model.addAttribute("sPmReport", sPmReport);
	   return "modules/partyManage/sPmReportDetail";
	}
	
	//@RequiresPermissions("partyManage:sPmReport:edit")
	@RequestMapping(value = "save")
	public String save(SPmReport sPmReport, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmReport)) {
	      return form(user.getProId(), sPmReport, model);
	    }
	    String status = sPmReportService.save(sPmReport, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmReport", sPmReport);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmReport/detail";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmReport/detail";
	    }
	}
	
	//@RequiresPermissions("partyManage:sPmReport:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmReport sPmReport, RedirectAttributes redirectAttributes) {
		sPmReportService.delete(sPmReport);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmReport/list";
	}

}