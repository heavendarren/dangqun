/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmContactSettings;
import com.thinkgem.jeesite.modules.partyManage.service.*;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 培养联系人Controller
 *
 * @author zhc
 * @version 2017-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmContactSettings")
public class SPmContactSettingsController extends BaseController {

  @Autowired
  private DQRecordService dqRecordService;

  @Autowired
  private SPmContactSettingsService sPmContactSettingsService;

  @Autowired
  private OfficeService officeService;

  @Autowired
  private SystemService systemService;

  @Autowired
  private SPmBasicService sPmBasicService;

  @Autowired
  private SPmAppSituationService sPmAppSituationService;

  @Autowired
  private SPmJobresService sPmJobresService;

  @ModelAttribute
  public SPmContactSettings get(@RequestParam(required = false) String id) {
    SPmContactSettings entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmContactSettingsService.get(id);
    }
    if (entity == null) {
      entity = new SPmContactSettings();
    }
    return entity;
  }

  //	@RequiresPermissions("partyManage:sPmContactSettings:view")
  @RequestMapping(value = "list")
  public String list(String proId, SPmContactSettings sPmContactSettings, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    Page<SPmContactSettings> page = sPmContactSettingsService.findPage(new Page<SPmContactSettings>(request, response), sPmContactSettings, user.getProId());
    model.addAttribute("page", page);
    return "modules/partyManage/activisttrain/sPmContactSettingsList";
  }

  @RequestMapping(value = "activisttrain/detail")
  public String detail(String proId, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }

    String roleId = dqRecordService.getSQR_ROLE_ID();
    List<String> userRoleIdList = user.getRoleIdList();
    String appRole = "0";
    for (String userRole : userRoleIdList) {
      if (roleId.equals(userRole)) {
        appRole = "1";
      }
    }
    model.addAttribute("appRole", appRole);
    return "modules/partyManage/sPmActivistTrainDetails";
  }

  @RequestMapping(value = "detail")
  public String detail(String proId, SPmContactSettings sPmContactSettings, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    Page<SPmContactSettings> page = sPmContactSettingsService.findPage(new Page<SPmContactSettings>(request, response), sPmContactSettings, user.getProId());
    model.addAttribute("page", page);
    return "modules/partyManage/activisttrain/sPmContactSettingsListDetails";
  }

  @RequestMapping(value = "activisttrain/form")
  public String form(String proId, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }

    String roleId = dqRecordService.getSQR_ROLE_ID();
    List<String> userRoleIdList = user.getRoleIdList();
    String appRole = "0";
    for (String userRole : userRoleIdList) {
      if (roleId.equals(userRole)) {
        appRole = "1";
      }
    }
    String recordId = dqRecordService.getRidByPid(user.getProId());
    model.addAttribute("recordId", recordId);
    model.addAttribute("appRole", appRole);
    return "modules/partyManage/sPmActivistTrain";
  }

  //	@RequiresPermissions("partyManage:sPmContactSettings:view")
  @RequestMapping(value = "form")
  public String form(SPmContactSettings sPmContactSettings, Model model) {
    model.addAttribute("sPmContactSettings", sPmContactSettings);
    return "modules/partyManage/activisttrain/sPmContactSettingsForm";
  }

  //	@RequiresPermissions("partyManage:sPmContactSettings:edit")
  @RequestMapping(value = "save")
  public String save(SPmContactSettings sPmContactSettings, Model model, RedirectAttributes redirectAttributes) {
    if (!beanValidator(model, sPmContactSettings)) {
      return form(sPmContactSettings, model);
    }
    User user = UserUtils.getUser();
    String status = sPmContactSettingsService.save(sPmContactSettings, user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmContactSettings", sPmContactSettings);
//      return "redirect:" + Global.getAdminPath()
//              + "/partyManage/sPmContactSettings/activisttrain/form";
    } else {
      addMessage(redirectAttributes, status);
    }
    return "redirect:" + Global.getAdminPath()
            + "/partyManage/sPmContactSettings/list";
  }

  //	@RequiresPermissions("partyManage:sPmContactSettings:edit")
  @RequestMapping(value = "delete")
  public String delete(SPmContactSettings sPmContactSettings, RedirectAttributes redirectAttributes) {
    sPmContactSettingsService.delete(sPmContactSettings);
    addMessage(redirectAttributes, "删除培养联系人成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmContactSettings/?repage";
  }

  @ResponseBody
  @RequestMapping(value = "treeData")
  public List<Map<String, Object>> treeData(@RequestParam(required = false) Boolean isAll, HttpServletResponse response) {
    List<Map<String, Object>> mapList = Lists.newArrayList();
    List<Office> list = officeService.findList(isAll);
    for (int i = 0; i < list.size(); i++) {
      Office e = list.get(i);
      Map<String, Object> officeMap = Maps.newHashMap();
      officeMap.put("id", e.getId());
      officeMap.put("pId", e.getParentId());
      officeMap.put("pIds", e.getParentIds());
      officeMap.put("name", e.getName());
      mapList.add(officeMap);
      List<User> userList = systemService.findUserByOfficeId(e.getId());
      for (int n = 0; n < userList.size(); n++) {
        Map<String, Object> userMap = Maps.newHashMap();
        User u = userList.get(n);
        userMap.put("id", u.getId());
        userMap.put("pId", e.getId());
        userMap.put("name", StringUtils.replace(u.getName(), " ", ""));
        mapList.add(userMap);
      }
    }
    return mapList;
  }

  //TODO 取已经入党人员信息带入
  @ResponseBody
  @RequestMapping(value = "contactData")
  public Map<String, Object> contactData(@RequestParam(required = false) String userId, HttpServletResponse response) {
    SPmContactSettings sPmContactSettings = new SPmContactSettings();
    /*String proId = dqRecordService.findByUserID(userId, "1");
    SPmBasic sPmBasic = sPmBasicService.getByproId(proId);
    sPmContactSettings.setContactNum(userId);
    sPmContactSettings.setName(sPmBasic.getName());
    sPmContactSettings.setSex(sPmBasic.getSex());
    sPmContactSettings.setSexName(DictUtils.getDictLabel(sPmBasic.getSex(), "XB", ""));
    sPmContactSettings.setOriginPlace(sPmBasic.getOriginPlace());
    sPmContactSettings.setBirthTime(sPmBasic.getBirthTime());
    sPmContactSettings.setNation(sPmBasic.getNation());
    SPmAppSituation sPmAppSituation = sPmAppSituationService.getByproId(proId);
    sPmContactSettings.setJionTime(sPmAppSituation.getJionTime());
    sPmContactSettings.setEducationDegree(sPmBasic.getEducation());
    sPmContactSettings.setPostPlace(sPmBasic.getWorkPlace());*/

    User user = UserUtils.getUser();
    sPmContactSettings.setPartyBranch(user.getOffice().getName());

    Map<String, Object> contactMap = Maps.newHashMap();
    contactMap.put("sPmContactSettings", sPmContactSettings);
    return contactMap;
  }

  @RequestMapping(value = "contactList")
  public String selectList(Model model) {
    return "modules/partyManage/activisttrain/contactList";
  }
}