/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmPmsSDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPmsS;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 这还算预备党员表决票汇总表Service
 * @author one
 * @version 2017-04-26
 */
@Service
@Transactional(readOnly = true)
public class SPmPmsSService extends CrudService<SPmPmsSDao, SPmPmsS> {

	@Autowired
	private DQRecordService dqRecordService;

	@Autowired
	private SPmJionFilesService sPmJionFilesService;

  @Autowired
  private SPmJionFilesDao sPmJionFilesDao;

	public SPmPmsS get(String id) {
		return super.get(id);
	}

	public List<SPmPmsS> findList(SPmPmsS sPmPmsS) {
		return super.findList(sPmPmsS);
	}

	public Page<SPmPmsS> findPage(Page<SPmPmsS> page, SPmPmsS sPmPmsS) {
		return super.findPage(page, sPmPmsS);
	}

	@Transactional(readOnly = false)
	public String save(SPmPmsS sPmPmsS, String proId) {
		if (proId != null && proId != "") {
			SPmPmsS sPmPmsSs = dao.getByproId(proId);
		      if (sPmPmsSs == null) {
		    	  sPmPmsS.setProId(proId);
		        super.save(sPmPmsS);
		      } else {
		    	  sPmPmsS.setId(sPmPmsSs.getId());
		    	  sPmPmsS.setProId(proId);
		        super.save(sPmPmsS);
		      }
		      return "success";
		    }
		    return "error proId is not found";
	}

  public String saves(SPmPmsS sPmPmsS, String proId) {
    SPmJionFiles sPmJionFiles = new SPmJionFiles();
    sPmJionFiles.setFileUrl(sPmPmsS.getFileUrl());
    sPmJionFiles.setFileName(sPmPmsS.getFileName());
    sPmJionFiles.setUploader(sPmPmsS.getUploader());
    sPmJionFiles.setUploadTime(sPmPmsS.getUploadTime());
    sPmJionFiles.setFormName("预备党员转正汇总表");
    if (proId != null && proId != "") {
      SPmPmsS sPmPmsSs = dao.getByproId(proId);
      if (sPmPmsSs == null) {
        sPmPmsS.setProId(proId);
        super.save(sPmPmsS);
        sPmJionFiles.setFormId(sPmPmsS.getId());
        sPmJionFilesDao.insert(sPmJionFiles);
      } else {
        sPmPmsS.setId(sPmPmsSs.getId());
        sPmPmsS.setProId(proId);
        super.save(sPmPmsS);
        sPmJionFiles.setFormId(sPmPmsSs.getId());
        sPmJionFilesDao.update(sPmJionFiles);
      }
      return "success";
    }
    return "error proId is not found";
  }

  public SPmPmsS getForms(SPmPmsS sPmPmsS) {
    SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmPmsS.getId());
    User user = UserUtils.getUser();
    if (sPmJionFiles == null) {
      sPmPmsS.setUploader(user.getName());
      sPmPmsS.setUploadTime(new Date());
    } else {
      sPmPmsS.setFileUrl(sPmJionFiles.getFileUrl());
      sPmPmsS.setFileName(sPmJionFiles.getFileName());
      sPmPmsS.setUploader(sPmJionFiles.getUploader());
      sPmPmsS.setUploadTime(sPmJionFiles.getUploadTime());
    }
    return sPmPmsS;
  }

  @Transactional(readOnly = false)
	public void delete(SPmPmsS sPmPmsS) {
		super.delete(sPmPmsS);
	}

	public SPmPmsS getByproId(String proId) {
		return dao.getByproId(proId);
	}

}