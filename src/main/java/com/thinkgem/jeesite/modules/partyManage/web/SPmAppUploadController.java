package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAppUpload;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmAppUploadService;
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
 * 职业证书的上传Controller
 *
 * @author psy
 * @version 2017-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmAppUpload")
public class SPmAppUploadController extends BaseController {

  @Autowired
  private SPmAppUploadService sPmAppUploadService;

  @Autowired
  private DQRecordService dqRecordService;

  @ModelAttribute
  public SPmAppUpload get(@RequestParam(required = false) String id) {
    SPmAppUpload entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmAppUploadService.get(id);
    }
    if (entity == null) {
      entity = new SPmAppUpload();
    }
    return entity;
  }

  @RequestMapping(value = "success")
  public String success(Model model) {
    return "modules/partyManage/success";
  }


  @RequestMapping(value = "list")
  public String list(SPmAppUpload sPmAppUpload, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<SPmAppUpload> page = sPmAppUploadService.findPage(new Page<SPmAppUpload>(request, response), sPmAppUpload);
    model.addAttribute("page", page);
    return "modules/partyManage/jionparty/sPmAppUploadList";
  }


  @RequestMapping(value = "form")
  public String form(String proId, SPmAppUpload sPmAppUpload, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmAppUpload sPmAppUploads = sPmAppUploadService.getByproId(user.getProId());
//    String recordId = dqRecordService.getRidByPid(user.getProId());
    if (sPmAppUploads != null) {
      sPmAppUploadService.getForms(sPmAppUploads);
//      model.addAttribute("recordId", recordId);
      model.addAttribute("sPmAppUpload", sPmAppUploads);
      return "modules/partyManage/jionparty/sPmAppUploadForm";
    }
    sPmAppUpload.setUploader(user.getName());
    sPmAppUpload.setUploadTime(new Date());
//    model.addAttribute("recordId", recordId);
    model.addAttribute("sPmAppUpload", sPmAppUpload);
    return "modules/partyManage/jionparty/sPmAppUploadForm";
  }

  @RequestMapping(value = "detail")
  public String detail(String proId, SPmAppUpload sPmAppUpload, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    SPmAppUpload sPmAppUploads = sPmAppUploadService.getByproId(user.getProId());
    if (sPmAppUploads != null) {
      sPmAppUploadService.getForms(sPmAppUploads);
      model.addAttribute("sPmAppUpload", sPmAppUploads);
      return "modules/partyManage/jionparty/sPmAppUploadFormDetails";
    }
    sPmAppUpload.setUploader(user.getName());
    sPmAppUpload.setUploadTime(new Date());
    model.addAttribute("sPmAppUpload", sPmAppUpload);
    return "modules/partyManage/jionparty/sPmAppUploadFormDetails";
  }


  @RequestMapping(value = "save")
  public String save(SPmAppUpload sPmAppUpload, Model model, RedirectAttributes redirectAttributes) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmAppUpload)) {
      return form(user.getProId(), sPmAppUpload, model);
    }
    String status = sPmAppUploadService.save(sPmAppUpload, user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmAppUpload", sPmAppUpload);
      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmAppUpload/form";
    } else {
      addMessage(redirectAttributes, status);
      return "redirect:" + Global.getAdminPath() + "/partyManage/sPmAppUpload/form";
    }
  }


  @RequestMapping(value = "delete")
  public String delete(SPmAppUpload sPmAppUpload, RedirectAttributes redirectAttributes) {
    sPmAppUploadService.delete(sPmAppUpload);
    addMessage(redirectAttributes, "删除成功！");
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmAppUpload/list";
  }

}