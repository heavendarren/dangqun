package com.thinkgem.jeesite.modules.partyManage.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 会议记录Entity
 * 
 * @author psy
 * @version 2017-04-18
 */
public class SPmMinutes extends DataEntity<SPmMinutes> {

	private static final long serialVersionUID = 1L;
	private String proId; // 流程数据id
	private String conTop; // 会议议题
	private Date conTime; // 时间
	private String place; // 地点
	private String host; // 主持人
	private String noteTaker; // 记录人
	private Long arrNum; // 应到人数
	private Long actNum; // 实到人数
	private String attenList; // 参加人员名单
	private String absList; // 缺席人员名单
	private String absReason; // 缺席原因
	private String meetMin; // 会议纪要

	// todo
	private String hostName; // 主持人中文名字
	private String noteTakerName; // 记录人中文名字
	private String fileUrl; // 非此表字段,记录文件路径
	private String fileName; // 非此表字段,记录文件名
	private String uploader; // 非此表字段,记录上传文件人员
	private Date uploadTime; // 非此表字段,记录上传文件时间

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

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

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

	public SPmMinutes() {
		super();
	}

	public SPmMinutes(String id) {
		super(id);
	}

	@Length(min = 0, max = 255, message = "会议议题长度必须介于 0 和 255 之间")
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

	@Length(min = 0, max = 100, message = "地点长度必须介于 0 和 100 之间")
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getNoteTaker() {
		return noteTaker;
	}

	public void setNoteTaker(String noteTaker) {
		this.noteTaker = noteTaker;
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

	public String getAttenList() {
		return attenList;
	}

	public void setAttenList(String attenList) {
		this.attenList = attenList;
	}

	public String getAbsList() {
		return absList;
	}

	public void setAbsList(String absList) {
		this.absList = absList;
	}

	@Length(min = 0, max = 255, message = "缺席原因长度必须介于 0 和 255 之间")
	public String getAbsReason() {
		return absReason;
	}

	public void setAbsReason(String absReason) {
		this.absReason = absReason;
	}

	@Length(min = 0, max = 255, message = "会议纪要长度必须介于 0 和 255 之间")
	public String getMeetMin() {
		return meetMin;
	}

	public void setMeetMin(String meetMin) {
		this.meetMin = meetMin;
	}

}