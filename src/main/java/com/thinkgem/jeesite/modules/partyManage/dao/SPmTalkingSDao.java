/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmTalkingS;

/**
 * 这还算预备党员谈话纪实表DAO接口
 * @author one
 * @version 2017-04-26
 */
@MyBatisDao
public interface SPmTalkingSDao extends CrudDao<SPmTalkingS> {
 
	SPmTalkingS getByproId(String proId);
	
}