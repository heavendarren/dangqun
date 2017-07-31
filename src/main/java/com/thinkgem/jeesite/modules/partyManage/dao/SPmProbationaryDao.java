/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmProbationary;
import org.apache.ibatis.annotations.Param;

/**
 * 这是预备党员考察纪实表DAO接口
 * @author one
 * @version 2017-04-28
 */
@MyBatisDao
public interface SPmProbationaryDao extends CrudDao<SPmProbationary> {

    SPmProbationary getByproId(String proId);

    SPmProbationary getByProIdType(@Param("proId") String proId, @Param("type")String type);

}