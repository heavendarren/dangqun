/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmPublicInformationDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPublicInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 公示信息Service
 *
 * @author zhc
 * @version 2017-04-20
 */
@Service
@Transactional(readOnly = true)
public class SPmPublicInformationService extends CrudService<SPmPublicInformationDao, SPmPublicInformation> {

  @Autowired
  private DQRecordService dqRecordService;

  @Autowired
  private SPmJionFilesDao sPmJionFilesDao;

  public SPmPublicInformation get(String id) {
    return super.get(id);
  }

  public List<SPmPublicInformation> findList(SPmPublicInformation sPmPublicInformation) {
    return super.findList(sPmPublicInformation);
  }

  public Page<SPmPublicInformation> findPage(Page<SPmPublicInformation> page, SPmPublicInformation sPmPublicInformation) {
    return super.findPage(page, sPmPublicInformation);
  }

  public Page<SPmPublicInformation> findPage(Page<SPmPublicInformation> page, SPmPublicInformation sPmPublicInformation, String proId) {
    List<SPmPublicInformation> sPmPublicInformationList = new ArrayList<SPmPublicInformation>();
//    String proId = redao.findByUserID(userId, "4");
    sPmPublicInformation.setPage(page);
    if (proId != null) {
      sPmPublicInformation = dao.getByproId(proId);
      if (sPmPublicInformation != null) {
        SPmJionFiles sPmJionFiles = new SPmJionFiles();
        sPmJionFiles.setFormId(sPmPublicInformation.getId());
        List<SPmJionFiles> sPmJionFilesList = sPmJionFilesDao.findLists(sPmJionFiles);
        for (SPmJionFiles sPmJionFile : sPmJionFilesList) {
          SPmPublicInformation sPmPublicInformations = new SPmPublicInformation();
          sPmPublicInformations.setFileUrl(sPmJionFile.getFileUrl());
          sPmPublicInformations.setFileName(sPmJionFile.getFileName());
          sPmPublicInformations.setUploader(sPmJionFile.getUploader());
          sPmPublicInformations.setUploadTime(sPmJionFile.getUploadTime());
          sPmPublicInformationList.add(sPmPublicInformations);
        }
      } else {
        sPmPublicInformation = new SPmPublicInformation();
        sPmPublicInformation.setProId(proId);
        sPmPublicInformationList.add(sPmPublicInformation);
      }
    } else {
      sPmPublicInformationList.add(sPmPublicInformation);
    }
    page.setList(sPmPublicInformationList);
    return page;
  }

  @Transactional(readOnly = false)
  public void save(SPmPublicInformation sPmPublicInformation) {
    super.save(sPmPublicInformation);
  }

  public String save(SPmPublicInformation sPmPublicInformation, /*String userId*/String proId, String fileUrl, String fileName) {
    SPmJionFiles sPmJionFiles = new SPmJionFiles();
    sPmJionFiles.preInsert();
    sPmJionFiles.setFileUrl(fileUrl);
    sPmJionFiles.setFileName(fileName);
    sPmJionFiles.setUploader(sPmPublicInformation.getUploader());
    sPmJionFiles.setUploadTime(sPmPublicInformation.getUploadTime());
    sPmJionFiles.setFormName("公示信息");
//    if (userId != null && userId != "") {}
//    String proId = redao.findByUserID(userId, "4");
    if (proId != null && proId != "") {
      SPmPublicInformation sPmPublicInformations = dao.getByproId(proId);
      if (sPmPublicInformations == null) {
        sPmPublicInformation.setProId(proId);
        super.save(sPmPublicInformation);
        dqRecordService.fillIn(proId);
      } else {
        sPmPublicInformation.setId(sPmPublicInformations.getId());
        super.save(sPmPublicInformation);
        dqRecordService.fillIn(proId);
      }
      sPmJionFiles.setFormId(sPmPublicInformation.getId());
      sPmJionFilesDao.insert(sPmJionFiles);
      return "success";
    }
    return "error proId is not found";
//            return "请先登录";
  }

  @Transactional(readOnly = false)
  public void delete(SPmPublicInformation sPmPublicInformation) {
    super.delete(sPmPublicInformation);
  }

}