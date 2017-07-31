/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmInvestigateSituation;
import com.thinkgem.jeesite.modules.partyManage.service.SPmInvestigateSituationService;
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
 * 培养考察情况Controller
 *
 * @author zhc
 * @version 2017-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmInvestigateSituation")
public class SPmInvestigateSituationController extends BaseController {

  @Autowired
  private SPmInvestigateSituationService sPmInvestigateSituationService;

  @ModelAttribute
  public SPmInvestigateSituation get(@RequestParam(required = false) String id) {
    SPmInvestigateSituation entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmInvestigateSituationService.get(id);
    }
    if (entity == null) {
      entity = new SPmInvestigateSituation();
    }
    return entity;
  }

  //	@RequiresPermissions("partyManage:sPmInvestigateSituation:view")
  @RequestMapping(value = "list")
  public String list(String proId, SPmInvestigateSituation sPmInvestigateSituation, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null) {
      user.setProId(proId);
    }
    Page<SPmInvestigateSituation> page = sPmInvestigateSituationService.findPage(new Page<SPmInvestigateSituation>(request, response), sPmInvestigateSituation, user.getProId());
    model.addAttribute("page", page);
    return "modules/partyManage/activisttrain/sPmInvestigateSituationList";
  }

  //	@RequiresPermissions("partyManage:sPmInvestigateSituation:view")
  @RequestMapping(value = "form")
  public String form(SPmInvestigateSituation sPmInvestigateSituation, Model model) {
    User user = UserUtils.getUser();
    sPmInvestigateSituation.setUploader(user.getName());
    sPmInvestigateSituation.setUploadTime(new Date());
    model.addAttribute("sPmInvestigateSituation", sPmInvestigateSituation);
    return "modules/partyManage/activisttrain/sPmInvestigateSituationForm";
  }

  @RequestMapping(value = "detail")
  public String detail(String id, Model model) {
    SPmInvestigateSituation sPmInvestigateSituation = sPmInvestigateSituationService.getWithUrl(id);
    model.addAttribute("sPmInvestigateSituation", sPmInvestigateSituation);
    return "modules/partyManage/activisttrain/sPmInvestigateSituationDetail";
  }

  @RequestMapping(value = "details")
  public String details(String proId, SPmInvestigateSituation sPmInvestigateSituation, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null) {
      user.setProId(proId);
    }
    Page<SPmInvestigateSituation> page = sPmInvestigateSituationService.findPage(new Page<SPmInvestigateSituation>(request, response), sPmInvestigateSituation, user.getProId());
    model.addAttribute("page", page);
    return "modules/partyManage/activisttrain/sPmInvestigateSituationListDetails";
  }

  //	@RequiresPermissions("partyManage:sPmInvestigateSituation:edit")
  @RequestMapping(value = "save")
  public String save(SPmInvestigateSituation sPmInvestigateSituation, Model model, RedirectAttributes redirectAttributes) {
    if (!beanValidator(model, sPmInvestigateSituation)) {
      return form(sPmInvestigateSituation, model);
    }
    User user = UserUtils.getUser();
    String status = sPmInvestigateSituationService.save(sPmInvestigateSituation, user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmInvestigateSituation", sPmInvestigateSituation);
//      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmContactSettings/activisttrain/form";
    } else {
      addMessage(redirectAttributes, status);
    }
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmInvestigateSituation/list";
  }

  //	@RequiresPermissions("partyManage:sPmInvestigateSituation:edit")
  @RequestMapping(value = "delete")
  public String delete(SPmInvestigateSituation sPmInvestigateSituation, RedirectAttributes redirectAttributes) {
    sPmInvestigateSituationService.delete(sPmInvestigateSituation);
    addMessage(redirectAttributes, "删除培养考察情况成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmInvestigateSituation/?repage";
  }

}