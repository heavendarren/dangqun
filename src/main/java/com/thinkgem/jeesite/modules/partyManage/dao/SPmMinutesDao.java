package com.thinkgem.jeesite.modules.partyManage.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAppUpload;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMinutes;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmThoughtReport;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 会议记录DAO接口
 * @author psy
 * @version 2017-04-18
 */
@MyBatisDao
public interface SPmMinutesDao extends CrudDao<SPmMinutes> {
	
	/**
	   * 根据proId查询存不存在已提交的数据
	   *
	   * @param proId
	   * @return 基本情况对象
	   */
	public SPmMinutes getByproId(String proId);
	/**
	   * 查找用户list
	   * @return
	   */
	public List<User> findUserList();
	/**
   * 查询数据列表，如果需要分页，请设置分页对象;
   *
   * @param sPmThoughtReport
   * @return
   */
    public List<SPmMinutes> findLists(SPmMinutes sPmMinutes);
	
}