/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 这还算预备党员表决票汇总表Entity
 * @author one
 * @version 2017-04-26
 */
public class SPmPmsS extends DataEntity<SPmPmsS> {
	
	private static final long serialVersionUID = 1L;
	private String partyBranch;		// 党支部
	private Date recTime;		// 时间
	private String name;		// 姓名
	private Long arrNum;		// 应到会有表决权的党员人数
	private Long actNum;		// 实到会有表决权的党员人数
	private Long validVote;		// 有效票
	private Long consentVote;		// 同意票
	private Long differentVote;		// 不同意票
	private Long abstention;		// 弃权票
	private String staSign;		// 计票人签字
	private String scrSign;		// 监票人签字
	private String branSign;		// 支部书记签字
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

	public SPmPmsS() {
		super();
	}

	public SPmPmsS(String id){
		super(id);
	}

	@Length(min=0, max=255, message="党支部长度必须介于 0 和 255 之间")
	public String getPartyBranch() {
		return partyBranch;
	}

	public void setPartyBranch(String partyBranch) {
		this.partyBranch = partyBranch;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRecTime() {
		return recTime;
	}

	public void setRecTime(Date recTime) {
		this.recTime = recTime;
	}
	
	@Length(min=0, max=255, message="姓名长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public Long getValidVote() {
		return validVote;
	}

	public void setValidVote(Long validVote) {
		this.validVote = validVote;
	}
	
	public Long getConsentVote() {
		return consentVote;
	}

	public void setConsentVote(Long consentVote) {
		this.consentVote = consentVote;
	}
	
	public Long getDifferentVote() {
		return differentVote;
	}

	public void setDifferentVote(Long differentVote) {
		this.differentVote = differentVote;
	}
	
	public Long getAbstention() {
		return abstention;
	}

	public void setAbstention(Long abstention) {
		this.abstention = abstention;
	}
	
	@Length(min=0, max=255, message="计票人签字长度必须介于 0 和 255 之间")
	public String getStaSign() {
		return staSign;
	}

	public void setStaSign(String staSign) {
		this.staSign = staSign;
	}
	
	@Length(min=0, max=255, message="监票人签字长度必须介于 0 和 255 之间")
	public String getScrSign() {
		return scrSign;
	}

	public void setScrSign(String scrSign) {
		this.scrSign = scrSign;
	}
	
	@Length(min=0, max=255, message="支部书记签字长度必须介于 0 和 255 之间")
	public String getBranSign() {
		return branSign;
	}

	public void setBranSign(String branSign) {
		this.branSign = branSign;
	}
	
	@Length(min=0, max=64, message="流程数据id长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
}