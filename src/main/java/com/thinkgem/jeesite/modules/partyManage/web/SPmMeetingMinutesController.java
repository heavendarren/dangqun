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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMeetingMinutes;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmMeetingMinutesService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 征求党员意见会议纪要Controller
 * @author psy
 * @version 2017-04-26
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmMeetingMinutes")
public class SPmMeetingMinutesController extends BaseController {

	@Autowired
	private SPmMeetingMinutesService sPmMeetingMinutesService;

	@ModelAttribute
	public SPmMeetingMinutes get(@RequestParam(required=false) String id) {
		SPmMeetingMinutes entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmMeetingMinutesService.get(id);
		}
		if (entity == null){
			entity = new SPmMeetingMinutes();
		}
		return entity;
	}

	//@RequiresPermissions("partyManage:sPmMeetingMinutes:view")
	@RequestMapping(value = "list")
	public String list(SPmMeetingMinutes sPmMeetingMinutes, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
	    Page<SPmMeetingMinutes> page = sPmMeetingMinutesService.findPage(new Page<SPmMeetingMinutes>(request, response), sPmMeetingMinutes, user.getProId());
	    model.addAttribute("page", page);
		return "modules/partyManage/sPmMeetingMinutesList";
	}

	//@RequiresPermissions("partyManage:sPmMeetingMinutes:view")
	@RequestMapping(value = "form")
	public String form(SPmMeetingMinutes sPmMeetingMinutes, Model model) {
		User user = UserUtils.getUser();
			SPmMeetingMinutes sPmMeetingMinutess = sPmMeetingMinutesService.getByproId(user.getProId());
			if (sPmMeetingMinutess != null) {
				sPmMeetingMinutesService.getForms(sPmMeetingMinutess);
				model.addAttribute("sPmMeetingMinutes", sPmMeetingMinutess);
				return "modules/partyManage/sPmMeetingMinutesForm";
			}
		sPmMeetingMinutes.setUploader(user.getName());
		sPmMeetingMinutes.setUploadTime(new Date());
		model.addAttribute("sPmMeetingMinutes", sPmMeetingMinutes);
		return "modules/partyManage/sPmMeetingMinutesForm";
	}

	//@RequiresPermissions("partyManage:sPmMeetingMinutes:edit")
	@RequestMapping(value = "save")
	public String save(SPmMeetingMinutes sPmMeetingMinutes, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		if (!beanValidator(model, sPmMeetingMinutes)) {
			return form(sPmMeetingMinutes, model);
		}
		String status = sPmMeetingMinutesService.save(sPmMeetingMinutes, user.getProId());
		if (status == "success") {
			addMessage(redirectAttributes, "保存成功");
			model.addAttribute("sPmMeetingMinutes", sPmMeetingMinutes);
			return "redirect:" + Global.getAdminPath() + "/partyManage/sPmMeetingMinutes/form";
		} else {
			addMessage(redirectAttributes, status);
			return "redirect:" + Global.getAdminPath() + "/partyManage/sPmMeetingMinutes/form";
		}
	}
	//@RequiresPermissions("partyManage:sPmMeetingMinutes:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmMeetingMinutes sPmMeetingMinutes, RedirectAttributes redirectAttributes) {
		sPmMeetingMinutesService.delete(sPmMeetingMinutes);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmMeetingMinutes/list";
	}

}