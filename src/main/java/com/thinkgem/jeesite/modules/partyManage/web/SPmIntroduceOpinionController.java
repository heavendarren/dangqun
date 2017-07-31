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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAuditChecklist;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmDevelopmentOption;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmIntroduceOpinion;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPublicInformation;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmIntroduceOpinionService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 入党介绍人和党小组意见Controller
 * @author psy
 * @version 2017-04-26
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmIntroduceOpinion")
public class SPmIntroduceOpinionController extends BaseController {

	@Autowired
	private SPmIntroduceOpinionService sPmIntroduceOpinionService;

	@ModelAttribute
	public SPmIntroduceOpinion get(@RequestParam(required=false) String id) {
		SPmIntroduceOpinion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmIntroduceOpinionService.get(id);
		}
		if (entity == null){
			entity = new SPmIntroduceOpinion();
		}
		return entity;
	}

	//@RequiresPermissions("partyManage:sPmIntroduceOpinion:view")
	@RequestMapping(value = "list")
	public String list(SPmIntroduceOpinion sPmIntroduceOpinion, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
	    Page<SPmIntroduceOpinion> page = sPmIntroduceOpinionService.findPage(new Page<SPmIntroduceOpinion>(request, response), sPmIntroduceOpinion, user.getProId());
	    model.addAttribute("page", page);
		return "modules/partyManage/sPmIntroduceOpinionList";
	}

	//@RequiresPermissions("partyManage:sPmIntroduceOpinion:view")
	@RequestMapping(value = "form")
	public String form(SPmIntroduceOpinion sPmIntroduceOpinion, Model model) {
		User user = UserUtils.getUser();
		SPmIntroduceOpinion sPmIntroduceOpinions = sPmIntroduceOpinionService.getByproId(user.getProId());
		if (sPmIntroduceOpinions != null) {
			sPmIntroduceOpinionService.getForms(sPmIntroduceOpinions);
			model.addAttribute("sPmIntroduceOpinion", sPmIntroduceOpinions);
			return "modules/partyManage/sPmIntroduceOpinionForm";
		}
		sPmIntroduceOpinion.setUploader(user.getName());
		sPmIntroduceOpinion.setUploadTime(new Date());
		model.addAttribute("sPmIntroduceOpinion", sPmIntroduceOpinion);
		return "modules/partyManage/sPmIntroduceOpinionForm";
	}
	//@RequiresPermissions("partyManage:sPmIntroduceOpinion:edit")
	@RequestMapping(value = "save")
	public String save(SPmIntroduceOpinion sPmIntroduceOpinion, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		/*if (!beanValidator(model, sPmIntroduceOpinion)) {
			return form(sPmIntroduceOpinion, model);
		}*/
		String status = sPmIntroduceOpinionService.save(sPmIntroduceOpinion, user.getProId());
		if (status == "success") {
			addMessage(redirectAttributes, "保存成功");
			model.addAttribute("sPmIntroduceOpinion", sPmIntroduceOpinion);
			return "redirect:" + Global.getAdminPath() + "/partyManage/sPmIntroduceOpinion/form";
		} else {
			addMessage(redirectAttributes, status);
			return "redirect:"+Global.getAdminPath()+"/partyManage/sPmIntroduceOpinion/form";
		}
	}
	//@RequiresPermissions("partyManage:sPmIntroduceOpinion:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmIntroduceOpinion sPmIntroduceOpinion, RedirectAttributes redirectAttributes) {
		sPmIntroduceOpinionService.delete(sPmIntroduceOpinion);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmIntroduceOpinion/list";
	}
	
	@RequestMapping(value = "pre")
	public String Preprobation(String proId) {
		User user = UserUtils.getUser();
		if (proId != null && proId != "") {
			user.setProId(proId);
		}
		return "modules/partyManage/sPmPreprobationList";
	}
	
	@RequestMapping(value = "preDetail")
	public String Preprobations(String proId) {
		User user = UserUtils.getUser();
		if (proId != null && proId != "") {
			user.setProId(proId);
		}
		return "modules/partyManage/sPmPreprobationListDetails";
	}

}