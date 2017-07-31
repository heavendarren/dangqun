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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMassOpinion;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMeetingMinutes;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmMeetingMinutesDao;

/**
 * 征求党员意见会议纪要Service
 * @author psy
 * @version 2017-04-26
 */
@Service
@Transactional(readOnly = true)
public class SPmMeetingMinutesService extends CrudService<SPmMeetingMinutesDao, SPmMeetingMinutes> {

	@Autowired
	private DQRecordService dqRecordService;

	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;

	public SPmMeetingMinutes get(String id) {
		return super.get(id);
	}

	public List<SPmMeetingMinutes> findList(SPmMeetingMinutes sPmMeetingMinutes) {
		return super.findList(sPmMeetingMinutes);
	}

	public Page<SPmMeetingMinutes> findPage(Page<SPmMeetingMinutes> page, SPmMeetingMinutes sPmMeetingMinutes) {
		return super.findPage(page, sPmMeetingMinutes);
	}
	
	public Page<SPmMeetingMinutes> findPage(Page<SPmMeetingMinutes> page, SPmMeetingMinutes sPmMeetingMinutes, String proId) {
		List<SPmMeetingMinutes> sPmMeetingMinutesList = new ArrayList<SPmMeetingMinutes>();
		//	    String proId = redao.findByUserID(userId, "23");
		sPmMeetingMinutes.setPage(page);
		if (proId != null) {
			sPmMeetingMinutes = dao.getByproId(proId);
			if (sPmMeetingMinutes != null) {
				sPmMeetingMinutes.setId(sPmMeetingMinutes.getId());
				SPmJionFiles sPmJionFiles = new SPmJionFiles();
				sPmJionFiles.setFormId(sPmMeetingMinutes.getId());
				List<SPmJionFiles> sPmJionFilesList = sPmJionFilesDao.findLists(sPmJionFiles);
				for (SPmJionFiles sPmJionFile : sPmJionFilesList) {
					sPmMeetingMinutes.setFileUrl(sPmJionFile.getFileUrl());
					sPmMeetingMinutes.setFileName(sPmJionFile.getFileName());
					sPmMeetingMinutes.setUploader(sPmJionFile.getUploader());
					sPmMeetingMinutes.setUploadTime(sPmJionFile.getUploadTime());
					sPmMeetingMinutesList.add(sPmMeetingMinutes);
				}
			} /*else {
				sPmMassOpinion = new SPmMassOpinion();
				sPmMassOpinion.setProId(proId);
				sPmMassOpinionList.add(sPmMassOpinion);
			}*/
		} else {
			sPmMeetingMinutesList.add(sPmMeetingMinutes);
		}
		page.setList(sPmMeetingMinutesList);
		return page;
	}

	public SPmMeetingMinutes getByproId(String proId) {
		return dao.getByproId(proId);
	}

	public SPmMeetingMinutes getForms(SPmMeetingMinutes sPmMeetingMinutes) {
		SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmMeetingMinutes.getId());
		sPmMeetingMinutes.setFileUrl(sPmJionFiles.getFileUrl());
		sPmMeetingMinutes.setFileName(sPmJionFiles.getFileName());
		sPmMeetingMinutes.setUploader(sPmJionFiles.getUploader());
		sPmMeetingMinutes.setUploadTime(sPmJionFiles.getUploadTime());
		return sPmMeetingMinutes;
	}

	public String save(SPmMeetingMinutes sPmMeetingMinutes, String proId) {
		SPmJionFiles sPmJionFiles = new SPmJionFiles();
		sPmJionFiles.preInsert();
		sPmJionFiles.setFileUrl(sPmMeetingMinutes.getFileUrl());
		sPmJionFiles.setFileName(sPmMeetingMinutes.getFileName());
		sPmJionFiles.setUploader(sPmMeetingMinutes.getUploader());
		sPmJionFiles.setUploadTime(sPmMeetingMinutes.getUploadTime());
		sPmJionFiles.setFormName("征求党员意见会议纪要");
		//			if (userId != null && userId != "") {return "error userId is null";}
		//				String proId = redao.findByUserID(userId, "25");
		if (proId != null && proId != "") {
			SPmMeetingMinutes sPmMeetingMinutess = dao.getByproId(proId);
			if (sPmMeetingMinutess == null) {
				sPmMeetingMinutes.setProId(proId);
				super.save(sPmMeetingMinutes);
				sPmJionFiles.setFormId(sPmMeetingMinutes.getId());
				sPmJionFilesDao.insert(sPmJionFiles);
				return "success";
			} else {
				sPmMeetingMinutes.setId(sPmMeetingMinutess.getId());
				super.save(sPmMeetingMinutes);
				sPmJionFiles.setFormId(sPmMeetingMinutes.getId());
				SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmMeetingMinutes.getId());
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
	public void save(SPmMeetingMinutes sPmMeetingMinutes) {
		super.save(sPmMeetingMinutes);
	}

	@Transactional(readOnly = false)
	public void delete(SPmMeetingMinutes sPmMeetingMinutes) {
		super.delete(sPmMeetingMinutes);
	}

}