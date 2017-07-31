/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmFamilyDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmFamily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 家庭成员Service
 *
 * @author zhc
 * @version 2017-04-17
 */
@Service
@Transactional(readOnly = true)
public class SPmFamilyService extends CrudService<SPmFamilyDao, SPmFamily> {

  @Autowired
  private DQRecordDao redao;

  public SPmFamily get(String id) {
    return super.get(id);
  }

  public List<SPmFamily> findList(SPmFamily sPmFamily) {
    return super.findList(sPmFamily);
  }

  public Page<SPmFamily> findPage(Page<SPmFamily> page, SPmFamily sPmFamily) {
    return super.findPage(page, sPmFamily);
  }

  public Page<SPmFamily> findPage(Page<SPmFamily> page, SPmFamily sPmFamily, String proId) {
//    String proId = redao.findByUserID(userId, "1");
    sPmFamily.setProId(proId);
    sPmFamily.setPage(page);
    List<SPmFamily> sPmFamilyList = new ArrayList<SPmFamily>();
    if (proId == null) {
      sPmFamilyList = dao.findList(sPmFamily);
    } else {
      sPmFamilyList = dao.findLists(sPmFamily);
    }
    if (sPmFamilyList.size() == 0) {
      sPmFamilyList = new ArrayList<SPmFamily>();
      sPmFamilyList.add(sPmFamily);
      page.setList(sPmFamilyList);
    } else {
      page.setList(sPmFamilyList);
    }
    return page;
  }

  @Transactional(readOnly = false)
  public String save(SPmFamily sPmFamily, String proId) {
//    if (userId != null && userId != "") {return "error userId is null";}
//      String proId = redao.findByUserID(userId, "1");
    if (proId != null && proId != "") {
      sPmFamily.setProId(proId);
      super.save(sPmFamily);
      return "success";
    }
    return "error proId is not found";
//            return "请先登录";
  }

  @Transactional(readOnly = false)
  public void delete(SPmFamily sPmFamily) {
    super.delete(sPmFamily);
  }

}