package com.thinkgem.jeesite.modules.partyManage.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMinutes;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmOath;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmOathDao;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 活动纪实Service
 * @author psy
 * @version 2017-04-19
 */
@Service
@Transactional(readOnly = true)
public class SPmOathService extends CrudService<SPmOathDao, SPmOath> {
	
	@Autowired
	  private UserDao userDao;

	  @Autowired
	  private DQRecordDao redao;

	  @Autowired
	  private SPmJionFilesDao sPmJionFilesDao;
	  
	  @Autowired
		private DQRecordService dqRecordService;

	public SPmOath get(String id) {
		return super.get(id);
	}
	
	public SPmOath getByproId(String proId) {
		SPmOath sPmOath = dao.getByproId(proId);
		    if (sPmOath == null) {
		      return sPmOath;
		    }
		    List<SPmOath> list = Lists.newArrayList();
		    list.add(sPmOath);
		    List<SPmOath> list1 = convert(list);
		    return list1.get(0);
	}
	
	public List<SPmOath> findList(SPmOath sPmOath) {
		return super.findList(sPmOath);
	}
	
	public Page<SPmOath> findPage(Page<SPmOath> page, SPmOath sPmOath) {
		return super.findPage(page, sPmOath);
	}
	
	public SPmOath getForms(SPmOath sPmOath) {
		SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmOath.getId());
	    User user = UserUtils.getUser();
	    if (sPmJionFiles == null) {
	    	sPmOath.setUploader(user.getName());
	    	sPmOath.setUploadTime(new Date());
	    } else {
	    	sPmOath.setFileUrl(sPmJionFiles.getFileUrl());
	    	sPmOath.setFileName(sPmJionFiles.getFileName());
	    	sPmOath.setUploader(sPmJionFiles.getUploader());
	    	sPmOath.setUploadTime(sPmJionFiles.getUploadTime());
	    }
	    return sPmOath;
	  }
	
	@Transactional(readOnly = false)
	public String save(SPmOath sPmOath,String proId) {
		SPmJionFiles sPmJionFiles = new SPmJionFiles();
	    sPmJionFiles.preInsert();
	    sPmJionFiles.setFileUrl(sPmOath.getFileUrl());
	    sPmJionFiles.setFileName(sPmOath.getFileName());
	    sPmJionFiles.setUploader(sPmOath.getUploader());
	    sPmJionFiles.setUploadTime(sPmOath.getUploadTime());
	    sPmJionFiles.setFormName("活动纪实");
	    if (proId != null && proId != "") {
	    	SPmOath sPmOath1 = dao.getByproId(proId);
	      if (sPmOath1 == null) {
	    	  sPmOath.setProId(proId);
	        super.save(sPmOath);
	        dqRecordService.fillIn(sPmOath.getProId());
	        sPmJionFiles.setFormId(sPmOath.getId());
	        sPmJionFilesDao.insert(sPmJionFiles);
	        SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmOath.getId());
	        if (sPmJionFileses != null) {
	          sPmJionFiles.setId(sPmJionFileses.getId());
	          sPmJionFiles.preUpdate();
	          sPmJionFilesDao.update(sPmJionFiles);
	        } else {
	          sPmJionFilesDao.insert(sPmJionFiles);
	        }
	        return "success";
	      } else {
	    	  sPmOath.setId(sPmOath1.getId());
	        super.save(sPmOath);
	        dqRecordService.fillIn(sPmOath.getProId());
	        SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmOath.getId());
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
	public void delete(SPmOath sPmOath) {
		super.delete(sPmOath);
	}

	
	/**
	   * 查找用户list
	   *
	   * @return
	   */
	  public List<User> findUserList() {
	    return dao.findUserList();
	  }
	  //显示节点名称
	  private List<SPmOath> convert(List<SPmOath> dataList) {
	    // 查询全部用户
	    List<User> list = findUserList();
	    // 循环展示数据
	    for (SPmOath sPmOaths : dataList) {
	      // 循环用户list
	      
	      StringBuffer sb = new StringBuffer("");
	      // 参加人员
	      if (StringUtils.isNotBlank(sPmOaths.getParticipant())) {
	        // 声明数组 根据逗号劈开
	        String[] sPmOathList = sPmOaths.getParticipant().split(",");
	        // 循环数组 当数据id等于用户id就取用户名字放StringBuffer 并追加
	        for (String oath : sPmOathList) {
	          // 循环用户
	          for (User o : list) {
	            if (oath.equals(o.getId())) {
	              sb.append(o.getName() + ",");
	            }
	          }
	        }
	        // 截取转换过后拼接的参加人员并放置
	        sPmOaths.setParticipant(sb.substring(0, sb.length() - 1));
	      }
	     
	    }
	    return dataList;
	  }
	
	
	
}