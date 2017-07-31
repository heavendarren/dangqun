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
import com.thinkgem.jeesite.modules.partyManage.service.SPmProveService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是证明材料表Controller
 * @author one
 * @version 2017-04-21
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmProve")
public class SPmProveController extends BaseController {

	@Autowired
	private SPmProveService sPmProveService;
	
	@ModelAttribute
	public SPmProve get(@RequestParam(required=false) String id) {
		SPmProve entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmProveService.get(id);
		}
		if (entity == null){
			entity = new SPmProve();
		}
		return entity;
	}
	
	//@RequiresPermissions("partyManage:sPmProve:view")
	@RequestMapping(value = "list")
	public String list(SPmProve sPmProve, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmProve> page = sPmProveService.findPage(new Page<SPmProve>(request, response), sPmProve); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmProveList";
	}

	//@RequiresPermissions("partyManage:sPmProve:view")
	@RequestMapping(value = "form")
	public String form(String proId, SPmProve sPmProve, Model model) {
		User user = UserUtils.getUser();
		String userId = user.getId();
		if (userId != null && userId != "") {
			if (proId != null && proId != "") {
				SPmProve sPmProves = sPmProveService.getByproId(user.getProId());
				if(sPmProves != null ){
					model.addAttribute("sPmProve", sPmProves);
				    return "modules/partyManage/sPmProveForm";
				}
			}
		}
		sPmProve.setProId(user.getProId());
		model.addAttribute("sPmProve", sPmProve);
		return "modules/partyManage/sPmProveForm";
	}

	@RequestMapping(value = "detail")
	public String detail(String proId, SPmProve sPmProve, Model model) {
	   User user = UserUtils.getUser();
	   if (proId != null && proId != "") {
		   user.setProId(proId);
	   }
	   SPmProve sPmProves = sPmProveService.getByproId(user.getProId());
	   if (sPmProves != null) {
		   sPmProveService.getForms(sPmProves);
	       model.addAttribute("sPmProve", sPmProves);
	       return "modules/partyManage/sPmProveDetail";
	    }
	   sPmProve.setUploader(user.getName());
	   sPmProve.setUploadTime(new Date());
	   model.addAttribute("sPmProve", sPmProve);
	   return "modules/partyManage/sPmProveDetail";
	}
	
	//@RequiresPermissions("partyManage:sPmProve:edit")
	@RequestMapping(value = "save")
	public String save(SPmProve sPmProve, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmProve)) {
	      return form(user.getProId(), sPmProve, model);
	    }
	    String status = sPmProveService.save(sPmProve, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmProve", sPmProve);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmProve/detail";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmProve/detail";
	    }
	}
	
	//@RequiresPermissions("partyManage:sPmProve:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmProve sPmProve, RedirectAttributes redirectAttributes) {
		sPmProveService.delete(sPmProve);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmProve/list";
	}

}