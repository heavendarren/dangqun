/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 文件表Entity
 * @author zhc
 * @version 2017-04-20
 */
public class SPmJionFiles extends DataEntity<SPmJionFiles> {
	
	private static final long serialVersionUID = 1L;
	private String formId;		// 表单id
	private String fileUrl;		// 文件路径
	private String fileName;		// 文件名
	private String uploader;		// 上传人
	private Date uploadTime;		// 上传时间
	private String formName;		// 表单名
	
	public SPmJionFiles() {
		super();
	}

	public SPmJionFiles(String id){
		super(id);
	}

	@Length(min=0, max=64, message="表单id长度必须介于 0 和 64 之间")
	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}
	
	@Length(min=0, max=255, message="文件路径长度必须介于 0 和 255 之间")
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	@Length(min=0, max=255, message="文件名长度必须介于 0 和 255 之间")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Length(min=0, max=64, message="上传人长度必须介于 0 和 64 之间")
	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	@Length(min=0, max=255, message="表单名长度必须介于 0 和 255 之间")
	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}
	
}