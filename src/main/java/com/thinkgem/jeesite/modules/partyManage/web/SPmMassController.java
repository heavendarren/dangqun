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
import com.thinkgem.jeesite.modules.partyManage.service.SPmMassService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是群众意见表Controller
 * @author one
 * @version 2017-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmMass")
public class SPmMassController extends BaseController {

	@Autowired
	private SPmMassService sPmMassService;
	
	@ModelAttribute
	public SPmMass get(@RequestParam(required=false) String id) {
		SPmMass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmMassService.get(id);
		}
		if (entity == null){
			entity = new SPmMass();
		}
		return entity;
	}
	
	//@RequiresPermissions("partyManage:sPmMass:view")
	@RequestMapping(value = "list")
	public String list(SPmMass sPmMass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmMass> page = sPmMassService.findPage(new Page<SPmMass>(request, response), sPmMass); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmMassList";
	}

	//@RequiresPermissions("partyManage:sPmMass:view")
	@RequestMapping(value = "form")
	public String form(String proId, SPmMass sPmMass, Model model) {
		
		User user = UserUtils.getUser();
		String userId = user.getId();
		if (userId != null && userId != "") {
			if (proId != null && proId != "") {
				SPmMass sPmMasss = sPmMassService.getByproId(user.getProId());
				if(sPmMasss != null ){
					model.addAttribute("sPmMass", sPmMasss);
				    return "modules/partyManage/sPmMassForm";
				}
			}
		}
		sPmMass.setProId(user.getProId());
		model.addAttribute("sPmMass", sPmMass);
		return "modules/partyManage/sPmMassForm";
	}

	@RequestMapping(value = "recom/detail")
	  public String detail(String proId, Model model) {
	    User user = UserUtils.getUser();
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	    }
	    return "modules/partyManage/sPmDevelopDetail";
	 }
	
	@RequestMapping(value = "detail")
	public String detail(String proId, SPmMass sPmMass, Model model) {
	   User user = UserUtils.getUser();
	   if (proId != null && proId != "") {
		   user.setProId(proId);
	   }
	   SPmMass sPmMasss = sPmMassService.getByproId(user.getProId());
	   if (sPmMasss != null) {
	       model.addAttribute("sPmMass", sPmMasss);
	       return "modules/partyManage/sPmMassDetail";
	    }
	   sPmMass.setStrartTime(new Date());
	   sPmMass.setUploader(user.getName());
	   sPmMass.setUploadTime(new Date());
	   model.addAttribute("sPmMass", sPmMass);
	   return "modules/partyManage/sPmMassDetail";
	}
	
	//@RequiresPermissions("partyManage:sPmMass:edit")
	@RequestMapping(value = "save")
	public String save(SPmMass sPmMass, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmMass)) {
	      return form(user.getProId(), sPmMass, model);
	    }
	    String status = sPmMassService.save(sPmMass, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmMass", sPmMass);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmMass/detail";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmMass/detail";
	    }
	}
	
	//@RequiresPermissions("partyManage:sPmMass:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmMass sPmMass, RedirectAttributes redirectAttributes) {
		sPmMassService.delete(sPmMass);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmMass/list";
	}

}