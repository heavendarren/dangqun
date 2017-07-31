/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmAppSituationDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAppSituation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 入党申请情况Service
 *
 * @author zhc
 * @version 2017-04-17
 */
@Service
@Transactional(readOnly = true)
public class SPmAppSituationService extends CrudService<SPmAppSituationDao, SPmAppSituation> {

  public SPmAppSituation get(String id) {
    return super.get(id);
  }

  public List<SPmAppSituation> findList(SPmAppSituation sPmAppSituation) {
    return super.findList(sPmAppSituation);
  }

  public Page<SPmAppSituation> findPage(Page<SPmAppSituation> page, SPmAppSituation sPmAppSituation) {
    return super.findPage(page, sPmAppSituation);
  }

  public SPmAppSituation getByproId(String proId) {
    return dao.getByproId(proId);
  }

  @Transactional(readOnly = false)
  public String save(SPmAppSituation sPmAppSituation, String proId) {
//    if (userId != null && userId != "") {return "error userId is null";}
//      String proId = redao.findByUserID(userId, "1");
    if (proId != null && proId != "") {
      SPmAppSituation sPmAppSituations = dao.getByproId(proId);
      if (sPmAppSituations == null) {
        sPmAppSituation.setProId(proId);
        super.save(sPmAppSituation);
      } else {
        sPmAppSituation.setId(sPmAppSituations.getId());
        sPmAppSituation.setProId(proId);
        super.save(sPmAppSituation);
      }
      return "success";
    }
    return "error proId is not found";
//            return "请先登录";
  }

  @Transactional(readOnly = false)
  public void delete(SPmAppSituation sPmAppSituation) {
    super.delete(sPmAppSituation);
  }

}