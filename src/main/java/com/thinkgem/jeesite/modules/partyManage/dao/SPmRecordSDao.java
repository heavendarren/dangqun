/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.dao;

import java.util.HashMap;
import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmRecordS;

@MyBatisDao
public interface SPmRecordSDao extends CrudDao<SPmRecordS> {

	List<HashMap<String, Object>> getRecursionList(String officeId);

}