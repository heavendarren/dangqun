/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 技术职务Entity
 * @author zhc
 * @version 2017-04-17
 */
public class SPmJszw extends DataEntity<SPmJszw> {
	
	private static final long serialVersionUID = 1L;
	private String proQua;		// 专业技术资格
	private Date dateElig;		// 获得资格日期
	private String techPos;		// 专业技术职务
	private Date appoStart;		// 聘任起始日期
	private Date dateTer;		// 聘任终止日期
	private String inputMan;		// 录入人
	private Date entryTime;		// 录入时间
	private String proId;		// 流程数据id
	
	public SPmJszw() {
		super();
	}

	public SPmJszw(String id){
		super(id);
	}

	@Length(min=0, max=255, message="专业技术资格长度必须介于 0 和 255 之间")
	public String getProQua() {
		return proQua;
	}

	public void setProQua(String proQua) {
		this.proQua = proQua;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDateElig() {
		return dateElig;
	}

	public void setDateElig(Date dateElig) {
		this.dateElig = dateElig;
	}
	
	@Length(min=0, max=255, message="专业技术职务长度必须介于 0 和 255 之间")
	public String getTechPos() {
		return techPos;
	}

	public void setTechPos(String techPos) {
		this.techPos = techPos;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAppoStart() {
		return appoStart;
	}

	public void setAppoStart(Date appoStart) {
		this.appoStart = appoStart;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDateTer() {
		return dateTer;
	}

	public void setDateTer(Date dateTer) {
		this.dateTer = dateTer;
	}
	
	@Length(min=0, max=64, message="录入人长度必须介于 0 和 64 之间")
	public String getInputMan() {
		return inputMan;
	}

	public void setInputMan(String inputMan) {
		this.inputMan = inputMan;
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