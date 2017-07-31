/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmInvestigateSituation;

import java.util.List;

/**
 * 培养考察情况DAO接口
 * @author zhc
 * @version 2017-04-24
 */
@MyBatisDao
public interface SPmInvestigateSituationDao extends CrudDao<SPmInvestigateSituation> {
  /**
   * 查询数据列表，如果需要分页，请设置分页对象;
   *
   * @param sPmInvestigateSituation
   * @return
   */
  List<SPmInvestigateSituation> findLists(SPmInvestigateSituation sPmInvestigateSituation);
}