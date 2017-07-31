/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 家庭成员Entity
 * @author zhc
 * @version 2017-04-17
 */
public class SPmFamily extends DataEntity<SPmFamily> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 成员姓名
	private String sex;		// 性别
	private Date birthDate;		// 出生年月
	private String polLook;		// 政治面貌
	private String workPos;		// 工作单位及职务
	private String identity;		// 个人身份
	private String relCate;		// 关系类别
	private String relWe;		// 与本人关系
	private String entryPeo;		// 录入人员
	private Date entryDate;		// 录入时间
	private String proId;		// 流程数据id
	
	public SPmFamily() {
		super();
	}

	public SPmFamily(String id){
		super(id);
	}

	@Length(min=0, max=255, message="成员姓名长度必须介于 0 和 255 之间")
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
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	@Length(min=0, max=255, message="政治面貌长度必须介于 0 和 255 之间")
	public String getPolLook() {
		return polLook;
	}

	public void setPolLook(String polLook) {
		this.polLook = polLook;
	}
	
	@Length(min=0, max=255, message="工作单位及职务长度必须介于 0 和 255 之间")
	public String getWorkPos() {
		return workPos;
	}

	public void setWorkPos(String workPos) {
		this.workPos = workPos;
	}
	
	@Length(min=0, max=255, message="个人身份长度必须介于 0 和 255 之间")
	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	@Length(min=0, max=255, message="关系类别长度必须介于 0 和 255 之间")
	public String getRelCate() {
		return relCate;
	}

	public void setRelCate(String relCate) {
		this.relCate = relCate;
	}
	
	@Length(min=0, max=255, message="与本人关系长度必须介于 0 和 255 之间")
	public String getRelWe() {
		return relWe;
	}

	public void setRelWe(String relWe) {
		this.relWe = relWe;
	}
	
	@Length(min=0, max=255, message="录入人员长度必须介于 0 和 255 之间")
	public String getEntryPeo() {
		return entryPeo;
	}

	public void setEntryPeo(String entryPeo) {
		this.entryPeo = entryPeo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	@Length(min=0, max=64, message="流程数据id长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
}