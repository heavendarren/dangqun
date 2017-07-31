/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.service.SPmJionFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 文件表Controller
 * @author zhc
 * @version 2017-04-20
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmJionFiles")
public class SPmJionFilesController extends BaseController {

	@Autowired
	private SPmJionFilesService sPmJionFilesService;
	
	@ModelAttribute
	public SPmJionFiles get(@RequestParam(required=false) String id) {
		SPmJionFiles entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sPmJionFilesService.get(id);
		}
		if (entity == null){
			entity = new SPmJionFiles();
		}
		return entity;
	}

//	@RequiresPermissions("partyManage:sPmJionFiles:edit")
	@RequestMapping(value = "save")
	public String save(SPmJionFiles sPmJionFiles, Model model, RedirectAttributes redirectAttributes) {
		sPmJionFilesService.save(sPmJionFiles);
		addMessage(redirectAttributes, "保存文件表成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmJionFiles/?repage";
	}
	
//	@RequiresPermissions("partyManage:sPmJionFiles:edit")
	@RequestMapping(value = "delete")
	public String delete(SPmJionFiles sPmJionFiles, RedirectAttributes redirectAttributes) {
		sPmJionFilesService.delete(sPmJionFiles);
		addMessage(redirectAttributes, "删除文件表成功");
		return "redirect:"+Global.getAdminPath()+"/partyManage/sPmJionFiles/?repage";
	}

}