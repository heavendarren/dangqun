/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmThoughtReportDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmThoughtReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 思想汇报Service
 *
 * @author zhc
 * @version 2017-04-24
 */
@Service
@Transactional(readOnly = true)
public class SPmThoughtReportService extends CrudService<SPmThoughtReportDao, SPmThoughtReport> {

  @Autowired
  private SPmJionFilesDao sPmJionFilesDao;

  @Autowired
  private DQRecordService dqRecordService;

  public SPmThoughtReport get(String id) {
    return super.get(id);
  }

  public SPmThoughtReport getWithUrl(String id) {
    SPmThoughtReport sPmThoughtReport = super.get(id);
    SPmJionFiles sPmJionFile = sPmJionFilesDao.getByformId(id);
    if (sPmThoughtReport != null) {
      sPmThoughtReport.setUploader(sPmJionFile.getUploader());
      sPmThoughtReport.setUploadTime(sPmJionFile.getUploadTime());
      sPmThoughtReport.setFileName(sPmJionFile.getFileName());
      sPmThoughtReport.setFileUrl(sPmJionFile.getFileUrl());
    }
    return sPmThoughtReport;
  }

  public List<SPmThoughtReport> findList(SPmThoughtReport sPmThoughtReport) {
    return super.findList(sPmThoughtReport);
  }

  public Page<SPmThoughtReport> findPage(Page<SPmThoughtReport> page, SPmThoughtReport sPmThoughtReport) {
    return super.findPage(page, sPmThoughtReport);
  }

  public Page<SPmThoughtReport> findPage(Page<SPmThoughtReport> page, SPmThoughtReport sPmThoughtReport, String proId) {
    //String proId = redao.findByUserID(userId, "7");
    sPmThoughtReport.setProId(proId);
    sPmThoughtReport.setPage(page);
    List<SPmThoughtReport> sPmThoughtReportList = new ArrayList<SPmThoughtReport>();
    if (proId == null) {
      sPmThoughtReportList = dao.findList(sPmThoughtReport);
    } else {
      sPmThoughtReportList = dao.findLists(sPmThoughtReport);
    }
    page.setList(sPmThoughtReportList);
    return page;
  }

  @Transactional(readOnly = false)
  public void save(SPmThoughtReport sPmThoughtReport) {
    super.save(sPmThoughtReport);
  }

  public String save(SPmThoughtReport sPmThoughtReport, String proId) {
    SPmJionFiles sPmJionFiles = new SPmJionFiles();
    sPmJionFiles.preInsert();
    sPmJionFiles.setFileUrl(sPmThoughtReport.getFileUrl());
    sPmJionFiles.setFileName(sPmThoughtReport.getFileName());
    sPmJionFiles.setUploader(sPmThoughtReport.getUploader());
    sPmJionFiles.setUploadTime(sPmThoughtReport.getUploadTime());
    sPmJionFiles.setFormName("思想汇报");
//		if (userId != null && userId != "") {return "error userId is null";}
//			String proId = redao.findByUserID(userId, "7");
    if (proId != null && proId != "") {
      sPmThoughtReport.setProId(proId);
      super.save(sPmThoughtReport);
      dqRecordService.fillIn(proId);
      sPmJionFiles.setFormId(sPmThoughtReport.getId());
      sPmJionFilesDao.insert(sPmJionFiles);
      return "success";
    }
    return "error proId is not found";
//            return "请先登录";
  }

  @Transactional(readOnly = false)
  public void delete(SPmThoughtReport sPmThoughtReport) {
    super.delete(sPmThoughtReport);
  }

}