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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPublicInformation;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmAuditChecklistService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 转正审查审核表Controller
 * @author psy
 * @version 2017-04-26
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmAuditChecklist")
public class SPmAuditChecklistController extends BaseController {

	@Autowired
	private SPmAuditChecklistService sPmAuditChecklistService;
	
	@ModelAttribute
	public SPmAuditChecklist get(@RequestParam(required=false) String id) {
		SPmAuditChecklist entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmAuditChecklistService.get(id);
		}
		if (entity == null){
			entity = new SPmAuditChecklist();
		}
		return entity;
	}
	
	//@RequiresPermissions("partyManage:sPmAuditChecklist:view")
	@RequestMapping(value = "list")
	public String list(SPmAuditChecklist sPmAuditChecklist, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
	    Page<SPmAuditChecklist> page = sPmAuditChecklistService.findPage(new Page<SPmAuditChecklist>(request, response), sPmAuditChecklist, user.getProId());
	    model.addAttribute("page", page);
		return "modules/partyManage/sPmAuditChecklistList";
	}

	//@RequiresPermissions("partyManage:sPmAuditChecklist:view")
	@RequestMapping(value = "form")
	public String form(SPmAuditChecklist sPmAuditChecklist, Model model) {
		User user = UserUtils.getUser();
	      SPmAuditChecklist sPmAuditChecklists = sPmAuditChecklistService.getByproId(user.getProId());
	      if (sPmAuditChecklists != null) {
	    	  sPmAuditChecklistService.getForms(sPmAuditChecklists);
	        model.addAttribute("sPmAuditChecklist", sPmAuditChecklists);
	        return "modules/partyManage/sPmAuditChecklistForm";
	    }
	    sPmAuditChecklist.setUploader(user.getName());
	    sPmAuditChecklist.setUploadTime(new Date());
	    model.addAttribute("sPmAuditChecklist", sPmAuditChecklist);
		return "modules/partyManage/sPmAuditChecklistForm";
	}

	//@RequiresPermissions("partyManage:sPmAuditChecklist:edit")
	@RequestMapping(value = "save")
	public String save(SPmAuditChecklist sPmAuditChecklist, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	 /*   if (!beanValidator(model, sPmAuditChecklist)) {
	      return form(sPmAuditChecklist, model);
	    }*/
	    String status = sPmAuditChecklistService.save(sPmAuditChecklist, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmAuditChecklist", sPmAuditChecklist);
	      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmAuditChecklist/form";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmAuditChecklist/form";
	    }
	  }
	
	//@RequiresPermissions("partyManage:sPmAuditChecklist:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmAuditChecklist sPmAuditChecklist, RedirectAttributes redirectAttributes) {
		sPmAuditChecklistService.delete(sPmAuditChecklist);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmAuditChecklist/list";
	}

}