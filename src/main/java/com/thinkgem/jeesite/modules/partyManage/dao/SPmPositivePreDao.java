package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPositivePre;

/**
 * 转正工作预审DAO接口
 * @author psy
 * @version 2017-04-26
 */
@MyBatisDao
public interface SPmPositivePreDao extends CrudDao<SPmPositivePre> {
	/**
	 * 根据proId查询存不存在已提交的数据
	 *
	 * @param proId
	 * @return 基本情况对象
	 */
	public SPmPositivePre getByproId(String proId);
	
}