/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMinutes;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMinutesS;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 这是会议记录表DAO接口
 * @author one
 * @version 2017-04-26
 */
@MyBatisDao
public interface SPmMinutesSDao extends CrudDao<SPmMinutesS> {

	

	/**
	   * 根据proId查询存不存在已提交的数据
	   *
	   * @param proId
	   * @return 基本情况对象
	   */
	SPmMinutesS getByproId(String proId);
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
    public List<SPmMinutesS> findLists(SPmMinutesS sPmMinutesS);
	
}