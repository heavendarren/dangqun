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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmBasic;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMass;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmMassDao;

/**
 * 这是群众意见表Service
 * @author one
 * @version 2017-04-19
 */
@Service
@Transactional(readOnly = true)
public class SPmMassService extends CrudService<SPmMassDao, SPmMass> {

	@Autowired
	private DQRecordService dqRecordService;
	
	public SPmMass get(String id) {
		return super.get(id);
	}
	
	public List<SPmMass> findList(SPmMass sPmMass) {
		return super.findList(sPmMass);
	}
	
	public Page<SPmMass> findPage(Page<SPmMass> page, SPmMass sPmMass) {
		return super.findPage(page, sPmMass);
	}
	
	@Transactional(readOnly = false)
	public String save(SPmMass sPmMass, String proId) {
		if (proId != null && proId != "") {
			SPmMass sPmMasss = dao.getByproId(proId);
		      if (sPmMasss == null) {
		    	  sPmMass.setProId(proId);
		        super.save(sPmMass);
		      } else {
		    	  sPmMass.setId(sPmMasss.getId());
		    	  sPmMass.setProId(proId);
		        super.save(sPmMass);
		      }
		      return "success";
		    }
		    return "error proId is not found";
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmMass sPmMass) {
		super.delete(sPmMass);
	}

	public SPmMass getByproId(String proId) {
		
		return dao.getByproId(proId);
		
	}
	
}