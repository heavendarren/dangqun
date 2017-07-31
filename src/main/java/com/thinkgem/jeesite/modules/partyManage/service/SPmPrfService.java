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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMass;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPrf;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmPrfDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是群团推荐表Service
 * @author one
 * @version 2017-04-17
 */
@Service
@Transactional(readOnly = true)
public class SPmPrfService extends CrudService<SPmPrfDao, SPmPrf> {
	
	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;
	@Autowired
	 private DQRecordDao redao;
	@Autowired
	private DQRecordService dqRecordService;

	public SPmPrf get(String id) {
		return super.get(id);
	}
	
	public List<SPmPrf> findList(SPmPrf sPmPrf) {
		return super.findList(sPmPrf);
	}
	
	public Page<SPmPrf> findPage(Page<SPmPrf> page, SPmPrf sPmPrf) {
		return super.findPage(page, sPmPrf);
	}
	
	@Transactional(readOnly = false)
	public void save(SPmPrf sPmPrf) {
		super.save(sPmPrf);
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmPrf sPmPrf) {
		super.delete(sPmPrf);
	}

	public SPmPrf getByproId(String proId) {
		return dao.getByproId(proId);
	}

	public String save(SPmPrf sPmPrf, String proId) {
		if (proId != null && proId != "") {
			SPmPrf sPmPrfs = dao.getByproId(proId);
		      if (sPmPrfs == null) {
		    	  sPmPrfs.setProId(proId);
		        super.save(sPmPrf);
		      } else {
		    	  sPmPrf.setId(sPmPrfs.getId());
		    	  sPmPrf.setProId(proId);
		        super.save(sPmPrf);
		      }
		      return "success";
		    }
		    return "error proId is not found";

	}

	public SPmPrf getForms(SPmPrf sPmPrf) {
		SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmPrf.getId());
		User user = UserUtils.getUser();
		if(sPmJionFiles == null){
			sPmPrf.setUploader(user.getName());
			sPmPrf.setUploadTime(new Date());
		}else{
			sPmPrf.setFileUrl(sPmJionFiles.getFileUrl());
			sPmPrf.setFileName(sPmJionFiles.getFileName());
			sPmPrf.setUploader(sPmJionFiles.getUploader());
			sPmPrf.setUploadTime(sPmJionFiles.getUploadTime());
		}
	    return sPmPrf;
		
	}
	
}