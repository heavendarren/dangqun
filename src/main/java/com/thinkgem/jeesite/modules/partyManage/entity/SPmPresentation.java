package com.thinkgem.jeesite.modules.partyManage.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 预备党员接收材料报告Entity
 * @author psy
 * @version 2017-04-24
 */
public class SPmPresentation extends DataEntity<SPmPresentation> {

	private static final long serialVersionUID = 1L;
	private String reviseFlag;		// 修改标记
	private String proId;		// 流程ID

	private String fileUrl; //非此表字段,记录文件路径
	private String fileName; //非此表字段,记录文件名
	private String uploader; //非此表字段,记录上传文件人员
	private Date uploadTime; //非此表字段,记录上传文件时间

	public SPmPresentation() {
		super();
	}

	public SPmPresentation(String id){
		super(id);
	}

	@Length(min=0, max=64, message="修改标记长度必须介于 0 和 64 之间")
	public String getReviseFlag() {
		return reviseFlag;
	}

	public void setReviseFlag(String reviseFlag) {
		this.reviseFlag = reviseFlag;
	}

	@Length(min=0, max=64, message="流程ID长度必须介于 0 和 64 之间")
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