/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmExamineRecord;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmExamineRecordService;
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
 * 发展对象审核备案Controller
 *
 * @author zhc
 * @version 2017-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmExamineRecord")
public class SPmExamineRecordController extends BaseController {

  @Autowired
  private SPmExamineRecordService sPmExamineRecordService;

  @Autowired
  private DQRecordService dqRecordService;

  @ModelAttribute
  public SPmExamineRecord get(@RequestParam(required = false) String id) {
    SPmExamineRecord entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmExamineRecordService.get(id);
    }
    if (entity == null) {
      entity = new SPmExamineRecord();
    }
    return entity;
  }

  //	@RequiresPermissions("partyManage:sPmExamineRecord:view")
  @RequestMapping(value = "list")
  public String list(SPmExamineRecord sPmExamineRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<SPmExamineRecord> page = sPmExamineRecordService.findPage(new Page<SPmExamineRecord>(request, response), sPmExamineRecord);
    model.addAttribute("page", page);
    return "modules/partyManage/sPmExamineRecordList";
  }

  //	@RequiresPermissions("partyManage:sPmExamineRecord:view")
  @RequestMapping(value = "form")
  public String form(String proId, SPmExamineRecord sPmExamineRecord, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmExamineRecord sPmExamineRecords = sPmExamineRecordService.getByproId(user.getProId());
    if (sPmExamineRecords != null) {
      sPmExamineRecordService.getForms(sPmExamineRecords);
      model.addAttribute("sPmExamineRecord", sPmExamineRecords);
      return "modules/partyManage/sPmExamineRecordForm";
    }
    sPmExamineRecord.setUploader(user.getName());
    sPmExamineRecord.setUploadTime(new Date());
    model.addAttribute("sPmExamineRecord", sPmExamineRecord);
    return "modules/partyManage/sPmExamineRecordForm";
  }

  @RequestMapping(value = "detail")
  public String detail(String proId, SPmExamineRecord sPmExamineRecord, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmExamineRecord sPmExamineRecords = sPmExamineRecordService.getByproId(user.getProId());
    if (sPmExamineRecords != null) {
      sPmExamineRecordService.getForms(sPmExamineRecords);
      model.addAttribute("sPmExamineRecord", sPmExamineRecords);
      return "modules/partyManage/sPmExamineRecordFormDetails";
    }
    sPmExamineRecord.setUploader(user.getName());
    sPmExamineRecord.setUploadTime(new Date());
    model.addAttribute("sPmExamineRecord", sPmExamineRecord);
    return "modules/partyManage/sPmExamineRecordFormDetails";
  }

  //	@RequiresPermissions("partyManage:sPmExamineRecord:edit")
  @RequestMapping(value = "save")
  public String save(SPmExamineRecord sPmExamineRecord, Model model, RedirectAttributes redirectAttributes) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmExamineRecord)) {
      return form(user.getProId(), sPmExamineRecord, model);
    }
    String status = sPmExamineRecordService.save(sPmExamineRecord, user.getProId());
    String recordId = dqRecordService.getRidByPid(user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmExamineRecord", sPmExamineRecord);
//      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmExamineRecord/form";
    } else {
      addMessage(redirectAttributes, status);
    }
    return "redirect:" + Global.getAdminPath() + "/partyManage/DQRecord/schedule/?id=" + recordId;
  }

  //	@RequiresPermissions("partyManage:sPmExamineRecord:edit")
  @RequestMapping(value = "delete")
  public String delete(SPmExamineRecord sPmExamineRecord, RedirectAttributes redirectAttributes) {
    sPmExamineRecordService.delete(sPmExamineRecord);
    addMessage(redirectAttributes, "删除发展对象审核备案成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmExamineRecord/?repage";
  }

}