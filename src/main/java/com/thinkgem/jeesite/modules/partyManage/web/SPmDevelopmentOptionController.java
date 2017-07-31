/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmDevelopmentOption;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmDevelopmentOptionService;
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
 * 确定为发展对象意见Controller
 *
 * @author zhc
 * @version 2017-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmDevelopmentOption")
public class SPmDevelopmentOptionController extends BaseController {

  @Autowired
  private SPmDevelopmentOptionService sPmDevelopmentOptionService;

  @Autowired
  private DQRecordService dqRecordService;

  @ModelAttribute
  public SPmDevelopmentOption get(@RequestParam(required = false) String id) {
    SPmDevelopmentOption entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmDevelopmentOptionService.get(id);
    }
    if (entity == null) {
      entity = new SPmDevelopmentOption();
    }
    return entity;
  }

  //	@RequiresPermissions("partyManage:sPmDevelopmentOption:view")
  @RequestMapping(value = "list")
  public String list(SPmDevelopmentOption sPmDevelopmentOption, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<SPmDevelopmentOption> page = sPmDevelopmentOptionService.findPage(new Page<SPmDevelopmentOption>(request, response), sPmDevelopmentOption);
    model.addAttribute("page", page);
    return "modules/partyManage/activisttrain/sPmDevelopmentOptionList";
  }

  //	@RequiresPermissions("partyManage:sPmDevelopmentOption:view")
  @RequestMapping(value = "form")
  public String form(String proId, SPmDevelopmentOption sPmDevelopmentOption, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmDevelopmentOption sPmDevelopmentOptions = sPmDevelopmentOptionService.getByproId(user.getProId());
    if (sPmDevelopmentOptions != null) {
      sPmDevelopmentOptionService.getForms(sPmDevelopmentOptions);
      model.addAttribute("sPmDevelopmentOption", sPmDevelopmentOptions);
      return "modules/partyManage/activisttrain/sPmDevelopmentOptionForm";
    }
    sPmDevelopmentOption.setUploader(user.getName());
    sPmDevelopmentOption.setUploadTime(new Date());
    model.addAttribute("sPmDevelopmentOption", sPmDevelopmentOption);
    return "modules/partyManage/activisttrain/sPmDevelopmentOptionForm";
  }

  @RequestMapping(value = "detail")
  public String detail(String proId, SPmDevelopmentOption sPmDevelopmentOption, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmDevelopmentOption sPmDevelopmentOptions = sPmDevelopmentOptionService.getByproId(user.getProId());
    if (sPmDevelopmentOptions != null) {
      sPmDevelopmentOptionService.getForms(sPmDevelopmentOptions);
      model.addAttribute("sPmDevelopmentOption", sPmDevelopmentOptions);
      return "modules/partyManage/activisttrain/sPmDevelopmentOptionFormDetails";
    }
    sPmDevelopmentOption.setUploader(user.getName());
    sPmDevelopmentOption.setUploadTime(new Date());
    model.addAttribute("sPmDevelopmentOption", sPmDevelopmentOption);
    return "modules/partyManage/activisttrain/sPmDevelopmentOptionFormDetails";
  }

  //	@RequiresPermissions("partyManage:sPmDevelopmentOption:edit")
  @RequestMapping(value = "save")
  public String save(SPmDevelopmentOption sPmDevelopmentOption, Model model, RedirectAttributes redirectAttributes) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmDevelopmentOption)) {
      return form(user.getProId(), sPmDevelopmentOption, model);
    }
    String status = sPmDevelopmentOptionService.save(sPmDevelopmentOption, user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmDevelopmentOption", sPmDevelopmentOption);
//      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmContactSettings/activisttrain/form";
    } else {
      addMessage(redirectAttributes, status);
    }
    String recordId = dqRecordService.getRidByPid(user.getProId());
    model.addAttribute("recordId", recordId);
    return "redirect:" + Global.getAdminPath() + "/partyManage/DQRecord/schedule/?id=" + recordId;
//    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmDevelopmentOption/form";
  }

  //	@RequiresPermissions("partyManage:sPmDevelopmentOption:edit")
  @RequestMapping(value = "delete")
  public String delete(SPmDevelopmentOption sPmDevelopmentOption, RedirectAttributes redirectAttributes) {
    sPmDevelopmentOptionService.delete(sPmDevelopmentOption);
    addMessage(redirectAttributes, "删除确定为发展对象意见成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmDevelopmentOption/?repage";
  }

}