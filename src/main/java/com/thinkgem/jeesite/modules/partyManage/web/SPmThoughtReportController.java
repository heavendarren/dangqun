/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmThoughtReport;
import com.thinkgem.jeesite.modules.partyManage.service.SPmThoughtReportService;
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
 * 思想汇报Controller
 *
 * @author zhc
 * @version 2017-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmThoughtReport")
public class SPmThoughtReportController extends BaseController {

  @Autowired
  private SPmThoughtReportService sPmThoughtReportService;

  @ModelAttribute
  public SPmThoughtReport get(@RequestParam(required = false) String id) {
    SPmThoughtReport entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmThoughtReportService.get(id);
    }
    if (entity == null) {
      entity = new SPmThoughtReport();
    }
    return entity;
  }

  //	@RequiresPermissions("partyManage:sPmThoughtReport:view")
  @RequestMapping(value = "list")
  public String list(String proId, SPmThoughtReport sPmThoughtReport, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    Page<SPmThoughtReport> page = sPmThoughtReportService.findPage(new Page<SPmThoughtReport>(request, response), sPmThoughtReport, user.getProId());
    model.addAttribute("page", page);
    return "modules/partyManage/activisttrain/sPmThoughtReportList";
  }

  @RequestMapping(value = "detail")
  public String detail(String id, Model model) {
    SPmThoughtReport sPmThoughtReport = sPmThoughtReportService.getWithUrl(id);
    model.addAttribute("sPmThoughtReport", sPmThoughtReport);
    return "modules/partyManage/activisttrain/sPmThoughtReportDetail";
  }

  @RequestMapping(value = "details")
  public String details(String proId, SPmThoughtReport sPmThoughtReport, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    Page<SPmThoughtReport> page = sPmThoughtReportService.findPage(new Page<SPmThoughtReport>(request, response), sPmThoughtReport, user.getProId());
    model.addAttribute("page", page);
    return "modules/partyManage/activisttrain/sPmThoughtReportListDetails";
  }

  //	@RequiresPermissions("partyManage:sPmThoughtReport:view")
  @RequestMapping(value = "form")
  public String form(SPmThoughtReport sPmThoughtReport, Model model) {
    User user = UserUtils.getUser();
    sPmThoughtReport.setUploader(user.getName());
    sPmThoughtReport.setUploadTime(new Date());
    model.addAttribute("sPmThoughtReport", sPmThoughtReport);
    return "modules/partyManage/activisttrain/sPmThoughtReportForm";
  }

  //	@RequiresPermissions("partyManage:sPmThoughtReport:edit")
  @RequestMapping(value = "save")
  public String save(SPmThoughtReport sPmThoughtReport, Model model, RedirectAttributes redirectAttributes) {
    if (!beanValidator(model, sPmThoughtReport)) {
      return form(sPmThoughtReport, model);
    }
    User user = UserUtils.getUser();
    String proId = user.getProId();
    String status = sPmThoughtReportService.save(sPmThoughtReport, proId);
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmThoughtReport", sPmThoughtReport);
//      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmContactSettings/activisttrain/form";
    } else {
      addMessage(redirectAttributes, status);
    }
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmThoughtReport/list";
  }

  //	@RequiresPermissions("partyManage:sPmThoughtReport:edit")
  @RequestMapping(value = "delete")
  public String delete(SPmThoughtReport sPmThoughtReport, RedirectAttributes redirectAttributes) {
    sPmThoughtReportService.delete(sPmThoughtReport);
    addMessage(redirectAttributes, "删除思想汇报成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmThoughtReport/?repage";
  }

}