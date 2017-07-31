package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.CodeTree;

@MyBatisDao
public interface CodeTreeDao extends TreeDao<CodeTree> {

	List<CodeTree> findListByType(String type, String pId);

}
