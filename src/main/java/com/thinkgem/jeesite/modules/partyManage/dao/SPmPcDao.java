/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPc;

/**
 * 这是党委会议记录表DAO接口
 * @author one
 * @version 2017-04-26
 */
@MyBatisDao
public interface SPmPcDao extends CrudDao<SPmPc> {

	SPmPc getByproId(String proId); 
	
}