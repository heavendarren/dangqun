/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPositiveApp;

/**
 * 这是转正申请表DAO接口
 * @author one
 * @version 2017-04-28
 */
@MyBatisDao
public interface SPmPositiveAppDao extends CrudDao<SPmPositiveApp> {

	
	SPmPositiveApp getByproId(String proId);

	List<SPmJionFiles> findJoinList(SPmJionFiles sPmPositive);
	
}