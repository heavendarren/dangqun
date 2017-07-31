package com.thinkgem.jeesite.modules.partyManage.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAnnouncement;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPublicInformation;

/**
 * 发展对象公示DAO接口
 * @author psy
 * @version 2017-04-24
 */
@MyBatisDao
public interface SPmAnnouncementDao extends CrudDao<SPmAnnouncement> {
	/**
	 * 根据proId查询存不存在已提交的数据
	 *
	 * @param proId
	 * @return 基本情况对象
	 */
	public SPmAnnouncement getByproId(String proId);

	/**
	 * 查询数据列表，如果需要分页，请设置分页对象;
	 *
	 * @param sPmAnnouncement
	 * @return
	 */
	public List<SPmAnnouncement> findLists(SPmAnnouncement sPmAnnouncement);

}