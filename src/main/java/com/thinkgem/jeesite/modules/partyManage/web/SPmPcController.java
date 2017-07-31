/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPb;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPc;
import com.thinkgem.jeesite.modules.partyManage.service.SPmPcService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是党委会议记录表Controller
 * @author one
 * @version 2017-04-26
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmPc")
public class SPmPcController extends BaseController {

	@Autowired
	private SPmPcService sPmPcService;
	
	@ModelAttribute
	public SPmPc get(@RequestParam(required=false) String id) {
		SPmPc entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmPcService.get(id);
		}
		if (entity == null){
			entity = new SPmPc();
		}
		return entity;
	}
	
	//@RequiresPermissions("partyManage:sPmPc:view")
	@RequestMapping(value = "list")
	public String list(SPmPc sPmPc, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmPc> page = sPmPcService.findPage(new Page<SPmPc>(request, response), sPmPc); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmPcList";
	}

	//@RequiresPermissions("partyManage:sPmPc:view")
	@RequestMapping(value = "form")
	public String form(String proId, SPmPc sPmPc, Model model) {
		User user = UserUtils.getUser();
		String userId = user.getId();
		if (userId != null && userId != "") {
			if (proId != null && proId != "") {
				SPmPc sPmPcs = sPmPcService.getByproId(user.getProId());
				if(sPmPcs != null ){
					model.addAttribute("sPmPc", sPmPcs);
				    return "modules/partyManage/sPmPcForm";
				}
			}
		}
		sPmPc.setProId(user.getProId());
		model.addAttribute("sPmPc", sPmPc);
		return "modules/partyManage/sPmPcForm";
	}

	@RequestMapping(value = "detail")
	public String detail(String proId, SPmPc sPmPc, Model model) {
	   User user = UserUtils.getUser();
	   if (proId != null && proId != "") {
		   user.setProId(proId);
	   }
	   SPmPc sPmPcs = sPmPcService.getByproId(user.getProId());
	   if (sPmPcs != null) {
		   //sPmPbService.getForms(sPmPbs);
	       model.addAttribute("sPmPc", sPmPcs);
	       return "modules/partyManage/sPmPcDetail";
	    }
	   model.addAttribute("sPmPc", sPmPc);
	   return "modules/partyManage/sPmPcDetail";
	}
	
	//@RequiresPermissions("partyManage:sPmPc:edit")
	@RequestMapping(value = "save")
	public String save(SPmPc sPmPc, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmPc)) {
	      return form(user.getProId(), sPmPc, model);
	    }
	    String status = sPmPcService.save(sPmPc, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmPc", sPmPc);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmPc/detail";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmPc/detail";
	    }
	}
	
	//@RequiresPermissions("partyManage:sPmPc:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmPc sPmPc, RedirectAttributes redirectAttributes) {
		sPmPcService.delete(sPmPc);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmPc/list";
	}

}