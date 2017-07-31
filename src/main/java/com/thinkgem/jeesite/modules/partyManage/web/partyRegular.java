package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.*;
import com.thinkgem.jeesite.modules.partyManage.service.*;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 预备党员转正.
 *
 * @author zhc
 * @version 2017-04-27
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/regular")
public class partyRegular extends BaseController {

  @RequestMapping(value = "detail")
  public String detail(String proId, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    return "modules/partyManage/sPmPartyRegularListDetails";
  }

  @Autowired
  private SPmMinutesService sPmMinutesService;

  @ResponseBody
  @RequestMapping(value = "findByIds")
  public String findByIds(String ids) {
    List<Object[]> list = sPmMinutesService.findByIds(ids);
    return JsonMapper.nonDefaultMapper().toJson(list);
  }

  @RequestMapping(value = "sPmMinutes/form")
  public String form(String proId, SPmMinutes sPmMinutes, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmMinutes sPmMinutes1 = sPmMinutesService.getByproIds(user.getProId());
    if (sPmMinutes1 != null) {
      sPmMinutes1 = sPmMinutesService.getForms(sPmMinutes1);
      model.addAttribute("sPmMinutes", sPmMinutes1);
      return "modules/partyManage/becomregular/sPmMinutesForm";
    }
    sPmMinutes.setUploader(user.getName());
    sPmMinutes.setUploadTime(new Date());
    model.addAttribute("sPmMinutes", sPmMinutes);
    return "modules/partyManage/becomregular/sPmMinutesForm";
  }

  @RequestMapping(value = "sPmMinutes/save")
  public String save(SPmMinutes sPmMinutes, Model model, RedirectAttributes redirectAttributes) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmMinutes)) {
      return form(user.getProId(), sPmMinutes, model);
    }
    String status = sPmMinutesService.save(sPmMinutes, user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmMinutes", sPmMinutes);
//      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmBeforePartyRecord/regular";
    } else {
      addMessage(redirectAttributes, status);
    }
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmBeforePartyRecord/regular";
  }

  @RequestMapping(value = "sPmMinutes/detail")
  public String detail(String proId, SPmMinutes sPmMinutes, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmMinutes sPmMinutess = sPmMinutesService.getByproId(user.getProId());
    if (sPmMinutess != null) {
      sPmMinutess = sPmMinutesService.getForms(sPmMinutess);
      model.addAttribute("sPmMinutes", sPmMinutess);
      return "modules/partyManage/becomregular/sPmMinutesFormDetails";
    }
    model.addAttribute("sPmMinutes", sPmMinutes);
    return "modules/partyManage/becomregular/sPmMinutesFormDetails";
  }

  @Autowired
  private SPmPmsSService sPmPmsSService;

  @RequestMapping(value = "sPmPmsS/form")
  public String form(String proId, SPmPmsS sPmPmss, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmPmsS sPmPmss1 = sPmPmsSService.getByproId(user.getProId());
    if (sPmPmss1 != null) {
      sPmPmss1 = sPmPmsSService.getForms(sPmPmss1);
      model.addAttribute("sPmPmss", sPmPmss1);
      return "modules/partyManage/becomregular/sPmPmsSForm";
    }
    sPmPmss.setPartyBranch(user.getOffice().getName());
    sPmPmss.setUploader(user.getName());
    sPmPmss.setUploadTime(new Date());
    model.addAttribute("sPmPmss", sPmPmss);
    return "modules/partyManage/becomregular/sPmPmsSForm";
  }

  @RequestMapping(value = "sPmPmsS/save")
  public String save(SPmPmsS sPmPmss, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmPmss)) {
      return form(user.getProId(), sPmPmss, model);
    }
    sPmPmsSService.saves(sPmPmss, user.getProId());
    addMessage(redirectAttributes, "保存表单成功");
