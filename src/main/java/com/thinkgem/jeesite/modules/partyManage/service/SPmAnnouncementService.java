package com.thinkgem.jeesite.modules.partyManage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAnnouncement;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPublicInformation;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmAnnouncementDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;

/**
 * 发展对象公示Service
 * @author psy
 * @version 2017-04-24
 */
@Service
@Transactional(readOnly = true)
public class SPmAnnouncementService extends CrudService<SPmAnnouncementDao, SPmAnnouncement> {

	@Autowired
	private DQRecordService dqRecordService;

	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;

	public SPmAnnouncement get(String id) {
		return super.get(id);
	}

	public List<SPmAnnouncement> findList(SPmAnnouncement sPmAnnouncement) {
		return super.findList(sPmAnnouncement);
	}

	public Page<SPmAnnouncement> findPage(Page<SPmAnnouncement> page, SPmAnnouncement sPmAnnouncement) {
		return super.findPage(page, sPmAnnouncement);
	}

	public Page<SPmAnnouncement> findPage(Page<SPmAnnouncement> page, SPmAnnouncement sPmAnnouncement, String proId) {
		List<SPmAnnouncement> sPmAnnouncementList = new ArrayList<SPmAnnouncement>();
		sPmAnnouncement.setPage(page);
	    if (proId != null) {
	    	sPmAnnouncement = dao.getByproId(proId);
	      if (sPmAnnouncement != null) {
	        SPmJionFiles sPmJionFiles = new SPmJionFiles();
	        sPmJionFiles.setFormId(sPmAnnouncement.getId());
	        List<SPmJionFiles> sPmJionFilesList = sPmJionFilesDao.findLists(sPmJionFiles);
	        for (SPmJionFiles sPmJionFile : sPmJionFilesList) {
	        	SPmAnnouncement sPmAnnouncements = new SPmAnnouncement();
	        	sPmAnnouncements.setFileUrl(sPmJionFile.getFileUrl());
	        	sPmAnnouncements.setFileName(sPmJionFile.getFileName());
	        	sPmAnnouncements.setUploader(sPmJionFile.getUploader());
	        	sPmAnnouncements.setUploadTime(sPmJionFile.getUploadTime());
	        	sPmAnnouncementList.add(sPmAnnouncements);
	        }
	      } else {
	    	  sPmAnnouncement = new SPmAnnouncement();
	    	  sPmAnnouncement.setProId(proId);
	        sPmAnnouncementList.add(sPmAnnouncement);
	      }
	    } else {
	    	sPmAnnouncementList.add(sPmAnnouncement);
	    }
	    page.setList(sPmAnnouncementList);
	    return page;
	  }

	@Transactional(readOnly = false)
	public void save(SPmAnnouncement sPmAnnouncement) {
		super.save(sPmAnnouncement);
	}
	
	public String save(SPmAnnouncement sPmAnnouncement, String proId, String fileUrl, String fileName) {
	    SPmJionFiles sPmJionFiles = new SPmJionFiles();
	    sPmJionFiles.preInsert();
	    sPmJionFiles.setFileUrl(fileUrl);
	    sPmJionFiles.setFileName(fileName);
	    sPmJionFiles.setUploader(sPmAnnouncement.getUploader());
	    sPmJionFiles.setUploadTime(sPmAnnouncement.getUploadTime());
	    sPmJionFiles.setFormName("发展公示信息");
	    if (proId != null && proId != "") {
	    	SPmAnnouncement sPmAnnouncements = dao.getByproId(proId);
	      if (sPmAnnouncements == null) {
	    	  sPmAnnouncement.setProId(proId);
	        super.save(sPmAnnouncement);
	        sPmJionFiles.setFormId(sPmAnnouncement.getId());
	        dqRecordService.fillIn(proId);
	      } else {
	    	  sPmAnnouncement.setId(sPmAnnouncements.getId());
	        super.save(sPmAnnouncement);
	        sPmJionFiles.setFormId(sPmAnnouncement.getId());
	        dqRecordService.fillIn(proId);
	      }
	      sPmJionFiles.setFormId(sPmAnnouncement.getId());
	      sPmJionFilesDao.insert(sPmJionFiles);
	      return "success";
	    }
	    return "error proId is not found";
	  }

	@Transactional(readOnly = false)
	public void delete(SPmAnnouncement sPmAnnouncement) {
		super.delete(sPmAnnouncement);
	}

}