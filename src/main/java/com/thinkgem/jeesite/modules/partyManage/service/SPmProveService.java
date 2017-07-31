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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmProve;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmProveDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是证明材料表Service
 * @author one
 * @version 2017-04-21
 */
@Service
@Transactional(readOnly = true)
public class SPmProveService extends CrudService<SPmProveDao, SPmProve> {

	@Autowired
	private DQRecordService dqRecordService;
	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;
	
	public SPmProve get(String id) {
		return super.get(id);
	}
	
	public List<SPmProve> findList(SPmProve sPmProve) {
		return super.findList(sPmProve);
	}
	
	public Page<SPmProve> findPage(Page<SPmProve> page, SPmProve sPmProve) {
		return super.findPage(page, sPmProve);
	}
	
	@Transactional(readOnly = false)
	public String save(SPmProve sPmProve, String proId) {
		if (proId != null && proId != "") {
			SPmProve sPmProves = dao.getByproId(proId);
		      if (sPmProves == null) {
		    	  sPmProve.setProId(proId);
		        super.save(sPmProve);
		      } else {
		    	  sPmProve.setId(sPmProves.getId());
		    	  sPmProve.setProId(proId);
		        super.save(sPmProve);
		      }
		      return "success";
		    }
		    return "error proId is not found";
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmProve sPmProve) {
		super.delete(sPmProve);
	}

	public SPmProve getByproId(String proId) {
		
		return dao.getByproId(proId);
	}

	public SPmProve getForms(SPmProve sPmProve) {
		SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmProve.getId());
		User user = UserUtils.getUser();
		if(sPmJionFiles == null){
			sPmProve.setUploader(user.getName());
			sPmProve.setUploadTime(new Date());
		}else{
			sPmProve.setFileUrl(sPmJionFiles.getFileUrl());
			sPmProve.setFileName(sPmJionFiles.getFileName());
			sPmProve.setUploader(sPmJionFiles.getUploader());
			sPmProve.setUploadTime(sPmJionFiles.getUploadTime());
		}
	    return sPmProve;
		
	}
	
}