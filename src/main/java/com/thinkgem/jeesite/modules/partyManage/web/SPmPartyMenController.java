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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmDevelopmentOption;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPartyMen;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmDevelopmentOptionService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmPartyMenService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 入党志愿书Controller
 * @author psy
 * @version 2017-04-27
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmPartyMen")
public class SPmPartyMenController extends BaseController {

	@Autowired
	private SPmPartyMenService sPmPartyMenService;

	@Autowired
	private DQRecordService dqRecordService;

	@ModelAttribute
	public SPmPartyMen get(@RequestParam(required=false) String id) {
		SPmPartyMen entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmPartyMenService.get(id);
		}
		if (entity == null){
			entity = new SPmPartyMen();
		}
		return entity;
	}

	//@RequiresPermissions("partyManage:sPmPartyMen:view")
	@RequestMapping(value = "list")
	public String list(String proId,SPmJionFiles sPmJionFiles, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();SPmPartyMen sPmPartyMen = new SPmPartyMen();
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	    }
		Page<SPmJionFiles> page = sPmPartyMenService.findJoinList(new Page<SPmJionFiles>(request, response), sPmJionFiles,user.getProId());
		sPmPartyMen.setUploader(user.getName());
		sPmPartyMen.setUploadTime(new Date());
		model.addAttribute("page", page);
		return "modules/partyManage/sPmPartyMenList";
	}

	
	@RequestMapping(value = "form")
	public String form(String proId,SPmPartyMen sPmPartyMen, Model model) {
		User user = UserUtils.getUser();
		if (proId != null && proId != "") {
			user.setProId(proId);
		}
		SPmPartyMen sPmPartyMens = sPmPartyMenService.getByproId(user.getProId());
		if (sPmPartyMens != null) {
			sPmPartyMenService.findForms(sPmPartyMens);
			sPmPartyMens.setUploader(user.getName());
			sPmPartyMens.setUploadTime(new Date());
			model.addAttribute("sPmPartyMen", sPmPartyMens);
			return "modules/partyManage/sPmPartyMenForm";
		}
		sPmPartyMen.setUploader(user.getName());
		sPmPartyMen.setUploadTime(new Date());
		model.addAttribute("sPmPartyMen", sPmPartyMen);
		return "modules/partyManage/sPmPartyMenForm";
	}
	//@RequiresPermissions("partyManage:sPmPartyMen:edit")
	@RequestMapping(value = "save")
	public String save(SPmPartyMen sPmPartyMen, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		if (!beanValidator(model, sPmPartyMen)) {
			return form(user.getProId(), sPmPartyMen, model);
		}
		List<String> fileUrlList = sPmPartyMen.getFileUrls();
		List<String> fileNameList = sPmPartyMen.getFileNames();
		List<String> status = new ArrayList<String>();
		for (int i = 0; i < fileUrlList.size(); i++) {
			String statu = sPmPartyMenService.save(sPmPartyMen, user.getProId(), fileUrlList.get(i), fileNameList.get(i),"file");
			status.add(statu);
		}
		List<String> imgUrlList = sPmPartyMen.getImgUrls();
		List<String> imgNameList = sPmPartyMen.getImgNames();
		for (int i = 0; i < imgUrlList.size(); i++) {
			String statu = sPmPartyMenService.save(sPmPartyMen, user.getProId(), imgUrlList.get(i), imgNameList.get(i),"img");
			status.add(statu);
		}
		
		 Map<String, String> sta = new HashMap<String, String>();
		    for (String statu : status) {
		      if (statu != "success") {
		        sta.put("statu", "保存失败");
		        break;
		      }
		      sta.put("statu", "保存成功");
		    }
		    addMessage(redirectAttributes, sta.get("statu"));
		    model.addAttribute("sPmPartyMen", sPmPartyMen);
			return "redirect:" + Global.getAdminPath() + "/partyManage/sPmPartyMen/form";
	}
	//@RequiresPermissions("partyManage:sPmPartyMen:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmPartyMen sPmPartyMen, RedirectAttributes redirectAttributes) {
		sPmPartyMenService.delete(sPmPartyMen);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmPartyMen/list";
	}

}