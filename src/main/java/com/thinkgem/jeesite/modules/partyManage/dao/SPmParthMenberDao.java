package com.thinkgem.jeesite.modules.partyManage.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmAppSituation;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmContactSettings;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmParthMenber;


/**
 * 确定入党介绍人DAO接口
 * @author psy
 * @version 2017-04-25
 */
@MyBatisDao
public interface SPmParthMenberDao extends CrudDao<SPmParthMenber> {
	
	/**
     * 根据proId查询存不存在已提交的数据
     *
     * @param proId
     * @return 确定入党介绍人情况对象
     */
    public SPmParthMenber getByproId(String proId);
	
	 /**
	   * 查询数据列表，如果需要分页，请设置分页对象;
	   *
	   * @param SPmParthMenber
	   * @return
	   */
	 public List<SPmParthMenber> findLists(SPmParthMenber sPmParthMenber);
}