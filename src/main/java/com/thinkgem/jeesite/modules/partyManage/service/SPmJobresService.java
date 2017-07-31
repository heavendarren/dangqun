/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJobresDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJobres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 工作简历Service
 *
 * @author zhc
 * @version 2017-04-17
 */
@Service
@Transactional(readOnly = true)
public class SPmJobresService extends CrudService<SPmJobresDao, SPmJobres> {

  @Autowired
  private DQRecordDao redao;

  public SPmJobres get(String id) {
    return super.get(id);
  }

  public List<SPmJobres> findList(SPmJobres sPmJobres) {
    return super.findList(sPmJobres);
  }

  public Page<SPmJobres> findPage(Page<SPmJobres> page, SPmJobres sPmJobres) {
    return super.findPage(page, sPmJobres);
  }

  public Page<SPmJobres> findPage(Page<SPmJobres> page, SPmJobres sPmJobres, String proId) {
//    String proId = redao.findByUserID(userId, "1");
    sPmJobres.setProId(proId);
    sPmJobres.setPage(page);
    List<SPmJobres> sPmJobresList = new ArrayList<SPmJobres>();
    if (proId == null) {
      sPmJobresList = dao.findList(sPmJobres);
    } else {
      sPmJobresList = dao.findLists(sPmJobres);
    }
    if (sPmJobresList.size() == 0) {
      sPmJobresList = new ArrayList<SPmJobres>();
      sPmJobresList.add(sPmJobres);
      page.setList(sPmJobresList);
    } else {
      page.setList(sPmJobresList);
    }
    return page;
  }

  @Transactional(readOnly = false)
  public String save(SPmJobres sPmJobres, String proId) {
//    if (userId != null && userId != "") {return "error userId is null";}
//      String proId = redao.findByUserID(userId, "1");
    if (proId != null && proId != "") {
      sPmJobres.setProId(proId);
      super.save(sPmJobres);
      return "success";
    }
    return "error proId is not found";
//            return "请先登录";
  }


  @Transactional(readOnly = false)
  public void delete(SPmJobres sPmJobres) {
    super.delete(sPmJobres);
  }

}