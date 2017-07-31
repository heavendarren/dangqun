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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmFormalMember;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmIntroduceOpinion;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMeetingMinutes;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmFormalMemberService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 按期转为中共正式党员的报告Controller
 * @author psy
 * @version 2017-04-26
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmFormalMember")
public class SPmFormalMemberController extends BaseController {

	@Autowired
	private SPmFormalMemberService sPmFormalMemberService;

	@ModelAttribute
	public SPmFormalMember get(@RequestParam(required=false) String id) {
		SPmFormalMember entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmFormalMemberService.get(id);
		}
		if (entity == null){
			entity = new SPmFormalMember();
		}
		return entity;
	}

	//@RequiresPermissions("partyManage:sPmFormalMember:view")
	@RequestMapping(value = "list")
	public String list(SPmFormalMember sPmFormalMember, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
	    Page<SPmFormalMember> page = sPmFormalMemberService.findPage(new Page<SPmFormalMember>(request, response), sPmFormalMember, user.getProId());
	    model.addAttribute("page", page);
		return "modules/partyManage/sPmFormalMemberList";
	}

	//@RequiresPermissions("partyManage:sPmFormalMember:view")
	@RequestMapping(value = "form")
	public String form(SPmFormalMember sPmFormalMember, Model model) {
		User user = UserUtils.getUser();
			SPmFormalMember sPmFormalMembers = sPmFormalMemberService.getByproId(user.getProId());
			if (sPmFormalMembers != null) {
				sPmFormalMemberService.getForms(sPmFormalMembers);
				model.addAttribute("sPmFormalMember", sPmFormalMembers);
				return "modules/partyManage/sPmFormalMemberForm";
			}
		sPmFormalMember.setUploader(user.getName());
		sPmFormalMember.setUploadTime(new Date());
		model.addAttribute("sPmFormalMember", sPmFormalMember);
		return "modules/partyManage/sPmFormalMemberForm";
	}

	//@RequiresPermissions("partyManage:sPmFormalMember:edit")
	@RequestMapping(value = "save")
	public String save(SPmFormalMember sPmFormalMember, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		if (!beanValidator(model, sPmFormalMember)) {
			return form(sPmFormalMember, model);
		}
		String status = sPmFormalMemberService.save(sPmFormalMember, user.getProId());
		if (status == "success") {
			addMessage(redirectAttributes, "保存成功");
			model.addAttribute("sPmFormalMember", sPmFormalMember);
			return "redirect:"+Global.getAdminPath()+"/partyManage/sPmFormalMember/form";
		} else {
			addMessage(redirectAttributes, status);
			return "redirect:"+Global.getAdminPath()+"/partyManage/sPmFormalMember/form";
		}
	}
	//@RequiresPermissions("partyManage:sPmIntroduceOpinion:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmFormalMember sPmFormalMember, RedirectAttributes redirectAttributes) {
		sPmFormalMemberService.delete(sPmFormalMember);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmFormalMember/list";
	}

}