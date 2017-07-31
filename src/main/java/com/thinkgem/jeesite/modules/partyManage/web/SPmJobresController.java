/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJobres;
import com.thinkgem.jeesite.modules.partyManage.service.SPmJobresService;
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
 * 这是工作简历表Controller
 *
 * @author one
 * @version 2017-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmJobres")
public class SPmJobresController extends BaseController {

  @Autowired
  private SPmJobresService sPmJobresService;

  @ModelAttribute
  public SPmJobres get(@RequestParam(required = false) String id) {
    SPmJobres entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmJobresService.get(id);
    }
    if (entity == null) {
      entity = new SPmJobres();
    }
    return entity;
  }

  //@RequiresPermissions("job:sPmJobres:view")
  @RequestMapping(value = "list")
  public String list(String proId, SPmJobres sPmJobres, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    Page<SPmJobres> page = sPmJobresService.findPage(new Page<SPmJobres>(request, response), sPmJobres, user.getProId());
    model.addAttribute("page", page);
    return "modules/partyManage/jionparty/sPmJobresList";
  }

  @RequestMapping(value = "detail")
  public String detail(String proId, SPmJobres sPmJobres, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    Page<SPmJobres> page = sPmJobresService.findPage(new Page<SPmJobres>(request, response), sPmJobres, user.getProId());
    model.addAttribute("page", page);
    return "modules/partyManage/jionparty/sPmJobresListDetails";
  }

  //@RequiresPermissions("job:sPmJobres:view")
  @RequestMapping(value = "form")
  public String form(SPmJobres sPmJobres, Model model) {
    User user = UserUtils.getUser();
    sPmJobres.setFillPeople(user.getName());
    sPmJobres.setFillTime(new Date());
    model.addAttribute("sPmJobres", sPmJobres);
    return "modules/partyManage/jionparty/sPmJobresForm";
  }

  //@RequiresPermissions("job:sPmJobres:edit")
  @RequestMapping(value = "save")
  public String save(SPmJobres sPmJobres, Model model, RedirectAttributes redirectAttributes) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmJobres)) {
      return form(sPmJobres, model);
    }
    String status = sPmJobresService.save(sPmJobres, user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmJobres", sPmJobres);
      return "redirect:" + Global.getAdminPath() +
              "/partyManage/sPmJobres/list";
    } else {
      addMessage(redirectAttributes, status);
      return "redirect:" + Global.getAdminPath()
              + "/partyManage/sPmJobres/list";
    }
  }

  //@RequiresPermissions("job:sPmJobres:edit")
  @RequestMapping(value = "delete")
  public String delete(SPmJobres sPmJobres, RedirectAttributes redirectAttributes) {
    sPmJobresService.delete(sPmJobres);
    addMessage(redirectAttributes, "删除简历成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmJobres/list";
  }

}