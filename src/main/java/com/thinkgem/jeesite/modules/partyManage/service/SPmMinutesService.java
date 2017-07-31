package com.thinkgem.jeesite.modules.partyManage.service;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmJionFilesDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmMinutesDao;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmJionFiles;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmMinutes;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 会议记录Service
 *
 * @author psy
 * @version 2017-04-18
 */
@Service
@Transactional(readOnly = true)
public class SPmMinutesService extends CrudService<SPmMinutesDao, SPmMinutes> {
  @Autowired
  private UserDao userDao;

  @Autowired
  private DQRecordService DQRecordService;

  @Autowired
  private SPmJionFilesDao sPmJionFilesDao;

  public SPmMinutes get(String id) {
    return super.get(id);
  }

  public SPmMinutes getByproId(String proId) {
    SPmMinutes sPmMinutes = dao.getByproId(proId);
    if (sPmMinutes == null) {
      return sPmMinutes;
    }
    List<SPmMinutes> list = Lists.newArrayList();
    list.add(sPmMinutes);
    List<SPmMinutes> list1 = convert(list);
    return list1.get(0);
  }

  /**
   * 通过编号获取用户名
   *
   * @return new Object[]{}
   */
  public List<Object[]> findByIds(String ids) {
    if (ids == null || ids == "") {
      return new ArrayList<Object[]>();
    }
    List<Object[]> list = Lists.newArrayList();
    String[] idss = StringUtils.split(ids, ",");
    User user = null;
    for (int i = 0; (idss.length - i) > 0; i++) {
      user = UserUtils.get(idss[i]);
      list.add(new Object[]{user.getProId(), user.getId(), user.getName()});
    }
    return list;
  }

  public SPmMinutes getByproIds(String proId) {
    SPmMinutes sPmMinutes = dao.getByproId(proId);
    if (sPmMinutes == null) {
      return sPmMinutes;
    } else {
      if (sPmMinutes.getHost() != null) {
        sPmMinutes.setHostName(UserUtils.get(sPmMinutes.getHost()).getName());
      }
      if (sPmMinutes.getNoteTaker() != null) {
        sPmMinutes.setNoteTakerName(UserUtils.get(sPmMinutes.getNoteTaker()).getName());
      }
      return sPmMinutes;
    }
  }

  public List<SPmMinutes> findList(SPmMinutes sPmMinutes) {
    return super.findList(sPmMinutes);
  }

  public Page<SPmMinutes> findPage(Page<SPmMinutes> page,
                                   SPmMinutes sPmMinutes) {
    Page<SPmMinutes> tempPage = super.findPage(page, sPmMinutes);
    tempPage.setList(convert(tempPage.getList()));
    return tempPage;
  }

  public Page<SPmMinutes> findPage(Page<SPmMinutes> page,
                                   SPmMinutes sPmMinutes, String proId) {
    Page<SPmMinutes> tempPage = super.findPage(page, sPmMinutes);
    tempPage.setList(convert(tempPage.getList()));
    return tempPage;
  }


  public SPmMinutes getForms(SPmMinutes sPmMinutes) {
    SPmJionFiles sPmJionFiles = sPmJionFilesDao.getByformId(sPmMinutes.getId());
    User user = UserUtils.getUser();
    if (sPmJionFiles == null) {
      sPmMinutes.setUploader(user.getName());
      sPmMinutes.setUploadTime(new Date());
    } else {
      sPmMinutes.setFileUrl(sPmJionFiles.getFileUrl());
      sPmMinutes.setFileName(sPmJionFiles.getFileName());
      sPmMinutes.setUploader(sPmJionFiles.getUploader());
      sPmMinutes.setUploadTime(sPmJionFiles.getUploadTime());
    }
    return sPmMinutes;
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
  private List<SPmMinutes> convert(List<SPmMinutes> dataList) {
    // 查询全部用户
    List<User> list = findUserList();
    // 循环展示数据
    for (SPmMinutes sPmMinutes2 : dataList) {
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
    }
    return dataList;
  }

  @Transactional(readOnly = false)
  public String save(SPmMinutes sPmMinutes, String proId) {
    SPmJionFiles sPmJionFiles = new SPmJionFiles();
    sPmJionFiles.preInsert();
    sPmJionFiles.setFileUrl(sPmMinutes.getFileUrl());
    sPmJionFiles.setFileName(sPmMinutes.getFileName());
    sPmJionFiles.setUploader(sPmMinutes.getUploader());
    sPmJionFiles.setUploadTime(sPmMinutes.getUploadTime());
    sPmJionFiles.setFormName("会议记录");
    if (proId != null && proId != "") {
      SPmMinutes sPmMinutes1 = dao.getByproId(proId);
      if (sPmMinutes1 == null) {
        sPmMinutes.setProId(proId);
        super.save(sPmMinutes);
        sPmJionFiles.setFormId(sPmMinutes.getId());
        DQRecordService.fillIn(proId);
        sPmJionFiles.setFormId(sPmMinutes.getId());
        sPmJionFilesDao.insert(sPmJionFiles);
        SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmMinutes.getId());
        if (sPmJionFileses != null) {
          sPmJionFiles.setId(sPmJionFileses.getId());
          sPmJionFiles.preUpdate();
          sPmJionFilesDao.update(sPmJionFiles);
        } else {
          sPmJionFilesDao.insert(sPmJionFiles);
        }
        return "success";
      } else {
        sPmMinutes.setId(sPmMinutes1.getId());
        sPmMinutes.setProId(sPmMinutes1.getProId());
        super.save(sPmMinutes);
        sPmJionFiles.setFormId(sPmMinutes.getId());
        DQRecordService.fillIn(proId);
        SPmJionFiles sPmJionFileses = sPmJionFilesDao.getByformId(sPmMinutes.getId());
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
  public void delete(SPmMinutes sPmMinutes) {
    super.delete(sPmMinutes);
  }

}