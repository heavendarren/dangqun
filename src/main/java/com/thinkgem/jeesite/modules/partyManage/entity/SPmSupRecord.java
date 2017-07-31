/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;
import java.util.List;

/**
 * 补录信息Entity
 *
 * @author zhc
 * @version 2017-05-02
 */
public class SPmSupRecord extends DataEntity<SPmSupRecord> {

  private static final long serialVersionUID = 1L;
  private String node;    // 流程节点
  private String proId;    // 流程数据id

  private List<String> fileUrls; //非此表字段,记录文件路径
  private List<String> fileNames; //非此表字段,记录文件名

  private String fileUrl; //非此表字段,记录文件路径
  private String fileName; //非此表字段,记录文件名
  private String uploader; //非此表字段,记录文件名
  private Date uploadTime; //非此表字段,记录文件名

  public SPmSupRecord() {
    super();
  }

  public SPmSupRecord(String id) {
    super(id);
  }

  @Length(min = 0, max = 64, message = "流程节点长度必须介于 0 和 64 之间")
  public String getNode() {
    return node;
  }

  public void setNode(String node) {
    this.node = node;
  }

  @Length(min = 0, max = 64, message = "流程数据id长度必须介于 0 和 64 之间")
  public String getProId() {
    return proId;
  }

  public void setProId(String proId) {
    this.proId = proId;
  }

  public List<String> getFileUrls() {
    return fileUrls;
  }

  public void setFileUrls(List<String> fileUrls) {
    this.fileUrls = fileUrls;
  }

  public List<String> getFileNames() {
    return fileNames;
  }

  public void setFileNames(List<String> fileNames) {
    this.fileNames = fileNames;
  }

  public String getFileUrl() {
    return fileUrl;
  }

  public void setFileUrl(String fileUrl) {
    this.fileUrl = fileUrl;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getUploader() {
    return uploader;
  }

  public void setUploader(String uploader) {
    this.uploader = uploader;
  }

  public Date getUploadTime() {
    return uploadTime;
  }

  public void setUploadTime(Date uploadTime) {
    this.uploadTime = uploadTime;
  }
}