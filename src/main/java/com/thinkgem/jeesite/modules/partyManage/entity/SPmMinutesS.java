/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 这是会议记录表Entity
 * @author one
 * @version 2017-04-26
 */
public class SPmMinutesS extends DataEntity<SPmMinutesS> {
	
	private static final long serialVersionUID = 1L;
	private String proId;		// 数据流程ID
	private String conTop;		// 会议议题
	private Date conTime;		// 时间
	private String place;		// 地点
	private String host;		// 主持人
	private String noteTaker;		// 记录人
	private String perPar;		// 入党介绍人
	private String perAtt;		// 列席人
	private Long arrNum;		// 应到会有表决权的党员人数
	private Long actNum;		// 实到会有表决权的党员人数
	private String attenList;		// 参会人员姓名
	private String absList;		// 缺席人员姓名
	private String meetMin;		// 会议内容
	
	private String hostName; // 主持人中文名字
	private String noteTakerName; // 记录人中文名字
	private String perAttName; //列席人中文名字
	private String fileUrl; // 非此表字段,记录文件路径
	private String fileName; // 非此表字段,记录文件名
	private String uploader; // 非此表字段,记录上传文件人员
	private Date uploadTime; // 非此表字段,记录上传文件时间
	
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getNoteTakerName() {
		return noteTakerName;
	}

	public void setNoteTakerName(String noteTakerName) {
		this.noteTakerName = noteTakerName;
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

	public SPmMinutesS() {
		super();
	}

	public SPmMinutesS(String id){
		super(id);
	}

	@Length(min=0, max=64, message="数据流程ID长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
	@Length(min=0, max=255, message="会议议题长度必须介于 0 和 255 之间")
	public String getConTop() {
		return conTop;
	}

	public void setConTop(String conTop) {
		this.conTop = conTop;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getConTime() {
		return conTime;
	}

	public void setConTime(Date conTime) {
		this.conTime = conTime;
	}
	
	@Length(min=0, max=100, message="地点长度必须介于 0 和 100 之间")
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	@Length(min=0, max=64, message="主持人长度必须介于 0 和 64 之间")
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	@Length(min=0, max=64, message="记录人长度必须介于 0 和 64 之间")
	public String getNoteTaker() {
		return noteTaker;
	}

	public void setNoteTaker(String noteTaker) {
		this.noteTaker = noteTaker;
	}
	
	@Length(min=0, max=64, message="入党介绍人长度必须介于 0 和 64 之间")
	public String getPerPar() {
		return perPar;
	}

	public void setPerPar(String perPar) {
		this.perPar = perPar;
	}
	
	@Length(min=0, max=64, message="列席人长度必须介于 0 和 64 之间")
	public String getPerAtt() {
		return perAtt;
	}

	public void setPerAtt(String perAtt) {
		this.perAtt = perAtt;
	}
	
	public Long getArrNum() {
		return arrNum;
	}

	public void setArrNum(Long arrNum) {
		this.arrNum = arrNum;
	}
	
	public Long getActNum() {
		return actNum;
	}

	public void setActNum(Long actNum) {
		this.actNum = actNum;
	}
	
	@Length(min=0, max=255, message="参会人员姓名长度必须介于 0 和 255 之间")
	public String getAttenList() {
		return attenList;
	}

	public void setAttenList(String attenList) {
		this.attenList = attenList;
	}
	
	@Length(min=0, max=255, message="缺席人员姓名长度必须介于 0 和 255 之间")
	public String getAbsList() {
		return absList;
	}

	public void setAbsList(String absList) {
		this.absList = absList;
	}
	
	@Length(min=0, max=255, message="会议内容长度必须介于 0 和 255 之间")
	public String getMeetMin() {
		return meetMin;
	}

	public void setMeetMin(String meetMin) {
		this.meetMin = meetMin;
	}

	public String getPerAttName() {
		return perAttName;
	}

	public void setPerAttName(String perAttName) {
		this.perAttName = perAttName;
	}

	
	
}