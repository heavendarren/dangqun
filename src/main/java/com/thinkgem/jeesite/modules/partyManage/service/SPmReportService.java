/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMass;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmReport;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmReportDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是政审报告表Service
 * @author one
 * @version 2017-04-21
 */
@Service
@Transactional(readOnly = true)
public class SPmReportService extends CrudService<SPmReportDao, SPmReport> {

	@Autowired
	private DQRecordService dqRecordService;
	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;
	
	public SPmReport get(String id) {
		return super.get(id);
	}
	
	public List<SPmReport> findList(SPmReport sPmReport) {
		return super.findList(sPmReport);
	}
	
	public Page<SPmReport> findPage(Page<SPmReport> page, SPmReport sPmReport) {
		return super.findPage(page, sPmReport);
	}
	
	@Transactional(readOnly = false)
	public String save(SPmReport sPmReport, String proId) {
		if (proId != null && proId != "") {
			SPmReport sPmReports = dao.getByproId(proId);
		      if (sPmReports == null) {
		    	  sPmReport.setProId(proId);
		        super.save(sPmReport);
		      } else {
		    	  sPmReport.setId(sPmReports.getId());
		    	  sPmReport.setProId(proId);
		        super.save(sPmReport);
		      }
		      return "success";
		    }
		    return "error proId is not found";
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmReport sPmReport) {
		super.delete(sPmReport);
	}

	public SPmReport getByproId(String proId) {
		return dao.getByproId(proId);
	}

	public SPmReport getForms(SPmReport sPmReport) {
		SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmReport.getId());
		User user = UserUtils.getUser();
		if(sPmJionFiles == null){
			sPmReport.setUploader(user.getName());
			sPmReport.setUploadTime(new Date());
		}else{
			sPmReport.setFileUrl(sPmJionFiles.getFileUrl());
			sPmReport.setFileName(sPmJionFiles.getFileName());
			sPmReport.setUploader(sPmJionFiles.getUploader());
			sPmReport.setUploadTime(sPmJionFiles.getUploadTime());
		}
	    return sPmReport;
		
	}
	
}