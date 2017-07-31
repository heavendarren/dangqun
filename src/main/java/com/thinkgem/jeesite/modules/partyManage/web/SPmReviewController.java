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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmCheck;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmReview;
import com.thinkgem.jeesite.modules.partyManage.service.SPmReviewService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是发展党员工作预审表Controller
 * @author one
 * @version 2017-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmReview")
public class SPmReviewController extends BaseController {

	@Autowired
	private SPmReviewService sPmReviewService;
	
	@ModelAttribute
	public SPmReview get(@RequestParam(required=false) String id) {
		SPmReview entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmReviewService.get(id);
		}
		if (entity == null){
			entity = new SPmReview();
		}
		return entity;
	}
	
	//@RequiresPermissions("partyManage:sPmReview:view")
	@RequestMapping(value = "list")
	public String list(SPmReview sPmReview, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmReview> page = sPmReviewService.findPage(new Page<SPmReview>(request, response), sPmReview); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmReviewList";
	}

	//@RequiresPermissions("partyManage:sPmReview:view")
	@RequestMapping(value = "form")
	public String form(String proId, SPmReview sPmReview, Model model) {
		
		User user = UserUtils.getUser();
		String userId = user.getId();
		if (userId != null && userId != "") {
			user.setProId(proId);
			if (proId != null && proId != "") {
				SPmReview sPmReviews = sPmReviewService.getByproId(proId);
				if(sPmReviews != null ){
					model.addAttribute("sPmReview", sPmReviews);
				    return "modules/partyManage/sPmReviewForm";
				}
			}
		}
		sPmReview.setUploader(user.getName());
		sPmReview.setUploadTime(new Date());
		sPmReview.setProId(user.getProId());
		model.addAttribute("sPmReview", sPmReview);
		return "modules/partyManage/sPmReviewForm";
	}
	
	@RequestMapping(value = "detail")
	public String detail(String proId, SPmReview sPmReview, Model model) {
	   User user = UserUtils.getUser();
	   if (proId != null && proId != "") {
		   user.setProId(proId);
	   }
	   SPmReview sPmReviews = sPmReviewService.getByproId(user.getProId());
	   if (sPmReviews != null) {
		   sPmReviewService.getForms(sPmReviews);
	       model.addAttribute("sPmReview", sPmReviews);
	       return "modules/partyManage/sPmReviewDetail";
	    }
	   sPmReview.setUploader(user.getName());
	   sPmReview.setUploadTime(new Date());
	   model.addAttribute("sPmReview", sPmReview);
	   return "modules/partyManage/sPmReviewDetail";
	}
	

	//@RequiresPermissions("partyManage:sPmReview:edit")
	@RequestMapping(value = "save")
	public String save(SPmReview sPmReview, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmReview)) {
	      return form(user.getProId(), sPmReview, model);
	    }
	    String status = sPmReviewService.save(sPmReview, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmReview", sPmReview);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmReview/detail";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmReview/detail";
	    }
	}
	
	//@RequiresPermissions("partyManage:sPmReview:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmReview sPmReview, RedirectAttributes redirectAttributes) {
		sPmReviewService.delete(sPmReview);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmReview/list";
	}

}