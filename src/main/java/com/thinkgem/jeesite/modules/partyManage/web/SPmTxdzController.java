package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmTxdz;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmTxdzService;
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
 * 展示出申请人的详细通讯地址Controller
 *
 * @author psy
 * @version 2017-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmTxdz")
public class SPmTxdzController extends BaseController {

  @Autowired
  private SPmTxdzService sPmTxdzService;

  @Autowired
  private DQRecordService dqRecordService;

  @ModelAttribute
  public SPmTxdz get(@RequestParam(required = false) String id) {
    SPmTxdz entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmTxdzService.get(id);
    }
    if (entity == null) {
      entity = new SPmTxdz();
    }
    return entity;
  }

  @RequestMapping(value = {"list"})
  public String list(SPmTxdz sPmTxdz, HttpServletRequest request,
                     HttpServletResponse response, Model model) {
    Page<SPmTxdz> page = sPmTxdzService.findPage(new Page<SPmTxdz>(request, response), sPmTxdz);
    model.addAttribute("page", page);
    return "modules/partyManage/jionparty/sPmTxdzList";
  }

  @RequestMapping(value = "detail")
  public String detail(String proId, SPmTxdz sPmTxdz, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmTxdz sPmTxdzs = sPmTxdzService.getByproId(user.getProId());
    if (sPmTxdzs != null) {
      model.addAttribute("sPmTxdz", sPmTxdzs);
      return "modules/partyManage/jionparty/sPmTxdzFormDetails";
    }
    sPmTxdz.setEntryPerson(user.getName());
    sPmTxdz.setEntryTime(new Date());
    model.addAttribute("sPmTxdz", sPmTxdz);
    return "modules/partyManage/jionparty/sPmTxdzFormDetails";
  }

  @RequestMapping(value = "form")
  public String form(String proId, SPmTxdz sPmTxdz, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmTxdz sPmTxdzs = sPmTxdzService.getByproId(user.getProId());
//    String recordId = dqRecordService.getRidByPid(user.getProId());
    if (sPmTxdzs != null) {
//      model.addAttribute("recordId", recordId);
      model.addAttribute("sPmTxdz", sPmTxdzs);
      return "modules/partyManage/jionparty/sPmTxdzForm";
    }
    sPmTxdz.setEntryPerson(user.getName());
    sPmTxdz.setEntryTime(new Date());
//    model.addAttribute("recordId", recordId);
    model.addAttribute("sPmTxdz", sPmTxdz);
    return "modules/partyManage/jionparty/sPmTxdzForm";
  }


  @RequestMapping(value = "save")
  public String save(SPmTxdz sPmTxdz, Model model, RedirectAttributes redirectAttributes) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmTxdz)) {
      return form(user.getProId(), sPmTxdz, model);
    }
    String status = sPmTxdzService.save(sPmTxdz, user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmTxdz", sPmTxdz);
      return "redirect:" + Global.getAdminPath() +
              "/partyManage/sPmTxdz/form";
    } else {
      addMessage(redirectAttributes, status);
      return "redirect:" + Global.getAdminPath()
              + "/partyManage/sPmTxdz/form";
    }
  }


  @RequestMapping(value = "delete")
  public String delete(SPmTxdz sPmTxdz, RedirectAttributes redirectAttributes) {
    sPmTxdzService.delete(sPmTxdz);
    addMessage(redirectAttributes, "删除通讯地址成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/SPmTxdz/form";
  }
}