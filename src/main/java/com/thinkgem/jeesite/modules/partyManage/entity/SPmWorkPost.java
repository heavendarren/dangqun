/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 工作岗位Entity
 * @author zhc
 * @version 2017-04-17
 */
public class SPmWorkPost extends DataEntity<SPmWorkPost> {
	
	private static final long serialVersionUID = 1L;
	private String workPost;		// 工作岗位
	private Date beginTime;		// 开始时间
	private Date endTime;		// 截止时间
	private String personIdentity;		// 个人身份
	private String firstCondition;		// 一线情况
	private String newStratum;		// 新阶层
	private String operator;		// 操作人
	private Date operatorTime;		// 操作时间
	private String operation;		// 操作
	private String proId;		// 流程数据id
	
	public SPmWorkPost() {
		super();
	}

	public SPmWorkPost(String id){
		super(id);
	}

	@Length(min=0, max=64, message="工作岗位长度必须介于 0 和 64 之间")
	public String getWorkPost() {
		return workPost;
	}

	public void setWorkPost(String workPost) {
		this.workPost = workPost;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=64, message="个人身份长度必须介于 0 和 64 之间")
	public String getPersonIdentity() {
		return personIdentity;
	}

	public void setPersonIdentity(String personIdentity) {
		this.personIdentity = personIdentity;
	}
	
	@Length(min=0, max=64, message="一线情况长度必须介于 0 和 64 之间")
	public String getFirstCondition() {
		return firstCondition;
	}

	public void setFirstCondition(String firstCondition) {
		this.firstCondition = firstCondition;
	}
	
	@Length(min=0, max=64, message="新阶层长度必须介于 0 和 64 之间")
	public String getNewStratum() {
		return newStratum;
	}

	public void setNewStratum(String newStratum) {
		this.newStratum = newStratum;
	}
	
	@Length(min=0, max=64, message="操作人长度必须介于 0 和 64 之间")
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
	
	@Length(min=0, max=64, message="操作长度必须介于 0 和 64 之间")
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	@Length(min=0, max=64, message="流程数据id长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
}