/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;

import java.util.List;

/**
 * 文件表DAO接口
 * @author zhc
 * @version 2017-04-20
 */
@MyBatisDao
public interface SPmJionFilesDao extends CrudDao<SPmJionFiles> {
  /**
   * 根据formId查询存不存在已提交的数据
   *
   * @param formId
   * @return 基本情况对象
   */
  public SPmJionFiles getByformId(String formId);

  /**
   * 查询数据列表，如果需要分页，请设置分页对象;
   *
   * @param sPmJionFiles
   * @return
   */
  public List<SPmJionFiles> findLists(SPmJionFiles sPmJionFiles);

}