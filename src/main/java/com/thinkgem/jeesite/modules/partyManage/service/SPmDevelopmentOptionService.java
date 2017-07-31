/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmDevelopmentOptionDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmDevelopmentOption;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 确定为发展对象意见Service
 *
 * @author zhc
 * @version 2017-04-24
 */
@Service
@Transactional(readOnly = true)
public class SPmDevelopmentOptionService extends CrudService<SPmDevelopmentOptionDao, SPmDevelopmentOption> {

  @Autowired
  private SPmJionFilesDao sPmJionFilesDao;

  @Autowired
  private DQRecordService dqRecordService;

  public SPmDevelopmentOption get(String id) {
    return super.get(id);
  }

  public List<SPmDevelopmentOption> findList(SPmDevelopmentOption sPmDevelopmentOption) {
    return super.findList(sPmDevelopmentOption);
  }

  public Page<SPmDevelopmentOption> findPage(Page<SPmDevelopmentOption> page, SPmDevelopmentOption sPmDevelopmentOption) {
    return super.findPage(page, sPmDevelopmentOption);
  }

  public SPmDevelopmentOption getByproId(String proId) {
    return dao.getByproId(proId);
  }

  public SPmDevelopmentOption getForms(SPmDevelopmentOption sPmDevelopmentOption) {
    SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmDevelopmentOption.getId());
    User user = UserUtils.getUser();
    if (sPmJionFiles == null) {
      sPmDevelopmentOption.setUploader(user.getName());
      sPmDevelopmentOption.setUploadTime(new Date());
    } else {
      sPmDevelopmentOption.setFileUrl(sPmJionFiles.getFileUrl());
      sPmDevelopmentOption.setFileName(sPmJionFiles.getFileName());
      sPmDevelopmentOption.setUploader(sPmJionFiles.getUploader());
      sPmDevelopmentOption.setUploadTime(sPmJionFiles.getUploadTime());
    }
    return sPmDevelopmentOption;
  }

  @Transactional(readOnly = false)
  public void save(SPmDevelopmentOption sPmDevelopmentOption) {
    super.save(sPmDevelopmentOption);
  }

  public String save(SPmDevelopmentOption sPmDevelopmentOption, String proId) {
    SPmJionFiles sPmJionFiles = new SPmJionFiles();
    sPmJionFiles.preInsert();
    sPmJionFiles.setFileUrl(sPmDevelopmentOption.getFileUrl());
    sPmJionFiles.setFileName(sPmDevelopmentOption.getFileName());
    sPmJionFiles.setUploader(sPmDevelopmentOption.getUploader());
    sPmJionFiles.setUploadTime(sPmDevelopmentOption.getUploadTime());
    sPmJionFiles.setFormName("确定为发展对象意见");
//		if (userId != null && userId != "") {return "error userId is null";}
//			String proId = redao.findByUserID(userId, "7");
    if (proId != null && proId != "") {
      SPmDevelopmentOption sPmDevelopmentOptions = dao.getByproId(proId);
      if (sPmDevelopmentOptions == null) {
        sPmDevelopmentOption.setProId(proId);
        super.save(sPmDevelopmentOption);
        dqRecordService.fillSe(proId);
        sPmJionFiles.setFormId(sPmDevelopmentOption.getId());
        sPmJionFilesDao.insert(sPmJionFiles);
        return "success";
      } else {
        sPmDevelopmentOption.setId(sPmDevelopmentOptions.getId());
        super.save(sPmDevelopmentOption);
        dqRecordService.fillSe(proId);
        sPmJionFiles.setFormId(sPmDevelopmentOption.getId());
        SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmDevelopmentOption.getId());
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
  public void delete(SPmDevelopmentOption sPmDevelopmentOption) {
    super.delete(sPmDevelopmentOption);
  }

}