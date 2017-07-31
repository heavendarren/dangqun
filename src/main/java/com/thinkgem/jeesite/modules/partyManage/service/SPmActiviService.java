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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmActivi;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMass;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmActiviDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是入党积极分子备案表Service
 * @author one
 * @version 2017-04-18
 */
@Service
@Transactional(readOnly = true)
public class SPmActiviService extends CrudService<SPmActiviDao, SPmActivi> {

	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;
	
	@Autowired
	private DQRecordService dqRecordService;
	
	public SPmActivi get(String id) {
		return super.get(id);
	}
	
	public List<SPmActivi> findList(SPmActivi sPmActivi) {
		return super.findList(sPmActivi);
	}
	
	public Page<SPmActivi> findPage(Page<SPmActivi> page, SPmActivi sPmActivi) {
		return super.findPage(page, sPmActivi);
	}
	
	@Transactional(readOnly = false)
	public String save(SPmActivi sPmActivi, String proId) {
		if (proId != null && proId != "") {
			SPmActivi sPmActivis = dao.getByproId(proId);
		      if (sPmActivis == null) {
		    	  sPmActivi.setProId(proId);
		        super.save(sPmActivi);
		      } else {
		    	  sPmActivi.setId(sPmActivis.getId());
		    	  sPmActivi.setProId(proId);
		        super.save(sPmActivi);
		      }
		      return "success";
		    }
		    return "error proId is not found";
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmActivi sPmActivi) {
		super.delete(sPmActivi);
	}

	public SPmActivi getByproId(String proId) {
		return dao.getByproId(proId);
	}

	public SPmActivi getForms(SPmActivi sPmActivi) {
		SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmActivi.getId());
		User user = UserUtils.getUser();
		if(sPmJionFiles == null){
			sPmActivi.setUploader(user.getName());
			sPmActivi.setUploadTime(new Date());
		}else{
			sPmActivi.setFileUrl(sPmJionFiles.getFileUrl());
			sPmActivi.setFileName(sPmJionFiles.getFileName());
			sPmActivi.setUploader(sPmJionFiles.getUploader());
			sPmActivi.setUploadTime(sPmJionFiles.getUploadTime());
		}
	    return sPmActivi;
		
		
	}
	
}