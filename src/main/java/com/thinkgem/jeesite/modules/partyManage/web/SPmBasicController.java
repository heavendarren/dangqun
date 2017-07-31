/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmBasic;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmBasicService;
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
 * 基本情况Controller
 *
 * @author zhc
 * @version 2017-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmBasic")
public class SPmBasicController extends BaseController {

  @Autowired
  private SPmBasicService sPmBasicService;

  @Autowired
  private DQRecordService dqRecordService;

  @ModelAttribute
  public SPmBasic get(@RequestParam(required = false) String id) {
    SPmBasic entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmBasicService.get(id);
    }
    if (entity == null) {
      entity = new SPmBasic();
    }
    return entity;
  }

  @RequestMapping(value = "list")
  public String list(SPmBasic sPmBasic, HttpServletRequest request,
                     HttpServletResponse response, Model model) {
    Page<SPmBasic> page = sPmBasicService.findPage(new Page<SPmBasic>(request,
            response), sPmBasic);
    model.addAttribute("page", page);
    return "modules/partyManage/jionparty/sPmBasicList";
  }

  @RequestMapping(value = "jionparty/detail")
  public String detail(String proId, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    return "modules/partyManage/sPmJionPartyDetails";
  }

  @RequestMapping(value = "detail")
  public String detail(String proId, SPmBasic sPmBasic, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmBasic sPmBasics = sPmBasicService.getByproId(user.getProId());
    if (sPmBasics != null) {
      model.addAttribute("sPmBasic", sPmBasics);
      return "modules/partyManage/jionparty/sPmBasicFormDetails";
    }
    sPmBasic.setOperator(user.getName());
    sPmBasic.setOperatorTime(new Date());
    model.addAttribute("sPmBasic", sPmBasic);
    return "modules/partyManage/jionparty/sPmBasicFormDetails";
  }

  @RequestMapping(value = "jionparty/form")
  public String form(String proId, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    String recordId = dqRecordService.getRidByPid(user.getProId());
    model.addAttribute("recordId", recordId);
    return "modules/partyManage/sPmJionParty";
  }

  @RequestMapping(value = "form")
  public String form(String proId, SPmBasic sPmBasic, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmBasic sPmBasics = sPmBasicService.getByproId(user.getProId());
//    String recordId = dqRecordService.getRidByPid(user.getProId());
    if (sPmBasics != null) {
//      model.addAttribute("recordId", recordId);
      model.addAttribute("sPmBasic", sPmBasics);
      return "modules/partyManage/jionparty/sPmBasicForm";
    }
    sPmBasic.setOperator(user.getName());
    sPmBasic.setOperatorTime(new Date());
//    model.addAttribute("recordId", recordId);
    model.addAttribute("sPmBasic", sPmBasic);
    return "modules/partyManage/jionparty/sPmBasicForm";
  }

  @RequestMapping(value = "save")
  public String save(SPmBasic sPmBasic, Model model,
                     RedirectAttributes redirectAttributes) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmBasic)) {
      return form(user.getProId(), sPmBasic, model);
    }
    String status = sPmBasicService.save(sPmBasic, user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmBasic", sPmBasic);
      return "redirect:" + Global.getAdminPath()
              + "/partyManage/sPmBasic/form";
    } else {
      addMessage(redirectAttributes, status);
      return "redirect:" + Global.getAdminPath()
              + "/partyManage/sPmBasic/form";
    }
  }

  @RequestMapping(value = "delete")
  public String delete(SPmBasic sPmBasic, RedirectAttributes redirectAttributes) {
    sPmBasicService.delete(sPmBasic);
    addMessage(redirectAttributes, "删除提交信息成功");
    return "redirect:" + Global.getAdminPath()
            + "/partyManage/sPmBasic/list";
  }

}