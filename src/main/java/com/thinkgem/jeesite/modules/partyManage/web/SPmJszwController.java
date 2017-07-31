package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJszw;
import com.thinkgem.jeesite.modules.partyManage.service.SPmJszwService;
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
 * 专业技术资格Controller
 *
 * @author psy
 * @version 2017-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmJszw")
public class SPmJszwController extends BaseController {

  @Autowired
  private SPmJszwService sPmJszwService;

  @ModelAttribute
  public SPmJszw get(@RequestParam(required = false) String id) {
    SPmJszw entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmJszwService.get(id);
    }
    if (entity == null) {
      entity = new SPmJszw();
    }
    return entity;
  }

  @RequestMapping(value = "list")
  public String list(String proId, SPmJszw sPmJszw, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    Page<SPmJszw> page = sPmJszwService.findPage(new Page<SPmJszw>(request, response), sPmJszw, user.getProId());
    model.addAttribute("page", page);
    return "modules/partyManage/jionparty/sPmJszwList";
  }

  @RequestMapping(value = "detail")
  public String detail(String proId, SPmJszw sPmJszw, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    Page<SPmJszw> page = sPmJszwService.findPage(new Page<SPmJszw>(request, response), sPmJszw, user.getProId());
    model.addAttribute("page", page);
    return "modules/partyManage/jionparty/sPmJszwListDetails";
  }

  @RequestMapping(value = "form")
  public String form(SPmJszw sPmJszw, Model model) {
    User user = UserUtils.getUser();
    sPmJszw.setInputMan(user.getName());
    sPmJszw.setEntryTime(new Date());
    model.addAttribute("sPmJszw", sPmJszw);
    return "modules/partyManage/jionparty/sPmJszwForm";
  }

  @RequestMapping(value = "save")
  public String save(SPmJszw sPmJszw, Model model, RedirectAttributes redirectAttributes) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmJszw)) {
      return form(sPmJszw, model);
    }
    String status = sPmJszwService.save(sPmJszw, user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmJszw", sPmJszw);
      return "redirect:" + Global.getAdminPath() +
              "/partyManage/sPmJszw/list";
    } else {
      addMessage(redirectAttributes, status);
      return "redirect:" + Global.getAdminPath()
              + "/partyManage/sPmJszw/list";
    }
  }

  @RequestMapping(value = "delete")
  public String delete(SPmJszw sPmJszw, RedirectAttributes redirectAttributes) {
    sPmJszwService.delete(sPmJszw);
    addMessage(redirectAttributes, "删除专业技术资格成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmJszw/list";
  }

}