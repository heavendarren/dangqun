/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMass;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPc;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmPcDao;

/**
 * 这是党委会议记录表Service
 * @author one
 * @version 2017-04-26
 */
@Service
@Transactional(readOnly = true)
public class SPmPcService extends CrudService<SPmPcDao, SPmPc> {

	
	public SPmPc get(String id) {
		return super.get(id);
	}
	
	public List<SPmPc> findList(SPmPc sPmPc) {
		return super.findList(sPmPc);
	}
	
	public Page<SPmPc> findPage(Page<SPmPc> page, SPmPc sPmPc) {
		return super.findPage(page, sPmPc);
	}
	
	@Transactional(readOnly = false)
	public String save(SPmPc sPmPc, String proId) {
		if (proId != null && proId != "") {
			SPmPc sPmPcs = dao.getByproId(proId);
		      if (sPmPcs == null) {
		    	  sPmPc.setProId(proId);
		        super.save(sPmPc);
		      } else {
		    	  sPmPc.setId(sPmPcs.getId());
		    	  sPmPc.setProId(proId);
		        super.save(sPmPc);
		      }
		      return "success";
		    }
		    return "error proId is not found";
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmPc sPmPc) {
		super.delete(sPmPc);
	}

	public SPmPc getByproId(String proId) {
		return dao.getByproId(proId);
	}
	
}