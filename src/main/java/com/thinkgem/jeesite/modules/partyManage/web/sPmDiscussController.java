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
@RequestMapping(value = "${adminPath}/partyManage/discuss")
public class sPmDiscussController {

    @RequestMapping(value = "/index")
    public String form(String proId, Model model) {
    	User user = UserUtils.getUser();
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	    }
        return "modules/partyManage/sPmDiscussForm";
    }

    @Autowired
    private SPmPmsSService sPmPmsSService;

    @RequestMapping(value = "sPmPmsS/form")
    public String form(SPmPmsS sPmPmsS, Model model) {
        model.addAttribute("sPmPmsS", sPmPmsS);
        return "modules/partyManage/sPmPmsSForm";
    }

    /*@RequestMapping(value = "sPmMass/list")
    public String list(SPmMass sPmMass, Model model) {
        model.addAttribute("sPmMass", sPmMass);
        return "modules/partyManage/sPmMassList";
    }*/

    @Autowired
    private SPmMinutesSService sPmMinutesSService;

    @RequestMapping(value = "sPmMinutesS/form")
    public String form(SPmMinutesS sPmMinutesS, Model model) {
        model.addAttribute("sPmMinutesS", sPmMinutesS);
        return "modules/partyManage/sPmMinutesSForm";
    }

    /*@RequestMapping(value = "sPmOpinion/list")
    public String list(SPmOpinion sPmOpinion, Model model) {
        model.addAttribute("sPmOpinion", sPmOpinion);
        return "modules/partyManage/sPmOpinionList";
    }*/

    @Autowired
    private SPmPbService sPmPbService;

    @RequestMapping(value = "sPmPb/form")
    public String form(SPmPb sPmPb, Model model) {
        model.addAttribute("sPmPb", sPmPb);
        return "modules/partyManage/sPmPbForm";
    }

   /* @RequestMapping(value = "sPmPb/list")
    public String list(SPmPb sPmPb, Model model) {
        model.addAttribute("sPmPb", sPmPb);
        return "modules/partyManage/sPmPbList";
    }*/

    @Autowired
    private SPmPcService sPmPcService;

    @RequestMapping(value = "sPmPc/form")
    public String form(SPmPc sPmPc, Model model) {
        model.addAttribute("sPmPc", sPmPc);
        return "modules/partyManage/sPmPbForm";
    }

    @Autowired
    private SPmTalkingSService sPmTalkingSService;

    @RequestMapping(value = "sPmTalkingS/form")
    public String form(SPmTalkingS sPmTalkingS, Model model) {
        model.addAttribute("sPmTalkingS", sPmTalkingS);
        return "modules/partyManage/sPmTalkingSForm";
    }
    
    
    public String save(Model model, RedirectAttributes redirectAttributes){

    	return "";
    }

}
