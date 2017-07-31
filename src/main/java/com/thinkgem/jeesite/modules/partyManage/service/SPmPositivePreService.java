package com.thinkgem.jeesite.modules.partyManage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAuditChecklist;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMassOpinion;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPositivePre;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmPositivePreDao;

/**
 * 转正工作预审Service
 * @author psy
 * @version 2017-04-26
 */
@Service
@Transactional(readOnly = true)
public class SPmPositivePreService extends CrudService<SPmPositivePreDao, SPmPositivePre> {

	@Autowired
	private DQRecordService dqRecordService;

	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;
	
	public SPmPositivePre get(String id) {
		return super.get(id);
	}
	
	public List<SPmPositivePre> findList(SPmPositivePre sPmPositivePre) {
		return super.findList(sPmPositivePre);
	}
	
	public SPmPositivePre getByproId(String proId) {
	    return dao.getByproId(proId);
	}
	
	public SPmPositivePre getForms(SPmPositivePre sPmPositivePre) {
	    SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmPositivePre.getId());
	    sPmPositivePre.setFileUrl(sPmJionFiles.getFileUrl());
	    sPmPositivePre.setFileName(sPmJionFiles.getFileName());
	    sPmPositivePre.setUploader(sPmJionFiles.getUploader());
	    sPmPositivePre.setUploadTime(sPmJionFiles.getUploadTime());
	    return sPmPositivePre;
	}
	
	public Page<SPmPositivePre> findPage(Page<SPmPositivePre> page, SPmPositivePre sPmPositivePre) {
		return super.findPage(page, sPmPositivePre);
	}
	
	public Page<SPmPositivePre> findPage(Page<SPmPositivePre> page, SPmPositivePre sPmPositivePre, String proId) {
		List<SPmPositivePre> sPmPositivePreList = new ArrayList<SPmPositivePre>();
		//	    String proId = redao.findByUserID(userId, "23");
		sPmPositivePre.setPage(page);
		if (proId != null) {
			sPmPositivePre = dao.getByproId(proId);
			if (sPmPositivePre != null) {
				sPmPositivePre.setId(sPmPositivePre.getId());
				SPmJionFiles sPmJionFiles = new SPmJionFiles();
				sPmJionFiles.setFormId(sPmPositivePre.getId());
				List<SPmJionFiles> sPmJionFilesList = sPmJionFilesDao.findLists(sPmJionFiles);
				for (SPmJionFiles sPmJionFile : sPmJionFilesList) {
					sPmPositivePre.setFileUrl(sPmJionFile.getFileUrl());
					sPmPositivePre.setFileName(sPmJionFile.getFileName());
					sPmPositivePre.setUploader(sPmJionFile.getUploader());
					sPmPositivePre.setUploadTime(sPmJionFile.getUploadTime());
					sPmPositivePreList.add(sPmPositivePre);
				}
			} /*else {
				sPmPositivePre = new SPmPositivePre();
				sPmPositivePre.setProId(proId);
				sPmPositivePreList.add(sPmPositivePre);
			}*/
		} else {
			sPmPositivePreList.add(sPmPositivePre);
		}
		page.setList(sPmPositivePreList);
		return page;
	}
	
	public String save(SPmPositivePre sPmPositivePre, String proId) {
	    SPmJionFiles sPmJionFiles = new SPmJionFiles();
	    sPmJionFiles.preInsert();
	    sPmJionFiles.setFileUrl(sPmPositivePre.getFileUrl());
	    sPmJionFiles.setFileName(sPmPositivePre.getFileName());
	    sPmJionFiles.setUploader(sPmPositivePre.getUploader());
	    sPmJionFiles.setUploadTime(sPmPositivePre.getUploadTime());
	    sPmJionFiles.setFormName(" 转正工作预审");
//			if (userId != null && userId != "") {return "error userId is null";}
//				String proId = redao.findByUserID(userId, "25");
	    if (proId != null && proId != "") {
	    	SPmPositivePre sPmPositivePres = dao.getByproId(proId);
	      if (sPmPositivePres == null) {
	    	  sPmPositivePre.setProId(proId);
	        super.save(sPmPositivePre);
	        sPmJionFiles.setFormId(sPmPositivePre.getId());
	        sPmJionFilesDao.insert(sPmJionFiles);
	        return "success";
	      } else {
	    	  sPmPositivePre.setId(sPmPositivePres.getId());
	        super.save(sPmPositivePre);
	        sPmJionFiles.setFormId(sPmPositivePre.getId());
	        SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmPositivePre.getId());
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
	public void save(SPmPositivePre sPmPositivePre) {
		super.save(sPmPositivePre);
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmPositivePre sPmPositivePre) {
		super.delete(sPmPositivePre);
	}
	
}