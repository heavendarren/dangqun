/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 学历学位Entity
 * @author zhc
 * @version 2017-04-19
 */
public class SPmEduDegree extends DataEntity<SPmEduDegree> {
	
	private static final long serialVersionUID = 1L;
	private String education;		// 学历
	private Date entranceDate;		// 入学日期
	private String graduatedUniversity;		// 毕业院校
	private String educationType;		// 教育类别
	private String academicDegree;		// 学位
	private Date degreeGrantDate;		// 学位授予日期
	private Date graduationDate;		// 毕业日期
	private String major;		// 专业
	private String operation;		// 操作
	private String operator;		// 填报人
	private Date operatorTime;		// 填报时间
	private String proId;		// 流程数据id
	
	public SPmEduDegree() {
		super();
	}

	public SPmEduDegree(String id){
		super(id);
	}

	@Length(min=0, max=64, message="学历长度必须介于 0 和 64 之间")
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEntranceDate() {
		return entranceDate;
	}

	public void setEntranceDate(Date entranceDate) {
		this.entranceDate = entranceDate;
	}
	
	@Length(min=0, max=64, message="毕业院校长度必须介于 0 和 64 之间")
	public String getGraduatedUniversity() {
		return graduatedUniversity;
	}

	public void setGraduatedUniversity(String graduatedUniversity) {
		this.graduatedUniversity = graduatedUniversity;
	}
	
	@Length(min=0, max=64, message="教育类别长度必须介于 0 和 64 之间")
	public String getEducationType() {
		return educationType;
	}

	public void setEducationType(String educationType) {
		this.educationType = educationType;
	}
	
	@Length(min=0, max=64, message="学位长度必须介于 0 和 64 之间")
	public String getAcademicDegree() {
		return academicDegree;
	}

	public void setAcademicDegree(String academicDegree) {
		this.academicDegree = academicDegree;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDegreeGrantDate() {
		return degreeGrantDate;
	}

	public void setDegreeGrantDate(Date degreeGrantDate) {
		this.degreeGrantDate = degreeGrantDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}
	
	@Length(min=0, max=10, message="专业长度必须介于 0 和 10 之间")
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	@Length(min=0, max=64, message="操作长度必须介于 0 和 64 之间")
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	@Length(min=0, max=64, message="填报人长度必须介于 0 和 64 之间")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	
	@Length(min=0, max=64, message="流程数据id长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
}