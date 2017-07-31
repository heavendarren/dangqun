package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMinutes;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmTalking;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmMinutesService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 会议记录Controller
 *
 * @author psy
 * @version 2017-04-18
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmMinutes")
public class SPmMinutesController extends BaseController {

  @Autowired
  private SPmMinutesService sPmMinutesService;
  
  @Autowired
  private DQRecordService dqRecordService;

  @ModelAttribute
  public SPmMinutes get(@RequestParam(required = false) String id) {
    SPmMinutes entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmMinutesService.get(id);
    }
    if (entity == null) {
      entity = new SPmMinutes();
    }
    return entity;
  }

  @RequestMapping(value = "success")
  public String success(Model model) {
    return "modules/partyManage/success";
  }

  //@RequiresPermissions("partyManage:sPmMinutes:view")
  @RequestMapping(value = "list")
  public String list(SPmMinutes sPmMinutes, HttpServletRequest request, HttpServletResponse response, Model model) {

    Page<SPmMinutes> page = sPmMinutesService.findPage(new Page<SPmMinutes>(request, response), sPmMinutes);
    model.addAttribute("page", page);
    return "modules/partyManage/sPmMinutesList";
  }
  
  @RequestMapping(value = "detail")
	public String detail(String proId, SPmMinutes sPmMinutes, Model model) {
		User user = UserUtils.getUser();
		if (proId != null && proId != "") {
			user.setProId(proId);
		}
		SPmMinutes sPmMinutess = sPmMinutesService.getByproId(user.getProId());
		if (sPmMinutess != null) {
			model.addAttribute("sPmMinutes", sPmMinutess);
			return "modules/partyManage/sPmMinutesFormDetails";
		}
		model.addAttribute("sPmMinutes", sPmMinutes);
		return "modules/partyManage/sPmMinutesFormDetails";
	}

  //@RequiresPermissions("partyManage:sPmMinutes:view")
  @RequestMapping(value = "form")
  public String form(String proId, SPmMinutes sPmMinutes, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
      SPmMinutes sPmMinutes1 = sPmMinutesService.getByproId(user.getProId());
      if (sPmMinutes1 != null) {
        sPmMinutesService.getForms(sPmMinutes1);
        sPmMinutes1.setUploader(user.getName());
        sPmMinutes1.setUploadTime(new Date());
        model.addAttribute("sPmMinutes", sPmMinutes1);
        return "modules/partyManage/sPmMinutesForm";
      }
    }
    sPmMinutes.setUploader(user.getName());
    sPmMinutes.setUploadTime(new Date());
    model.addAttribute("sPmMinutes", sPmMinutes);
    return "modules/partyManage/sPmMinutesForm";
  }

  //@RequiresPermissions("partyManage:sPmMinutes:edit")
  @RequestMapping(value = "save")
  public String save(SPmMinutes sPmMinutes, Model model, RedirectAttributes redirectAttributes) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmMinutes)) {
      return form(user.getProId(), sPmMinutes, model);
    }
    /*sPmMinutesService.save(sPmMinutes);
    addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmMinutes/list";*/

    String status = sPmMinutesService.save(sPmMinutes, user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmMinutes", sPmMinutes);
      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmMinutes/detail";
    } else {
      addMessage(redirectAttributes, status);
      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmMinutes/detail";
    }

  }

  //@RequiresPermissions("partyManage:sPmMinutes:edit")
  @RequestMapping(value = "delete")
  public String delete(SPmMinutes sPmMinutes, RedirectAttributes redirectAttributes) {
    sPmMinutesService.delete(sPmMinutes);
    addMessage(redirectAttributes, "删除成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmMinutes/detail";
  }

}