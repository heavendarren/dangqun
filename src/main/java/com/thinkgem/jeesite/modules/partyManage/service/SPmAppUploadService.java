/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmAppUploadDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAppUpload;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 申请书上传Service
 *
 * @author zhc
 * @version 2017-04-17
 */
@Service
@Transactional(readOnly = true)
public class SPmAppUploadService extends CrudService<SPmAppUploadDao, SPmAppUpload> {

  @Autowired
  private DQRecordDao redao;

  @Autowired
  private SPmJionFilesDao sPmJionFilesDao;

  public SPmAppUpload get(String id) {
    return super.get(id);
  }

  public List<SPmAppUpload> findList(SPmAppUpload sPmAppUpload) {
    return super.findList(sPmAppUpload);
  }

  public Page<SPmAppUpload> findPage(Page<SPmAppUpload> page, SPmAppUpload sPmAppUpload) {
    return super.findPage(page, sPmAppUpload);
  }

  public SPmAppUpload getByproId(String proId) {
    return dao.getByproId(proId);
  }

  public SPmAppUpload getForms(SPmAppUpload sPmAppUpload) {
    SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmAppUpload.getId());
    User user = UserUtils.getUser();
    if (sPmJionFiles == null) {
      sPmAppUpload.setUploader(user.getName());
      sPmAppUpload.setUploadTime(new Date());
    } else {
      sPmAppUpload.setFileUrl(sPmJionFiles.getFileUrl());
      sPmAppUpload.setFileName(sPmJionFiles.getFileName());
      sPmAppUpload.setUploader(sPmJionFiles.getUploader());
      sPmAppUpload.setUploadTime(sPmJionFiles.getUploadTime());
    }
    return sPmAppUpload;
  }

  @Transactional(readOnly = false)
  public String save(SPmAppUpload sPmAppUpload, String proId) {
    SPmJionFiles sPmJionFiles = new SPmJionFiles();
    sPmJionFiles.preInsert();
    sPmJionFiles.setFileUrl(sPmAppUpload.getFileUrl());
    sPmJionFiles.setFileName(sPmAppUpload.getFileName());
    sPmJionFiles.setUploader(sPmAppUpload.getUploader());
    sPmJionFiles.setUploadTime(sPmAppUpload.getUploadTime());
    sPmJionFiles.setFormName("申请书");
//    if (userId != null && userId != "") {return "error userId is null";}
//      String proId = redao.findByUserID(userId, "1");
    if (proId != null && proId != "") {
      SPmAppUpload sPmAppUploads = dao.getByproId(proId);
      if (sPmAppUploads == null) {
        sPmAppUpload.setProId(proId);
        super.save(sPmAppUpload);
        sPmJionFiles.setFormId(sPmAppUpload.getId());
        sPmJionFilesDao.insert(sPmJionFiles);
        return "success";
      } else {
        sPmAppUpload.setId(sPmAppUploads.getId());
        super.save(sPmAppUpload);
        sPmJionFiles.setFormId(sPmAppUpload.getId());
        SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmAppUpload.getId());
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
//            return "请先登录";
  }

  @Transactional(readOnly = false)
  public void delete(SPmAppUpload sPmAppUpload) {
    super.delete(sPmAppUpload);
  }

}