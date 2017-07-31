/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmContactSettingsDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmContactSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 培养联系人Service
 *
 * @author zhc
 * @version 2017-04-24
 */
@Service
@Transactional(readOnly = true)
public class SPmContactSettingsService extends CrudService<SPmContactSettingsDao, SPmContactSettings> {

  @Autowired
  private DQRecordService dqRecordService;

  public SPmContactSettings get(String id) {
    return super.get(id);
  }

  public List<SPmContactSettings> findList(SPmContactSettings sPmContactSettings) {
    return super.findList(sPmContactSettings);
  }

  public Page<SPmContactSettings> findPage(Page<SPmContactSettings> page, SPmContactSettings sPmContactSettings) {
    return super.findPage(page, sPmContactSettings);
  }

  public Page<SPmContactSettings> findPage(Page<SPmContactSettings> page, SPmContactSettings sPmContactSettings, String proId) {
//    String proId = redao.findByUserID(userId, "7");
    sPmContactSettings.setProId(proId);
    sPmContactSettings.setPage(page);
    List<SPmContactSettings> sPmContactSettingsList = new ArrayList<SPmContactSettings>();
    if (proId == null) {
      sPmContactSettingsList = dao.findList(sPmContactSettings);
    } else {
      sPmContactSettingsList = dao.findLists(sPmContactSettings);
    }
    if (sPmContactSettingsList.size() == 0) {
      sPmContactSettingsList = new ArrayList<SPmContactSettings>();
      sPmContactSettingsList.add(sPmContactSettings);
      page.setList(sPmContactSettingsList);
    } else {
      page.setList(sPmContactSettingsList);
    }
    return page;
  }

  @Transactional(readOnly = false)
  public void save(SPmContactSettings sPmContactSettings) {
    super.save(sPmContactSettings);
  }

  public String save(SPmContactSettings sPmContactSettings, String proId) {
//		if (userId != null && userId != "") {return "error userId is null";}
//			String proId = redao.findByUserID(userId, "7");
    if (proId != null && proId != "") {
      sPmContactSettings.setProId(proId);
      super.save(sPmContactSettings);
      dqRecordService.fillIn(proId);
      return "success";
    }
    return "error proId is not found";
//            return "请先登录";
  }

  @Transactional(readOnly = false)
  public void delete(SPmContactSettings sPmContactSettings) {
    super.delete(sPmContactSettings);
  }

}