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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJustPartyPublic;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPb;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJustPartyPublic;
import com.thinkgem.jeesite.modules.partyManage.service.SPmJustPartyPublicService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是拟转正公示表Controller
 * @author one
 * @version 2017-04-28
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmJustPartyPublic")
public class SPmJustPartyPublicController extends BaseController {

	@Autowired
	private SPmJustPartyPublicService sPmJustPartyPublicService;
	
	@ModelAttribute
	public SPmJustPartyPublic get(@RequestParam(required=false) String id) {
		SPmJustPartyPublic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmJustPartyPublicService.get(id);
		}
		if (entity == null){
			entity = new SPmJustPartyPublic();
		}
		return entity;
	}
	
	//@RequiresPermissions("partyManage:sPmJustPartyPublic:view")
	@RequestMapping(value = "list")
	public String list(SPmJustPartyPublic sPmJustPartyPublic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmJustPartyPublic> page = sPmJustPartyPublicService.findPage(new Page<SPmJustPartyPublic>(request, response), sPmJustPartyPublic); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmJustPartyPublicList";
	}

	//@RequiresPermissions("partyManage:sPmJustPartyPublic:view")
	@RequestMapping(value = "form")
	public String form(String proId, SPmJustPartyPublic sPmJustPartyPublic, Model model) {
		User user = UserUtils.getUser();
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	    }
	    SPmJustPartyPublic sPmJustPartyPublics = sPmJustPartyPublicService.getByproId(proId);
	    if (sPmJustPartyPublics != null) {
	    	sPmJustPartyPublicService.getForms(sPmJustPartyPublics);
	      model.addAttribute("sPmJustPartyPublic", sPmJustPartyPublics);
	      return "modules/partyManage/sPmJustPartyPublicForm";
	    }
	    sPmJustPartyPublic.setUploader(user.getName());
	    sPmJustPartyPublic.setUploadTime(new Date());
	    model.addAttribute("sPmJustPartyPublic", sPmJustPartyPublic);
	    return "modules/partyManage/sPmJustPartyPublicForm";
	}

	@RequestMapping(value = "detail")
	  public String detial(String proId, SPmJustPartyPublic sPmJustPartyPublic, Model model) {
	    User user = UserUtils.getUser();
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	    }
	    SPmJustPartyPublic sPmJustPartyPublics = sPmJustPartyPublicService.getByproId(user.getProId());
	    if (sPmJustPartyPublics != null) {
	      sPmJustPartyPublicService.getForms(sPmJustPartyPublics);
	      model.addAttribute("sPmJustPartyPublic", sPmJustPartyPublics);
	      return "modules/partyManage/sPmJustPartyPublicDetail";
	    }
	    sPmJustPartyPublic.setUploader(user.getName());
	    sPmJustPartyPublic.setUploadTime(new Date());
	    model.addAttribute("sPmJustPartyPublic", sPmJustPartyPublic);
	    return "modules/partyManage/sPmJustPartyPublicDetail";
	  }
	
	//@RequiresPermissions("partyManage:sPmJustPartyPublic:edit")
	@RequestMapping(value = "save")
	public String save(SPmJustPartyPublic sPmJustPartyPublic, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmJustPartyPublic)) {
	      return form(user.getProId(), sPmJustPartyPublic, model);
	    }
	    String status = sPmJustPartyPublicService.save(sPmJustPartyPublic, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmJustPartyPublic", sPmJustPartyPublic);
	      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmJustPartyPublic/form";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmJustPartyPublic/form";
	    }
	}
	
	//@RequiresPermissions("partyManage:sPmJustPartyPublic:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmJustPartyPublic sPmJustPartyPublic, RedirectAttributes redirectAttributes) {
		sPmJustPartyPublicService.delete(sPmJustPartyPublic);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmJustPartyPublic/?repage";
	}

}