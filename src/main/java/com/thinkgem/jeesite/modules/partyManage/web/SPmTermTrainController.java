/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmTermTrain;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmTermTrainService;
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
 * 发展对象短期集中培训Controller
 *
 * @author zhc
 * @version 2017-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmTermTrain")
public class SPmTermTrainController extends BaseController {

  @Autowired
  private SPmTermTrainService sPmTermTrainService;

  @Autowired
  private DQRecordService dqRecordService;

  @ModelAttribute
  public SPmTermTrain get(@RequestParam(required = false) String id) {
    SPmTermTrain entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmTermTrainService.get(id);
    }
    if (entity == null) {
      entity = new SPmTermTrain();
    }
    return entity;
  }

  //	@RequiresPermissions("partyManage:sPmTermTrain:view")
  @RequestMapping(value = "list")
  public String list(SPmTermTrain sPmTermTrain, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<SPmTermTrain> page = sPmTermTrainService.findPage(new Page<SPmTermTrain>(request, response), sPmTermTrain);
    model.addAttribute("page", page);
    return "modules/partyManage/sPmTermTrainList";
  }

  //	@RequiresPermissions("partyManage:sPmTermTrain:view")
  @RequestMapping(value = "form")
  public String form(String proId, SPmTermTrain sPmTermTrain, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmTermTrain sPmTermTrains = sPmTermTrainService.getByproId(user.getProId());
    if (sPmTermTrains != null) {
      sPmTermTrainService.getForms(sPmTermTrains);
      model.addAttribute("sPmTermTrain", sPmTermTrains);
      return "modules/partyManage/sPmTermTrainForm";
    }
    sPmTermTrain.setUploader(user.getName());
    sPmTermTrain.setUploadTime(new Date());
    model.addAttribute("sPmTermTrain", sPmTermTrain);
    return "modules/partyManage/sPmTermTrainForm";
  }

  @RequestMapping(value = "detail")
  public String detail(String proId, SPmTermTrain sPmTermTrain, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmTermTrain sPmTermTrains = sPmTermTrainService.getByproId(user.getProId());
    if (sPmTermTrains != null) {
      sPmTermTrainService.getForms(sPmTermTrains);
      model.addAttribute("sPmTermTrain", sPmTermTrains);
      return "modules/partyManage/sPmTermTrainFormDetails";
    }
    sPmTermTrain.setUploader(user.getName());
    sPmTermTrain.setUploadTime(new Date());
    model.addAttribute("sPmTermTrain", sPmTermTrain);
    return "modules/partyManage/sPmTermTrainFormDetails";
  }

  //	@RequiresPermissions("partyManage:sPmTermTrain:edit")
  @RequestMapping(value = "save")
  public String save(SPmTermTrain sPmTermTrain, Model model, RedirectAttributes redirectAttributes) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmTermTrain)) {
      return form(user.getProId(), sPmTermTrain, model);
    }
    String status = sPmTermTrainService.save(sPmTermTrain, user.getProId());
    String recordId = dqRecordService.getRidByPid(user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmTermTrain", sPmTermTrain);
//      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmTermTrain/form";
    } else {
      addMessage(redirectAttributes, status);
    }
    return "redirect:" + Global.getAdminPath() + "/partyManage/DQRecord/schedule/?id=" + recordId;
  }

  //	@RequiresPermissions("partyManage:sPmTermTrain:edit")
  @RequestMapping(value = "delete")
  public String delete(SPmTermTrain sPmTermTrain, RedirectAttributes redirectAttributes) {
    sPmTermTrainService.delete(sPmTermTrain);
    addMessage(redirectAttributes, "删除发展对象短期集中培训成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmTermTrain/?repage";
  }

}