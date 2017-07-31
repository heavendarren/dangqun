/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPositiveApp;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmPositiveAppDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是转正申请表Service
 * @author one
 * @version 2017-04-28
 */
@Service
@Transactional(readOnly = true)
public class SPmPositiveAppService extends CrudService<SPmPositiveAppDao, SPmPositiveApp> {

	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;

	@Autowired
	private DQRecordService dqRecordService;
	
	public SPmPositiveApp get(String id) {
		return super.get(id);
	}
	
	public List<SPmPositiveApp> findList(SPmPositiveApp sPmPositiveApp) {
		return super.findList(sPmPositiveApp);
	}
	
	public Page<SPmPositiveApp> findPage(Page<SPmPositiveApp> page, SPmPositiveApp sPmPositiveApp) {
		return super.findPage(page, sPmPositiveApp);
	}
	
	@Transactional(readOnly = false)
	public void save(SPmPositiveApp sPmPositiveApp) {
		super.save(sPmPositiveApp);
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmPositiveApp sPmPositiveApp) {
		super.delete(sPmPositiveApp);
	}

	public SPmPositiveApp getByproId(String proId) {
		return dao.getByproId(proId);
		
	}

	public SPmPositiveApp findForms(SPmPositiveApp sPmPositiveApp) {
		SPmJionFiles sPmJionFiles = new SPmJionFiles();
		sPmJionFiles.setFormId(sPmPositiveApp.getId());
		List<SPmJionFiles> sPmJionFilesList = sPmJionFilesDao.findLists(sPmJionFiles);
		List<String> imgUrls = new ArrayList<String>();
		List<String> fileUrls = new ArrayList<String>();
		List<String> imgNames = new ArrayList<String>();
		List<String> fileNames = new ArrayList<String>();
		List<String> fileUrlss = new ArrayList<String>();
		List<String> fileNamess = new ArrayList<String>();
		for (int i = 0; i < sPmJionFilesList.size(); i++) {
			if (sPmJionFilesList.get(i).getRemarks() == "img") {
				imgUrls.add(sPmJionFilesList.get(i).getFileUrl());
				imgNames.add(sPmJionFilesList.get(i).getFileName());
			}else{
				fileUrls.add(sPmJionFilesList.get(i).getFileUrl());
				fileNames.add(sPmJionFilesList.get(i).getFileName());
				fileUrlss.add(sPmJionFilesList.get(i).getFileUrl());
				fileNamess.add(sPmJionFilesList.get(i).getFileName());
			}
			
			sPmPositiveApp.setUploader(sPmJionFilesList.get(i).getUploader());
			sPmPositiveApp.setUploadTime(sPmJionFilesList.get(i).getUploadTime());
		}
		sPmPositiveApp.setFileUrlss(fileUrlss);
		sPmPositiveApp.setFileNamess(fileNamess);
		sPmPositiveApp.setFileUrls(fileUrls);
		sPmPositiveApp.setFileNames(fileNames);
		sPmPositiveApp.setImgUrls(imgUrls);
		sPmPositiveApp.setImgNames(imgNames);
		return sPmPositiveApp;
		
	}
	
	
	public String save(SPmPositiveApp sPmPositiveApp, String proId, String url, String name,String remark) {
		SPmJionFiles sPmJionFiles = new SPmJionFiles();
		sPmJionFiles.preInsert();
		sPmJionFiles.setFileUrl(url);
		sPmJionFiles.setFileName(name);
		sPmJionFiles.setUploader(sPmPositiveApp.getUploader());
		sPmJionFiles.setUploadTime(sPmPositiveApp.getUploadTime());
		sPmJionFiles.setFormName("拟转正公示");
		sPmJionFiles.setRemarks(remark);
		if (proId != null && proId != "") {
			SPmPositiveApp sPmPositiveApps = dao.getByproId(proId);
			if (sPmPositiveApps == null) {
				sPmPositiveApp.setProId(proId);
				super.save(sPmPositiveApp);
				sPmJionFiles.setFormId(sPmPositiveApp.getId());
				dqRecordService.fillIn(proId);
			} else {
				sPmPositiveApp.setId(sPmPositiveApps.getId());
				sPmPositiveApp.setProId(proId);
				super.save(sPmPositiveApp);
				sPmJionFiles.setFormId(sPmPositiveApp.getId());
				dqRecordService.fillIn(proId);
			}
			
			sPmJionFilesDao.insert(sPmJionFiles);
			return "success";
		}
		return "error proId is not found";
	}
	
	public SPmPositiveApp getForms(SPmPositiveApp sPmPositiveApp) {
		SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmPositiveApp.getId());
	    User user = UserUtils.getUser();
	    if (sPmJionFiles == null) {
	    	sPmPositiveApp.setUploader(user.getName());
	    	sPmPositiveApp.setUploadTime(new Date());
	    } else {
	    	sPmPositiveApp.setFileUrl(sPmJionFiles.getFileUrl());
	    	sPmPositiveApp.setFileName(sPmJionFiles.getFileName());
	    	sPmPositiveApp.setUploader(sPmJionFiles.getUploader());
	    	sPmPositiveApp.setUploadTime(sPmJionFiles.getUploadTime());
	    }
	    return sPmPositiveApp;
	  }


	/**
	 * custom method findJoinList;  自定义方法 联合查询
	 *
	 * @param SPmPositivaApp
	 * @return
	 */
	public Page<SPmJionFiles> findJoinList(Page<SPmJionFiles> page, SPmJionFiles sPmJionFiles,String formId){
		sPmJionFiles.setPage(page);
		sPmJionFiles.setFormId(formId);
		page.setList(dao.findJoinList(sPmJionFiles));
		return page;
	}
	
}
























