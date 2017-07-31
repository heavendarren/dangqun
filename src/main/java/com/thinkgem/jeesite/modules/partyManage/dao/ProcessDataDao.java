package com.thinkgem.jeesite.modules.partyManage.dao;


import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.ProcessData;

/**
 * 流程进度dao
 * @author xingtianpeng
 *
 */
@MyBatisDao
public interface ProcessDataDao extends CrudDao<ProcessData> {

	public ProcessData findByAppID(String appID, String status);
	
	public List<ProcessData> findlist(String appID);
	
	public ProcessData findByNode(String id, String node);
	
	public ProcessData findByID(String id);
}
