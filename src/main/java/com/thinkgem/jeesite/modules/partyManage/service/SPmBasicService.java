/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.ProcessDataDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmBasicDao;
import com.thinkgem.jeesite.modules.partyManage.entity.ProcessData;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmBasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 基本情况Service
 *
 * @author zhc
 * @version 2017-04-17
 */
@Service
@Transactional(readOnly = true)
public class SPmBasicService extends CrudService<SPmBasicDao, SPmBasic> {

  @Autowired
  private ProcessDataDao processDataDao;

  public SPmBasic get(String id) {
    return super.get(id);
  }

  public List<SPmBasic> findList(SPmBasic sPmBasic) {
    return super.findList(sPmBasic);
  }

  public Page<SPmBasic> findPage(Page<SPmBasic> page, SPmBasic sPmBasic) {
    return super.findPage(page, sPmBasic);
  }

  public SPmBasic getByproId(String proId) {
    return dao.getByproId(proId);
  }

  @Transactional(readOnly = false)
  public String save(SPmBasic sPmBasic, String proId) {
//    if (userId != null && userId != "") {return "error userId is null";}
//      String proId = redao.findByUserID(userId, "1");
    if (proId != null && proId != "") {
      SPmBasic sPmBasics = dao.getByproId(proId);
      if (sPmBasics == null) {
        sPmBasic.setProId(proId);
        super.save(sPmBasic);
      } else {
        sPmBasic.setId(sPmBasics.getId());
        sPmBasic.setProId(proId);
        super.save(sPmBasic);
      }
      return "success";
    }
    return "error proId is not found";
//            return "请先登录";
  }

  @Transactional(readOnly = false)
  public void delete(SPmBasic sPmBasic) {
    super.delete(sPmBasic);
  }

  public ProcessData down(String proId, String node) {
    ProcessData processData = processDataDao.findByID(proId);
    processData = processDataDao.findByNode(processData.getAppID(), node);
    return processData;
  }
}