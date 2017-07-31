/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJszwDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJszw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 技术职务Service
 *
 * @author zhc
 * @version 2017-04-17
 */
@Service
@Transactional(readOnly = true)
public class SPmJszwService extends CrudService<SPmJszwDao, SPmJszw> {

  @Autowired
  private DQRecordDao redao;

  public SPmJszw get(String id) {
    return super.get(id);
  }

  public List<SPmJszw> findList(SPmJszw sPmJszw) {
    return super.findList(sPmJszw);
  }

  public Page<SPmJszw> findPage(Page<SPmJszw> page, SPmJszw sPmJszw) {
    return super.findPage(page, sPmJszw);
  }

  public Page<SPmJszw> findPage(Page<SPmJszw> page, SPmJszw sPmJszw, String proId) {
//    String proId = redao.findByUserID(userId, "1");
    sPmJszw.setProId(proId);
    sPmJszw.setPage(page);
    List<SPmJszw> sPmJszwList = new ArrayList<SPmJszw>();
    if (proId == null) {
      sPmJszwList = dao.findList(sPmJszw);
    } else {
      sPmJszwList = dao.findLists(sPmJszw);
    }
    if (sPmJszwList.size() == 0) {
      sPmJszwList = new ArrayList<SPmJszw>();
      sPmJszwList.add(sPmJszw);
      page.setList(sPmJszwList);
    } else {
      page.setList(sPmJszwList);
    }
    return page;
  }

  @Transactional(readOnly = false)
  public String save(SPmJszw sPmJszw, String proId) {
//    if (userId != null && userId != "") {return "error userId is null";}
//      String proId = redao.findByUserID(userId, "1");
    if (proId != null && proId != "") {
      sPmJszw.setProId(proId);
      super.save(sPmJszw);
      return "success";
    }
    return "error proId is not found";
//            return "请先登录";
  }

  @Transactional(readOnly = false)
  public void delete(SPmJszw sPmJszw) {
    super.delete(sPmJszw);
  }

}