/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmExamineRecord;

/**
 * 发展对象审核备案DAO接口
 * @author zhc
 * @version 2017-04-24
 */
@MyBatisDao
public interface SPmExamineRecordDao extends CrudDao<SPmExamineRecord> {
  /**
   * 根据proId查询存不存在已提交的数据
   *
   * @param proId
   * @return 基本情况对象
   */
  SPmExamineRecord getByproId(String proId);
}