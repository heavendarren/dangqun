/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 这是党员意见表Entity
 * @author one
 * @version 2017-04-19
 */
public class SPmOpinion extends DataEntity<SPmOpinion> {
	
	private static final long serialVersionUID = 1L;
	private Date startTime;		// 时间
	private String place;		// 地点
	private String hostPeo;		// 主持人
	private String noteTaker;		// 记录人
	private Long numArr;		// 应到人数
	private Long numAct;		// 实到人数
	private String namePart;		// 参会人员姓名
	private String absentee;		// 缺席人员姓名与原因
	private String content;		// 会议内容
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

	public SPmOpinion() {
		super();
	}

	public SPmOpinion(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@Length(min=0, max=255, message="地点长度必须介于 0 和 255 之间")
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	@Length(min=0, max=255, message="主持人长度必须介于 0 和 255 之间")
	public String getHostPeo() {
		return hostPeo;
	}

	public void setHostPeo(String hostPeo) {
		this.hostPeo = hostPeo;
	}
	
	@Length(min=0, max=255, message="记录人长度必须介于 0 和 255 之间")
	public String getNoteTaker() {
		return noteTaker;
	}

	public void setNoteTaker(String noteTaker) {
		this.noteTaker = noteTaker;
	}
	
	public Long getNumArr() {
		return numArr;
	}

	public void setNumArr(Long numArr) {
		this.numArr = numArr;
	}
	
	public Long getNumAct() {
		return numAct;
	}

	public void setNumAct(Long numAct) {
		this.numAct = numAct;
	}
	
	@Length(min=0, max=255, message="参会人员姓名长度必须介于 0 和 255 之间")
	public String getNamePart() {
		return namePart;
	}

	public void setNamePart(String namePart) {
		this.namePart = namePart;
	}
	
	@Length(min=0, max=255, message="缺席人员姓名与原因长度必须介于 0 和 255 之间")
	public String getAbsentee() {
		return absentee;
	}

	public void setAbsentee(String absentee) {
		this.absentee = absentee;
	}
	
	@Length(min=0, max=255, message="会议内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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