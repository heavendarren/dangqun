/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmProbationary;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmProbationaryDao;

/**
 * 这是预备党员考察纪实表Service
 * @author one
 * @version 2017-04-28
 */
@Service
@Transactional(readOnly = true)
public class SPmProbationaryService extends CrudService<SPmProbationaryDao, SPmProbationary> {

	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;


	public SPmProbationary get(String id) {
		return super.get(id);
	}

	public List<SPmProbationary> findList(SPmProbationary sPmProbationary) {
		return super.findList(sPmProbationary);
	}

	public Page<SPmProbationary> findPage(Page<SPmProbationary> page, SPmProbationary sPmProbationary) {
		return super.findPage(page, sPmProbationary);
	}

	@Transactional(readOnly = false)
	public String save(SPmProbationary sPmProbationary, String proId) {
		if (proId != null && proId != "") {
			SPmProbationary sPmProbationarys = dao.getByproId(proId);
			if (sPmProbationarys == null) {
				sPmProbationary.setProId(proId);
				super.save(sPmProbationary);
			} else {
				sPmProbationary.setId(sPmProbationarys.getId());
				sPmProbationary.setProId(proId);
				super.save(sPmProbationary);
			}
			return "success";
		}
		return "error proId is not found";
	}

	public SPmProbationary getForms(SPmProbationary sPmProbationary) {
		SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmProbationary.getProId());
		User user = UserUtils.getUser();
		if (sPmJionFiles == null) {
			sPmProbationary.setUploader(user.getName());
			sPmProbationary.setUploadTime(new Date());
		} else {
			sPmProbationary.setFileUrl(sPmJionFiles.getFileUrl());
			sPmProbationary.setFileName(sPmJionFiles.getFileName());
			sPmProbationary.setUploader(sPmJionFiles.getUploader());
			sPmProbationary.setUploadTime(sPmJionFiles.getUploadTime());
		}
		return sPmProbationary;
	}

	@Transactional(readOnly = false)
	public void delete(SPmProbationary sPmProbationary) {
		super.delete(sPmProbationary);
	}

	@Transactional(readOnly = false)
	public SPmProbationary getByproId(String proId) {
		return dao.getByproId(proId);
	}

	@Transactional(readOnly = false)
	public SPmProbationary getByProIdType(String proId,String type) {
		return dao.getByProIdType(proId,type);
	}

}