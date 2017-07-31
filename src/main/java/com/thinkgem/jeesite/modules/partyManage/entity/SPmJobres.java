/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 工作简历Entity
 * @author zhc
 * @version 2017-04-17
 */
public class SPmJobres extends DataEntity<SPmJobres> {
	
	private static final long serialVersionUID = 1L;
	private String resume;		// 工作简历
	private String fillPeople;		// 填写人
	private Date fillTime;		// 填写时间
	private String proId;		// 流程数据id
	
	public SPmJobres() {
		super();
	}

	public SPmJobres(String id){
		super(id);
	}

	@Length(min=0, max=1000, message="工作简历长度必须介于 0 和 1000 之间")
	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}
	
	@Length(min=0, max=64, message="填写人长度必须介于 0 和 64 之间")
	public String getFillPeople() {
		return fillPeople;
	}

	public void setFillPeople(String fillPeople) {
		this.fillPeople = fillPeople;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFillTime() {
		return fillTime;
	}

	public void setFillTime(Date fillTime) {
		this.fillTime = fillTime;
	}
	
	@Length(min=0, max=64, message="流程数据id长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
}