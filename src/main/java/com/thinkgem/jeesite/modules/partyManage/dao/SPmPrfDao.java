/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPrf;

/**
 * 这是群团推荐表DAO接口
 * @author one
 * @version 2017-04-17
 */
@MyBatisDao
public interface SPmPrfDao extends CrudDao<SPmPrf> {

	SPmPrf getByproId(String proId);
	
}