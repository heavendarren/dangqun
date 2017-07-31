package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.modules.partyManage.entity.*;
import com.thinkgem.jeesite.modules.partyManage.service.*;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Created by zhc on 2017/4/17.
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/political")
public class sPmPoliticalController {

    @RequestMapping(value = "/index")
    public String form(String proId,Model model) {
    	User user = UserUtils.getUser();
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	    }
        return "modules/partyManage/sPmPoliticalForm";
    }

    @Autowired
    private SPmAutoService sPmAutoService;

    @RequestMapping(value = "sPmAuto/form")
    public String form(SPmAuto sPmAuto, Model model) {
        model.addAttribute("sPmAuto", sPmAuto);
        return "modules/partyManage/sPmAutoForm";
    }

  /*  @RequestMapping(value = "sPmMrs/list")
    public String list(SPmMrs sPmMrs, Model model) {
        model.addAttribute("sPmMrs", sPmMrs);
        return "modules/partyManage/sPmMrsList";
    }*/

    @Autowired
    private SPmProveService sPmProveService;

    @RequestMapping(value = "sPmProve/form")
    public String form(SPmProve sPmProve, Model model) {
        model.addAttribute("sPmProve", sPmProve);
        return "modules/partyManage/sPmProveForm";
    }

    /*@RequestMapping(value = "sPmPrf/list")
    public String list(SPmPrf sPmPrf, Model model) {
        model.addAttribute("sPmPrf", sPmPrf);
        return "modules/partyManage/sPmPrfList";
    }*/

    @Autowired
    private SPmReportService sPmReportService;

    @RequestMapping(value = "sPmReport/form")
    public String form(SPmReport sPmReport, Model model) {
        model.addAttribute("sPmReport", sPmReport);
        return "modules/partyManage/sPmReportForm";
    }

    /*@RequestMapping(value = "sPmPms/list")
    public String list(SPmPms sPmPms, Model model) {
        model.addAttribute("sPmPms", sPmPms);
        return "modules/partyManage/sPmPmsList";
    }*/

    @Autowired
    private SPmCheckService sPmCheckService;

    @RequestMapping(value = "sPmCheck/form")
    public String form(SPmCheck sPmCheck, Model model) {
        model.addAttribute("sPmCheck", sPmCheck);
        return "modules/partyManage/sPmCheckForm";
    }

    /*@RequestMapping(value = "sPmContent/list")
    public String list(SPmContent sPmContent, Model model) {
        model.addAttribute("sPmContent", sPmContent);
        return "modules/partyManage/sPmContentList";
    }*/


    public String save(Model model, RedirectAttributes redirectAttributes){

    	return "";
    }

}
