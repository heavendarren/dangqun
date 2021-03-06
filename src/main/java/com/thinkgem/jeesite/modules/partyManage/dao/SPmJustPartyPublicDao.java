/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJustPartyPublic;

/**
 * 这是拟转正公示表DAO接口
 * @author one
 * @version 2017-04-28
 */
@MyBatisDao
public interface SPmJustPartyPublicDao extends CrudDao<SPmJustPartyPublic> {
	
	/**
	   * 根据proId查询存不存在已提交的数据
	   *
	   * @param proId
	   * @return 基本情况对象
	   */
	  
	
	SPmJustPartyPublic getByproId(String proId);
	
	
}