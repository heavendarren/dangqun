package com.thinkgem.jeesite.modules.partyManage.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.DQRecord;

/**
 * 发展纪实dao
 * @author xigntianpeng
 * 4-11
 */
@MyBatisDao
public interface DQRecordDao extends CrudDao<DQRecord> {

	/**
	 * 姓名查询
	 */
	public List<DQRecord> findByName(String name, String orgID);
	
	/**
	 * 阶段查询
	 */
	public List<DQRecord> findByStage(String stage, String userID, String officeID);
	
	/**
	 * ID查询
	 */
	public DQRecord findByID(String ID);
	
	/**
	 * ID查询
	 */
	public DQRecord findByRID(String ID);
	
	/**
	 * 纪实 添加
	 */
	public int saveRecord(DQRecord record);
	
	/**
	 * 流程进度查询 
	 */
	public String findByUserID(String userID, String node);
	
	/**
	 * 用户
	 * @param ID
	 * @return
	 */
	public DQRecord findOne(String userID);
	
	/**
	 * 查询书记用户ID
	 * @param recordId
	 * @param roleId
	 * @return
	 */
	public String seckUserID(String recordId, String roleId);
}
