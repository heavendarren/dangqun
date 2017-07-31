/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.ProcessData;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmSupRecord;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmSupRecordService;
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
import java.util.*;

/**
 * 补录信息Controller
 *
 * @author zhc
 * @version 2017-05-02
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmSupRecord")
public class SPmSupRecordController extends BaseController {

  @Autowired
  private SPmSupRecordService sPmSupRecordService;

  @Autowired
  private DQRecordService dqRecordService;

  @ModelAttribute
  public SPmSupRecord get(@RequestParam(required = false) String id) {
    SPmSupRecord entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmSupRecordService.get(id);
    }
    if (entity == null) {
      entity = new SPmSupRecord();
    }
    return entity;
  }

  @RequestMapping(value = "detail")
  public String detail(String proId, SPmSupRecord sPmSupRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    ProcessData processData = sPmSupRecordService.getProData(user.getProId());
    if (processData != null) {
      sPmSupRecord.setNode(processData.getNode());
    }
    Page<SPmSupRecord> page = sPmSupRecordService.findPage(new Page<SPmSupRecord>(request, response), sPmSupRecord, user.getProId());
    model.addAttribute("page", page);
    return "modules/partyManage/sPmSupRecordListDetails";
  }

  //  @RequiresPermissions("partyManage:sPmSupRecord:view")
  @RequestMapping(value = "list")
  public String list(String proId, SPmSupRecord sPmSupRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    ProcessData processData = sPmSupRecordService.getProData(user.getProId());
    if (processData != null) {
      sPmSupRecord.setNode(processData.getNode());
    }
    Page<SPmSupRecord> page = sPmSupRecordService.findPage(new Page<SPmSupRecord>(request, response), sPmSupRecord, user.getProId());
    model.addAttribute("page", page);
    String recordId = dqRecordService.getRidByPid(user.getProId());
    model.addAttribute("recordId", recordId);
    return "modules/partyManage/sPmSupRecordList";
  }

  //  @RequiresPermissions("partyManage:sPmSupRecord:view")
  @RequestMapping(value = "form")
  public String form(SPmSupRecord sPmSupRecord, Model model) {
    User user = UserUtils.getUser();
    sPmSupRecord.setUploader(user.getName());
    sPmSupRecord.setUploadTime(new Date());
    model.addAttribute("sPmSupRecord", sPmSupRecord);
    return "modules/partyManage/sPmSupRecordForm";
  }

  //  @RequiresPermissions("partyManage:sPmSupRecord:edit")
  @RequestMapping(value = "save")
  public String save(SPmSupRecord sPmSupRecord, Model model, RedirectAttributes redirectAttributes) {
    if (!beanValidator(model, sPmSupRecord)) {
      return form(sPmSupRecord, model);
    }
    User user = UserUtils.getUser();
    String proId = user.getProId();
    ProcessData processData = sPmSupRecordService.getProData(proId);
    if (processData != null) {
      sPmSupRecord.setNode(processData.getNode());
    }
    List<String> fileUrlList = sPmSupRecord.getFileUrls();
    List<String> fileNameList = sPmSupRecord.getFileNames();
    List<String> status = new ArrayList<String>();
    for (int i = 0; i < fileUrlList.size(); i++) {
      String statu = sPmSupRecordService.save(sPmSupRecord, proId, fileUrlList.get(i), fileNameList.get(i));
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
    model.addAttribute("sPmSupRecord", sPmSupRecord);
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmSupRecord/list";
  }

  //  @RequiresPermissions("partyManage:sPmSupRecord:edit")
  @RequestMapping(value = "delete")
  public String delete(SPmSupRecord sPmSupRecord, RedirectAttributes redirectAttributes) {
    sPmSupRecordService.delete(sPmSupRecord);
    addMessage(redirectAttributes, "删除补录信息成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmSupRecord/list";
  }

}