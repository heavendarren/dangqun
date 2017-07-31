/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmContent;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMass;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPrf;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmContentDao;

/**
 * 这是支部大会会议内容表Service
 * @author one
 * @version 2017-04-17
 */
@Service
@Transactional(readOnly = true)
public class SPmContentService extends CrudService<SPmContentDao, SPmContent> {

	@Autowired
	private DQRecordService dqRecordService;
	
	public SPmContent get(String id) {
		return super.get(id);
	}
	
	public List<SPmContent> findList(SPmContent sPmContent) {
		return super.findList(sPmContent);
	}
	
	public Page<SPmContent> findPage(Page<SPmContent> page, SPmContent sPmContent) {
		return super.findPage(page, sPmContent);
	}
	
	@Transactional(readOnly = false)
	public void save(SPmContent sPmContent) {
		super.save(sPmContent);
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmContent sPmContent) {
		super.delete(sPmContent);
	}

	public SPmContent getByproId(String proId) {
		return dao.getByproId(proId);
		
	}
	public String save(SPmContent sPmContent, String proId) {
		if (proId != null && proId != "") {
			SPmContent sPmContents = dao.getByproId(proId);
		      if (sPmContents == null) {
		    	  sPmContent.setProId(proId);
		        super.save(sPmContent);
		      } else {
		    	  sPmContent.setId(sPmContents.getId());
		    	  sPmContent.setProId(proId);
		        super.save(sPmContent);
		      }
		      return "success";
		    }
		    return "error proId is not found";

	}
	
}