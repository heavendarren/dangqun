/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMrs;

/**
 * 这是群众推荐汇总表DAO接口
 * @author one
 * @version 2017-04-17
 */
@MyBatisDao
public interface SPmMrsDao extends CrudDao<SPmMrs> {

	SPmMrs getByproId(String proId);
	
}