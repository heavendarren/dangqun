/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmTermTrainDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmTermTrain;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 发展对象短期集中培训Service
 *
 * @author zhc
 * @version 2017-04-24
 */
@Service
@Transactional(readOnly = true)
public class SPmTermTrainService extends CrudService<SPmTermTrainDao, SPmTermTrain> {

  @Autowired
  private DQRecordService dqRecordService;

  @Autowired
  private SPmJionFilesDao sPmJionFilesDao;

  public SPmTermTrain get(String id) {
    return super.get(id);
  }

  public List<SPmTermTrain> findList(SPmTermTrain sPmTermTrain) {
    return super.findList(sPmTermTrain);
  }

  public Page<SPmTermTrain> findPage(Page<SPmTermTrain> page, SPmTermTrain sPmTermTrain) {
    return super.findPage(page, sPmTermTrain);
  }

  public SPmTermTrain getByproId(String proId) {
    return dao.getByproId(proId);
  }

  public SPmTermTrain getForms(SPmTermTrain sPmTermTrain) {
    SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmTermTrain.getId());
    User user = UserUtils.getUser();
    if (sPmJionFiles == null) {
      sPmTermTrain.setUploader(user.getName());
      sPmTermTrain.setUploadTime(new Date());
    } else {
      sPmTermTrain.setFileUrl(sPmJionFiles.getFileUrl());
      sPmTermTrain.setFileName(sPmJionFiles.getFileName());
      sPmTermTrain.setUploader(sPmJionFiles.getUploader());
      sPmTermTrain.setUploadTime(sPmJionFiles.getUploadTime());
    }
    return sPmTermTrain;
  }

  @Transactional(readOnly = false)
  public void save(SPmTermTrain sPmTermTrain) {
    super.save(sPmTermTrain);
  }

  public String save(SPmTermTrain sPmTermTrain, String proId) {
    SPmJionFiles sPmJionFiles = new SPmJionFiles();
    sPmJionFiles.preInsert();
    sPmJionFiles.setFileUrl(sPmTermTrain.getFileUrl());
    sPmJionFiles.setFileName(sPmTermTrain.getFileName());
    sPmJionFiles.setUploader(sPmTermTrain.getUploader());
    sPmJionFiles.setUploadTime(sPmTermTrain.getUploadTime());
    sPmJionFiles.setFormName("发展对象短期集中培训");
//    if (userId != null && userId != "") {return "error userId is null";}
//      String proId = redao.findByUserID(userId, "13");
    if (proId != null && proId != "") {
      SPmTermTrain sPmTermTrains = dao.getByproId(proId);
      if (sPmTermTrains == null) {
        sPmTermTrain.setProId(proId);
        super.save(sPmTermTrain);
        dqRecordService.fillIn(proId);
        sPmJionFiles.setFormId(sPmTermTrain.getId());
        sPmJionFilesDao.insert(sPmJionFiles);
        return "success";
      } else {
        sPmTermTrain.setId(sPmTermTrains.getId());
        super.save(sPmTermTrain);
        dqRecordService.fillIn(proId);
        sPmJionFiles.setFormId(sPmTermTrain.getId());
        SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmTermTrain.getId());
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
  public void delete(SPmTermTrain sPmTermTrain) {
    super.delete(sPmTermTrain);
  }

}