package com.thinkgem.jeesite.modules.partyManage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAuditChecklist;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmIntroduceOpinion;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPublicInformation;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmAuditChecklistDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;

/**
 * 转正审查审核表Service
 * @author psy
 * @version 2017-04-26
 */
@Service
@Transactional(readOnly = true)
public class SPmAuditChecklistService extends CrudService<SPmAuditChecklistDao, SPmAuditChecklist> {

	@Autowired
	private DQRecordService dqRecordService;

	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;
	
	public SPmAuditChecklist get(String id) {
		return super.get(id);
	}
	
	public List<SPmAuditChecklist> findList(SPmAuditChecklist sPmAuditChecklist) {
		return super.findList(sPmAuditChecklist);
	}
	
	public SPmAuditChecklist getByproId(String proId) {
	    return dao.getByproId(proId);
	}
	
	public SPmAuditChecklist getForms(SPmAuditChecklist sPmAuditChecklist) {
		    SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmAuditChecklist.getId());
		    sPmAuditChecklist.setFileUrl(sPmJionFiles.getFileUrl());
		    sPmAuditChecklist.setFileName(sPmJionFiles.getFileName());
		    sPmAuditChecklist.setUploader(sPmJionFiles.getUploader());
		    sPmAuditChecklist.setUploadTime(sPmJionFiles.getUploadTime());
		    return sPmAuditChecklist;
	 }
	
	public Page<SPmAuditChecklist> findPage(Page<SPmAuditChecklist> page, SPmAuditChecklist sPmAuditChecklist) {
		return super.findPage(page, sPmAuditChecklist);
	}
	
	public Page<SPmAuditChecklist> findPage(Page<SPmAuditChecklist> page, SPmAuditChecklist sPmAuditChecklist, String proId) {
	    List<SPmAuditChecklist> sPmAuditChecklistList = new ArrayList<SPmAuditChecklist>();
//	    String proId = redao.findByUserID(userId, "23");
	    sPmAuditChecklist.setPage(page);
	    if (proId != null) {
	    	sPmAuditChecklist = dao.getByproId(proId);
	      if (sPmAuditChecklist != null) {
	    	  sPmAuditChecklist.setId(sPmAuditChecklist.getId());
	        SPmJionFiles sPmJionFiles = new SPmJionFiles();
	        sPmJionFiles.setFormId(sPmAuditChecklist.getId());
	        List<SPmJionFiles> sPmJionFilesList = sPmJionFilesDao.findLists(sPmJionFiles);
	        for (SPmJionFiles sPmJionFile : sPmJionFilesList) {
	        	sPmAuditChecklist.setFileUrl(sPmJionFile.getFileUrl());
	        	sPmAuditChecklist.setFileName(sPmJionFile.getFileName());
	        	sPmAuditChecklist.setUploader(sPmJionFile.getUploader());
	        	sPmAuditChecklist.setUploadTime(sPmJionFile.getUploadTime());
	        	sPmAuditChecklistList.add(sPmAuditChecklist);
	        }
	      } /*else {
	    	  sPmAuditChecklist = new SPmAuditChecklist();
	    	  sPmAuditChecklist.setProId(proId);
	    	  sPmAuditChecklistList.add(sPmAuditChecklist);
	      }*/
	    } else {
	    	sPmAuditChecklistList.add(sPmAuditChecklist);
	    }
	    page.setList(sPmAuditChecklistList);
	    return page;
	  }
	
	public String save(SPmAuditChecklist sPmAuditChecklist, String proId) {
	    SPmJionFiles sPmJionFiles = new SPmJionFiles();
	    sPmJionFiles.preInsert();
	    sPmJionFiles.setFileUrl(sPmAuditChecklist.getFileUrl());
	    sPmJionFiles.setFileName(sPmAuditChecklist.getFileName());
	    sPmJionFiles.setUploader(sPmAuditChecklist.getUploader());
	    sPmJionFiles.setUploadTime(sPmAuditChecklist.getUploadTime());
	    sPmJionFiles.setFormName("转正审查审核");
//			if (userId != null && userId != "") {return "error userId is null";}
//				String proId = redao.findByUserID(userId, "25");
	    if (proId != null && proId != "") {
	    	SPmAuditChecklist sPmAuditChecklists = dao.getByproId(proId);
	      if (sPmAuditChecklists == null) {
	    	  sPmAuditChecklist.setProId(proId);
	        super.save(sPmAuditChecklist);
	        sPmJionFiles.setFormId(sPmAuditChecklist.getId());
	        sPmJionFilesDao.insert(sPmJionFiles);
	        return "success";
	      } else {
	    	  sPmAuditChecklist.setId(sPmAuditChecklists.getId());
	        super.save(sPmAuditChecklist);
	        sPmJionFiles.setFormId(sPmAuditChecklist.getId());
	        SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmAuditChecklist.getId());
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
	public void save(SPmAuditChecklist sPmAuditChecklist) {
		super.save(sPmAuditChecklist);
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmAuditChecklist sPmAuditChecklist) {
		super.delete(sPmAuditChecklist);
	}
	
}