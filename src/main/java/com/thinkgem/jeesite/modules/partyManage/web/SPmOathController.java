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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMinutes;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmOath;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmProbationary;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmMinutesService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmOathService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 宣誓活动纪实Controller
 * @author psy
 * @version 2017-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmOath")
public class SPmOathController extends BaseController {

	@Autowired
	private SPmOathService sPmOathService;
	
	@Autowired
	private DQRecordService dqRecordService;
	
	@Autowired
	private SPmMinutesService sPmMinutesService;
	
	
	@ModelAttribute
	public SPmOath get(@RequestParam(required=false) String id) {
		SPmOath entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmOathService.get(id);
		}
		if (entity == null){
			entity = new SPmOath();
		}
		return entity;
	}
	
	@RequestMapping(value = "success")
	public String success(Model model) {
		return "modules/partyManage/success";
	}
	
	//@RequiresPermissions("partyManage:sPmOath:view")
	@RequestMapping(value = "list")
	public String list(SPmOath sPmOath, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmOath> page = sPmOathService.findPage(new Page<SPmOath>(request, response), sPmOath); 
		model.addAttribute("page", page);
		return "modules/partyManage/sPmOathList";
	}

	//@RequiresPermissions("partyManage:sPmOath:view")
	@RequestMapping(value = "form")
	public String form(String proId,SPmOath sPmOath, Model model) {
		 User user = UserUtils.getUser();
		    if (proId != null && proId != "") {
		      user.setProId(proId);
		      SPmOath sPmOath1 = sPmOathService.getByproId(user.getProId());
		     
		      if (sPmOath1 != null) {
		    	sPmOathService.getForms(sPmOath1);
		    	sPmOath1.getActTime();
		        sPmOath1.setUploader(user.getName());
		        sPmOath1.setUploadTime(new Date());
		        model.addAttribute("sPmOath", sPmOath1);
		        return "modules/partyManage/sPmOathForm";
		      }
		    }
		    sPmOath.getActTime();
		    sPmOath.setUploader(user.getName());
		    sPmOath.setUploadTime(new Date());
		    model.addAttribute("sPmOath", sPmOath);
		    return "modules/partyManage/sPmOathForm";
	}

	//@RequiresPermissions("partyManage:sPmOath:edit")
	@RequestMapping(value = "save")
	public String save(SPmOath sPmOath, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmOath)) {
	      return form(user.getProId(), sPmOath, model);
	    }
	    sPmOath.getParticipant();
	    sPmOath.getParticipantName();
	    String status = sPmOathService.save(sPmOath, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmOath", sPmOath);
	      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmOath/detail";
	    } else {
	      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmOath/detail";
	    }

	}
	
	@RequestMapping(value = "detail")
	  public String detail(String proId, SPmOath sPmOath, Model model) {
		User user = UserUtils.getUser();
		
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	      SPmOath sPmOaths = sPmOathService.getByproId(user.getProId());
	      
	      if (sPmOaths != null) {
	    	  sPmOathService.getForms(sPmOaths);
	    	  
	    	  sPmOaths.setUploader(user.getName());
	    	  sPmOaths.setUploadTime(new Date());
	        model.addAttribute("sPmOath", sPmOaths);
	        return "modules/partyManage/sPmOathDetail";
	      }
	    }
	    
	    sPmOath.setUploader(user.getName());
	    sPmOath.setUploadTime(new Date());
	    model.addAttribute("sPmOath", sPmOath);
	    return "modules/partyManage/sPmOathDetail";
	  }
	

	//@RequiresPermissions("partyManage:sPmOath:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmOath sPmOath, RedirectAttributes redirectAttributes) {
		sPmOathService.delete(sPmOath);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmOath/list";
	}
}