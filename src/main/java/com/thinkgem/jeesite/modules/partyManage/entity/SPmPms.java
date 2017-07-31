/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 这是党员推荐汇总表Entity
 * @author one
 * @version 2017-04-17
 */
public class SPmPms extends DataEntity<SPmPms> {
	
	private static final long serialVersionUID = 1L;
	private Date recTime;		// 推荐时间
	private String recLoca;		// 推荐地点
	private Long tnoPm;		// 党员总数
	private Long numPm;		// 参加推荐党员人数
	private Long lssueRec;		// 发出推荐票
	private Long takeRec;		// 收回推荐票
	private Long validVote;		// 有效票
	private Long invalidBall;		// 无效票
	private String resultRec;		// 别推荐人结果
	private String staSign;		// 计票人签字
	private String scrSign;		// 监票人签字
	private String branSign;		// 支部书记签字
	private String proId;		//流程数据id
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

	public SPmPms() {
		super();
	}

	public SPmPms(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRecTime() {
		return recTime;
	}

	public void setRecTime(Date recTime) {
		this.recTime = recTime;
	}
	
	@Length(min=0, max=255, message="推荐地点长度必须介于 0 和 255 之间")
	public String getRecLoca() {
		return recLoca;
	}

	public void setRecLoca(String recLoca) {
		this.recLoca = recLoca;
	}
	
	public Long getTnoPm() {
		return tnoPm;
	}

	public void setTnoPm(Long tnoPm) {
		this.tnoPm = tnoPm;
	}
	
	public Long getNumPm() {
		return numPm;
	}

	public void setNumPm(Long numPm) {
		this.numPm = numPm;
	}
	
	public Long getLssueRec() {
		return lssueRec;
	}

	public void setLssueRec(Long lssueRec) {
		this.lssueRec = lssueRec;
	}
	
	public Long getTakeRec() {
		return takeRec;
	}

	public void setTakeRec(Long takeRec) {
		this.takeRec = takeRec;
	}
	
	public Long getValidVote() {
		return validVote;
	}

	public void setValidVote(Long validVote) {
		this.validVote = validVote;
	}
	
	public Long getInvalidBall() {
		return invalidBall;
	}

	public void setInvalidBall(Long invalidBall) {
		this.invalidBall = invalidBall;
	}
	
	@Length(min=0, max=255, message="别推荐人结果长度必须介于 0 和 255 之间")
	public String getResultRec() {
		return resultRec;
	}

	public void setResultRec(String resultRec) {
		this.resultRec = resultRec;
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