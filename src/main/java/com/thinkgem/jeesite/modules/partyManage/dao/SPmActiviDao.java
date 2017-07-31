/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmActivi;

/**
 * 这是入党积极分子备案表DAO接口
 * @author one
 * @version 2017-04-18
 */
@MyBatisDao
public interface SPmActiviDao extends CrudDao<SPmActivi> {

	SPmActivi getByproId(String proId);
	
}