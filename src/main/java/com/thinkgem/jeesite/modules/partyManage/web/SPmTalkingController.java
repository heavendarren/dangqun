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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPositivePre;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmTalking;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmTalkingService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 谈话纪实Controller
 * 
 * @author psy
 * @version 2017-04-17
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmTalking")
public class SPmTalkingController extends BaseController {

	@Autowired
	private SPmTalkingService sPmTalkingService;

	@ModelAttribute
	public SPmTalking get(@RequestParam(required = false) String id) {
		SPmTalking entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = sPmTalkingService.get(id);
		}
		if (entity == null) {
			entity = new SPmTalking();
		}
		return entity;
	}

	// @RequiresPermissions("talking:sPmTalking:view")
	@RequestMapping(value =  "list")
	public String list(String proId,SPmTalking sPmTalking, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (proId != null && proId != "") {
			user.setProId(proId);
		}
		Page<SPmTalking> page = sPmTalkingService.findPage(new Page<SPmTalking>(request, response), sPmTalking, user.getProId());
		model.addAttribute("page", page);
		return "modules/partyManage/sPmTalkingList";
	}

	@RequestMapping(value = "detail")
	public String detail(String proId, SPmTalking sPmTalking, Model model) {
		User user = UserUtils.getUser();
		if (proId != null && proId != "") {
			user.setProId(proId);
		}
		SPmTalking sPmTalkings = sPmTalkingService.getByproId(user.getProId());
		if (sPmTalkings != null) {
			model.addAttribute("sPmTalking", sPmTalkings);
			return "modules/partyManage/sPmTalkingFormDetails";
		}
		model.addAttribute("sPmTalking", sPmTalking);
		return "modules/partyManage/sPmTalkingFormDetails";
	}



	@RequestMapping(value = "form")
	public String form(String proId,SPmTalking sPmTalking, Model model) {
		User user = UserUtils.getUser();
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	    }
	    SPmTalking sPmTalkings = sPmTalkingService.getByproId(user.getProId());
	    if (sPmTalkings != null) {
	      model.addAttribute("sPmTalking", sPmTalkings);
	      return "modules/partyManage/sPmTalkingForm";	
	    }
	    model.addAttribute("sPmTalking", sPmTalking);
		return "modules/partyManage/sPmTalkingForm";	
	}
	
	// @RequiresPermissions("talking:sPmTalking:edit")
	@RequestMapping(value = "save")
	public String save(SPmTalking sPmTalking, Model model,
			RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmTalking)) {
	      return form(user.getProId(), sPmTalking, model);
	    }
	    String status = sPmTalkingService.save(sPmTalking, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmTalking", sPmTalking);
	      return "redirect:" + Global.getAdminPath()
					+ "/partyManage/sPmTalking/detail";
	    } else {
	      addMessage(redirectAttributes, status);
			return "redirect:" + Global.getAdminPath()
					+ "/partyManage/sPmTalking/detail";
		}

	}

	// @RequiresPermissions("talking:sPmTalking:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmTalking sPmTalking,
			RedirectAttributes redirectAttributes) {
		sPmTalkingService.delete(sPmTalking);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + Global.getAdminPath()
				+ "/partyManage/sPmTalking/sPmTalkingFormDetails";
	}
}