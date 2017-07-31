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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAuto;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMass;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmAutoDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是自传表Service
 * @author one
 * @version 2017-04-21
 */
@Service
@Transactional(readOnly = true)
public class SPmAutoService extends CrudService<SPmAutoDao, SPmAuto> {

	@Autowired
	private DQRecordService dqRecordService;
	
	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;
	
	public SPmAuto get(String id) {
		return super.get(id);
	}
	
	public List<SPmAuto> findList(SPmAuto sPmAuto) {
		return super.findList(sPmAuto);
	}
	
	public Page<SPmAuto> findPage(Page<SPmAuto> page, SPmAuto sPmAuto) {
		return super.findPage(page, sPmAuto);
	}
	
	@Transactional(readOnly = false)
	public String save(SPmAuto sPmAuto, String proId) {
		if (proId != null && proId != "") {
			SPmAuto sPmAutos = dao.getByproId(proId);
		      if (sPmAutos == null) {
		    	  sPmAuto.setProId(proId);
		        super.save(sPmAuto);
		      } else {
		    	  sPmAuto.setId(sPmAutos.getId());
		    	  sPmAuto.setProId(proId);
		        super.save(sPmAuto);
		      }
		      return "success";
		    }
		    return "error proId is not found";
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmAuto sPmAuto) {
		super.delete(sPmAuto);
	}

	public SPmAuto getByproId(String proId) {
		return dao.getByproId(proId);
	}

	public SPmAuto getForms(SPmAuto sPmAuto) {
		SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmAuto.getId());
		User user = UserUtils.getUser();
		if(sPmJionFiles == null){
			sPmAuto.setUploader(user.getName());
			sPmAuto.setUploadTime(new Date());
		}else{
			sPmAuto.setFileUrl(sPmJionFiles.getFileUrl());
			sPmAuto.setFileName(sPmJionFiles.getFileName());
			sPmAuto.setUploader(sPmJionFiles.getUploader());
			sPmAuto.setUploadTime(sPmJionFiles.getUploadTime());
		}
	    return sPmAuto;
		
	}
	
}