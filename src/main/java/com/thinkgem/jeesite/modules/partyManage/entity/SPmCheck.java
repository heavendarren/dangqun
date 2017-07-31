/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 这是发展对象政审表Entity
 * @author one
 * @version 2017-04-21
 */
public class SPmCheck extends DataEntity<SPmCheck> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String sex;		// 性别
	private Date dateBirth;		// 出生年月
	private Date apcaTime;		// 申请入党时间
	private String incumbent;		// 现任职务
	private String political;		// 在重大政治斗争中的表现
	private String abideLaw;		// 遵纪守法及遵守社会公德情况
	private String problem;		// 其他需要说明的问题
	private String branchOpinion;		// 支部意见
	private String partyOpinion;		// 基层党委意见
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

	public SPmCheck() {
		super();
	}

	public SPmCheck(String id){
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApcaTime() {
		return apcaTime;
	}

	public void setApcaTime(Date apcaTime) {
		this.apcaTime = apcaTime;
	}
	
	@Length(min=0, max=255, message="现任职务长度必须介于 0 和 255 之间")
	public String getIncumbent() {
		return incumbent;
	}

	public void setIncumbent(String incumbent) {
		this.incumbent = incumbent;
	}
	
	@Length(min=0, max=255, message="在重大政治斗争中的表现长度必须介于 0 和 255 之间")
	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}
	
	@Length(min=0, max=255, message="遵纪守法及遵守社会公德情况长度必须介于 0 和 255 之间")
	public String getAbideLaw() {
		return abideLaw;
	}

	public void setAbideLaw(String abideLaw) {
		this.abideLaw = abideLaw;
	}
	
	@Length(min=0, max=255, message="其他需要说明的问题长度必须介于 0 和 255 之间")
	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}
	
	@Length(min=0, max=255, message="支部意见长度必须介于 0 和 255 之间")
	public String getBranchOpinion() {
		return branchOpinion;
	}

	public void setBranchOpinion(String branchOpinion) {
		this.branchOpinion = branchOpinion;
	}
	
	@Length(min=0, max=255, message="基层党委意见长度必须介于 0 和 255 之间")
	public String getPartyOpinion() {
		return partyOpinion;
	}

	public void setPartyOpinion(String partyOpinion) {
		this.partyOpinion = partyOpinion;
	}
	
	@Length(min=0, max=64, message="流程数据id长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
}