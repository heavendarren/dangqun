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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAnnouncement;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPublicInformation;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmAnnouncementService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 发展对象公示Controller
 * @author psy
 * @version 2017-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmAnnouncement")
public class SPmAnnouncementController extends BaseController {

	@Autowired
	private SPmAnnouncementService sPmAnnouncementService;
	
	@Autowired
	private DQRecordService  dqRecordService;
	
	@ModelAttribute
	public SPmAnnouncement get(@RequestParam(required=false) String id) {
		SPmAnnouncement entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmAnnouncementService.get(id);
		}
		if (entity == null){
			entity = new SPmAnnouncement();
		}
		return entity;
	}
	
	 @RequestMapping(value = "detail")
	  public String detail(String proId, SPmAnnouncement sPmAnnouncement , HttpServletRequest request, HttpServletResponse response, Model model) {
	    User user = UserUtils.getUser();
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	    }
	    Page<SPmAnnouncement> page = sPmAnnouncementService.findPage(new Page<SPmAnnouncement>(request, response), sPmAnnouncement, user.getProId());
	    model.addAttribute("page", page);
	    return "modules/partyManage/sPmAnnouncementFormDetails";
	  }

	
	//@RequiresPermissions("partyManage:sPmAnnouncement:view")
	@RequestMapping(value = "list")
	  public String list(String proId, SPmAnnouncement sPmAnnouncement, HttpServletRequest request, HttpServletResponse response, Model model) {
		 User user = UserUtils.getUser();
		    if (proId != null && proId != "") {
		      user.setProId(proId);
		    }
		    String recordId = dqRecordService.getRidByPid(user.getProId());
		    Page<SPmAnnouncement> page = sPmAnnouncementService.findPage(new Page<SPmAnnouncement>(request, response), sPmAnnouncement, user.getProId());
		    model.addAttribute("page", page);
		    model.addAttribute("recordId", recordId);
		return "modules/partyManage/sPmAnnouncementList";
	}

	//@RequiresPermissions("partyManage:sPmAnnouncement:view")
	@RequestMapping(value = "form")
	public String form(SPmAnnouncement sPmAnnouncement, Model model) {
		User user = UserUtils.getUser();
		sPmAnnouncement.setUploader(user.getName());
	    sPmAnnouncement.setUploadTime(new Date());
	    model.addAttribute("sPmAnnouncement", sPmAnnouncement);
		return "modules/partyManage/sPmAnnouncementForm";
	}

	//@RequiresPermissions("partyManage:sPmAnnouncement:edit")
	@RequestMapping(value = "save")
	public String save(SPmAnnouncement sPmAnnouncement, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sPmAnnouncement)) {
		      return form(sPmAnnouncement, model);
		    }
		    User user = UserUtils.getUser();
		    String proId = user.getProId();
		    List<String> fileUrlList = sPmAnnouncement.getFileUrls();
		    List<String> fileNameList = sPmAnnouncement.getFileNames();
		    List<String> status = new ArrayList<String>();
		    for (int i = 0; i < fileUrlList.size(); i++) {
		      String statu = sPmAnnouncementService.save(sPmAnnouncement, proId, fileUrlList.get(i), fileNameList.get(i));
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
		    model.addAttribute("sPmAnnouncement", sPmAnnouncement);
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmAnnouncement/list";
  }
	
	//@RequiresPermissions("partyManage:sPmAnnouncement:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmAnnouncement sPmAnnouncement, RedirectAttributes redirectAttributes) {
		sPmAnnouncementService.delete(sPmAnnouncement);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmAnnouncement/list";
	}

}