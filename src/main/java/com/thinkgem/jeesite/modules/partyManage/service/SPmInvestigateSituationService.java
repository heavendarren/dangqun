/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmInvestigateSituationDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmInvestigateSituation;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 培养考察情况Service
 *
 * @author zhc
 * @version 2017-04-24
 */
@Service
@Transactional(readOnly = true)
public class SPmInvestigateSituationService extends CrudService<SPmInvestigateSituationDao, SPmInvestigateSituation> {

  @Autowired
  private SPmJionFilesDao sPmJionFilesDao;

  public SPmInvestigateSituation get(String id) {
    return super.get(id);
  }

  public SPmInvestigateSituation getWithUrl(String id) {
    SPmInvestigateSituation sPmInvestigateSituation = super.get(id);
    SPmJionFiles sPmJionFile = sPmJionFilesDao.getByformId(id);
    if (sPmInvestigateSituation != null) {
      sPmInvestigateSituation.setUploader(sPmJionFile.getUploader());
      sPmInvestigateSituation.setUploadTime(sPmJionFile.getUploadTime());
      sPmInvestigateSituation.setFileName(sPmJionFile.getFileName());
      sPmInvestigateSituation.setFileUrl(sPmJionFile.getFileUrl());
    }
    return sPmInvestigateSituation;
  }

  public List<SPmInvestigateSituation> findList(SPmInvestigateSituation sPmInvestigateSituation) {
    return super.findList(sPmInvestigateSituation);
  }

  public Page<SPmInvestigateSituation> findPage(Page<SPmInvestigateSituation> page, SPmInvestigateSituation sPmInvestigateSituation) {
    return super.findPage(page, sPmInvestigateSituation);
  }

  public Page<SPmInvestigateSituation> findPage(Page<SPmInvestigateSituation> page, SPmInvestigateSituation sPmInvestigateSituation, String proId) {
//    String proId = redao.findByUserID(userId, "7");
    sPmInvestigateSituation.setProId(proId);
    sPmInvestigateSituation.setPage(page);
    List<SPmInvestigateSituation> sPmInvestigateSituationList = new ArrayList<SPmInvestigateSituation>();
    if (proId == null) {
      sPmInvestigateSituationList = dao.findList(sPmInvestigateSituation);
    } else {
      sPmInvestigateSituationList = dao.findLists(sPmInvestigateSituation);
    }
    List<SPmInvestigateSituation> sPmInvestigateSituationLists = new ArrayList<SPmInvestigateSituation>();
    if (sPmInvestigateSituationList != null) {
      for (SPmInvestigateSituation sPmInvestigateSituations : sPmInvestigateSituationList) {
        SPmJionFiles sPmJionFile = sPmJionFilesDao.getByformId(sPmInvestigateSituations.getId());
        if (sPmJionFile != null) {
          sPmInvestigateSituations.setUploader(sPmJionFile.getUploader());
          sPmInvestigateSituations.setUploadTime(sPmJionFile.getUploadTime());
          sPmInvestigateSituations.setFileName(sPmJionFile.getFileName());
          sPmInvestigateSituations.setFileUrl(sPmJionFile.getFileUrl());
        }
        sPmInvestigateSituationLists.add(sPmInvestigateSituations);
      }
    }
    page.setList(sPmInvestigateSituationLists);
    return page;
  }

  @Transactional(readOnly = false)
  public void save(SPmInvestigateSituation sPmInvestigateSituation) {
    super.save(sPmInvestigateSituation);
  }

  public String save(SPmInvestigateSituation sPmInvestigateSituation, String proId) {
    SPmJionFiles sPmJionFiles = new SPmJionFiles();
    sPmJionFiles.preInsert();
    sPmJionFiles.setFileUrl(sPmInvestigateSituation.getFileUrl());
    sPmJionFiles.setFileName(sPmInvestigateSituation.getFileName());
    sPmJionFiles.setUploader(sPmInvestigateSituation.getUploader());
    sPmJionFiles.setUploadTime(sPmInvestigateSituation.getUploadTime());
    sPmJionFiles.setFormName("培养考察情况");
//    if (userId != null && userId != "") {return "error userId is null";}
//      String proId = redao.findByUserID(userId, "7");
    if (proId != null && proId != "") {
      sPmInvestigateSituation.setProId(proId);
      super.save(sPmInvestigateSituation);
      sPmJionFiles.setFormId(sPmInvestigateSituation.getId());
      sPmJionFilesDao.insert(sPmJionFiles);
      return "success";
    }
    return "error proId is not found";
//            return "请先登录";
  }

  @Transactional(readOnly = false)
  public void delete(SPmInvestigateSituation sPmInvestigateSituation) {
    super.delete(sPmInvestigateSituation);
  }

}