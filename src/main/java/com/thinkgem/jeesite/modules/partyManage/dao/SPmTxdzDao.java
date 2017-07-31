/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmTxdz;

/**
 * 通讯地址DAO接口
 *
 * @author zhc
 * @version 2017-04-17
 */
@MyBatisDao
public interface SPmTxdzDao extends CrudDao<SPmTxdz> {
  /**
   * 根据proId查询存不存在已提交的数据
   *
   * @param proId
   * @return 入党申请人情况对象
   */
  public SPmTxdz getByproId(String proId);
}