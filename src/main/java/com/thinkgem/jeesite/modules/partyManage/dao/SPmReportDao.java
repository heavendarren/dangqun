/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmReport;

/**
 * 这是政审报告表DAO接口
 * @author one
 * @version 2017-04-21
 */
@MyBatisDao
public interface SPmReportDao extends CrudDao<SPmReport> {

	SPmReport getByproId(String proId);
	
}