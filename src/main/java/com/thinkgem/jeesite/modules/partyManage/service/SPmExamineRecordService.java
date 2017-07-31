/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmExamineRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmExamineRecord;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 发展对象审核备案Service
 *
 * @author zhc
 * @version 2017-04-24
 */
@Service
@Transactional(readOnly = true)
public class SPmExamineRecordService extends CrudService<SPmExamineRecordDao, SPmExamineRecord> {

  @Autowired
  private DQRecordService dqRecordService;

  @Autowired
  private SPmJionFilesDao sPmJionFilesDao;

  public SPmExamineRecord get(String id) {
    return super.get(id);
  }

  public List<SPmExamineRecord> findList(SPmExamineRecord sPmExamineRecord) {
    return super.findList(sPmExamineRecord);
  }

  public Page<SPmExamineRecord> findPage(Page<SPmExamineRecord> page, SPmExamineRecord sPmExamineRecord) {
    return super.findPage(page, sPmExamineRecord);
  }

  public SPmExamineRecord getByproId(String proId) {
    return dao.getByproId(proId);
  }

  public SPmExamineRecord getForms(SPmExamineRecord sPmExamineRecord) {
    SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmExamineRecord.getId());
    User user = UserUtils.getUser();
    if (sPmJionFiles == null) {
      sPmExamineRecord.setUploader(user.getName());
      sPmExamineRecord.setUploadTime(new Date());
    } else {
      sPmExamineRecord.setFileUrl(sPmJionFiles.getFileUrl());
      sPmExamineRecord.setFileName(sPmJionFiles.getFileName());
      sPmExamineRecord.setUploader(sPmJionFiles.getUploader());
      sPmExamineRecord.setUploadTime(sPmJionFiles.getUploadTime());
    }

    return sPmExamineRecord;
  }

  @Transactional(readOnly = false)
  public void save(SPmExamineRecord sPmExamineRecord) {
    super.save(sPmExamineRecord);
  }

  public String save(SPmExamineRecord sPmExamineRecord, String proId) {
    SPmJionFiles sPmJionFiles = new SPmJionFiles();
    sPmJionFiles.preInsert();
    sPmJionFiles.setFileUrl(sPmExamineRecord.getFileUrl());
    sPmJionFiles.setFileName(sPmExamineRecord.getFileName());
    sPmJionFiles.setUploader(sPmExamineRecord.getUploader());
    sPmJionFiles.setUploadTime(sPmExamineRecord.getUploadTime());
    sPmJionFiles.setFormName("发展对象审核备案");
//    if (userId != null && userId != "") {return "error userId is null";}
//      String proId = redao.findByUserID(userId, "10");
    if (proId != null && proId != "") {
      SPmExamineRecord sPmExamineRecords = dao.getByproId(proId);
      if (sPmExamineRecords == null) {
        sPmExamineRecord.setProId(proId);
        super.save(sPmExamineRecord);
        dqRecordService.fillIn(proId);
        sPmJionFiles.setFormId(sPmExamineRecord.getId());
        sPmJionFilesDao.insert(sPmJionFiles);
        return "success";
      } else {
        sPmExamineRecord.setId(sPmExamineRecords.getId());
        super.save(sPmExamineRecord);
        dqRecordService.fillIn(proId);
        sPmJionFiles.setFormId(sPmExamineRecord.getId());
        SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmExamineRecord.getId());
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
  public void delete(SPmExamineRecord sPmExamineRecord) {
    super.delete(sPmExamineRecord);
  }

}