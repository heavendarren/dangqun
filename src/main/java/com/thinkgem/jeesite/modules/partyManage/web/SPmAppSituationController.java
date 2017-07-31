/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAppSituation;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmAppSituationService;
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
 * 入党申请人情况Controller
 *
 * @author zhc
 * @version 2017-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmAppSituation")
public class SPmAppSituationController extends BaseController {

  @Autowired
  private SPmAppSituationService sPmAppSituationService;

  @Autowired
  private DQRecordService dqRecordService;

  @ModelAttribute
  public SPmAppSituation get(@RequestParam(required = false) String id) {
    SPmAppSituation entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmAppSituationService.get(id);
    }
    if (entity == null) {
      entity = new SPmAppSituation();
    }
    return entity;
  }

  //	@RequiresPermissions("partyManage:sPmAppSituation:view")
  @RequestMapping(value = "list")
  public String list(SPmAppSituation sPmAppSituation, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<SPmAppSituation> page = sPmAppSituationService.findPage(new Page<SPmAppSituation>(request, response), sPmAppSituation);
    model.addAttribute("page", page);
    return "modules/partyManage/jionparty/sPmAppSituationList";
  }

  @RequestMapping(value = "detail")
  public String detail(String proId, SPmAppSituation sPmAppSituation, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmAppSituation sPmAppSituations = sPmAppSituationService.getByproId(user.getProId());
    if (sPmAppSituations != null) {
      model.addAttribute("sPmAppSituation", sPmAppSituations);
      return "modules/partyManage/jionparty/sPmAppSituationFormDetails";
    }
    sPmAppSituation.setOperator(user.getName());
    sPmAppSituation.setOperatorTime(new Date());
    model.addAttribute("sPmAppSituation", sPmAppSituation);
    return "modules/partyManage/jionparty/sPmAppSituationFormDetails";
  }

  //	@RequiresPermissions("partyManage:sPmAppSituation:view")
  @RequestMapping(value = "form")
  public String form(String proId, SPmAppSituation sPmAppSituation, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmAppSituation sPmAppSituations = sPmAppSituationService.getByproId(user.getProId());
//    String recordId = dqRecordService.getRidByPid(user.getProId());
    if (sPmAppSituations != null) {
//      model.addAttribute("recordId", recordId);
      model.addAttribute("sPmAppSituation", sPmAppSituations);
      return "modules/partyManage/jionparty/sPmAppSituationForm";
    }
    sPmAppSituation.setOperator(user.getName());
    sPmAppSituation.setOperatorTime(new Date());
//    model.addAttribute("recordId", recordId);
    model.addAttribute("sPmAppSituation", sPmAppSituation);
    return "modules/partyManage/jionparty/sPmAppSituationForm";
  }

  //	@RequiresPermissions("partyManage:sPmAppSituation:edit")
  @RequestMapping(value = "save")
  public String save(SPmAppSituation sPmAppSituation, Model model, RedirectAttributes redirectAttributes) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmAppSituation)) {
      return form(user.getProId(), sPmAppSituation, model);
    }
    String status = sPmAppSituationService.save(sPmAppSituation, user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmAppSituation", sPmAppSituation);
      return "redirect:" + Global.getAdminPath() +
              "/partyManage/sPmAppSituation/form";
    } else {
      addMessage(redirectAttributes, status);
      return "redirect:" + Global.getAdminPath()
              + "/partyManage/sPmAppSituation/form";
    }
  }

  //	@RequiresPermissions("partyManage:sPmAppSituation:edit")
  @RequestMapping(value = "delete")
  public String delete(SPmAppSituation sPmAppSituation, RedirectAttributes redirectAttributes) {
    sPmAppSituationService.delete(sPmAppSituation);
    addMessage(redirectAttributes, "删除入党申请人情况成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmAppSituation/list";
  }

}