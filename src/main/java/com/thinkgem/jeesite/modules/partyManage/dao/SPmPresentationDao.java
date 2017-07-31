package com.thinkgem.jeesite.modules.partyManage.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAppUpload;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPartyMen;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPresentation;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPublicInformation;

/**
 * 预备党员接收材料报告DAO接口
 * @author psy
 * @version 2017-04-24
 */
@MyBatisDao
public interface SPmPresentationDao extends CrudDao<SPmPresentation> {
	/**
	 * 根据proId查询存不存在已提交的数据
	 *
	 * @param proId
	 * @return 基本情况对象
	 */
	public SPmPresentation getByproId(String proId);

	/**
	 * custom method findJoinList;  自定义方法 联合查询
	 * 
	 * @param SPmPresentation
	 * @return
	 */
	public List<SPmJionFiles> findJoinList(SPmJionFiles sPmJionFiles);
	
}