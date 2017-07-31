package com.thinkgem.jeesite.modules.partyManage.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmContactSettings;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmParthMenber;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmAppSituationService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmBasicService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmJobresService;
import com.thinkgem.jeesite.modules.partyManage.service.SPmParthMenberService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 确定入党介绍人Controller
 * @author psy
 * @version 2017-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmParthMenber")
public class SPmParthMenberController extends BaseController {

	@Autowired
	private SPmParthMenberService sPmParthMenberService;
	
	@Autowired
	private DQRecordService dqRecordService;
	
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
	public SPmParthMenber get(@RequestParam(required=false) String id) {
		SPmParthMenber entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmParthMenberService.get(id);
		}
		if (entity == null){
			entity = new SPmParthMenber();
		}
		return entity;
	}
	
	//@RequiresPermissions("partyManage:sPmParthMenber:view")
	@RequestMapping(value = "list")
	 public String list(String proId, SPmParthMenber sPmParthMenber, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	    }
	    Page<SPmParthMenber> page = sPmParthMenberService.findPage(new Page<SPmParthMenber>(request, response), sPmParthMenber, user.getProId());
	    model.addAttribute("page", page);
		return "modules/partyManage/sPmParthMenberList";
	}
	
	
	@RequestMapping(value = "detail")
	  public String detail(String proId, SPmParthMenber sPmParthMenber, HttpServletRequest request, HttpServletResponse response, Model model) {
	    User user = UserUtils.getUser();
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	    }
	    Page<SPmParthMenber> page = sPmParthMenberService.findPage(new Page<SPmParthMenber>(request, response), sPmParthMenber, user.getProId());
	    model.addAttribute("page", page);
	    return "modules/partyManage/sPmParthMenberListDetails";
	  }
	
	
	//@RequiresPermissions("partyManage:sPmParthMenber:view")
	@RequestMapping(value = "form")
	public String form(SPmParthMenber sPmParthMenber, Model model) {
		model.addAttribute("sPmParthMenber", sPmParthMenber);
		return "modules/partyManage/sPmParthMenberForm";
	}

	//@RequiresPermissions("partyManage:sPmParthMenber:edit")
	@RequestMapping(value = "save")
	public String save(SPmParthMenber sPmParthMenber, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sPmParthMenber)) {
		      return form(sPmParthMenber, model);
		    }
		    User user = UserUtils.getUser();
		    String status = sPmParthMenberService.save(sPmParthMenber, user.getProId());
		    if (status == "success") {
		      addMessage(redirectAttributes, "保存成功");
		      model.addAttribute("sPmParthMenber", sPmParthMenber);
		      return "redirect:" + Global.getAdminPath()
		              + "/partyManage/sPmParthMenber/detail";
		    } else {
		      addMessage(redirectAttributes, status);
	      return "redirect:" + Global.getAdminPath()
	              + "/partyManage/sPmParthMenber/detail";
	    }
	  }
	//@RequiresPermissions("partyManage:sPmParthMenber:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmParthMenber sPmParthMenber, RedirectAttributes redirectAttributes) {
		sPmParthMenberService.delete(sPmParthMenber);
		addMessage(redirectAttributes, "删除确定入党介绍人成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmParthMenber/form";
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
		  SPmParthMenber sPmParthMenber = new SPmParthMenber();
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
	    sPmParthMenber.setPartyBranch(user.getOffice().getName());

	    Map<String, Object> contactMap = Maps.newHashMap();
	    contactMap.put("sPmParthMenber", sPmParthMenber);
	    return contactMap;
	  }

	  @RequestMapping(value = "contactList")
	  public String selectList(Model model) {
	    return "modules/partyManage/activisttrain/contactList";
	  }
	
}