package com.thinkgem.jeesite.modules.partyManage.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAuditChecklist;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmDevelopmentOption;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmIntroduceOpinion;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMeetingMinutes;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPublicInformation;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmIntroduceOpinionDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 入党介绍人和党小组意见Service
 * @author psy
 * @version 2017-04-26
 */
@Service
@Transactional(readOnly = true)
public class SPmIntroduceOpinionService extends CrudService<SPmIntroduceOpinionDao, SPmIntroduceOpinion> {


	@Autowired
	private DQRecordService dqRecordService;

	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;

	public SPmIntroduceOpinion get(String id) {
		return super.get(id);
	}

	public List<SPmIntroduceOpinion> findList(SPmIntroduceOpinion sPmIntroduceOpinion) {
		return super.findList(sPmIntroduceOpinion);
	}

	public Page<SPmIntroduceOpinion> findPage(Page<SPmIntroduceOpinion> page, SPmIntroduceOpinion sPmIntroduceOpinion) {
		return super.findPage(page, sPmIntroduceOpinion);
	}
	
	public SPmIntroduceOpinion getByproId(String proId) {
	    return dao.getByproId(proId);
	  }
	
	 public SPmIntroduceOpinion getForms(SPmIntroduceOpinion sPmIntroduceOpinion) {
		    SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmIntroduceOpinion.getId());
		    sPmIntroduceOpinion.setFileUrl(sPmJionFiles.getFileUrl());
		    sPmIntroduceOpinion.setFileName(sPmJionFiles.getFileName());
		    sPmIntroduceOpinion.setUploader(sPmJionFiles.getUploader());
		    sPmIntroduceOpinion.setUploadTime(sPmJionFiles.getUploadTime());
		    return sPmIntroduceOpinion;
		  }
	 
	 public Page<SPmIntroduceOpinion> findPage(Page<SPmIntroduceOpinion> page, SPmIntroduceOpinion sPmIntroduceOpinion, String proId) {
		    List<SPmIntroduceOpinion> sPmIntroduceOpinionList = new ArrayList<SPmIntroduceOpinion>();
		    sPmIntroduceOpinion.setPage(page);
		    if (proId != null) {
		    	sPmIntroduceOpinion = dao.getByproId(proId);
		      if (sPmIntroduceOpinion != null) {
		    	  sPmIntroduceOpinion.setId(sPmIntroduceOpinion.getId());
		        SPmJionFiles sPmJionFiles = new SPmJionFiles();
		        sPmJionFiles.setFormId(sPmIntroduceOpinion.getId());
		        List<SPmJionFiles> sPmJionFilesList = sPmJionFilesDao.findLists(sPmJionFiles);
		        for (SPmJionFiles sPmJionFile : sPmJionFilesList) {
		        	sPmIntroduceOpinion.setFileUrl(sPmJionFile.getFileUrl());
		        	sPmIntroduceOpinion.setFileName(sPmJionFile.getFileName());
		        	sPmIntroduceOpinion.setUploader(sPmJionFile.getUploader());
		        	sPmIntroduceOpinion.setUploadTime(sPmJionFile.getUploadTime());
		        	sPmIntroduceOpinionList.add(sPmIntroduceOpinion);
		        }
		      } /*else {
		    	  sPmIntroduceOpinion = new SPmIntroduceOpinion();
		    	  sPmIntroduceOpinion.setProId(proId);
		    	  sPmIntroduceOpinionList.add(sPmIntroduceOpinion);
		      }*/
		    } else {
		    	sPmIntroduceOpinionList.add(sPmIntroduceOpinion);
		    }
		    page.setList(sPmIntroduceOpinionList);
		    return page;
		  }
	 
	@Transactional(readOnly = false)
	public void save(SPmIntroduceOpinion sPmIntroduceOpinion) {
		super.save(sPmIntroduceOpinion);
	}
	
	public String save(SPmIntroduceOpinion sPmIntroduceOpinion, String proId) {
		SPmJionFiles sPmJionFiles = new SPmJionFiles();
		sPmJionFiles.preInsert();
		sPmJionFiles.setFileUrl(sPmIntroduceOpinion.getFileUrl());
		sPmJionFiles.setFileName(sPmIntroduceOpinion.getFileName());
		sPmJionFiles.setUploader(sPmIntroduceOpinion.getUploader());
		sPmJionFiles.setUploadTime(sPmIntroduceOpinion.getUploadTime());
		sPmJionFiles.setFormName("入党介绍人和党小组意见");
		//			if (userId != null && userId != "") {return "error userId is null";}
		//				String proId = redao.findByUserID(userId, "25");
		if (proId != null && proId != "") {
			SPmIntroduceOpinion sPmIntroduceOpinions = dao.getByproId(proId);
			if (sPmIntroduceOpinions == null) {
				sPmIntroduceOpinion.setProId(proId);
				super.save(sPmIntroduceOpinion);
				sPmJionFiles.setFormId(sPmIntroduceOpinion.getId());
				sPmJionFilesDao.insert(sPmJionFiles);
				return "success";
			} else {
				sPmIntroduceOpinion.setId(sPmIntroduceOpinions.getId());
				super.save(sPmIntroduceOpinion);
				sPmJionFiles.setFormId(sPmIntroduceOpinion.getId());
				SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmIntroduceOpinion.getId());
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
	public void delete(SPmIntroduceOpinion sPmIntroduceOpinion) {
		super.delete(sPmIntroduceOpinion);
	}
}