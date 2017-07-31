/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJszw;

import java.util.List;

/**
 * 技术职务DAO接口
 *
 * @author zhc
 * @version 2017-04-17
 */
@MyBatisDao
public interface SPmJszwDao extends CrudDao<SPmJszw> {
  /**
   * 查询数据列表，如果需要分页，请设置分页对象;
   *
   * @param sPmJszw
   * @return
   */
  public List<SPmJszw> findLists(SPmJszw sPmJszw);
}