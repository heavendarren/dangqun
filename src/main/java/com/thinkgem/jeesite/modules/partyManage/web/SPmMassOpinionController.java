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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmDevelopmentOption;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmIntroduceOpinion;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMassOpinion;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmMassOpinionService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 征求群众意见会议纪要Controller
 * @author psy
 * @version 2017-04-26
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmMassOpinion")
public class SPmMassOpinionController extends BaseController {

	@Autowired
	private SPmMassOpinionService sPmMassOpinionService;
	
	@ModelAttribute
	public SPmMassOpinion get(@RequestParam(required=false) String id) {
		SPmMassOpinion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmMassOpinionService.get(id);
		}
		if (entity == null){
			entity = new SPmMassOpinion();
		}
		return entity;
	}
	
	//@RequiresPermissions("partyManage:sPmMassOpinion:view")
	@RequestMapping(value = "list")
	public String list(SPmMassOpinion sPmMassOpinion, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
	    Page<SPmMassOpinion> page = sPmMassOpinionService.findPage(new Page<SPmMassOpinion>(request, response), sPmMassOpinion, user.getProId());
	    model.addAttribute("page", page);
		return "modules/partyManage/sPmMassOpinionList";
	}

	//@RequiresPermissions("partyManage:sPmMassOpinion:view")
	@RequestMapping(value = "form")
	public String form(SPmMassOpinion sPmMassOpinion, Model model) {
		User user = UserUtils.getUser();
	      SPmMassOpinion sPmMassOpinions = sPmMassOpinionService.getByproId(user.getProId());
	      if (sPmMassOpinions != null) {
	    	  sPmMassOpinionService.getForms(sPmMassOpinions);
	        model.addAttribute("sPmMassOpinion", sPmMassOpinions);
	        return "modules/partyManage/sPmMassOpinionForm";
	      }
	    sPmMassOpinion.setUploader(user.getName());
	    sPmMassOpinion.setUploadTime(new Date());
	    model.addAttribute("sPmMassOpinion", sPmMassOpinion);
		return "modules/partyManage/sPmMassOpinionForm";
	}

	//@RequiresPermissions("partyManage:sPmMassOpinion:edit")
	@RequestMapping(value = "save")
	public String save(SPmMassOpinion sPmMassOpinion, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmMassOpinion)) {
	      return form(sPmMassOpinion, model);
	    }
	    String status = sPmMassOpinionService.save(sPmMassOpinion, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmMassOpinion", sPmMassOpinion);
	      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmMassOpinion/form";
	    } else {
	      addMessage(redirectAttributes, status);
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmMassOpinion/form";
	    }
	}	
	//@RequiresPermissions("partyManage:sPmMassOpinion:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmMassOpinion sPmMassOpinion, RedirectAttributes redirectAttributes) {
		sPmMassOpinionService.delete(sPmMassOpinion);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmMassOpinion/list";
	}
}