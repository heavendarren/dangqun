/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPrepPartyPublic;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmPrepPartyPublicService;
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
 * 拟接收预备党员的公示Controller
 *
 * @author zhc
 * @version 2017-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmPrepPartyPublic")
public class SPmPrepPartyPublicController extends BaseController {

  @Autowired
  private SPmPrepPartyPublicService sPmPrepPartyPublicService;

  @Autowired
  private DQRecordService dqRecordService;

  @ModelAttribute
  public SPmPrepPartyPublic get(@RequestParam(required = false) String id) {
    SPmPrepPartyPublic entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmPrepPartyPublicService.get(id);
    }
    if (entity == null) {
      entity = new SPmPrepPartyPublic();
    }
    return entity;
  }

  //	@RequiresPermissions("partyManage:sPmPrepPartyPublic:view")
  @RequestMapping(value = "list")
  public String list(SPmPrepPartyPublic sPmPrepPartyPublic, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<SPmPrepPartyPublic> page = sPmPrepPartyPublicService.findPage(new Page<SPmPrepPartyPublic>(request, response), sPmPrepPartyPublic);
    model.addAttribute("page", page);
    return "modules/partyManage/sPmPrepPartyPublicList";
  }

  //	@RequiresPermissions("partyManage:sPmPrepPartyPublic:view")
  @RequestMapping(value = "form")
  public String form(String proId, SPmPrepPartyPublic sPmPrepPartyPublic, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmPrepPartyPublic sPmPrepPartyPublics = sPmPrepPartyPublicService.getByproId(user.getProId());
    if (sPmPrepPartyPublics != null) {
      sPmPrepPartyPublicService.getForms(sPmPrepPartyPublics);
      model.addAttribute("sPmPrepPartyPublic", sPmPrepPartyPublics);
      return "modules/partyManage/sPmPrepPartyPublicForm";
    }
    sPmPrepPartyPublic.setUploader(user.getName());
    sPmPrepPartyPublic.setUploadTime(new Date());
    model.addAttribute("sPmPrepPartyPublic", sPmPrepPartyPublic);
    return "modules/partyManage/sPmPrepPartyPublicForm";
  }

  @RequestMapping(value = "detail")
  public String detial(String proId, SPmPrepPartyPublic sPmPrepPartyPublic, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmPrepPartyPublic sPmPrepPartyPublics = sPmPrepPartyPublicService.getByproId(user.getProId());
    if (sPmPrepPartyPublics != null) {
      sPmPrepPartyPublicService.getForms(sPmPrepPartyPublics);
      model.addAttribute("sPmPrepPartyPublic", sPmPrepPartyPublics);
      return "modules/partyManage/sPmPrepPartyPublicFormDetails";
    }
    sPmPrepPartyPublic.setUploader(user.getName());
    sPmPrepPartyPublic.setUploadTime(new Date());
    model.addAttribute("sPmPrepPartyPublic", sPmPrepPartyPublic);
    return "modules/partyManage/sPmPrepPartyPublicFormDetails";
  }

  //	@RequiresPermissions("partyManage:sPmPrepPartyPublic:edit")
  @RequestMapping(value = "save")
  public String save(SPmPrepPartyPublic sPmPrepPartyPublic, Model model, RedirectAttributes redirectAttributes) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmPrepPartyPublic)) {
      return form(user.getProId(), sPmPrepPartyPublic, model);
    }
    String status = sPmPrepPartyPublicService.save(sPmPrepPartyPublic, user.getProId());
    String recordId = dqRecordService.getRidByPid(user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmPrepPartyPublic", sPmPrepPartyPublic);
//      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmPrepPartyPublic/form";
    } else {
      addMessage(redirectAttributes, status);
    }
    return "redirect:" + Global.getAdminPath() + "/partyManage/DQRecord/schedule/?id=" + recordId;

  }

  //	@RequiresPermissions("partyManage:sPmPrepPartyPublic:edit")
  @RequestMapping(value = "delete")
  public String delete(SPmPrepPartyPublic sPmPrepPartyPublic, RedirectAttributes redirectAttributes) {
    sPmPrepPartyPublicService.delete(sPmPrepPartyPublic);
    addMessage(redirectAttributes, "删除拟接收预备党员的公示成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmPrepPartyPublic/?repage";
  }

}