/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 通讯地址Entity
 * @author zhc
 * @version 2017-04-17
 */
public class SPmTxdz extends DataEntity<SPmTxdz> {
	
	private static final long serialVersionUID = 1L;
	private String workUnit;		// 工作单位
	private String unitAddress;		// 单位地址
	private String postCode;		// 单位邮编
	private String workPhone;		// 工作电话
	private String homeAddress;		// 家庭住址
	private String homeZip;		// 住宅邮编
	private String homePhone;		// 住宅电话
	private String email;		// 电子邮箱
	private String entryPerson;		// 录入人员
	private Date entryTime;		// 录入时间
	private String proId;		// 流程数据id
	
	public SPmTxdz() {
		super();
	}

	public SPmTxdz(String id){
		super(id);
	}

	@Length(min=0, max=64, message="工作单位长度必须介于 0 和 64 之间")
	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}
	
	@Length(min=0, max=100, message="单位地址长度必须介于 0 和 100 之间")
	public String getUnitAddress() {
		return unitAddress;
	}

	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}
	
	@Length(min=0, max=64, message="单位邮编长度必须介于 0 和 64 之间")
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	@Length(min=0, max=64, message="工作电话长度必须介于 0 和 64 之间")
	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	
	@Length(min=0, max=100, message="家庭住址长度必须介于 0 和 100 之间")
	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	@Length(min=0, max=64, message="住宅邮编长度必须介于 0 和 64 之间")
	public String getHomeZip() {
		return homeZip;
	}

	public void setHomeZip(String homeZip) {
		this.homeZip = homeZip;
	}
	
	@Length(min=0, max=64, message="住宅电话长度必须介于 0 和 64 之间")
	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	
	@Length(min=0, max=100, message="电子邮箱长度必须介于 0 和 100 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=64, message="录入人员长度必须介于 0 和 64 之间")
	public String getEntryPerson() {
		return entryPerson;
	}

	public void setEntryPerson(String entryPerson) {
		this.entryPerson = entryPerson;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	
	@Length(min=0, max=64, message="流程数据id长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
}