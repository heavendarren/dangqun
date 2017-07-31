package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmFormalMember;


/**
 * 按期转为中共正式党员的报告DAO接口
 * @author psy
 * @version 2017-04-26
 */
@MyBatisDao
public interface SPmFormalMemberDao extends CrudDao<SPmFormalMember> {
	/**
	 * 根据proId查询存不存在已提交的数据
	 *
	 * @param proId
	 * @return 基本情况对象
	 */
	public SPmFormalMember getByproId(String proId);
	
}