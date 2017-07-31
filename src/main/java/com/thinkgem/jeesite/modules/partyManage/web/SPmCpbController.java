package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.modules.partyManage.entity.*;
import com.thinkgem.jeesite.modules.partyManage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Created by zhc on 2017/4/17.
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmCpbController")
public class SPmCpbController {

    @RequestMapping(value = "/index")
    public String form(Model model) {
        return "modules/partyManage/sPmCpbForm";
    }

    @Autowired
    private SPmAutoService autoService;

    @RequestMapping(value = "sPmAuto/form")
    public String form(SPmAuto sPmAuto, Model model) {
        model.addAttribute("sPmAuto", sPmAuto);
        return "modules/partyManage/sPmAutoForm";
    }

    @RequestMapping(value = "sPmAuto/list")
    public String list(SPmAuto sPmAuto, Model model) {
        model.addAttribute("sPmAuto", sPmAuto);
        return "modules/partyManage/sPmAutoList";
    }

    @Autowired
    private SPmProveService sPmProveService;

    @RequestMapping(value = "sPmProve/form")
    public String form(SPmProve sPmProve, Model model) {
        model.addAttribute("sPmProve", sPmProve);
        return "modules/partyManage/sPmProveForm";
    }

    @RequestMapping(value = "sPmProve/list")
    public String list(SPmProve sPmProve, Model model) {
        model.addAttribute("sPmProve", sPmProve);
        return "modules/partyManage/sPmProveList";
    }

    @Autowired
    private SPmReportService sPmReportService;

    @RequestMapping(value = "sPmReport/form")
    public String form(SPmReport sPmReport, Model model) {
        model.addAttribute("sPmReport", sPmReport);
        return "modules/partyManage/sPmReportForm";
    }

    @RequestMapping(value = "sPmReport/list")
    public String list(SPmReport sPmReport, Model model) {
        model.addAttribute("sPmReport", sPmReport);
        return "modules/partyManage/sPmReportList";
    }

    @Autowired
    private SPmCheckService sPmCheckService;

    @RequestMapping(value = "sPmCheck/form")
    public String form(SPmCheck sPmCheck, Model model) {
        model.addAttribute("sPmCheck", sPmCheck);
        return "modules/partyManage/sPmCheckForm";
    }

    @RequestMapping(value = "sPmCheck/list")
    public String list(SPmCheck sPmCheck, Model model) {
        model.addAttribute("sPmCheck", sPmCheck);
        return "modules/partyManage/sPmCheckList";
    }


    public String save(Model model, RedirectAttributes redirectAttributes){

    	return "";
    }

}
