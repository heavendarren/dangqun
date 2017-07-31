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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPartyMen;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmPartyMenDao;

/**
 * 入党志愿书Service
 * @author psy
 * @version 2017-04-27
 */
@Service
@Transactional(readOnly = true)
public class SPmPartyMenService extends CrudService<SPmPartyMenDao, SPmPartyMen> {

	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;

	@Autowired
	private DQRecordService dqRecordService;

	public SPmPartyMen get(String id) {
		return super.get(id);
	}
	
	public List<SPmPartyMen> findList(SPmPartyMen sPmPartyMen) {
		return super.findList(sPmPartyMen);
	}

	public SPmPartyMen getByproId(String proId) {
		return dao.getByproId(proId);
	}

	public SPmPartyMen getForms(SPmPartyMen sPmPartyMen) {
	    SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmPartyMen.getId());
	    sPmPartyMen.setFileUrl(sPmJionFiles.getFileUrl());
	    sPmPartyMen.setFileName(sPmJionFiles.getFileName());
	    sPmPartyMen.setUploader(sPmJionFiles.getUploader());
	    sPmPartyMen.setUploadTime(sPmJionFiles.getUploadTime());
	    return sPmPartyMen;
	  }
	
	public SPmPartyMen findForms(SPmPartyMen sPmPartyMen) {
		SPmJionFiles sPmJionFiles = new SPmJionFiles();
		sPmJionFiles.setFormId(sPmPartyMen.getId());
		List<SPmJionFiles> sPmJionFilesList = sPmJionFilesDao.findLists(sPmJionFiles);
		List<String> imgUrls = new ArrayList<String>();
		List<String> fileUrls = new ArrayList<String>();
		List<String> imgNames = new ArrayList<String>();
		List<String> fileNames = new ArrayList<String>();
		for (int i = 0; i < sPmJionFilesList.size(); i++) {
			if (sPmJionFilesList.get(i).getRemarks() == "img") {
				imgUrls.add(sPmJionFilesList.get(i).getFileUrl());
				imgNames.add(sPmJionFilesList.get(i).getFileName());
			}else{
				fileUrls.add(sPmJionFilesList.get(i).getFileUrl());
				fileNames.add(sPmJionFilesList.get(i).getFileName());
			}
			sPmPartyMen.setUploader(sPmJionFilesList.get(i).getUploader());
			sPmPartyMen.setUploadTime(sPmJionFilesList.get(i).getUploadTime());
		}
		sPmPartyMen.setFileUrls(fileUrls);
		sPmPartyMen.setFileNames(fileNames);
		sPmPartyMen.setImgUrls(imgUrls);
		sPmPartyMen.setImgNames(imgNames);
		return sPmPartyMen;
	}

	public Page<SPmPartyMen> findPage(Page<SPmPartyMen> page, SPmPartyMen sPmPartyMen) {
		return super.findPage(page, sPmPartyMen);
	}
	/**
	 * custom method findJoinList;  自定义方法 联合查询
	 * 
	 * @param SPmPartyMen
	 * @return
	 */
	public Page<SPmJionFiles> findJoinList(Page<SPmJionFiles> page, SPmJionFiles sPmJionFiles,String formId){
		sPmJionFiles.setPage(page);
		sPmJionFiles.setFormId(formId);
		page.setList(dao.findJoinList(sPmJionFiles));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(SPmPartyMen sPmPartyMen) {
		super.save(sPmPartyMen);
	}

	public String save(SPmPartyMen sPmPartyMen, String proId, String url, String name,String remark) {
		SPmJionFiles sPmJionFiles = new SPmJionFiles();
		sPmJionFiles.preInsert();
		sPmJionFiles.setFileUrl(url);
		sPmJionFiles.setFileName(name);
		sPmJionFiles.setUploader(sPmPartyMen.getUploader());
		sPmJionFiles.setUploadTime(sPmPartyMen.getUploadTime());
		sPmJionFiles.setFormName("入党志愿书");
		sPmJionFiles.setRemarks(remark);
		if (proId != null && proId != "") {
			SPmPartyMen sPmPartyMens = dao.getByproId(proId);
			if (sPmPartyMens == null) {
				sPmPartyMen.setProId(proId);
				super.save(sPmPartyMen);
				dqRecordService.fillIn(proId);
				sPmJionFiles.setFormId(sPmPartyMen.getId());			
			} else {
				sPmPartyMen.setId(sPmPartyMens.getId());
				sPmPartyMen.setProId(proId);
				super.save(sPmPartyMen);
				dqRecordService.fillIn(proId);
				sPmJionFiles.setFormId(sPmPartyMen.getId());			
			}
			
			sPmJionFilesDao.insert(sPmJionFiles);
			return "success";
		}
		return "error proId is not found";
	}

	@Transactional(readOnly = false)
	public void delete(SPmPartyMen sPmPartyMen) {
		super.delete(sPmPartyMen);
	}

}