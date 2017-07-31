/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmWorkPost;
import com.thinkgem.jeesite.modules.partyManage.service.SPmWorkPostService;
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
 * 工作岗位Controller
 *
 * @author zhc
 * @version 2017-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/sPmWorkPost")
public class SPmWorkPostController extends BaseController {

  @Autowired
  private SPmWorkPostService sPmWorkPostService;

  @ModelAttribute
  public SPmWorkPost get(@RequestParam(required = false) String id) {
    SPmWorkPost entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = sPmWorkPostService.get(id);
    }
    if (entity == null) {
      entity = new SPmWorkPost();
    }
    return entity;
  }

  //	@RequiresPermissions("partyManage:sPmWorkPost:view")
  @RequestMapping(value = "list")
  public String list(String proId, SPmWorkPost sPmWorkPost, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    Page<SPmWorkPost> page = sPmWorkPostService.findPage(new Page<SPmWorkPost>(request, response), sPmWorkPost, user.getProId());
    model.addAttribute("page", page);
    return "modules/partyManage/jionparty/sPmWorkPostList";
  }

  @RequestMapping(value = "detail")
  public String detail(String proId, SPmWorkPost sPmWorkPost, HttpServletRequest request, HttpServletResponse response, Model model) {
    User user = UserUtils.getUser();
    if (proId != null && proId != "") {
      user.setProId(proId);
    }
    Page<SPmWorkPost> page = sPmWorkPostService.findPage(new Page<SPmWorkPost>(request, response), sPmWorkPost, user.getProId());
    model.addAttribute("page", page);
    return "modules/partyManage/jionparty/sPmWorkPostListDetails";
  }

  //	@RequiresPermissions("partyManage:sPmWorkPost:view")
  @RequestMapping(value = "form")
  public String form(SPmWorkPost sPmWorkPost, Model model) {
    User user = UserUtils.getUser();
    sPmWorkPost.setOperator(user.getName());
    sPmWorkPost.setOperatorTime(new Date());
    model.addAttribute("sPmWorkPost", sPmWorkPost);
    return "modules/partyManage/jionparty/sPmWorkPostForm";
  }

  //	@RequiresPermissions("partyManage:sPmWorkPost:edit")
  @RequestMapping(value = "save")
  public String save(SPmWorkPost sPmWorkPost, Model model, RedirectAttributes redirectAttributes) {
    User user = UserUtils.getUser();
    if (!beanValidator(model, sPmWorkPost)) {
      return form(sPmWorkPost, model);
    }
    String status = sPmWorkPostService.save(sPmWorkPost, user.getProId());
    if (status == "success") {
      addMessage(redirectAttributes, "保存成功");
      model.addAttribute("sPmWorkPost", sPmWorkPost);
      return "redirect:" + Global.getAdminPath() +
              "/partyManage/sPmWorkPost/list";
    } else {
      addMessage(redirectAttributes, status);
      return "redirect:" + Global.getAdminPath()
              + "/partyManage/sPmWorkPost/list";
    }
  }

  @RequestMapping(value = "success")
  public String success(Model model) {
    return "modules/partyManage/success";
  }

  //	@RequiresPermissions("partyManage:sPmWorkPost:edit")
  @RequestMapping(value = "delete")
  public String delete(SPmWorkPost sPmWorkPost, RedirectAttributes redirectAttributes) {
    sPmWorkPostService.delete(sPmWorkPost);
    addMessage(redirectAttributes, "删除工作岗位成功");
    return "redirect:" + Global.getAdminPath() + "/partyManage/sPmWorkPost/list";
  }

}