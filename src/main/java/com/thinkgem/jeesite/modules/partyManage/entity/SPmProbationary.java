/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 这是预备党员考察纪实表Entity
 * @author one
 * @version 2017-04-28
 */
public class SPmProbationary extends DataEntity<SPmProbationary> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String sex;		// 性别
	private String plaOf;		// 籍贯
	private Date dateBirth;		// 出生年月
	private String nation;		// 民族
	private String degEdu;		// 文化程度
	private Date joinTime;		// 参加工作时间
	private Date apcaTime;		// 入党时间
	private String incumbent;		// 现任职务职称
	private String situation;		// 个人学习工作简要情况
	private String introducer;		// 入党介绍人意见
	private String partyGroup;		// 党小组意见
	private String proId;		// 流程Id
	private String type;		// 类型
	
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

	public SPmProbationary() {
		super();
	}

	public SPmProbationary(String id){
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
	
	@Length(min=0, max=255, message="籍贯长度必须介于 0 和 255 之间")
	public String getPlaOf() {
		return plaOf;
	}

	public void setPlaOf(String plaOf) {
		this.plaOf = plaOf;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	
	@Length(min=0, max=255, message="民族长度必须介于 0 和 255 之间")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Length(min=0, max=255, message="文化程度长度必须介于 0 和 255 之间")
	public String getDegEdu() {
		return degEdu;
	}

	public void setDegEdu(String degEdu) {
		this.degEdu = degEdu;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApcaTime() {
		return apcaTime;
	}

	public void setApcaTime(Date apcaTime) {
		this.apcaTime = apcaTime;
	}
	
	@Length(min=0, max=255, message="现任职务职称长度必须介于 0 和 255 之间")
	public String getIncumbent() {
		return incumbent;
	}

	public void setIncumbent(String incumbent) {
		this.incumbent = incumbent;
	}
	
	@Length(min=0, max=255, message="个人学习工作简要情况长度必须介于 0 和 255 之间")
	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}
	
	@Length(min=0, max=255, message="入党介绍人意见长度必须介于 0 和 255 之间")
	public String getIntroducer() {
		return introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}
	
	@Length(min=0, max=255, message="党小组意见长度必须介于 0 和 255 之间")
	public String getPartyGroup() {
		return partyGroup;
	}

	public void setPartyGroup(String partyGroup) {
		this.partyGroup = partyGroup;
	}
	
	@Length(min=0, max=64, message="流程Id长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
	@Length(min=0, max=255, message="类型长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}