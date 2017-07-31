/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmWorkPostDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmWorkPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 工作岗位Service
 *
 * @author zhc
 * @version 2017-04-17
 */
@Service
@Transactional(readOnly = true)
public class SPmWorkPostService extends CrudService<SPmWorkPostDao, SPmWorkPost> {

  @Autowired
  private DQRecordDao redao;

  public SPmWorkPost get(String id) {
    return super.get(id);
  }

  public List<SPmWorkPost> findList(SPmWorkPost sPmWorkPost) {
    return super.findList(sPmWorkPost);
  }

  public Page<SPmWorkPost> findPage(Page<SPmWorkPost> page, SPmWorkPost sPmWorkPost) {
    return super.findPage(page, sPmWorkPost);
  }

  public Page<SPmWorkPost> findPage(Page<SPmWorkPost> page, SPmWorkPost sPmWorkPost, String proId) {
//    String proId = redao.findByUserID(userId, "1");
    sPmWorkPost.setProId(proId);
    sPmWorkPost.setPage(page);
    List<SPmWorkPost> sPmWorkPostList = new ArrayList<SPmWorkPost>();
    if (proId == null) {
      sPmWorkPostList = dao.findList(sPmWorkPost);
    } else {
      sPmWorkPostList = dao.findLists(sPmWorkPost);
    }
    if (sPmWorkPostList.size() == 0) {
      sPmWorkPostList = new ArrayList<SPmWorkPost>();
      sPmWorkPostList.add(sPmWorkPost);
      page.setList(sPmWorkPostList);
    } else {
      page.setList(sPmWorkPostList);
    }
    return page;
  }

  @Transactional(readOnly = false)
  public String save(SPmWorkPost sPmWorkPost, String proId) {
//    if (userId != null && userId != "") {return "error userId is null";}
//      String proId = redao.findByUserID(userId, "1");
    if (proId != null && proId != "") {
      sPmWorkPost.setProId(proId);
      super.save(sPmWorkPost);
      return "success";
    }
    return "error proId is not found";
//            return "请先登录";
  }

  @Transactional(readOnly = false)
  public void delete(SPmWorkPost sPmWorkPost) {
    super.delete(sPmWorkPost);
  }

}