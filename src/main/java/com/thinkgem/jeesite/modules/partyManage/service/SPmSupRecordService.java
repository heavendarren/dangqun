/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.ProcessDataDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmSupRecordDao;
import com.thinkgem.jeesite.modules.partyManage.entity.ProcessData;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmSupRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 补录信息Service
 *
 * @author zhc
 * @version 2017-05-02
 */
@Service
@Transactional(readOnly = true)
public class SPmSupRecordService extends CrudService<SPmSupRecordDao, SPmSupRecord> {

  @Autowired
  private ProcessDataDao processDataDao;

  @Autowired
  private SPmJionFilesDao sPmJionFilesDao;

  public SPmSupRecord get(String id) {
    return super.get(id);
  }

  public List<SPmSupRecord> findList(SPmSupRecord sPmSupRecord) {
    return super.findList(sPmSupRecord);
  }

  public Page<SPmSupRecord> findPage(Page<SPmSupRecord> page, SPmSupRecord sPmSupRecord) {
    return super.findPage(page, sPmSupRecord);
  }

  public Page<SPmSupRecord> findPage(Page<SPmSupRecord> page, SPmSupRecord sPmSupRecord, String proId) {
    List<SPmSupRecord> sPmSupRecordList = new ArrayList<SPmSupRecord>();
    if (proId != null) {
      sPmSupRecord = dao.getByproId(proId, sPmSupRecord.getNode());
      if (sPmSupRecord != null) {
        SPmJionFiles sPmJionFiles = new SPmJionFiles();
        sPmJionFiles.setFormId(sPmSupRecord.getId());
        List<SPmJionFiles> sPmJionFilesList = sPmJionFilesDao.findLists(sPmJionFiles);
        for (SPmJionFiles sPmJionFile : sPmJionFilesList) {
          SPmSupRecord sPmSupRecords = new SPmSupRecord();
          sPmSupRecords.setFileUrl(sPmJionFile.getFileUrl());
          sPmSupRecords.setFileName(sPmJionFile.getFileName());
          sPmSupRecords.setUploader(sPmJionFile.getUploader());
          sPmSupRecords.setUploadTime(sPmJionFile.getUploadTime());
          sPmSupRecordList.add(sPmSupRecords);
        }
      } else {
        sPmSupRecord = new SPmSupRecord();
        sPmSupRecord.setProId(proId);
        sPmSupRecordList.add(sPmSupRecord);
      }
    } else {
      sPmSupRecordList.add(sPmSupRecord);
    }
    sPmSupRecord.setPage(page);
    page.setList(sPmSupRecordList);
    return page;
  }

  @Transactional(readOnly = false)
  public void save(SPmSupRecord sPmSupRecord) {
    super.save(sPmSupRecord);
  }

  public ProcessData getProData(String proId) {
    ProcessData processData = processDataDao.findByID(proId);
    return processData;
  }

  public String save(SPmSupRecord sPmSupRecord, String proId, String fileUrl, String fileName) {
    SPmJionFiles sPmJionFiles = new SPmJionFiles();
    sPmJionFiles.preInsert();
    sPmJionFiles.setFileUrl(fileUrl);
    sPmJionFiles.setFileName(fileName);
    sPmJionFiles.setUploader(sPmSupRecord.getUploader());
    sPmJionFiles.setUploadTime(sPmSupRecord.getUploadTime());
    sPmJionFiles.setFormName("补录信息");
    if (proId != null && proId != "") {
      SPmSupRecord sPmSupRecords = dao.getByproId(proId, sPmSupRecord.getNode());
      if (sPmSupRecords == null) {
        sPmSupRecord.setProId(proId);
        super.save(sPmSupRecord);
      } else {
        sPmSupRecord.setId(sPmSupRecords.getId());
        sPmSupRecord.setProId(proId);
        super.save(sPmSupRecord);
      }
      sPmJionFiles.setFormId(sPmSupRecord.getId());
      sPmJionFilesDao.insert(sPmJionFiles);
      return "success";
    }
    return "error proId is not found";
  }

  @Transactional(readOnly = false)
  public void delete(SPmSupRecord sPmSupRecord) {
    super.delete(sPmSupRecord);
  }

}