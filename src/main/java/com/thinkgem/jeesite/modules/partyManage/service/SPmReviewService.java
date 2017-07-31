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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmReview;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmReviewDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是发展党员工作预审表Service
 * @author one
 * @version 2017-04-24
 */
@Service
@Transactional(readOnly = true)
public class SPmReviewService extends CrudService<SPmReviewDao, SPmReview> {

	@Autowired
	private DQRecordService dqRecordService;
	
	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;
	
	public SPmReview get(String id) {
		return super.get(id);
	}
	
	public List<SPmReview> findList(SPmReview sPmReview) {
		return super.findList(sPmReview);
	}
	
	public Page<SPmReview> findPage(Page<SPmReview> page, SPmReview sPmReview) {
		return super.findPage(page, sPmReview);
	}
	
	@Transactional(readOnly = false)
	public String save(SPmReview sPmReview, String proId) {
		if (proId != null && proId != "") {
			SPmReview sPmReviews = dao.getByproId(proId);
		      if (sPmReviews == null) {
		    	  sPmReview.setProId(proId);
		        super.save(sPmReview);
		        dqRecordService.fillIn(proId);
		      } else {
		    	  sPmReview.setId(sPmReviews.getId());
		    	  sPmReview.setProId(proId);
		        super.save(sPmReview);
		        dqRecordService.fillIn(proId);
		      }
		      return "success";
		    }
		    return "error proId is not found";
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmReview sPmReview) {
		super.delete(sPmReview);
	}

	public SPmReview getByproId(String proId) {
		return dao.getByproId(proId);
	}

	public SPmReview getForms(SPmReview sPmReview) {
		SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmReview.getId());
		User user = UserUtils.getUser();
		if(sPmJionFiles == null){
			sPmReview.setUploader(user.getName());
			sPmReview.setUploadTime(new Date());
		}else{
			sPmReview.setFileUrl(sPmJionFiles.getFileUrl());
			sPmReview.setFileName(sPmJionFiles.getFileName());
			sPmReview.setUploader(sPmJionFiles.getUploader());
			sPmReview.setUploadTime(sPmJionFiles.getUploadTime());
		}
	    return sPmReview;
		
	}
	
}