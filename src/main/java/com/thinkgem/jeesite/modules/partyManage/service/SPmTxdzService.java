/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmTxdzDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmTxdz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 通讯地址Service
 *
 * @author zhc
 * @version 2017-04-17
 */
@Service
@Transactional(readOnly = true)
public class SPmTxdzService extends CrudService<SPmTxdzDao, SPmTxdz> {

  @Autowired
  private DQRecordDao redao;

  public SPmTxdz get(String id) {
    return super.get(id);
  }

  public List<SPmTxdz> findList(SPmTxdz sPmTxdz) {
    return super.findList(sPmTxdz);
  }

  public Page<SPmTxdz> findPage(Page<SPmTxdz> page, SPmTxdz sPmTxdz) {
    return super.findPage(page, sPmTxdz);
  }


  public SPmTxdz getByproId(String proId) {
    return dao.getByproId(proId);
  }

  @Transactional(readOnly = false)
  public String save(SPmTxdz sPmTxdz, String proId) {
//    String proId = redao.findByUserID(userId, "1");
    if (proId != null && proId != "") {
      SPmTxdz sPmTxdzs = dao.getByproId(proId);
      if (sPmTxdzs == null) {
        sPmTxdz.setProId(proId);
        super.save(sPmTxdz);
      } else {
        sPmTxdz.setId(sPmTxdzs.getId());
        sPmTxdz.setProId(proId);
        super.save(sPmTxdz);
      }
      return "success";
    }
    return "error proId is not found";
//            return "请先登录";
  }

  @Transactional(readOnly = false)
  public void delete(SPmTxdz sPmTxdz) {
    super.delete(sPmTxdz);
  }

}