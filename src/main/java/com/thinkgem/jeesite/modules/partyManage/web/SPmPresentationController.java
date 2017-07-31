package com.thinkgem.jeesite.modules.partyManage.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAppUpload;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPresentation;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPublicInformation;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmPresentationService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 预备党员接收材料报告Controller
 * @author psy
 * @version 2017-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmPresentation")
public class SPmPresentationController extends BaseController {

	@Autowired
	private SPmPresentationService sPmPresentationService;
	
	@Autowired
	private DQRecordService dqRecordService;

	
	@ModelAttribute
	public SPmPresentation get(@RequestParam(required=false) String id) {
		SPmPresentation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmPresentationService.get(id);
		}
		if (entity == null){
			entity = new SPmPresentation();
		}
		return entity;
	}
	
	@RequestMapping(value = "list")
	public String list(String flag,SPmPresentation sPmPresentation, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
	    Page<SPmPresentation> page = sPmPresentationService.findPage(new Page<SPmPresentation>(request, response), sPmPresentation); 
		model.addAttribute("page", page);
		if(StringUtils.isNotBlank(flag)){
			if(flag.equals("detail")){
				return "modules/partyManage/sPmPresentationDetails";
			}			
		}
		return "modules/partyManage/sPmPresentationDetails";
	}

	//@RequiresPermissions("partyManage:sPmPresentation:view")
	@RequestMapping(value = "form")
	public String form(String proId,SPmPresentation sPmPresentation, Model model) {
		 User user = UserUtils.getUser();
		    if (proId != null && proId != "") {
		      user.setProId(proId);
		    }
		    SPmPresentation sPmPresentations = sPmPresentationService.getByproId(user.getProId());
		    if (sPmPresentations != null) {
		    	sPmPresentationService.getForms(sPmPresentations);
		      model.addAttribute("sPmPresentation", sPmPresentations);
		      return "modules/partyManage/sPmPresentationForm";
		    }
		    sPmPresentation.setUploader(user.getName());
		    sPmPresentation.setUploadTime(new Date());
		    model.addAttribute("sPmPresentation", sPmPresentation);
		return "modules/partyManage/sPmPresentationForm";
	}
	
	@RequestMapping(value = "detail")
	  public String detail(String proId, SPmPresentation sPmPresentation, Model model) {
	    User user = UserUtils.getUser();
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	    }
	    SPmPresentation sPmPresentations = sPmPresentationService.getByproId(user.getProId());
	    if (sPmPresentations != null) {
	    	sPmPresentationService.getForms(sPmPresentations);
	      model.addAttribute("sPmPresentation", sPmPresentations);
	      return "modules/partyManage/sPmPresentationDetails";
	    }
	    sPmPresentation.setUploader(user.getName());
	    sPmPresentation.setUploadTime(new Date());
	    model.addAttribute("sPmPresentation", sPmPresentation);
	    return "modules/partyManage/sPmPresentationDetails";
	  }

	//@RequiresPermissions("partyManage:sPmPresentation:edit")
	@RequestMapping(value = "save")
	public String save(SPmPresentation sPmPresentation, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
	    if (!beanValidator(model, sPmPresentation)) {
	      return form(user.getProId(), sPmPresentation, model);
	    }
	    String status = sPmPresentationService.save(sPmPresentation, user.getProId());
	    if (status == "success") {
	      addMessage(redirectAttributes, "保存成功");
	      model.addAttribute("sPmPresentation", sPmPresentation);
	      return "redirect:"+Global.getAdminPath()+"/partyManage/sPmPresentation/form";
	    } else {
	      addMessage(redirectAttributes, status);
			return "redirect:"+Global.getAdminPath()+"/partyManage/sPmPresentation/form";
		}
	}
	//@RequiresPermissions("partyManage:sPmPresentation:edit")
	@RequestMapping(value = "delete")
	public String delete(String id,SPmPresentation sPmPresentation, RedirectAttributes redirectAttributes) {
		sPmPresentation.getId();
		sPmPresentationService.deletePicture(id);
		addMessage(redirectAttributes, "删除报告成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmPresentation/form";
	}
}