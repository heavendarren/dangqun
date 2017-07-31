/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmEduDegree;
import com.thinkgem.jeesite.modules.partyManage.service.SPmEduDegreeService;
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
 * 学历学位Controller
 *
 * @author zhc
 * @version 2017-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmEduDegree")
public class SPmEduDegreeController extends BaseController {

  @Autowired
  private SPmEduDegreeService sPmEduDegreeService;

  @ModelAttribute
  public SPmEduDegree get(@RequestParam(required = false) String id) {
    SPmEduDegree entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmEduDegreeService.get(id);
    }
    if (entity == null) {
      entity = new SPmEduDegree();
    }
    return entity;
  }

  //	@RequiresPermissions("partyManage:sPmEduDegree:view")
  @RequestMapping(value = "list")
  public String list(String proId, SPmEduDegree sPmEduDegree, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    Page<SPmEduDegree> page = sPmEduDegreeService.findPage(new Page<SPmEduDegree>(request, response), sPmEduDegree, user.getProId());
    model.addAttribute("page", page);
    return "modules/partyManage/jionparty/sPmEduDegreeList";
  }

  //	@RequiresPermissions("partyManage:sPmEduDegree:view")
  @RequestMapping(value = "detail")
  public String detail(String proId, SPmEduDegree sPmEduDegree, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    Page<SPmEduDegree> page = sPmEduDegreeService.findPage(new Page<SPmEduDegree>(request, response), sPmEduDegree, user.getProId());
    model.addAttribute("page", page);
    return "modules/partyManage/jionparty/sPmEduDegreeListDetails";
  }

  //	@RequiresPermissions("partyManage:sPmEduDegree:view")
  @RequestMapping(value = "form")
  public String form(SPmEduDegree sPmEduDegree, Model model) {
    User user = UserUtils.getUser();
    sPmEduDegree.setOperator(user.getName());
    sPmEduDegree.setOperatorTime(new Date());
    model.addAttribute("sPmEduDegree", sPmEduDegree);
    return "modules/partyManage/jionparty/sPmEduDegreeForm";
  }

  //	@RequiresPermissions("partyManage:sPmEduDegree:edit")
  @RequestMapping(value = "save")
  public String save(SPmEduDegree sPmEduDegree, Model model, RedirectAttributes redirectAttributes) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmEduDegree)) {
      return form(sPmEduDegree, model);
    }
    String status = sPmEduDegreeService.save(sPmEduDegree, user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmEduDegree", sPmEduDegree);
      return "redirect:" + Global.getAdminPath() +
              "/partyManage/sPmEduDegree/list";
    } else {
      addMessage(redirectAttributes, status);
      return "redirect:" + Global.getAdminPath()
              + "/partyManage/sPmEduDegree/list";
    }
  }

  //	@RequiresPermissions("partyManage:sPmEduDegree:edit")
  @RequestMapping(value = "delete")
  public String delete(SPmEduDegree sPmEduDegree, RedirectAttributes redirectAttributes) {
    sPmEduDegreeService.delete(sPmEduDegree);
    addMessage(redirectAttributes, "删除成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmEduDegree/list";
  }

}