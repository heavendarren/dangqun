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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmContent;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMass;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPrf;
import com.thinkgem.jeesite.modules.partyManage.service.SPmContentService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是支部大会会议内容表Controller
 * @author one
 * @version 2017-04-17
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmContent")
public class SPmContentController extends BaseController {

	@Autowired
	private SPmContentService sPmContentService;
	
	@ModelAttribute
	public SPmContent get(@RequestParam(required=false) String id) {
		SPmContent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmContentService.get(id);
		}
		if (entity == null){
			entity = new SPmContent();
		}
		return entity;
	}
	
	@RequestMapping(value = "list")
	public String list(SPmContent sPmContent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmContent> page = sPmContentService.findPage(new Page<SPmContent>(request, response), sPmContent); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmContentList";
	}

	@RequestMapping(value = "form")
	public String form(String proId, SPmContent sPmContent, Model model) {
		
		User user = UserUtils.getUser();
		String userId = user.getId();
		if (userId != null && userId != "") {
			if (proId != null && proId != "") {
				SPmContent sPmContents = sPmContentService.getByproId(user.getProId());
				if(sPmContents != null ){
					model.addAttribute("sPmContent", sPmContents);
				    return "modules/partyManage/sPmContentForm";
				}
			}
		}
		sPmContent.setProId(user.getProId());
		model.addAttribute("sPmContent", sPmContent);
		return "modules/partyManage/sPmContentForm";
	}

	@RequestMapping(value = "detail")
	public String detail(String proId, SPmContent sPmContent, Model model) {
	   User user = UserUtils.getUser();
	   if (proId != null && proId != "") {
		   user.setProId(proId);
	   }
	   SPmContent sPmContents = sPmContentService.getByproId(user.getProId());
	   if (sPmContents != null) {
		   
	       model.addAttribute("sPmContent", sPmContents);
	       return "modules/partyManage/sPmContentDetail";
	    }
	   sPmContent.setUploader(user.getName());
	   sPmContent.setUploadTime(new Date());
	   model.addAttribute("sPmContent", sPmContent);
	   return "modules/partyManage/sPmContentDetail";
	}
	
	@RequestMapping(value = "save")
	public String save(SPmContent sPmContent, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmContent)) {
	      return form(user.getProId(), sPmContent, model);
	    }
	    String status = sPmContentService.save(sPmContent, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmMass", sPmContent);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmContent/detail";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmContent/detail";
	    }
	}
	
	@RequestMapping(value = "delete")
	public String delete(SPmContent sPmContent, RedirectAttributes redirectAttributes) {
		sPmContentService.delete(sPmContent);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmContent/list";
	}

}