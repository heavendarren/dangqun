package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmPresentationDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAppUpload;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmIntroduceOpinion;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPresentation;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 预备党员接收材料报告Service
 *
 * @author psy
 * @version 2017-04-24
 */
@Service
@Transactional(readOnly = true)
public class SPmPresentationService extends CrudService<SPmPresentationDao, SPmPresentation> {

  @Autowired
  private SPmJionFilesDao sPmJionFilesDao;

  @Autowired
  private DQRecordService dqRecordService;

  public SPmPresentation get(String id) {
    return super.get(id);
  }

  public SPmPresentation getByproId(String proId) {
    return dao.getByproId(proId);
  }

  public List<SPmPresentation> findList(SPmPresentation sPmPresentation) {
    return super.findList(sPmPresentation);
  }

  public SPmPresentation getForms(SPmPresentation sPmPresentation) {
    SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmPresentation.getId());
    User user = UserUtils.getUser();
    if (sPmJionFiles != null) {
    	sPmPresentation.setFileUrl(sPmJionFiles.getFileUrl());
        sPmPresentation.setFileName(sPmJionFiles.getFileName());
        sPmPresentation.setUploader(sPmJionFiles.getUploader());
        sPmPresentation.setUploadTime(sPmJionFiles.getUploadTime());
	}else{
    sPmPresentation.setUploader(user.getName());
    sPmPresentation.setUploadTime(new Date());
	}
    return sPmPresentation;
  }
  
/*
  public SPmPresentation findForms(SPmPresentation sPmPresentation) {
    SPmJionFiles sPmJionFiles = new SPmJionFiles();
    sPmJionFiles.setFormId(sPmPresentation.getId());
    List<SPmJionFiles> sPmJionFilesList = sPmJionFilesDao.findLists(sPmJionFiles);
    List<String> fileUrls = new ArrayList<String>();
    List<String> fileNames = new ArrayList<String>();
    for (int i = 0; i < sPmJionFilesList.size(); i++) {
      fileUrls.add(sPmJionFilesList.get(i).getFileUrl());
      fileNames.add(sPmJionFilesList.get(i).getFileName());
      sPmPresentation.setUploader(sPmJionFilesList.get(i).getUploader());
      sPmPresentation.setUploadTime(sPmJionFilesList.get(i).getUploadTime());
    }
    sPmPresentation.setFileUrls(fileUrls);
    sPmPresentation.setFileNames(fileNames);
    return sPmPresentation;
  }
*/
  public Page<SPmPresentation> findPage(Page<SPmPresentation> page, SPmPresentation sPmPresentation) {
    return super.findPage(page, sPmPresentation);
  }
/*
  public Page<SPmPresentation> findPage(Page<SPmPresentation> page, SPmPresentation sPmPresentation, String proId) {
    List<SPmPresentation> sPmPresentationList = new ArrayList<SPmPresentation>();
    sPmPresentation.setPage(page);
    if (proId != null) {
      sPmPresentation = dao.getByproId(proId);
      if (sPmPresentation != null) {
        sPmPresentation.setId(sPmPresentation.getId());
        SPmJionFiles sPmJionFiles = new SPmJionFiles();
        sPmJionFiles.setFormId(sPmPresentation.getId());
        List<SPmJionFiles> sPmJionFilesList = sPmJionFilesDao.findLists(sPmJionFiles);
        for (SPmJionFiles sPmJionFile : sPmJionFilesList) {
          sPmPresentation.setFileUrl(sPmJionFile.getFileUrl());
          sPmPresentation.setFileName(sPmJionFile.getFileName());
          sPmPresentation.setUploader(sPmJionFile.getUploader());
          sPmPresentation.setUploadTime(sPmJionFile.getUploadTime());
          sPmPresentationList.add(sPmPresentation);
        }
      } else {
        sPmPresentation = new SPmPresentation();
        sPmPresentation.setProId(proId);
        sPmPresentationList.add(sPmPresentation);
      }
    } else {
      sPmPresentationList.add(sPmPresentation);
    }
    page.setList(sPmPresentationList);
    return page;
  }
*/
  /**
   * custom method findJoinList;  自定义方法 联合查询
   *
   * @param
   * @return
   */
  public Page<SPmJionFiles> findJoinList(Page<SPmJionFiles> page, SPmJionFiles sPmJionFiles ,String userId) {
    sPmJionFiles.setPage(page);
    sPmJionFiles.setFormId(userId);
    page.setList(dao.findJoinList(sPmJionFiles));
    return page;
  }

  @Transactional(readOnly = false)
  public void save(SPmPresentation sPmPresentation) {
    super.save(sPmPresentation);
  }

  public String save(SPmPresentation sPmPresentation, String proId) {
	  SPmJionFiles sPmJionFiles = new SPmJionFiles();
	    sPmJionFiles.preInsert();
	    sPmJionFiles.setFileUrl(sPmPresentation.getFileUrl());
	    sPmJionFiles.setFileName(sPmPresentation.getFileName());
	    sPmJionFiles.setUploader(sPmPresentation.getUploader());
	    sPmJionFiles.setUploadTime(sPmPresentation.getUploadTime());
	    sPmJionFiles.setFormName("预备党员接收材料报告");
	    if (proId != null && proId != "") {
	    	SPmPresentation sPmPresentations = dao.getByproId(proId);
	      if (sPmPresentations == null) {
	    	  sPmPresentation.setProId(proId);
	        super.save(sPmPresentation);
	        sPmJionFiles.setFormId(sPmPresentation.getId());
	        sPmJionFilesDao.insert(sPmJionFiles);
	        return "success";
	      } else {
	    	  sPmPresentation.setId(sPmPresentations.getId());
	        super.save(sPmPresentation);
	        sPmJionFiles.setFormId(sPmPresentation.getId());
	        SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmPresentation.getId());
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
  public void delete(SPmPresentation sPmPresentation) {
    super.delete(sPmPresentation);
  }

  public String deletePicture(String id) {
	  return sPmJionFilesDao.delete(id) == 1 ? "success" : "error";
	}
}