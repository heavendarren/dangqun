/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * 公示信息Entity
 *
 * @author zhc
 * @version 2017-04-20
 */
public class SPmPublicInformation extends DataEntity<SPmPublicInformation> {

  private static final long serialVersionUID = 1L;
  private String skipFlag;    // 跳过标记
  private String proId;    // 流程数据id

  private List<String> fileUrls; //非此表字段,记录文件路径
  private String fileUrl; //非此表字段,记录文件路径
  private List<String> fileNames; //非此表字段,记录文件名
  private String fileName; //非此表字段,记录文件名
  private String uploader; //非此表字段,记录上传文件人员
  private Date uploadTime; //非此表字段,记录上传文件时间

  public SPmPublicInformation() {
    super();
  }

  public SPmPublicInformation(String id) {
    super(id);
  }

  @Length(min = 0, max = 64, message = "跳过标记长度必须介于 0 和 64 之间")
  public String getSkipFlag() {
    return skipFlag;
  }

  public void setSkipFlag(String skipFlag) {
    this.skipFlag = skipFlag;
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

  public String getFileUrl() {
    return fileUrl;
  }

  public void setFileUrl(String fileUrl) {
    this.fileUrl = fileUrl;
  }

  public List<String> getFileNames() {
    return fileNames;
  }

  public void setFileNames(List<String> fileNames) {
    this.fileNames = fileNames;
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