package com.thinkgem.jeesite.modules.partyManage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmDevelopmentOption;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMassOpinion;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMeetingMinutes;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmMassOpinionDao;

/**
 * 征求群众意见会议纪要Service
 * @author psy
 * @version 2017-04-26
 */
@Service
@Transactional(readOnly = true)
public class SPmMassOpinionService extends CrudService<SPmMassOpinionDao, SPmMassOpinion> {

	@Autowired
	private DQRecordService dqRecordService;

	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;

	public SPmMassOpinion get(String id) {
		return super.get(id);
	}

	public List<SPmMassOpinion> findList(SPmMassOpinion sPmMassOpinion) {
		return super.findList(sPmMassOpinion);
	}

	public Page<SPmMassOpinion> findPage(Page<SPmMassOpinion> page, SPmMassOpinion sPmMassOpinion) {
		return super.findPage(page, sPmMassOpinion);
	}

	public Page<SPmMassOpinion> findPage(Page<SPmMassOpinion> page, SPmMassOpinion sPmMassOpinion, String proId) {
		List<SPmMassOpinion> sPmMassOpinionList = new ArrayList<SPmMassOpinion>();
		//	    String proId = redao.findByUserID(userId, "23");
		sPmMassOpinion.setPage(page);
		if (proId != null) {
			sPmMassOpinion = dao.getByproId(proId);
			if (sPmMassOpinion != null) {
				sPmMassOpinion.setId(sPmMassOpinion.getId());
				SPmJionFiles sPmJionFiles = new SPmJionFiles();
				sPmJionFiles.setFormId(sPmMassOpinion.getId());
				List<SPmJionFiles> sPmJionFilesList = sPmJionFilesDao.findLists(sPmJionFiles);
				for (SPmJionFiles sPmJionFile : sPmJionFilesList) {
					sPmMassOpinion.setFileUrl(sPmJionFile.getFileUrl());
					sPmMassOpinion.setFileName(sPmJionFile.getFileName());
					sPmMassOpinion.setUploader(sPmJionFile.getUploader());
					sPmMassOpinion.setUploadTime(sPmJionFile.getUploadTime());
					sPmMassOpinionList.add(sPmMassOpinion);
				}
			} /*else {
				sPmMassOpinion = new SPmMassOpinion();
				sPmMassOpinion.setProId(proId);
				sPmMassOpinionList.add(sPmMassOpinion);
			}*/
		} else {
			sPmMassOpinionList.add(sPmMassOpinion);
		}
		page.setList(sPmMassOpinionList);
		return page;
	}

	public SPmMassOpinion getByproId(String proId) {
		return dao.getByproId(proId);
	}

	public SPmMassOpinion getForms(SPmMassOpinion sPmMassOpinion) {
		SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmMassOpinion.getId());
		sPmMassOpinion.setFileUrl(sPmJionFiles.getFileUrl());
		sPmMassOpinion.setFileName(sPmJionFiles.getFileName());
		sPmMassOpinion.setUploader(sPmJionFiles.getUploader());
		sPmMassOpinion.setUploadTime(sPmJionFiles.getUploadTime());
		return sPmMassOpinion;
	}

	public String save(SPmMassOpinion sPmMassOpinion, String proId) {
		SPmJionFiles sPmJionFiles = new SPmJionFiles();
		sPmJionFiles.preInsert();
		sPmJionFiles.setFileUrl(sPmMassOpinion.getFileUrl());
		sPmJionFiles.setFileName(sPmMassOpinion.getFileName());
		sPmJionFiles.setUploader(sPmMassOpinion.getUploader());
		sPmJionFiles.setUploadTime(sPmMassOpinion.getUploadTime());
		sPmJionFiles.setFormName("征求群众意见会议纪要");
		//			if (userId != null && userId != "") {return "error userId is null";}
		//				String proId = redao.findByUserID(userId, "7");
		if (proId != null && proId != "") {
			SPmMassOpinion sPmMassOpinions = dao.getByproId(proId);
			if (sPmMassOpinions == null) {
				sPmMassOpinion.setProId(proId);
				super.save(sPmMassOpinion);
				sPmJionFiles.setFormId(sPmMassOpinion.getId());
				sPmJionFilesDao.insert(sPmJionFiles);
				return "success";
			} else {
				sPmMassOpinion.setId(sPmMassOpinions.getId());
				super.save(sPmMassOpinion);
				sPmJionFiles.setFormId(sPmMassOpinion.getId());
				SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmMassOpinion.getId());
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

	@Transactional(readOnly = false)
	public void save(SPmMassOpinion sPmMassOpinion) {
		super.save(sPmMassOpinion);
	}

	@Transactional(readOnly = false)
	public void delete(SPmMassOpinion sPmMassOpinion) {
		super.delete(sPmMassOpinion);
	}

}