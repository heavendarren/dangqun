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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAppUpload;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMrs;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmMrsDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是群众推荐汇总表Service
 * @author one
 * @version 2017-04-17
 */
@Service
@Transactional(readOnly = true)
public class SPmMrsService extends CrudService<SPmMrsDao, SPmMrs> {

	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;
	
	@Autowired
	private DQRecordService dqRecordService;

	public SPmMrs get(String id) {
		return super.get(id);
	}
	
	public List<SPmMrs> findList(SPmMrs sPmMrs) {
		return super.findList(sPmMrs);
	}
	
	public Page<SPmMrs> findPage(Page<SPmMrs> page, SPmMrs sPmMrs) {
		return super.findPage(page, sPmMrs);
	}
	
	@Transactional(readOnly = false)
	public String save(SPmMrs sPmMrs, String proId) {
		SPmJionFiles sPmJionFiles = new SPmJionFiles();
	    sPmJionFiles.preInsert();
	    sPmJionFiles.setFileUrl(sPmMrs.getFileUrl());
	    sPmJionFiles.setFileName(sPmMrs.getFileName());
	    sPmJionFiles.setUploader(sPmMrs.getUploader());
	    sPmJionFiles.setUploadTime(sPmMrs.getUploadTime());
	    sPmJionFiles.setFormName("群众推荐汇总表");
//	    if (userId != null && userId != "") {return "error userId is null";}
//	      String proId = redao.findByUserID(userId, "1");
	    if (proId != null && proId != "") {
	    	SPmMrs sPmMrss = dao.getByproId(proId);
	      if (sPmMrss == null) {
	    	  sPmMrs.setProId(proId);
	        super.save(sPmMrss);
	        sPmJionFiles.setFormId(sPmMrs.getId());
	        sPmJionFilesDao.insert(sPmJionFiles);
	        return "success";
	      } else {
	    	  sPmMrs.setId(sPmMrss.getId());
	        super.save(sPmMrs);
	        sPmJionFiles.setFormId(sPmMrs.getId());
	        SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmMrs.getId());
	        if (sPmJionFileses != null) {
	          sPmJionFiles.setId(sPmJionFileses.getId());
	          sPmJionFiles.preUpdate();
	          sPmJionFilesDao.update(sPmJionFiles);
	        } else {
	          sPmJionFilesDao.insert(sPmJionFiles);
	        }
	        return "success";
	      }
	    }
	      return "error proId is not found";
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmMrs sPmMrs) {
		super.delete(sPmMrs);
	}

	public SPmMrs getByproId(String proId) {
		return dao.getByproId(proId);
	}

	public SPmMrs getForms(SPmMrs sPmMrs) {
		SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmMrs.getId());
		User user = UserUtils.getUser();
		if(sPmJionFiles == null){
			sPmMrs.setUploader(user.getName());
			sPmMrs.setUploadTime(new Date());
		}else{
			sPmMrs.setFileUrl(sPmJionFiles.getFileUrl());
			sPmMrs.setFileName(sPmJionFiles.getFileName());
			sPmMrs.setUploader(sPmJionFiles.getUploader());
			sPmMrs.setUploadTime(sPmJionFiles.getUploadTime());
		}
	    return sPmMrs;
		
	}
	
}