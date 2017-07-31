package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmBasic;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmTalking;

/**
 * 谈话纪实DAO接口
 * @author psy
 * @version 2017-04-21
 */
@MyBatisDao
public interface SPmTalkingDao extends CrudDao<SPmTalking> {
	/**
     * 根据proId查询存不存在已提交的数据
     *
     * @param proId
     * @return 基本情况对象
     */
    public SPmTalking getByproId(String proId);
	
}