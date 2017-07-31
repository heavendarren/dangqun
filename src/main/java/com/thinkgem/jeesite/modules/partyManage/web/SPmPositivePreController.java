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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPositivePre;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmPositivePreService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 转正工作预审Controller
 * @author psy
 * @version 2017-04-26
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmPositivePre")
public class SPmPositivePreController extends BaseController {

	@Autowired
	private SPmPositivePreService sPmPositivePreService;

	
	@ModelAttribute
	public SPmPositivePre get(@RequestParam(required=false) String id) {
		SPmPositivePre entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmPositivePreService.get(id);
		}
		if (entity == null){
			entity = new SPmPositivePre();
		}
		return entity;
	}
	
	//@RequiresPermissions("partyManage:sPmPositivePre:view")
	@RequestMapping(value = "list")
	public String list(SPmPositivePre sPmPositivePre, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
	    Page<SPmPositivePre> page = sPmPositivePreService.findPage(new Page<SPmPositivePre>(request, response), sPmPositivePre, user.getProId());
	    model.addAttribute("page", page);
		return "modules/partyManage/sPmPositivePreList";
	}

	//@RequiresPermissions("partyManage:sPmPositivePre:view")
	@RequestMapping(value = "form")
	public String form(SPmPositivePre sPmPositivePre, Model model) {
		User user = UserUtils.getUser();
	      SPmPositivePre sPmPositivePres = sPmPositivePreService.getByproId(user.getProId());
	      if (sPmPositivePres != null) {
	    	  sPmPositivePreService.getForms(sPmPositivePres);
	        model.addAttribute("sPmPositivePre", sPmPositivePres);
	        return "modules/partyManage/sPmPositivePreForm";
	      }
	    sPmPositivePre.setUploader(user.getName());
	    sPmPositivePre.setUploadTime(new Date());
	    model.addAttribute("sPmPositivePre", sPmPositivePre);
		return "modules/partyManage/sPmPositivePreForm";
	}

	//@RequiresPermissions("partyManage:sPmPositivePre:edit")
	@RequestMapping(value = "save")
	public String save(SPmPositivePre sPmPositivePre, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmPositivePre)) {
	      return form(sPmPositivePre, model);
	    }
	    String status = sPmPositivePreService.save(sPmPositivePre, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmPositivePre", sPmPositivePre);
	      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmPositivePre/form";
	    } else {
	      addMessage(redirectAttributes, status);
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmPositivePre/form";
		}
	}
	
	//@RequiresPermissions("partyManage:sPmPositivePre:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmPositivePre sPmPositivePre, RedirectAttributes redirectAttributes) {
		sPmPositivePreService.delete(sPmPositivePre);
		addMessage(redirectAttributes, "删除转正工作预审成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmPositivePre/list";
	}

}