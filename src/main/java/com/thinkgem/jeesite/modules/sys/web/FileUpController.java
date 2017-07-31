package com.thinkgem.jeesite.modules.sys.web;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Plupload;
import com.thinkgem.jeesite.modules.sys.utils.PluploadUtil;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/sys/fileUp")
public class FileUpController extends BaseController {
	public static final String FileDir = "uploadfile" + File.separator;

	@RequiresPermissions("user")
	@RequestMapping(value = { "demo" })
	public String demo(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "jeesite/test/fileUp";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "upFile")
	public Map<String, Object> upload(Plupload plupload,
			HttpServletRequest request, HttpServletResponse response) {

		plupload.setRequest(request);
		// 文件存储路径
		File dir = new File(plupload.getRequest().getSession()
				.getServletContext().getRealPath("/")
				+ FileDir + UserUtils.getUser().getId());
		Map<String, Object> m = Maps.newHashMap();
		m.put("status", false);
		try {
			// 上传文件
			PluploadUtil.upload(plupload, dir);
			// 判断文件是否上传成功（被分成块的文件是否全部上传完成）
			if (PluploadUtil.isUploadFinish(plupload)) {
				m.put("status", true);
				m.put("fileUrl", plupload.getTargetName());
			}

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return m;
	}
}
