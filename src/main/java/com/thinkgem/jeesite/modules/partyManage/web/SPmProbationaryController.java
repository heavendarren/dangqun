/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmBasic;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPmsS;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmProbationary;
import com.thinkgem.jeesite.modules.partyManage.service.SPmProbationaryService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是预备党员考察纪实表Controller
 * @author one
 * @version 2017-04-28
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmProbationary")
public class SPmProbationaryController extends BaseController {

	@Autowired
	private SPmProbationaryService sPmProbationaryService;

	@ModelAttribute
	public SPmProbationary get(@RequestParam(required=false) String id) {
		SPmProbationary entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmProbationaryService.get(id);
		}
		if (entity == null){
			entity = new SPmProbationary();
		}
		return entity;
	}

	//@RequiresPermissions("partyManage:sPmProbationary:view")
	@RequestMapping(value = "list")
	public String list(SPmProbationary sPmProbationary, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SPmProbationary> page = sPmProbationaryService.findPage(new Page<SPmProbationary>(request, response), sPmProbationary);
		model.addAttribute("page", page);
		return "modules/partyManage/sPmProbationaryList";
	}

	@RequestMapping(value = "detail")
	public String detail(String proId,String type, Model model, SPmProbationary sPmProbationary) {
		User user = UserUtils.getUser();
		if (proId != null && proId != "") {
			user.setProId(proId);
		}
		SPmProbationary sPmProbationarys = sPmProbationaryService.getByProIdType(user.getProId(),type);
		//sPmProbationaryService.getForms(sPmProbationarys);
		sPmProbationary.setUploader(user.getName());
		sPmProbationary.setUploadTime(new Date());
		model.addAttribute("sPmProbationary", sPmProbationarys);
		model.addAttribute("proId",proId);
		return "modules/partyManage/sPmProbationaryDetail";
	}

	@RequestMapping(value = "ajaxDetail")
	@ResponseBody
	public SPmProbationary ajaxdetail(String proId, String type) {
		User user = UserUtils.getUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (proId != null && proId != "") user.setProId(user.getProId());
		SPmProbationary sPmProbationary = sPmProbationaryService.getByProIdType(user.getProId(),type);
		sPmProbationary.setSex(DictUtils.getDictLabel(sPmProbationary.getSex(),"XB",""));//性别
		sPmProbationary.setPlaOf(DictUtils.getDictLabel(sPmProbationary.getPlaOf(), "JG", ""));//籍贯
		sPmProbationary.setDateBirth(sPmProbationary.getDateBirth());


		return sPmProbationary;
	}


	//@RequiresPermissions("partyManage:sPmProbationary:view")
	@RequestMapping(value = "form")
	public String form(String proId, SPmProbationary sPmProbationary, Model model,String type) {
		User user = UserUtils.getUser();
		if (proId != null && proId != "") {
			user.setProId(proId);
		}
		SPmProbationary sPmProbationarys = sPmProbationaryService.getByProIdType(proId,type);
		if(sPmProbationarys != null ){
			model.addAttribute("proId",proId);
			model.addAttribute("sPmProbationary", sPmProbationarys);
			return "modules/partyManage/sPmProbationaryForm";
		}
		sPmProbationary.setProId(user.getProId());
		model.addAttribute("proId",proId);
		model.addAttribute("sPmProbationary", sPmProbationary);
		return "modules/partyManage/sPmProbationaryForm";
	}

	//@RequiresPermissions("partyManage:sPmProbationary:edit")
	@RequestMapping(value = "save")
	public String save(String proId, SPmProbationary sPmProbationary, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		proId = user.getProId();
		if (!beanValidator(model, sPmProbationary)) {
			return form(proId, sPmProbationary, model, sPmProbationary.getType());
		}
		String status = sPmProbationaryService.save(sPmProbationary, proId);
		if (status == "success") {
			addMessage(redirectAttributes, "保存成功");
			model.addAttribute("sPmProbationary", sPmProbationary);
			return "redirect:" + Global.getAdminPath()
					+ "/partyManage/sPmProbationary/detail";
		} else {
			addMessage(redirectAttributes, status);
			return "redirect:" + Global.getAdminPath()
					+ "/partyManage/sPmProbationary/detail";
		}
	}

	//@RequiresPermissions("partyManage:sPmProbationary:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmProbationary sPmProbationary, RedirectAttributes redirectAttributes) {
		sPmProbationaryService.delete(sPmProbationary);
		addMessage(redirectAttributes, "删除表单成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmProbationary/list";
	}

}