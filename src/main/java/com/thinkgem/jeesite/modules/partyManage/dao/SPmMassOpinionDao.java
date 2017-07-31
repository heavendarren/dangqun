package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmDevelopmentOption;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMassOpinion;

/**
 * 征求群众意见会议纪要DAO接口
 * @author psy
 * @version 2017-04-26
 */
@MyBatisDao
public interface SPmMassOpinionDao extends CrudDao<SPmMassOpinion> {
	
	/**
	   * 根据proId查询存不存在已提交的数据
	   *
	   * @param proId
	   * @return 基本情况对象
	   */
	SPmMassOpinion getByproId(String proId);
	
}