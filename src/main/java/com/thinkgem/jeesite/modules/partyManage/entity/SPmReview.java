/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 这是发展党员工作预审表Entity
 * @author one
 * @version 2017-04-24
 */
public class SPmReview extends DataEntity<SPmReview> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String sex;		// 性别
	private String nation;		// 民族
	private Date dateBirth;		// 出生年月
	private String degree;		// 文化程度
	private Date workHours;		// 参加工作时间
	private Date partyTime;		// 申请入党时间
	private Date objectTime;		// 确定发展对象时间
	private Date activistTime;		// 确定积极分子问题
	private Date shortTime;		// 短期集中培训时间
	private String branOpin;		// 所在支部预审意见
	private String genOpin;		// 党总支部预审意见
	private String partyOpin;		// 上级党（工）委预审意见
	private String trial;		// 审卷人
	private String collar;		// 领卷人
	private Date updateUpdate;		// 更新时间
	private String proId;		// 流程数据id
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

	public SPmReview() {
		super();
	}

	public SPmReview(String id){
		super(id);
	}

	@Length(min=0, max=255, message="姓名长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="性别长度必须介于 0 和 255 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=255, message="民族长度必须介于 0 和 255 之间")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	
	@Length(min=0, max=255, message="文化程度长度必须介于 0 和 255 之间")
	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWorkHours() {
		return workHours;
	}

	public void setWorkHours(Date workHours) {
		this.workHours = workHours;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPartyTime() {
		return partyTime;
	}

	public void setPartyTime(Date partyTime) {
		this.partyTime = partyTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getObjectTime() {
		return objectTime;
	}

	public void setObjectTime(Date objectTime) {
		this.objectTime = objectTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActivistTime() {
		return activistTime;
	}

	public void setActivistTime(Date activistTime) {
		this.activistTime = activistTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getShortTime() {
		return shortTime;
	}

	public void setShortTime(Date shortTime) {
		this.shortTime = shortTime;
	}
	
	@Length(min=0, max=255, message="所在支部预审意见长度必须介于 0 和 255 之间")
	public String getBranOpin() {
		return branOpin;
	}

	public void setBranOpin(String branOpin) {
		this.branOpin = branOpin;
	}
	
	@Length(min=0, max=255, message="党总支部预审意见长度必须介于 0 和 255 之间")
	public String getGenOpin() {
		return genOpin;
	}

	public void setGenOpin(String genOpin) {
		this.genOpin = genOpin;
	}
	
	@Length(min=0, max=64, message="上级党（工）委预审意见长度必须介于 0 和 64 之间")
	public String getPartyOpin() {
		return partyOpin;
	}

	public void setPartyOpin(String partyOpin) {
		this.partyOpin = partyOpin;
	}
	
	@Length(min=0, max=64, message="审卷人长度必须介于 0 和 64 之间")
	public String getTrial() {
		return trial;
	}

	public void setTrial(String trial) {
		this.trial = trial;
	}
	
	@Length(min=0, max=64, message="领卷人长度必须介于 0 和 64 之间")
	public String getCollar() {
		return collar;
	}

	public void setCollar(String collar) {
		this.collar = collar;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateUpdate() {
		return updateUpdate;
	}

	public void setUpdateUpdate(Date updateUpdate) {
		this.updateUpdate = updateUpdate;
	}
	
	@Length(min=0, max=64, message="流程数据id长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
}