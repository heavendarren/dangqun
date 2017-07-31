package com.thinkgem.jeesite.modules.partyManage.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPartyMen;

/**
 * 入党志愿书DAO接口
 * @author psy
 * @version 2017-04-27
 */
@MyBatisDao
public interface SPmPartyMenDao extends CrudDao<SPmPartyMen> {
	/**
	 * 根据proId查询存不存在已提交的数据
	 *
	 * @param proId
	 * @return 基本情况对象
	 */
	public SPmPartyMen getByproId(String proId);

	/**
	 * 查询数据列表，如果需要分页，请设置分页对象;
	 *
	 * @param SPmPartyMen
	 * @return
	 */
	public List<SPmPartyMen> findLists(SPmPartyMen sPmPartyMen);
	
	/**
	 * custom method findJoinList;  自定义方法 联合查询
	 * 
	 * @param SPmPartyMen
	 * @return
	 */
	public List<SPmJionFiles> findJoinList(SPmJionFiles sPmPartyMen);
	

}