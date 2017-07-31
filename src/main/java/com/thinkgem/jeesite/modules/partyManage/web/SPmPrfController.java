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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmBasic;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMass;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMrs;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPrf;
import com.thinkgem.jeesite.modules.partyManage.service.SPmPrfService;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是群团推荐表Controller
 * @author one
 * @version 2017-04-17
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmPrf")
public class SPmPrfController extends BaseController {

	@Autowired
	private SPmPrfService sPmPrfService;
	@Autowired
	private DQRecordService dqRecordService;
	
	@ModelAttribute
	public SPmPrf get(@RequestParam(required=false) String id) {
		SPmPrf entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmPrfService.get(id);
		}
		if (entity == null){
			entity = new SPmPrf();
		}
		return entity;
	}
	
	@RequestMapping(value = "list")
	public String list(SPmPrf sPmPrf, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmPrf> page = sPmPrfService.findPage(new Page<SPmPrf>(request, response), sPmPrf); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmPrfList";
	}

	@RequestMapping(value = "form")
	public String form(String proId, SPmPrf sPmPrf, Model model) {
		User user = UserUtils.getUser();
		String userId = user.getId();
		if (userId != null && userId != "") {
			if (proId != null && proId != "") {
				SPmPrf sPmPrfs = sPmPrfService.getByproId(user.getProId());
				if(sPmPrfs != null ){
					model.addAttribute("sPmPrf", sPmPrfs);
				    return "modules/partyManage/sPmPrfForm";
				}
			}
		}
		sPmPrf.setProId(user.getProId());
		model.addAttribute("sPmPrf", sPmPrf);
		return "modules/partyManage/sPmPrfForm";   
	}

	@RequestMapping(value = "detail")
	public String detail(String proId, SPmPrf sPmPrf, Model model) {
	   User user = UserUtils.getUser();
	   if (proId != null && proId != "") {
		   user.setProId(proId);
	   }
	   SPmPrf sPmPrfs = sPmPrfService.getByproId(user.getProId());
	   if (sPmPrfs != null) {
		   //sPmPrfService.getForms(sPmPrfs);
	       model.addAttribute("sPmPrf", sPmPrfs);
	       return "modules/partyManage/sPmPrfDetail";
	    }
	   sPmPrf.setUploader(user.getName());
	   sPmPrf.setUploadTime(new Date());
	   model.addAttribute("sPmPrf", sPmPrf);
	   return "modules/partyManage/sPmPrfDetail";
	}
	
	@RequestMapping(value = "save")
	public String save(SPmPrf sPmPrf, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmPrf)) {
	      return form(user.getProId(), sPmPrf, model);
	    }
	    String status = sPmPrfService.save(sPmPrf, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmPrf", sPmPrf);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmPrf/detail";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmPrf/detail";
	    }
	}
	
	@RequestMapping(value = "delete")
	public String delete(SPmPrf sPmPrf, RedirectAttributes redirectAttributes) {
		sPmPrfService.delete(sPmPrf);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmPrf/list";
	}

}