//    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmBeforePartyRecord/regular";
    return "redirect:" + Global.getAdminPath() + "/partyManage/regular/sPmPmsS/form";
  }

  @RequestMapping(value = "sPmPmsS/detail")
  public String detail(String proId, SPmPmsS sPmPmss, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmPmsS sPmPmss1 = sPmPmsSService.getByproId(user.getProId());
    if (sPmPmss1 != null) {
      sPmPmss1 = sPmPmsSService.getForms(sPmPmss1);
      model.addAttribute("sPmPmss", sPmPmss1);
      return "modules/partyManage/becomregular/sPmPmsSFormDetails";
    }
    model.addAttribute("sPmPmss", sPmPmss);
    return "modules/partyManage/becomregular/sPmPmsSFormDetails";
  }

  @Autowired
  private SPmPbService sPmPbService;

  @RequestMapping(value = "sPmPb/form")
  public String form(String proId, SPmPb sPmPb, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmPb sPmPbs = sPmPbService.getByproId(user.getProId());
    if (sPmPbs != null) {
      model.addAttribute("sPmPb", sPmPbs);
      return "modules/partyManage/becomregular/sPmPbForm";
    }
    model.addAttribute("sPmPb", sPmPb);
    return "modules/partyManage/becomregular/sPmPbForm";
  }

  @RequestMapping(value = "sPmPb/save")
  public String save(SPmPb sPmPb, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmPb)) {
      return form(user.getProId(), sPmPb, model);
    }
    sPmPbService.save(sPmPb, user.getProId());
    addMessage(redirectAttributes, "保存表单成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/regular/sPmPb/form";
  }

  @RequestMapping(value = "sPmPb/detail")
  public String detail(String proId, SPmPb sPmPb, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmPb sPmPbs = sPmPbService.getByproId(user.getProId());
    if (sPmPbs != null) {
      model.addAttribute("sPmPb", sPmPbs);
      return "modules/partyManage/becomregular/sPmPbFormDetails";
    }
    model.addAttribute("sPmPb", sPmPb);
    return "modules/partyManage/becomregular/sPmPbFormDetails";
  }

  @Autowired
  private SPmPcService sPmPcService;

  @RequestMapping(value = "sPmPc/form")
  public String form(String proId, SPmPc sPmPc, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmPc sPmPcs = sPmPcService.getByproId(user.getProId());
    if (sPmPcs != null) {
      model.addAttribute("sPmPc", sPmPcs);
      return "modules/partyManage/becomregular/sPmPcForm";
    }
    model.addAttribute("sPmPc", sPmPc);
    return "modules/partyManage/becomregular/sPmPcForm";
  }

  @RequestMapping(value = "sPmPc/save")
  public String save(SPmPc sPmPc, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmPc)) {
      return form(user.getProId(), sPmPc, model);
    }
    sPmPcService.save(sPmPc, user.getProId());

    addMessage(redirectAttributes, "保存表单成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/regular/sPmPc/form";
  }

  @RequestMapping(value = "sPmPc/detail")
  public String detail(String proId, SPmPc sPmPc, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmPc sPmPcs = sPmPcService.getByproId(user.getProId());
    if (sPmPcs != null) {
      model.addAttribute("sPmPc", sPmPcs);
      return "modules/partyManage/becomregular/sPmPcFormDetails";
    }
    model.addAttribute("sPmPc", sPmPc);
    return "modules/partyManage/becomregular/sPmPcFormDetails";
  }

  @Autowired
  private SPmTalkingService sPmTalkingService;

  @RequestMapping(value = "sPmTalking/form")
  public String form(String proId, SPmTalking sPmTalking, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmTalking sPmTalkings = sPmTalkingService.getByproId(user.getProId());
    if (sPmTalkings != null) {
      model.addAttribute("sPmTalking", sPmTalkings);
      return "modules/partyManage/becomregular/sPmTalkingForm";
    }
    model.addAttribute("sPmTalking", sPmTalking);
    return "modules/partyManage/becomregular/sPmTalkingForm";
  }

  @RequestMapping(value = "sPmTalking/save")
  public String save(SPmTalking sPmTalking, Model model,
                     RedirectAttributes redirectAttributes) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmTalking)) {
      return form(user.getProId(), sPmTalking, model);
    }
    String status = sPmTalkingService.save(sPmTalking, user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmTalking", sPmTalking);
//      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmBeforePartyRecord/regular";
    } else {
      addMessage(redirectAttributes, status);

    }
    return "redirect:" + Global.getAdminPath() + "/partyManage/regular/sPmTalking/form";
  }

  @RequestMapping(value = "sPmTalking/detail")
  public String detail(String proId, SPmTalking sPmTalking, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmTalking sPmTalkings = sPmTalkingService.getByproId(user.getProId());
    if (sPmTalkings != null) {
      model.addAttribute("sPmTalking", sPmTalkings);
      return "modules/partyManage/becomregular/sPmTalkingFormDetails";
    }
    model.addAttribute("sPmTalking", sPmTalking);
    return "modules/partyManage/becomregular/sPmTalkingFormDetails";
  }
}
