/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 这是证明材料表Entity
 * @author one
 * @version 2017-04-21
 */
public class SPmProve extends DataEntity<SPmProve> {
	
	private static final long serialVersionUID = 1L;
	private String upload;		// 文件上传
	private String proId;		// 流程数据id
	private String fileUrl; //非此表字段,记录文件路径
	private String fileName; //非此表字段,记录文件名
	private String uploader; //非此表字段,记录上传文件人员
	private Date uploadTime; //非此表字段,记录上传文件时间
	
	public SPmProve() {
		super();
	}

	public SPmProve(String id){
		super(id);
	}

	@Length(min=0, max=255, message="文件上传长度必须介于 0 和 255 之间")
	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}
	
	@Length(min=0, max=255, message="流程数据id长度必须介于 0 和 255 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
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