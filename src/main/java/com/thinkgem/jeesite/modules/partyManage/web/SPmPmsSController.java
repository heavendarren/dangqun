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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPms;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPmsS;
import com.thinkgem.jeesite.modules.partyManage.service.SPmPmsSService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是预备党员表决票汇总表Controller
 * @author one
 * @version 2017-04-26
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmPmsS")
public class SPmPmsSController extends BaseController {

	@Autowired
	private SPmPmsSService sPmPmsSService;
	
	@ModelAttribute
	public SPmPmsS get(@RequestParam(required=false) String id) {
		SPmPmsS entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmPmsSService.get(id);
		}
		if (entity == null){
			entity = new SPmPmsS();
		}
		return entity;
	}
	
	//@RequiresPermissions("partyManage:sPmPmsS:view")
	@RequestMapping(value = "list")
	public String list(SPmPmsS sPmPmsS, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmPmsS> page = sPmPmsSService.findPage(new Page<SPmPmsS>(request, response), sPmPmsS); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmPmsSList";
	}

	//@RequiresPermissions("partyManage:sPmPmsS:view")
	@RequestMapping(value = "form")
	public String form(String proId, SPmPmsS sPmPmss, Model model) {
		
		User user = UserUtils.getUser();
		String userId = user.getId();
		if (userId != null && userId != "") {
			if (proId != null && proId != "") {
				SPmPmsS sPmPmsss = sPmPmsSService.getByproId(user.getProId());
				if(sPmPmsss != null ){
					model.addAttribute("sPmPmss", sPmPmsss);
				    return "modules/partyManage/sPmPmsSForm";
				}
			}
		}
		sPmPmss.setProId(user.getProId());
		model.addAttribute("sPmPmss", sPmPmss);
		return "modules/partyManage/sPmPmsSForm";
	}

	@RequestMapping(value = "detail")
	public String detail(String proId, SPmPmsS sPmPmss, Model model) {
	   User user = UserUtils.getUser();
	   if (proId != null && proId != "") {
		   user.setProId(proId);
	   }
	   SPmPmsS sPmPmss1 = sPmPmsSService.getByproId(user.getProId());
	   if (sPmPmss1 != null) {
		   //sPmActiviService.getForms(sPmActivis);
	       model.addAttribute("sPmPmss", sPmPmss1);
	       return "modules/partyManage/sPmPmsSDetail";
	    }
	   sPmPmss.setUploader(user.getName());
	   sPmPmss.setUploadTime(new Date());
	   model.addAttribute("sPmPmss", sPmPmss);
	   return "modules/partyManage/sPmPmsSDetail";
	}
	
	//@RequiresPermissions("partyManage:sPmPmsS:edit")
	@RequestMapping(value = "save")
	public String save(SPmPmsS sPmPmss, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmPmss)) {
	      return form(user.getProId(), sPmPmss, model);
	    }
	    String status = sPmPmsSService.save(sPmPmss, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmPmss", sPmPmss);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmPmsS/detail";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmPmsS/detail";
	    }
	}
	
	//@RequiresPermissions("partyManage:sPmPmsS:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmPmsS sPmPmsS, RedirectAttributes redirectAttributes) {
		sPmPmsSService.delete(sPmPmsS);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmPmsS/list";
	}

}