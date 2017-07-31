package com.thinkgem.jeesite.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.CodeTree;
import com.thinkgem.jeesite.modules.sys.service.CodeTreeService;

@Controller
@RequestMapping(value = "${adminPath}/sys/codeTree")
public class CodeTreeController extends BaseController {

	@Autowired
	private CodeTreeService codeTreeService;

	@ModelAttribute("codeTree")
	public CodeTree get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return codeTreeService.get(id);
		} else {
			return new CodeTree();
		}
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(
			@RequestParam(required = true) String type,
			@RequestParam(required = false, defaultValue = "root") String pId,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<CodeTree> list = codeTreeService.findListByType(type, pId);
		for (int i = 0; i < list.size(); i++) {
			CodeTree e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentId());
			map.put("code", e.getCode());
			map.put("name", e.getName());
			map.put("isParent", Integer.valueOf(e.getLeaf()));
			map.put("canSelect", e.getCanSelect());
			mapList.add(map);
		}
		return mapList;
	}
}
