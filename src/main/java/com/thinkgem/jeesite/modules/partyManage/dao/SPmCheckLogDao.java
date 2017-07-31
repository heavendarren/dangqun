package com.thinkgem.jeesite.modules.partyManage.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmCheckLog;

/**
 * 审核记录
 * @author xingtianpeng
 *
 */
@MyBatisDao
public interface SPmCheckLogDao extends CrudDao<SPmCheckLog> {

	public List<SPmCheckLog> findByIdNode(String recordID, String nodeID);
}
