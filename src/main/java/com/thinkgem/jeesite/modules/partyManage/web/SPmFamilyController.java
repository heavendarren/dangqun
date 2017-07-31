/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmFamily;
import com.thinkgem.jeesite.modules.partyManage.service.SPmFamilyService;
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
 * 家庭成员关系Controller
 *
 * @author one
 * @version 2017-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmFamily")
public class SPmFamilyController extends BaseController {

  @Autowired
  private SPmFamilyService sPmFamilyService;

  @ModelAttribute
  public SPmFamily get(@RequestParam(required = false) String id) {
    SPmFamily entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmFamilyService.get(id);
    }
    if (entity == null) {
      entity = new SPmFamily();
    }
    return entity;
  }

  @RequestMapping(value = "list")
  public String list(String proId, SPmFamily sPmFamily, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    Page<SPmFamily> page = sPmFamilyService.findPage(new Page<SPmFamily>(request, response), sPmFamily, user.getProId());
    model.addAttribute("page", page);
    return "modules/partyManage/jionparty/sPmFamilyList";
  }

  @RequestMapping(value = "detail")
  public String detail(String proId, SPmFamily sPmFamily, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    Page<SPmFamily> page = sPmFamilyService.findPage(new Page<SPmFamily>(request, response), sPmFamily, user.getProId());
    model.addAttribute("page", page);
    return "modules/partyManage/jionparty/sPmFamilyListDetails";
  }

  @RequestMapping(value = "form")
  public String form(SPmFamily sPmFamily, Model model) {
    User user = UserUtils.getUser();
    sPmFamily.setEntryPeo(user.getName());
    sPmFamily.setEntryDate(new Date());
    model.addAttribute("sPmFamily", sPmFamily);
    return "modules/partyManage/jionparty/sPmFamilyForm";
  }

  @RequestMapping(value = "save")
  public String save(SPmFamily sPmFamily, Model model, RedirectAttributes redirectAttributes) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmFamily)) {
      return form(sPmFamily, model);
    }
    String status = sPmFamilyService.save(sPmFamily, user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmFamily", sPmFamily);
      return "redirect:" + Global.getAdminPath() +
              "/partyManage/sPmFamily/list";
    } else {
      addMessage(redirectAttributes, status);
      return "redirect:" + Global.getAdminPath()
              + "/partyManage/sPmFamily/list";
    }
  }

  @RequestMapping(value = "delete")
  public String delete(SPmFamily sPmFamily, RedirectAttributes redirectAttributes) {
    sPmFamilyService.delete(sPmFamily);
    addMessage(redirectAttributes, "删除成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmFamily/list";
  }

}