/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmBeforePartyRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmBeforePartyRecord;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 预备党员备案Service
 *
 * @author zhc
 * @version 2017-04-25
 */
@Service
@Transactional(readOnly = true)
public class SPmBeforePartyRecordService extends CrudService<SPmBeforePartyRecordDao, SPmBeforePartyRecord> {

  @Autowired
  private DQRecordService dqRecordService;

  @Autowired
  private SPmJionFilesDao sPmJionFilesDao;

  public SPmBeforePartyRecord get(String id) {
    return super.get(id);
  }

  public List<SPmBeforePartyRecord> findList(SPmBeforePartyRecord sPmBeforePartyRecord) {
    return super.findList(sPmBeforePartyRecord);
  }

  public Page<SPmBeforePartyRecord> findPage(Page<SPmBeforePartyRecord> page, SPmBeforePartyRecord sPmBeforePartyRecord) {
    return super.findPage(page, sPmBeforePartyRecord);
  }

  public SPmBeforePartyRecord getByproId(String proId) {
    return dao.getByproId(proId);
  }

  public SPmBeforePartyRecord getForms(SPmBeforePartyRecord sPmBeforePartyRecord) {
    SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmBeforePartyRecord.getId());
    User user = UserUtils.getUser();
    if (sPmJionFiles == null) {
      sPmBeforePartyRecord.setUploader(user.getName());
      sPmBeforePartyRecord.setUploadTime(new Date());
    } else {
      sPmBeforePartyRecord.setFileUrl(sPmJionFiles.getFileUrl());
      sPmBeforePartyRecord.setFileName(sPmJionFiles.getFileName());
      sPmBeforePartyRecord.setUploader(sPmJionFiles.getUploader());
      sPmBeforePartyRecord.setUploadTime(sPmJionFiles.getUploadTime());
    }
    return sPmBeforePartyRecord;
  }

  @Transactional(readOnly = false)
  public void save(SPmBeforePartyRecord sPmBeforePartyRecord) {
    super.save(sPmBeforePartyRecord);
  }

  public String save(SPmBeforePartyRecord sPmBeforePartyRecord, String proId) {
    SPmJionFiles sPmJionFiles = new SPmJionFiles();
    sPmJionFiles.preInsert();
    sPmJionFiles.setFileUrl(sPmBeforePartyRecord.getFileUrl());
    sPmJionFiles.setFileName(sPmBeforePartyRecord.getFileName());
    sPmJionFiles.setUploader(sPmBeforePartyRecord.getUploader());
    sPmJionFiles.setUploadTime(sPmBeforePartyRecord.getUploadTime());
    sPmJionFiles.setFormName("预备党员备案");
//    if (userId != null && userId != "") {return "error userId is null";}
//      String proId = redao.findByUserID(userId, "19");
    if (proId != null && proId != "") {
      SPmBeforePartyRecord sPmBeforePartyRecords = dao.getByproId(proId);
      if (sPmBeforePartyRecords == null) {
        sPmBeforePartyRecord.setProId(proId);
        super.save(sPmBeforePartyRecord);
        sPmJionFiles.setFormId(sPmBeforePartyRecord.getId());
        sPmJionFilesDao.insert(sPmJionFiles);
        if (sPmJionFiles.getFileUrl() != null && sPmJionFiles.getFileUrl() != "") {
          dqRecordService.fillIn(proId);
        }
        return "success";
      } else {
        sPmBeforePartyRecord.setId(sPmBeforePartyRecords.getId());
        super.save(sPmBeforePartyRecord);
        sPmJionFiles.setFormId(sPmBeforePartyRecord.getId());
        SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmBeforePartyRecord.getId());
        if (sPmJionFileses != null) {
          sPmJionFiles.setId(sPmJionFileses.getId());
          sPmJionFiles.preUpdate();
          sPmJionFilesDao.update(sPmJionFiles);
        } else {
          sPmJionFilesDao.insert(sPmJionFiles);
        }
        if (sPmJionFiles.getFileUrl() != null && sPmJionFiles.getFileUrl() != "") {
          dqRecordService.fillIn(proId);
        }
        return "success";
      }
    }
    return "error proId is not found";
//            return "请先登录";
  }

  @Transactional(readOnly = false)
  public void delete(SPmBeforePartyRecord sPmBeforePartyRecord) {
    super.delete(sPmBeforePartyRecord);
  }

}