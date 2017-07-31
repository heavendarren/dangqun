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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJustPartyPublic;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJustPartyPublicDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是拟转正公示表Service
 * @author one
 * @version 2017-04-28
 */
@Service
@Transactional(readOnly = true)
public class SPmJustPartyPublicService extends CrudService<SPmJustPartyPublicDao, SPmJustPartyPublic> {

	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;
	@Autowired
	private DQRecordService dqRecordService;
	
	public SPmJustPartyPublic get(String id) {
		return super.get(id);
	}
	
	public List<SPmJustPartyPublic> findList(SPmJustPartyPublic sPmJustPartyPublic) {
		return super.findList(sPmJustPartyPublic);
	}
	
	public Page<SPmJustPartyPublic> findPage(Page<SPmJustPartyPublic> page, SPmJustPartyPublic sPmJustPartyPublic) {
		return super.findPage(page, sPmJustPartyPublic);
	}
	
	@Transactional(readOnly = false)
	public void save(SPmJustPartyPublic sPmJustPartyPublic) {
		super.save(sPmJustPartyPublic);
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmJustPartyPublic sPmJustPartyPublic) {
		super.delete(sPmJustPartyPublic);
	}
	public SPmJustPartyPublic getForms(SPmJustPartyPublic sPmJustPartyPublic) {
		SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmJustPartyPublic.getId());
	    User user = UserUtils.getUser();
	    if (sPmJionFiles == null) {
	    	sPmJustPartyPublic.setUploader(user.getName());
	    	sPmJustPartyPublic.setUploadTime(new Date());
	    } else {
	    	sPmJustPartyPublic.setFileUrl(sPmJionFiles.getFileUrl());
	    	sPmJustPartyPublic.setFileName(sPmJionFiles.getFileName());
	    	sPmJustPartyPublic.setUploader(sPmJionFiles.getUploader());
	    	sPmJustPartyPublic.setUploadTime(sPmJionFiles.getUploadTime());
	    }
	    return sPmJustPartyPublic;
	  }
	public SPmJustPartyPublic getByproId(String proId) {
	    return dao.getByproId(proId);
	  }
	public String save(SPmJustPartyPublic sPmJustPartyPublic, String proId) {
	    SPmJionFiles sPmJionFiles = new SPmJionFiles();
	    sPmJionFiles.preInsert();
	    sPmJionFiles.setFileUrl(sPmJustPartyPublic.getFileUrl());
	    sPmJionFiles.setFileName(sPmJustPartyPublic.getFileName());
	    sPmJionFiles.setUploader(sPmJustPartyPublic.getUploader());
	    sPmJionFiles.setUploadTime(sPmJustPartyPublic.getUploadTime());
	    sPmJionFiles.setFormName("拟转正的公示");
//	    if (userId != null && userId != "") {return "error userId is null";}
//	      String proId = redao.findByUserID(userId, "16");
	    if (proId != null && proId != "") {
	      SPmJustPartyPublic sPmJustPartyPublics = dao.getByproId(proId);
	      if (sPmJustPartyPublics == null) {
	        sPmJustPartyPublic.setProId(proId);
	        super.save(sPmJustPartyPublic);
	        sPmJionFiles.setFormId(sPmJustPartyPublic.getId());
	        sPmJionFilesDao.insert(sPmJionFiles);
	        dqRecordService.fillIn(sPmJustPartyPublic.getProId());
	        return "success";
	      } else {
	        sPmJustPartyPublic.setId(sPmJustPartyPublics.getId());
	        super.save(sPmJustPartyPublic);
	        sPmJionFiles.setFormId(sPmJustPartyPublic.getId());
	        dqRecordService.fillIn(sPmJustPartyPublic.getProId());
	        SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmJustPartyPublic.getId());
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
//	            return "请先登录";
	  }
	
	
}