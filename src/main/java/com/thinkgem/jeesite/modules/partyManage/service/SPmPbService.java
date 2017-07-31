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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmPb;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmPbDao;

/**
 * 这是党总支会议记录表Service
 * @author one
 * @version 2017-04-26
 */
@Service
@Transactional(readOnly = true)
public class SPmPbService extends CrudService<SPmPbDao, SPmPb> {

	
	public SPmPb get(String id) {
		return super.get(id);
	}
	
	public List<SPmPb> findList(SPmPb sPmPb) {
		return super.findList(sPmPb);
	}
	
	public Page<SPmPb> findPage(Page<SPmPb> page, SPmPb sPmPb) {
		return super.findPage(page, sPmPb);
	}
	
	@Transactional(readOnly = false)
	public String save(SPmPb sPmPb, String proId) {
		if (proId != null && proId != "") {
			SPmPb sPmPbs = dao.getByproId(proId);
		      if (sPmPbs == null) {
		    	  sPmPb.setProId(proId);
		        super.save(sPmPb);
		      } else {
		    	  sPmPb.setId(sPmPbs.getId());
		    	  sPmPb.setProId(proId);
		        super.save(sPmPb);
		      }
		      return "success";
		    }
		    return "error proId is not found";
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmPb sPmPb) {
		super.delete(sPmPb);
	}

	public SPmPb getByproId(String proId) {
		return dao.getByproId(proId);
	}
	
}