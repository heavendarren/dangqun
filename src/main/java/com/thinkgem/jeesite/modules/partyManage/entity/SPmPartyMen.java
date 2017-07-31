package com.thinkgem.jeesite.modules.partyManage.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 入党志愿书Entity
 * @author psy
 * @version 2017-04-27
 */
public class SPmPartyMen extends DataEntity<SPmPartyMen> {

	private static final long serialVersionUID = 1L;
	private String proId;		// 流程ID

	private List<String> fileUrls; //非此表字段,记录文件路径
	private List<String> imgUrls; //非此表字段,记录文件路径
	private String fileUrl; //非此表字段,记录文件路径
	private List<String> fileNames; //非此表字段,记录文件名
	private List<String> imgNames; //非此表字段,记录文件名
	private String fileName; //非此表字段,记录文件名
	private String uploader; //非此表字段,记录上传文件人员
	private Date uploadTime; //非此表字段,记录上传文件时间

	public SPmPartyMen() {
		super();
	}

	public SPmPartyMen(String id){
		super(id);
	}

	@Length(min=0, max=64, message="流程ID长度必须介于 0 和 64 之间")
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

	public List<String> getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(List<String> imgUrls) {
		this.imgUrls = imgUrls;
	}

	public List<String> getImgNames() {
		return imgNames;
	}

	public void setImgNames(List<String> imgNames) {
		this.imgNames = imgNames;
	}
	
	

}