/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 这是群团推荐表Entity
 * @author one
 * @version 2017-04-17
 */
public class SPmPrf extends DataEntity<SPmPrf> {
	
	private static final long serialVersionUID = 1L;
	private String recOb;		// 推荐对象
	private String sex;		// 性别
	private String plaOf;		// 籍贯
	private Date dateBirth;		// 出生年月
	private String nation;		// 民族
	private String degEdu;		// 文化程度
	private Date joinTime;		// 加入群团组织时间
	private Date apcaTime;		// 申请入党时间
	private String offCap;		// 现任职务和职称
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

	public SPmPrf() {
		super();
	}

	public SPmPrf(String id){
		super(id);
	}

	@Length(min=0, max=255, message="推荐对象长度必须介于 0 和 255 之间")
	public String getRecOb() {
		return recOb;
	}

	public void setRecOb(String recOb) {
		this.recOb = recOb;
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
	
	@Length(min=0, max=255, message="现任职务和职称长度必须介于 0 和 255 之间")
	public String getOffCap() {
		return offCap;
	}

	public void setOffCap(String offCap) {
		this.offCap = offCap;
	}
	@Length(min=0, max=64, message="流程数据id长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
}