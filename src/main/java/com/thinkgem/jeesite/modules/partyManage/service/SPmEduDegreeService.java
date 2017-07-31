/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmEduDegreeDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmEduDegree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 学历学位Service
 *
 * @author zhc
 * @version 2017-04-19
 */
@Service
@Transactional(readOnly = true)
public class SPmEduDegreeService extends CrudService<SPmEduDegreeDao, SPmEduDegree> {

  @Autowired
  private DQRecordDao redao;

  public SPmEduDegree get(String id) {
    return super.get(id);
  }

  public List<SPmEduDegree> findList(SPmEduDegree sPmEduDegree) {
    return super.findList(sPmEduDegree);
  }

  public Page<SPmEduDegree> findPage(Page<SPmEduDegree> page, SPmEduDegree sPmEduDegree) {
    return super.findPage(page, sPmEduDegree);
  }

  public Page<SPmEduDegree> findPage(Page<SPmEduDegree> page, SPmEduDegree sPmEduDegree, String proId) {
//    String proId = redao.findByUserID(userId, "1");
    sPmEduDegree.setProId(proId);
    sPmEduDegree.setPage(page);
    List<SPmEduDegree> sPmEduDegreeList = new ArrayList<SPmEduDegree>();
    if (proId == null) {
      sPmEduDegreeList = dao.findList(sPmEduDegree);
    } else {
      sPmEduDegreeList = dao.findLists(sPmEduDegree);
    }
    if (sPmEduDegreeList.size() == 0) {
      sPmEduDegreeList = new ArrayList<SPmEduDegree>();
      sPmEduDegreeList.add(sPmEduDegree);
      page.setList(sPmEduDegreeList);
    } else {
      page.setList(sPmEduDegreeList);
    }
    return page;
  }

  @Transactional(readOnly = false)
  public String save(SPmEduDegree sPmEduDegree, String proId) {
//    if (userId != null && userId != "") {return "error userId is null";}
//      String proId = redao.findByUserID(userId, "1");
    if (proId != null && proId != "") {
      sPmEduDegree.setProId(proId);
      super.save(sPmEduDegree);
      return "success";
    }
    return "error proId is not found";
//            return "请先登录";
  }

  @Transactional(readOnly = false)
  public void delete(SPmEduDegree sPmEduDegree) {
    super.delete(sPmEduDegree);
  }

}