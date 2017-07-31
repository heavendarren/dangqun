/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 预备党员备案Entity
 * @author zhc
 * @version 2017-04-25
 */
public class SPmBeforePartyRecord extends DataEntity<SPmBeforePartyRecord> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String sex;		// 性别
	private String originPlace;		// 籍贯
	private Date birthTime;		// 出生年月
	private String education;		// 文化程度
	private Date workTime;		// 参加工作时间
	private Date jionAppTime;		// 申请入党时间
	private Date activistTime;		// 确定积极分子时间
	private Date meetingTime;		// 支部大会讨论时间
	private Date examineTime;		// 党委审批时间
	private String workPost;		// 工作单位及职务
	private String partyOption;		// 党委意见
	private String superPartyRecord;		// 上级党委组织部门备案
	private String nation;		// 民族
	private String proId;		// 流程数据id

	private String fileUrl; //非此表字段,记录文件路径
	private String fileName; //非此表字段,记录文件名
	private String uploader; //非此表字段,记录上传文件人员
	private Date uploadTime; //非此表字段,记录上传文件时间
	
	public SPmBeforePartyRecord() {
		super();
	}

	public SPmBeforePartyRecord(String id){
		super(id);
	}

	@Length(min=0, max=64, message="姓名长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="性别长度必须介于 0 和 64 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=64, message="籍贯长度必须介于 0 和 64 之间")
	public String getOriginPlace() {
		return originPlace;
	}

	public void setOriginPlace(String originPlace) {
		this.originPlace = originPlace;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(Date birthTime) {
		this.birthTime = birthTime;
	}
	
	@Length(min=0, max=64, message="文化程度长度必须介于 0 和 64 之间")
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getJionAppTime() {
		return jionAppTime;
	}

	public void setJionAppTime(Date jionAppTime) {
		this.jionAppTime = jionAppTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActivistTime() {
		return activistTime;
	}

	public void setActivistTime(Date activistTime) {
		this.activistTime = activistTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(Date meetingTime) {
		this.meetingTime = meetingTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExamineTime() {
		return examineTime;
	}

	public void setExamineTime(Date examineTime) {
		this.examineTime = examineTime;
	}
	
	@Length(min=0, max=64, message="工作单位及职务长度必须介于 0 和 64 之间")
	public String getWorkPost() {
		return workPost;
	}

	public void setWorkPost(String workPost) {
		this.workPost = workPost;
	}
	
	@Length(min=0, max=64, message="党委意见长度必须介于 0 和 64 之间")
	public String getPartyOption() {
		return partyOption;
	}

	public void setPartyOption(String partyOption) {
		this.partyOption = partyOption;
	}
	
	@Length(min=0, max=64, message="上级党委组织部门备案长度必须介于 0 和 64 之间")
	public String getSuperPartyRecord() {
		return superPartyRecord;
	}

	public void setSuperPartyRecord(String superPartyRecord) {
		this.superPartyRecord = superPartyRecord;
	}
	
	@Length(min=0, max=64, message="民族长度必须介于 0 和 64 之间")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Length(min=0, max=64, message="流程数据id长度必须介于 0 和 64 之间")
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