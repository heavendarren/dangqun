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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPms;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmPmsDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是党员推荐汇总表Service
 * @author one
 * @version 2017-04-17
 */
@Service
@Transactional(readOnly = true)
public class SPmPmsService extends CrudService<SPmPmsDao, SPmPms> {

	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;
	
	@Autowired
	private DQRecordService dqRecordService;	
	
	public SPmPms get(String id) {
		return super.get(id);
	}
	
	public List<SPmPms> findList(SPmPms sPmPms) {
		return super.findList(sPmPms);
	}
	
	public Page<SPmPms> findPage(Page<SPmPms> page, SPmPms sPmPms) {
		return super.findPage(page, sPmPms);
	}
	
	@Transactional(readOnly = false)
	public String save(SPmPms sPmPms, String proId) {
		if (proId != null && proId != "") {
			SPmPms sPmPmss = dao.getByproId(proId);
		      if (sPmPmss == null) {
		    	  sPmPmss.setProId(proId);
		        super.save(sPmPms);
		      } else {
		    	  sPmPms.setId(sPmPmss.getId());
		    	  sPmPms.setProId(proId);
		        super.save(sPmPms);
		      }
		      return "success";
		    }
		    return "error proId is not found";
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmPms sPmPms) {
		super.delete(sPmPms);
	}

	public SPmPms getByproId(String proId) {
		return dao.getByproId(proId);
	}

	public SPmPms getForms(SPmPms sPmPms) {
		SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmPms.getId());
		User user = UserUtils.getUser();
		if(sPmJionFiles == null){
			sPmPms.setUploader(user.getName());
			sPmPms.setUploadTime(new Date());
		}else{
			sPmPms.setFileUrl(sPmJionFiles.getFileUrl());
			sPmPms.setFileName(sPmJionFiles.getFileName());
			sPmPms.setUploader(sPmJionFiles.getUploader());
			sPmPms.setUploadTime(sPmJionFiles.getUploadTime());
		}
	    return sPmPms;
	}
	
}