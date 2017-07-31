package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmIntroduceOpinion;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMeetingMinutes;

/**
 * 征求党员意见会议纪要DAO接口
 * @author psy
 * @version 2017-04-26
 */
@MyBatisDao
public interface SPmMeetingMinutesDao extends CrudDao<SPmMeetingMinutes> {
	/**
	 * 根据proId查询存不存在已提交的数据
	 *
	 * @param proId
	 * @return 基本情况对象
	 */
	public SPmMeetingMinutes getByproId(String proId);

	
}