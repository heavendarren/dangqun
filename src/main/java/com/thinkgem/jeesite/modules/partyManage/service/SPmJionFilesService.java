/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文件表Service
 * @author zhc
 * @version 2017-04-20
 */
@Service
@Transactional(readOnly = true)
public class SPmJionFilesService extends CrudService<SPmJionFilesDao, SPmJionFiles> {

	public SPmJionFiles get(String id) {
		return super.get(id);
	}
	
	public List<SPmJionFiles> findList(SPmJionFiles sPmJionFiles) {
		return super.findList(sPmJionFiles);
	}
	
	public Page<SPmJionFiles> findPage(Page<SPmJionFiles> page, SPmJionFiles sPmJionFiles) {
		return super.findPage(page, sPmJionFiles);
	}
	
	@Transactional(readOnly = false)
	public void save(SPmJionFiles sPmJionFiles) {
		super.save(sPmJionFiles);
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmJionFiles sPmJionFiles) {
		super.delete(sPmJionFiles);
	}
	
}