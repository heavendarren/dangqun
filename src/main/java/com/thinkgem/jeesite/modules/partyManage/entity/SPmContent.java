/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 这是支部大会会议内容表Entity
 * @author one
 * @version 2017-04-17
 */
public class SPmContent extends DataEntity<SPmContent> {
	
	private static final long serialVersionUID = 1L;
	private String meCont;		// 支部大会会议内容
	private String proId;       //流程数据id
	private String fileUrl; //非此表字段,记录文件路径
	private String fileName; //非此表字段,记录文件名
	private String uploader; //非此表字段,记录上传文件人员
	private Date uploadTime; //非此表字段,记录上传文件时间
	
	
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

	public SPmContent() {
		super();
	}

	public SPmContent(String id){
		super(id);
	}

	@Length(min=0, max=1000, message="支部大会会议内容长度必须介于 0 和 1000 之间")
	public String getMeCont() {
		return meCont;
	}

	public void setMeCont(String meCont) {
		this.meCont = meCont;
	}
	@Length(min=0, max=64, message="流程数据id长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
}