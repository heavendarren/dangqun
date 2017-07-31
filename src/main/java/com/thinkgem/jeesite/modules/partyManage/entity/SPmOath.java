package com.thinkgem.jeesite.modules.partyManage.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 宣誓活动纪实Entity
 * @author psy
 * @version 2017-04-25
 */
public class SPmOath extends DataEntity<SPmOath> {
	
	private static final long serialVersionUID = 1L;
	private String proId;		// 数据流程ID
	private String organName;		// organ_name
	private Date actTime;		// 活动时间
	private String actPlace;		// 活动地点
	private String personName;		// 领誓人姓名
	private String lnnerParty;		// 党内职务
	private String participant;		// 参加人员
	private String actSit;		// 活动情况
	
	// todo
	private String participantName; // 参加人员名字
	private String fileUrl; // 非此表字段,记录文件路径
	private String fileName; // 非此表字段,记录文件名
	private String uploader; // 非此表字段,记录上传文件人员
	private Date uploadTime; // 非此表字段,记录上传文件时间
	
	public SPmOath() {
		super();
	}

	public SPmOath(String id){
		super(id);
	}

	@Length(min=0, max=64, message="数据流程ID长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
	@Length(min=0, max=100, message="organ_name长度必须介于 0 和 100 之间")
	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActTime() {
		return actTime;
	}

	public void setActTime(Date actTime) {
		this.actTime = actTime;
	}
	
	@Length(min=0, max=100, message="活动地点长度必须介于 0 和 100 之间")
	public String getActPlace() {
		return actPlace;
	}

	public void setActPlace(String actPlace) {
		this.actPlace = actPlace;
	}
	
	@Length(min=0, max=64, message="领誓人姓名长度必须介于 0 和 64 之间")
	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	@Length(min=0, max=64, message="党内职务长度必须介于 0 和 64 之间")
	public String getLnnerParty() {
		return lnnerParty;
	}

	public void setLnnerParty(String lnnerParty) {
		this.lnnerParty = lnnerParty;
	}
	
	@Length(min=0, max=255, message="参加人员长度必须介于 0 和 255 之间")
	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}
	
	@Length(min=0, max=255, message="活动情况长度必须介于 0 和 255 之间")
	public String getActSit() {
		return actSit;
	}

	public void setActSit(String actSit) {
		this.actSit = actSit;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
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