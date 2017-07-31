package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.modules.sys.dao.CodeTreeDao;
import com.thinkgem.jeesite.modules.sys.entity.CodeTree;

@Service
@Transactional(readOnly = true)
public class CodeTreeService extends TreeService<CodeTreeDao, CodeTree> {

	public List<CodeTree> findListByType(String type, String pId) {
		return dao.findListByType(type, pId);
	}

}
