package com.thinkgem.jeesite.modules.partyManage.web;

import javax.servlet.http.HttpServletRequest;

import com.thinkgem.jeesite.common.config.Global;
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
@RequestMapping(value = "${adminPath}/partyManage/develop")
public class sPmDevelopController {

    @RequestMapping(value = "/index")
    public String form(String proId, Model model) {
    	User user = UserUtils.getUser();
	    if (proId != null && proId != "") {
	      user.setProId(proId);
	    }
        return "modules/partyManage/sPmDevelopForm";
    }

    @Autowired
    private SPmMassService sPmMassService;

    @RequestMapping(value = "sPmMass/form")
    public String form(String proId, SPmMass sPmMass, Model model) {
    	User user = UserUtils.getUser();
	    sPmMass.setProId(user.getProId());
        model.addAttribute("sPmMass", sPmMass);
        return "modules/partyManage/sPmMassForm";
    }

    @RequestMapping(value = "sPmMass/save")
	public String save(SPmMass sPmMass, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
    	
		sPmMass.setProId(user.getProId());
		model.addAttribute("sPmMass", sPmMass);
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmMass/form";
	}
    
   

	@Autowired
    private SPmOpinionService sPmOpinionService;

    @RequestMapping(value = "sPmOpinion/form")
    public String form(SPmOpinion sPmOpinion, Model model) {
    	User user = UserUtils.getUser();
    	sPmOpinion.setProId(user.getProId());
        model.addAttribute("sPmOpinion", sPmOpinion);
        return "modules/partyManage/sPmOpinionForm";
    }

  /*  @RequestMapping(value = "sPmOpinion/list")
    public String list(SPmOpinion sPmOpinion, Model model) {
        model.addAttribute("sPmOpinion", sPmOpinion);
        return "modules/partyManage/sPmOpinionList";
    }*/

    @Autowired
    private SPmMinutesService sPmMinutesService;

    @RequestMapping(value = "sPmMinutes/form")
    public String form(SPmMinutes sPmMinutes, Model model) {
    	User user = UserUtils.getUser();
    	sPmMinutes.setProId(user.getProId());
        model.addAttribute("sPmMinutes", sPmMinutes);
        return "modules/partyManage/sPmMinutesForm";
    }

    /*@RequestMapping(value = "sPmMinutes/list")
    public String list(SPmMinutes sPmMinutes, Model model) {
        model.addAttribute("sPmMinutes", sPmMinutes);
        return "modules/partyManage/sPmMinutesList";
    }*/

    public String save(Model model, RedirectAttributes redirectAttributes){

    	return "";
    }

}
