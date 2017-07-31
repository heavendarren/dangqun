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
@RequestMapping(value = "${adminPath}/partyManage/recomActive")
public class RecomActive {

    @RequestMapping(value = "/index")
    public String form(String proId, Model model) {
    	User user = UserUtils.getUser();
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	    }
        return "modules/partyManage/sPmActivistForm";
    }

    @Autowired
    private SPmMrsService mrsService;

    @RequestMapping(value = "sPmMrs/form")
    public String form(SPmMrs sPmMrs, Model model) {
        model.addAttribute("sPmMrs", sPmMrs);
        return "modules/partyManage/sPmMrsForm";
    }

    @RequestMapping(value = "sPmMrs/list")
    public String list(SPmMrs sPmMrs, Model model) {
        model.addAttribute("sPmMrs", sPmMrs);
        return "modules/partyManage/sPmMrsList";
    }

    @Autowired
    private SPmPrfService sPmPrfService;

    @RequestMapping(value = "sPmPrf/form")
    public String form(SPmPrf sPmPrf, Model model) {
        model.addAttribute("sPmPrf", sPmPrf);
        return "modules/partyManage/sPmPrfForm";
    }

    @RequestMapping(value = "sPmPrf/list")
    public String list(SPmPrf sPmPrf, Model model) {
        model.addAttribute("sPmPrf", sPmPrf);
        return "modules/partyManage/sPmPrfList";
    }

    @Autowired
    private SPmPmsService sPmPmsService;

    @RequestMapping(value = "sPmPms/form")
    public String form(SPmPms sPmPms, Model model) {
        model.addAttribute("sPmPms", sPmPms);
        return "modules/partyManage/sPmPmsForm";
    }

    @RequestMapping(value = "sPmPms/list")
    public String list(SPmPms sPmPms, Model model) {
        model.addAttribute("sPmPms", sPmPms);
        return "modules/partyManage/sPmPmsList";
    }

    @Autowired
    private SPmContentService sPmContentService;

    @RequestMapping(value = "sPmContent/form")
    public String form(SPmContent sPmContent, Model model) {
        model.addAttribute("sPmContent", sPmContent);
        return "modules/partyManage/sPmContentForm";
    }

    @RequestMapping(value = "sPmContent/list")
    public String list(SPmContent sPmContent, Model model) {
        model.addAttribute("sPmContent", sPmContent);
        return "modules/partyManage/sPmContentList";
    }


    public String save(Model model, RedirectAttributes redirectAttributes){

    	return "";
    }

}
