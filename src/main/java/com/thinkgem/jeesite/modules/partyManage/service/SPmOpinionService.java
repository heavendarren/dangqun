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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMass;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmOpinion;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmOpinionDao;

/**
 * 这是党员意见表Service
 * @author one
 * @version 2017-04-19
 */
@Service
@Transactional(readOnly = true)
public class SPmOpinionService extends CrudService<SPmOpinionDao, SPmOpinion> {

	@Autowired
	private DQRecordService dqRecordService;
	
	public SPmOpinion get(String id) {
		return super.get(id);
	}
	
	public List<SPmOpinion> findList(SPmOpinion sPmOpinion) {
		return super.findList(sPmOpinion);
	}
	
	public Page<SPmOpinion> findPage(Page<SPmOpinion> page, SPmOpinion sPmOpinion) {
		return super.findPage(page, sPmOpinion);
	}
	
	@Transactional(readOnly = false)
	public String save(SPmOpinion sPmOpinion, String proId) {
		if (proId != null && proId != "") {
			SPmOpinion sPmOpinions = dao.getByproId(proId);
		      if (sPmOpinions == null) {
		    	  sPmOpinion.setProId(proId);
		        super.save(sPmOpinion);
		      } else {
		    	  sPmOpinion.setId(sPmOpinions.getId());
		    	  sPmOpinion.setProId(proId);
		        super.save(sPmOpinion);
		      }
		      return "success";
		    }
		    return "error proId is not found";
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmOpinion sPmOpinion) {
		super.delete(sPmOpinion);
	}

	public SPmOpinion getByproId(String proId) {
		
		return dao.getByproId(proId);
	}
	
}