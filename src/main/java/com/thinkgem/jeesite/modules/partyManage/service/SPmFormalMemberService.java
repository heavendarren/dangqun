package com.thinkgem.jeesite.modules.partyManage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmFormalMember;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmIntroduceOpinion;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMassOpinion;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmFormalMemberDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;

/**
 * 按期转为中共正式党员的报告Service
 * @author psy
 * @version 2017-04-26
 */
@Service
@Transactional(readOnly = true)
public class SPmFormalMemberService extends CrudService<SPmFormalMemberDao, SPmFormalMember> {
	
	@Autowired
	private DQRecordService dqRecordService;
	
	@Autowired
	private SPmJionFilesDao sPmJionFilesDao;

	public SPmFormalMember get(String id) {
		return super.get(id);
	}
	
	public List<SPmFormalMember> findList(SPmFormalMember sPmFormalMember) {
		return super.findList(sPmFormalMember);
	}
	
	public SPmFormalMember getByproId(String proId) {
	    return dao.getByproId(proId);
	  }
	
	 public SPmFormalMember getForms(SPmFormalMember sPmFormalMember) {
		    SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmFormalMember.getId());
		    sPmFormalMember.setFileUrl(sPmJionFiles.getFileUrl());
		    sPmFormalMember.setFileName(sPmJionFiles.getFileName());
		    sPmFormalMember.setUploader(sPmJionFiles.getUploader());
		    sPmFormalMember.setUploadTime(sPmJionFiles.getUploadTime());
		    return sPmFormalMember;
		  }
	
	public Page<SPmFormalMember> findPage(Page<SPmFormalMember> page, SPmFormalMember sPmFormalMember) {
		return super.findPage(page, sPmFormalMember);
	}
	
	public Page<SPmFormalMember> findPage(Page<SPmFormalMember> page, SPmFormalMember sPmFormalMember, String proId) {
		List<SPmFormalMember> sPmFormalMemberList = new ArrayList<SPmFormalMember>();
		//	    String proId = redao.findByUserID(userId, "23");
		sPmFormalMember.setPage(page);
		if (proId != null) {
			sPmFormalMember = dao.getByproId(proId);
			if (sPmFormalMember != null) {
				sPmFormalMember.setId(sPmFormalMember.getId());
				SPmJionFiles sPmJionFiles = new SPmJionFiles();
				sPmJionFiles.setFormId(sPmFormalMember.getId());
				List<SPmJionFiles> sPmJionFilesList = sPmJionFilesDao.findLists(sPmJionFiles);
				for (SPmJionFiles sPmJionFile : sPmJionFilesList) {
					sPmFormalMember.setFileUrl(sPmJionFile.getFileUrl());
					sPmFormalMember.setFileName(sPmJionFile.getFileName());
					sPmFormalMember.setUploader(sPmJionFile.getUploader());
					sPmFormalMember.setUploadTime(sPmJionFile.getUploadTime());
					sPmFormalMemberList.add(sPmFormalMember);
				}
			} /*else {
				sPmFormalMember = new SPmFormalMember();
				sPmFormalMember.setProId(proId);
				sPmFormalMemberList.add(sPmFormalMember);
			}*/
		} else {
			sPmFormalMemberList.add(sPmFormalMember);
		}
		page.setList(sPmFormalMemberList);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SPmFormalMember sPmFormalMember) {
		super.save(sPmFormalMember);
	}
	
	public String save(SPmFormalMember sPmFormalMember, String proId) {
	    SPmJionFiles sPmJionFiles = new SPmJionFiles();
	    sPmJionFiles.preInsert();
	    sPmJionFiles.setFileUrl(sPmFormalMember.getFileUrl());
	    sPmJionFiles.setFileName(sPmFormalMember.getFileName());
	    sPmJionFiles.setUploader(sPmFormalMember.getUploader());
	    sPmJionFiles.setUploadTime(sPmFormalMember.getUploadTime());
	    sPmJionFiles.setFormName("按期转为中共正式党员报告");
//			if (userId != null && userId != "") {return "error userId is null";}
//				String proId = redao.findByUserID(userId, "25");
	    if (proId != null && proId != "") {
	    	SPmFormalMember sPmFormalMembers = dao.getByproId(proId);
	      if (sPmFormalMembers == null) {
	    	  sPmFormalMember.setProId(proId);
	        super.save(sPmFormalMember);
	        sPmJionFiles.setFormId(sPmFormalMember.getId());
	        sPmJionFilesDao.insert(sPmJionFiles);
	        return "success";
	      } else {
	    	  sPmFormalMember.setId(sPmFormalMembers.getId());
	        super.save(sPmFormalMember);
	        sPmJionFiles.setFormId(sPmFormalMember.getId());
	        SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmFormalMember.getId());
	        if (sPmJionFileses != null) {
	          sPmJionFiles.setId(sPmJionFileses.getId());
	          sPmJionFiles.preUpdate();
	          sPmJionFilesDao.update(sPmJionFiles);
	        } else {
	          sPmJionFilesDao.insert(sPmJionFiles);
	        }
	        return "success";
	      }
	    }
	    return "error proId is not found";
	  }
	
	@Transactional(readOnly = false)
	public void delete(SPmFormalMember sPmFormalMember) {
		super.delete(sPmFormalMember);
	}	
}