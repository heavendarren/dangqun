package com.thinkgem.jeesite.modules.partyManage.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmOath;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 宣誓活动纪实DAO接口
 * @author psy
 * @version 2017-04-25
 */
@MyBatisDao
public interface SPmOathDao extends CrudDao<SPmOath> {
	/**
	   * 根据proId查询存不存在已提交的数据
	   *
	   * @param proId
	   * @return 基本情况对象
	   */
	public SPmOath getByproId(String proId);
	/**
	   * 查找用户list
	   * @return
	   */
	public List<User> findUserList();
	
	
}