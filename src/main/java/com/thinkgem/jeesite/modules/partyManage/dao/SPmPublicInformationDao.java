/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPublicInformation;

import java.util.List;

/**
 * 公示信息DAO接口
 * @author zhc
 * @version 2017-04-20
 */
@MyBatisDao
public interface SPmPublicInformationDao extends CrudDao<SPmPublicInformation> {

  /**
   * 根据proId查询存不存在已提交的数据
   *
   * @param proId
   * @return 基本情况对象
   */
  public SPmPublicInformation getByproId(String proId);

  /**
   * 查询数据列表，如果需要分页，请设置分页对象;
   *
   * @param sPmPublicInformation
   * @return
   */
  public List<SPmPublicInformation> findLists(SPmPublicInformation sPmPublicInformation);
}