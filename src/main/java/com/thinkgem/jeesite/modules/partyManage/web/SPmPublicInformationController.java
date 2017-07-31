/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPublicInformation;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmPublicInformationService;
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
import java.util.*;

/**
 * 公示信息Controller
 *
 * @author zhc
 * @version 2017-04-20
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmPublicInformation")
public class SPmPublicInformationController extends BaseController {

  @Autowired
  private SPmPublicInformationService sPmPublicInformationService;

  @Autowired
  private DQRecordService dqRecordService;

  @ModelAttribute
  public SPmPublicInformation get(@RequestParam(required = false) String id) {
    SPmPublicInformation entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmPublicInformationService.get(id);
    }
    if (entity == null) {
      entity = new SPmPublicInformation();
    }
    return entity;
  }

  @RequestMapping(value = "detail")
  public String detail(String proId, SPmPublicInformation sPmPublicInformation, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    Page<SPmPublicInformation> page = sPmPublicInformationService.findPage(new Page<SPmPublicInformation>(request, response), sPmPublicInformation, user.getProId());
    model.addAttribute("page", page);
    return "modules/partyManage/sPmPublicInformationDetails";
  }

  //	@RequiresPermissions("partyManage:sPmPublicInformation:view")
  @RequestMapping(value = "list")
  public String list(String proId, SPmPublicInformation sPmPublicInformation, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    String recordId = dqRecordService.getRidByPid(user.getProId());
    Page<SPmPublicInformation> page = sPmPublicInformationService.findPage(new Page<SPmPublicInformation>(request, response), sPmPublicInformation, user.getProId());
    model.addAttribute("page", page);
    model.addAttribute("recordId", recordId);
    return "modules/partyManage/sPmPublicInformationList";
  }

  //	@RequiresPermissions("partyManage:sPmPublicInformation:view")
  @RequestMapping(value = "form")
  public String form(SPmPublicInformation sPmPublicInformation, Model model) {
    User user = UserUtils.getUser();
    sPmPublicInformation.setUploader(user.getName());
    sPmPublicInformation.setUploadTime(new Date());
    model.addAttribute("sPmPublicInformation", sPmPublicInformation);
    return "modules/partyManage/sPmPublicInformationForm";
  }

  //	@RequiresPermissions("partyManage:sPmPublicInformation:edit")
  @RequestMapping(value = "save")
  public String save(SPmPublicInformation sPmPublicInformation, Model model, RedirectAttributes redirectAttributes) {
    if (!beanValidator(model, sPmPublicInformation)) {
      return form(sPmPublicInformation, model);
    }
    User user = UserUtils.getUser();
    String proId = user.getProId();
    List<String> fileUrlList = sPmPublicInformation.getFileUrls();
    List<String> fileNameList = sPmPublicInformation.getFileNames();
    List<String> status = new ArrayList<String>();
    for (int i = 0; i < fileUrlList.size(); i++) {
      String statu = sPmPublicInformationService.save(sPmPublicInformation, proId, fileUrlList.get(i), fileNameList.get(i));
      status.add(statu);
    }
//    for (String fileUrl : fileUrlList) {
//      String statu = sPmPublicInformationService.save(sPmPublicInformation,userId,fileUrl);
//      status.add(statu);
//    }
    Map<String, String> sta = new HashMap<String, String>();
    for (String statu : status) {
      if (statu != "success") {
        sta.put("statu", "保存失败");
        break;
      }
      sta.put("statu", "保存成功");
    }
    addMessage(redirectAttributes, sta.get("statu"));
    model.addAttribute("sPmPublicInformation", sPmPublicInformation);
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmPublicInformation/list";
  }

  //	@RequiresPermissions("partyManage:sPmPublicInformation:edit")
  @RequestMapping(value = "delete")
  public String delete(SPmPublicInformation sPmPublicInformation, RedirectAttributes redirectAttributes) {
    sPmPublicInformationService.delete(sPmPublicInformation);
    addMessage(redirectAttributes, "删除公示信息成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmPublicInformation/list";
  }

}