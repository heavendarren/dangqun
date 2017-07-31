/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmBeforePartyRecord;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmBeforePartyRecordService;
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
 * 预备党员备案Controller
 *
 * @author zhc
 * @version 2017-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmBeforePartyRecord")
public class SPmBeforePartyRecordController extends BaseController {

  @Autowired
  private SPmBeforePartyRecordService sPmBeforePartyRecordService;

  @Autowired
  private DQRecordService dqRecordService;

  @ModelAttribute
  public SPmBeforePartyRecord get(@RequestParam(required = false) String id) {
    SPmBeforePartyRecord entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmBeforePartyRecordService.get(id);
    }
    if (entity == null) {
      entity = new SPmBeforePartyRecord();
    }
    return entity;
  }

  //	@RequiresPermissions("partyManage:sPmBeforePartyRecord:view")
  @RequestMapping(value = "list")
  public String list(SPmBeforePartyRecord sPmBeforePartyRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<SPmBeforePartyRecord> page = sPmBeforePartyRecordService.findPage(new Page<SPmBeforePartyRecord>(request, response), sPmBeforePartyRecord);
    model.addAttribute("page", page);
    return "modules/partyManage/sPmBeforePartyRecordList";
  }

  //	@RequiresPermissions("partyManage:sPmBeforePartyRecord:view")
  @RequestMapping(value = "form")
  public String form(String proId, SPmBeforePartyRecord sPmBeforePartyRecord, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmBeforePartyRecord sPmBeforePartyRecords = sPmBeforePartyRecordService.getByproId(user.getProId());
    if (sPmBeforePartyRecords != null) {
      sPmBeforePartyRecordService.getForms(sPmBeforePartyRecords);
      model.addAttribute("sPmBeforePartyRecord", sPmBeforePartyRecords);
      return "modules/partyManage/sPmBeforePartyRecordForm";
    }
    sPmBeforePartyRecord.setUploader(user.getName());
    sPmBeforePartyRecord.setUploadTime(new Date());
    model.addAttribute("sPmBeforePartyRecord", sPmBeforePartyRecord);
    return "modules/partyManage/sPmBeforePartyRecordForm";
  }

  @RequestMapping(value = "detail")
  public String detail(String proId, SPmBeforePartyRecord sPmBeforePartyRecord, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmBeforePartyRecord sPmBeforePartyRecords = sPmBeforePartyRecordService.getByproId(user.getProId());
    if (sPmBeforePartyRecords != null) {
      sPmBeforePartyRecordService.getForms(sPmBeforePartyRecords);
      model.addAttribute("sPmBeforePartyRecord", sPmBeforePartyRecords);
      return "modules/partyManage/sPmBeforePartyRecordFormDetails";
    }
    sPmBeforePartyRecord.setUploader(user.getName());
    sPmBeforePartyRecord.setUploadTime(new Date());
    model.addAttribute("sPmBeforePartyRecord", sPmBeforePartyRecord);
    return "modules/partyManage/sPmBeforePartyRecordFormDetails";
  }

  //	@RequiresPermissions("partyManage:sPmBeforePartyRecord:edit")
  @RequestMapping(value = "save")
  public String save(SPmBeforePartyRecord sPmBeforePartyRecord, Model model, RedirectAttributes redirectAttributes) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmBeforePartyRecord)) {
      return form(user.getProId(), sPmBeforePartyRecord, model);
    }
    String status = sPmBeforePartyRecordService.save(sPmBeforePartyRecord, user.getProId());
    String recordId = dqRecordService.getRidByPid(user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmBeforePartyRecord", sPmBeforePartyRecord);
//      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmBeforePartyRecord/form";
    } else {
      addMessage(redirectAttributes, status);
    }
    return "redirect:" + Global.getAdminPath() + "/partyManage/DQRecord/schedule/?id=" + recordId;
  }

  //	@RequiresPermissions("partyManage:sPmBeforePartyRecord:edit")
  @RequestMapping(value = "delete")
  public String delete(SPmBeforePartyRecord sPmBeforePartyRecord, RedirectAttributes redirectAttributes) {
    sPmBeforePartyRecordService.delete(sPmBeforePartyRecord);
    addMessage(redirectAttributes, "删除预备党员备案成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmBeforePartyRecord/?repage";
  }


  @RequestMapping(value = "regular")
  public String detail(String proId, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    String recordId = dqRecordService.getRidByPid(user.getProId());
    model.addAttribute("recordId", recordId);
    return "modules/partyManage/sPmPartyRegularList";
  }
}