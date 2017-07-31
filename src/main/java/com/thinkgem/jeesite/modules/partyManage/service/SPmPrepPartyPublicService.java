/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmPrepPartyPublicDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPrepPartyPublic;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 拟接收预备党员的公示Service
 *
 * @author zhc
 * @version 2017-04-24
 */
@Service
@Transactional(readOnly = true)
public class SPmPrepPartyPublicService extends CrudService<SPmPrepPartyPublicDao, SPmPrepPartyPublic> {

  @Autowired
  private DQRecordService dqRecordService;

  @Autowired
  private SPmJionFilesDao sPmJionFilesDao;

  public SPmPrepPartyPublic get(String id) {
    return super.get(id);
  }

  public List<SPmPrepPartyPublic> findList(SPmPrepPartyPublic sPmPrepPartyPublic) {
    return super.findList(sPmPrepPartyPublic);
  }

  public Page<SPmPrepPartyPublic> findPage(Page<SPmPrepPartyPublic> page, SPmPrepPartyPublic sPmPrepPartyPublic) {
    return super.findPage(page, sPmPrepPartyPublic);
  }

  public SPmPrepPartyPublic getForms(SPmPrepPartyPublic sPmPrepPartyPublic) {
    SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmPrepPartyPublic.getId());
    User user = UserUtils.getUser();
    if (sPmJionFiles == null) {
      sPmPrepPartyPublic.setUploader(user.getName());
      sPmPrepPartyPublic.setUploadTime(new Date());
    } else {
      sPmPrepPartyPublic.setFileUrl(sPmJionFiles.getFileUrl());
      sPmPrepPartyPublic.setFileName(sPmJionFiles.getFileName());
      sPmPrepPartyPublic.setUploader(sPmJionFiles.getUploader());
      sPmPrepPartyPublic.setUploadTime(sPmJionFiles.getUploadTime());
    }
    return sPmPrepPartyPublic;
  }

  public SPmPrepPartyPublic getByproId(String proId) {
    return dao.getByproId(proId);
  }

  @Transactional(readOnly = false)
  public void save(SPmPrepPartyPublic sPmPrepPartyPublic) {
    super.save(sPmPrepPartyPublic);
  }

  public String save(SPmPrepPartyPublic sPmPrepPartyPublic, String proId) {
    SPmJionFiles sPmJionFiles = new SPmJionFiles();
    sPmJionFiles.preInsert();
    sPmJionFiles.setFileUrl(sPmPrepPartyPublic.getFileUrl());
    sPmJionFiles.setFileName(sPmPrepPartyPublic.getFileName());
    sPmJionFiles.setUploader(sPmPrepPartyPublic.getUploader());
    sPmJionFiles.setUploadTime(sPmPrepPartyPublic.getUploadTime());
    sPmJionFiles.setFormName("拟接收预备党员的公示");
//    if (userId != null && userId != "") {return "error userId is null";}
//      String proId = redao.findByUserID(userId, "16");
    if (proId != null && proId != "") {
      SPmPrepPartyPublic sPmPrepPartyPublics = dao.getByproId(proId);
      if (sPmPrepPartyPublics == null) {
        sPmPrepPartyPublic.setProId(proId);
        super.save(sPmPrepPartyPublic);
        dqRecordService.fillIn(proId);
        sPmJionFiles.setFormId(sPmPrepPartyPublic.getId());
        sPmJionFilesDao.insert(sPmJionFiles);
        return "success";
      } else {
        sPmPrepPartyPublic.setId(sPmPrepPartyPublics.getId());
        super.save(sPmPrepPartyPublic);
        dqRecordService.fillIn(proId);
        sPmJionFiles.setFormId(sPmPrepPartyPublic.getId());
        SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmPrepPartyPublic.getId());
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
  public void delete(SPmPrepPartyPublic sPmPrepPartyPublic) {
    super.delete(sPmPrepPartyPublic);
  }

}