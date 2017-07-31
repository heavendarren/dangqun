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
import com.thinkgem.jeesite.modules.partyManage.entity.SPmTalkingS;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmTalkingSDao;

/**
 * 这还算预备党员谈话纪实表Service
 * @author one
 * @version 2017-04-26
 */
@Service
@Transactional(readOnly = true)
public class SPmTalkingSService extends CrudService<SPmTalkingSDao, SPmTalkingS> {
	
	public SPmTalkingS get(String id) {
		return super.get(id);
	}
	
	public List<SPmTalkingS> findList(SPmTalkingS sPmTalkingS) {
		return super.findList(sPmTalkingS);
	}
	
	public Page<SPmTalkingS> findPage(Page<SPmTalkingS> page, SPmTalkingS sPmTalkingS) {
		return super.findPage(page, sPmTalkingS);
	}
	
	@Transactional(readOnly = false)
	public String save(SPmTalkingS sPmTalkingS, String proId) {
		if (proId != null && proId != "") {
			SPmTalkingS sPmTalkingSs = dao.getByproId(proId);
		      if (sPmTalkingSs == null) {
		    	  sPmTalkingS.setProId(proId);
		        super.save(sPmTalkingS);
		      } else {
		    	  sPmTalkingS.setId(sPmTalkingS.getId());
		    	  sPmTalkingS.setProId(proId);
		        super.save(sPmTalkingS);
		      }
		      return "success";
		    }
		    return "error proId is not found";
	}
	
	@Transactional(readOnly = false)
	public void delete(SPmTalkingS sPmTalkingS) {
		super.delete(sPmTalkingS);
	}

	public SPmTalkingS getByproId(String proId) {
		return dao.getByproId(proId);
	}
	
}