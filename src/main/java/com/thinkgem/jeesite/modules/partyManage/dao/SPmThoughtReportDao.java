/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmThoughtReport;

import java.util.List;

/**
 * 思想汇报DAO接口
 * @author zhc
 * @version 2017-04-24
 */
@MyBatisDao
public interface SPmThoughtReportDao extends CrudDao<SPmThoughtReport> {

  /**
   * 查询数据列表，如果需要分页，请设置分页对象;
   *
   * @param sPmThoughtReport
   * @return
   */
  List<SPmThoughtReport> findLists(SPmThoughtReport sPmThoughtReport);
}