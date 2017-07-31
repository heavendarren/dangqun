package com.thinkgem.jeesite.modules.partyManage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmBasic;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmParthMenber;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmTalking;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmTalkingDao;

/**
 * 谈话纪实Service
 * 
 * @author psy
 * @version 2017-04-17
 */
@Service
@Transactional(readOnly = true)
public class SPmTalkingService extends CrudService<SPmTalkingDao, SPmTalking> {

	@Autowired
	private DQRecordService dqRecordService;

	public SPmTalking get(String id) {
		return super.get(id);
	}

	public List<SPmTalking> findList(SPmTalking sPmTalking) {
		return super.findList(sPmTalking);
	}

	public Page<SPmTalking> findPage(Page<SPmTalking> page,
			SPmTalking sPmTalking) {
		return super.findPage(page, sPmTalking);
	}

	public Page<SPmTalking> findPage(Page<SPmTalking> page,
			SPmTalking sPmTalking,String proId) {
		return super.findPage(page, sPmTalking);
	}

	public SPmTalking getByproId(String proId) {
		return dao.getByproId(proId);
	}

	@Transactional(readOnly = false)
	public void save(SPmTalking sPmTalking) {
		super.save(sPmTalking);
	}


	@Transactional(readOnly = false)
	public String save(SPmTalking sPmTalking,String proId) {
		if (proId != null && proId != "") {
			SPmTalking sPmTalkings = dao.getByproId(proId);
		      if (sPmTalkings == null) {
		    	  sPmTalking.setProId(proId);
		        super.save(sPmTalking);
		        dqRecordService.fillIn(proId);
		      } else {
		    	  sPmTalking.setId(sPmTalkings.getId());
		    	  sPmTalking.setProId(proId);
		        super.save(sPmTalking);
		        dqRecordService.fillIn(proId);
		      }
		      return "success";
		    }
		    return "error proId is not found";
//		            return "请先登录";
		  }


	@Transactional(readOnly = false)
	public void delete(SPmTalking sPmTalking) {
		super.delete(sPmTalking);
	}

}