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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmBasic;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmCheck;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmCheckDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是发展对象政审表Service
 * @author one
 * @version 2017-04-21
 */
@Service
@Transactional(readOnly = true)
public class SPmCheckService extends CrudService<SPmCheckDao, SPmCheck> {

	@Autowired
	private DQRecordService dqRecordService;
	
	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;
	
	public SPmCheck get(String id) {
		return super.get(id);
	}
	
	public List<SPmCheck> findList(SPmCheck sPmCheck) {
		return super.findList(sPmCheck);
	}
	
	public Page<SPmCheck> findPage(Page<SPmCheck> page, SPmCheck sPmCheck) {
		return super.findPage(page, sPmCheck);
	}
	
	@Transactional(readOnly = false)
	public String save(SPmCheck sPmCheck,String proId) {
		if (proId != null && proId != "") {
			SPmCheck sPmChecks = dao.getByproId(proId);
		      if (sPmChecks == null) {
		    	  sPmCheck.setProId(proId);
		        super.save(sPmCheck);
		      } else {
		    	  sPmCheck.setId(sPmChecks.getId());
		    	  sPmCheck.setProId(proId);
		        super.save(sPmCheck);
		      }
		      return "success";
		    }
		    return "error proId is not found";
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmCheck sPmCheck) {
		super.delete(sPmCheck);
	}

	public SPmCheck getByproId(String proId) {
		return dao.getByproId(proId);
	}

	public SPmCheck getForms(SPmCheck sPmCheck) {
		SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmCheck.getId());
		User user = UserUtils.getUser();
		if(sPmJionFiles == null){
			sPmCheck.setUploader(user.getName());
			sPmCheck.setUploadTime(new Date());
		}else{
			sPmCheck.setFileUrl(sPmJionFiles.getFileUrl());
			sPmCheck.setFileName(sPmJionFiles.getFileName());
			sPmCheck.setUploader(sPmJionFiles.getUploader());
			sPmCheck.setUploadTime(sPmJionFiles.getUploadTime());
		}
	    return sPmCheck;
		
	}
	
}