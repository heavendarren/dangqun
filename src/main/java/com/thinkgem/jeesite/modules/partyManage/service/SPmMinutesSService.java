/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMinutesS;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmMinutesSDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 这是会议记录表Service
 * @author one
 * @version 2017-04-26
 */
@Service
@Transactional(readOnly = true)
public class SPmMinutesSService extends CrudService<SPmMinutesSDao, SPmMinutesS> {


	@Autowired
    private SPmJionFilesDao sPmJionFilesDao;
	
	public SPmMinutesS get(String id) {
		return super.get(id);
	}
	
	public List<SPmMinutesS> findList(SPmMinutesS sPmMinutesS) {
		return super.findList(sPmMinutesS);
	}
	
	public Page<SPmMinutesS> findPage(Page<SPmMinutesS> page, SPmMinutesS sPmMinutesS) {
		return super.findPage(page, sPmMinutesS);
	}
	
	@Transactional(readOnly = false)
	public String save(SPmMinutesS sPmMinutesS, String proId) {
		/*if (sPmMinutesS != null) {
	          super.save(sPmMinutesS);
	          return "success";
	        }else{
	          if(sPmMinutesS.getId()!=null){
	            super.save(sPmMinutesS);
	            return "success";
	          }
	          return "this information is exsit";
	        }*/
		
		SPmJionFiles sPmJionFiles = new SPmJionFiles();
	    sPmJionFiles.preInsert();
	    sPmJionFiles.setFileUrl(sPmMinutesS.getFileUrl());
	    sPmJionFiles.setFileName(sPmMinutesS.getFileName());
	    sPmJionFiles.setUploader(sPmMinutesS.getUploader());
	    sPmJionFiles.setUploadTime(sPmMinutesS.getUploadTime());
	    sPmJionFiles.setFormName("会议记录");
	    if (proId != null && proId != "") {
	      SPmMinutesS sPmMinutes1 = dao.getByproId(proId);
	      if (sPmMinutes1 == null) {
	        sPmMinutesS.setProId(proId);
	        super.save(sPmMinutesS);
	        sPmJionFiles.setFormId(sPmMinutesS.getId());
	        sPmJionFilesDao.insert(sPmJionFiles);
	        SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmMinutesS.getId());
	        if (sPmJionFileses != null) {
	          sPmJionFiles.setId(sPmJionFileses.getId());
	          sPmJionFiles.preUpdate();
	          sPmJionFilesDao.update(sPmJionFiles);
	        } else {
	          sPmJionFilesDao.insert(sPmJionFiles);
	        }
	        return "success";
	      } else {
	        sPmMinutesS.setId(sPmMinutes1.getId());
	        super.save(sPmMinutesS);
	        SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmMinutesS.getId());
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
	public void delete(SPmMinutesS sPmMinutesS) {
		super.delete(sPmMinutesS);
	}

	public SPmMinutesS getByproId(String proId) {
		return dao.getByproId(proId);
	}

	public SPmMinutesS getForms(SPmMinutesS sPmMinutes1) {
		SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmMinutes1.getId());
		User user = UserUtils.getUser();
		if(sPmJionFiles == null){
			sPmMinutes1.setUploader(user.getName());
			sPmMinutes1.setUploadTime(new Date());
		}else{
			sPmMinutes1.setFileUrl(sPmJionFiles.getFileUrl());
			sPmMinutes1.setFileName(sPmJionFiles.getFileName());
			sPmMinutes1.setUploader(sPmJionFiles.getUploader());
			sPmMinutes1.setUploadTime(sPmJionFiles.getUploadTime());
		}
	    return sPmMinutes1;
		
	}
	
	 /**
	   * 查找用户list
	   *
	   * @return
	   */
	  public List<User> findUserList() {
	    return dao.findUserList();
	  }

	  private List<SPmMinutesS> convert(List<SPmMinutesS> dataList) {
	    // 查询全部用户
	    List<User> list = findUserList();
	    // 循环展示数据
	    for (SPmMinutesS sPmMinutes2 : dataList) {
	      // 循环用户list
	      for (User o : list) {
	        // 每循环一次展示数据,就循环全部用户,直到查找对应数据
	        // 当数据id等于用户id就取用户名字放临时变量 用作展示
	        if (StringUtils.isNotBlank(sPmMinutes2.getHost())) {
	          if (sPmMinutes2.getHost().equals(o.getId())) {
	            sPmMinutes2.setHostName(o.getName());
	          }
	        }
	        if (StringUtils.isNotBlank(sPmMinutes2.getNoteTaker())) {
	          if (sPmMinutes2.getNoteTaker().equals(o.getId())) {
	            sPmMinutes2.setNoteTakerName(o.getName());
	          }
	        }
	        if (StringUtils.isNotBlank(sPmMinutes2.getPerAtt())) {
		      if (sPmMinutes2.getPerAtt().equals(o.getId())) {
		        sPmMinutes2.setPerAttName(o.getName());
		      }
		    }
	        
	      }
	      StringBuffer sb = new StringBuffer("");
	      // 参加人员
	      if (StringUtils.isNotBlank(sPmMinutes2.getAttenList())) {
	        // 声明数组 根据逗号劈开
	        String[] attenList = sPmMinutes2.getAttenList().split(",");
	        // 循环数组 当数据id等于用户id就取用户名字放StringBuffer 并追加
	        for (String atten : attenList) {
	          // 循环用户
	          for (User o : list) {
	            if (atten.equals(o.getId())) {
	              sb.append(o.getName() + ",");
	            }
	          }
	        }
	        // 截取转换过后拼接的参加人员并放置
	        sPmMinutes2.setAttenList(sb.substring(0, sb.length() - 1));
	      }
	      // 缺勤人员
	      if (StringUtils.isNotBlank(sPmMinutes2.getAbsList())) {
	        sb = new StringBuffer("");
	        // 声明数组 根据逗号劈开
	        String[] attenList = sPmMinutes2.getAbsList().split(",");
	        // 循环数组 当数据id等于用户id就取用户名字放StringBuffer 并追加
	        for (String atten : attenList) {
	          // 循环用户
	          for (User o : list) {
	            if (atten.equals(o.getId())) {
	              sb.append(o.getName() + ",");
	            }
	          }
	        }
	        // 截取转换过后拼接的参加人员并放置
	        sPmMinutes2.setAbsList(sb.substring(0, sb.length() - 1));
	      }
	      
	   // 列席人员
	      if (StringUtils.isNotBlank(sPmMinutes2.getAttenList())) {
	        // 声明数组 根据逗号劈开
	        String[] attenList = sPmMinutes2.getAttenList().split(",");
	        // 循环数组 当数据id等于用户id就取用户名字放StringBuffer 并追加
	        for (String atten : attenList) {
	          // 循环用户
	          for (User o : list) {
	            if (atten.equals(o.getId())) {
	              sb.append(o.getName() + ",");
	            }
	          }
	        }
	        // 截取转换过后拼接的参加人员并放置
	        sPmMinutes2.setAttenList(sb.substring(0, sb.length() - 1));
	      }
	      
	    }
	    return dataList;
	  }
	
	
	
}






















