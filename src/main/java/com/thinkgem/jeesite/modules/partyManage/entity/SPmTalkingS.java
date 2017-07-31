/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 这还算预备党员谈话纪实表Entity
 * @author one
 * @version 2017-04-26
 */
public class SPmTalkingS extends DataEntity<SPmTalkingS> {
	
	private static final long serialVersionUID = 1L;
	private String proId;		// 数据流程ID
	private String nameDeve;		// 发展对象姓名
	private Date mineDeve;		// 确定发展对象时间
	private String talker;		// 谈话人姓名
	private String talkPost;		// 谈话人职务
	private Date talkTime;		// 谈话时间
	private String talkPlace;		// 谈话地点
	private String talkDoc;		// 谈话内容
	
	public SPmTalkingS() {
		super();
	}

	public SPmTalkingS(String id){
		super(id);
	}

	@Length(min=0, max=64, message="数据流程ID长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
	@Length(min=0, max=64, message="发展对象姓名长度必须介于 0 和 64 之间")
	public String getNameDeve() {
		return nameDeve;
	}

	public void setNameDeve(String nameDeve) {
		this.nameDeve = nameDeve;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMineDeve() {
		return mineDeve;
	}

	public void setMineDeve(Date mineDeve) {
		this.mineDeve = mineDeve;
	}
	
	@Length(min=0, max=64, message="谈话人姓名长度必须介于 0 和 64 之间")
	public String getTalker() {
		return talker;
	}

	public void setTalker(String talker) {
		this.talker = talker;
	}
	
	@Length(min=0, max=64, message="谈话人职务长度必须介于 0 和 64 之间")
	public String getTalkPost() {
		return talkPost;
	}

	public void setTalkPost(String talkPost) {
		this.talkPost = talkPost;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTalkTime() {
		return talkTime;
	}

	public void setTalkTime(Date talkTime) {
		this.talkTime = talkTime;
	}
	
	@Length(min=0, max=100, message="谈话地点长度必须介于 0 和 100 之间")
	public String getTalkPlace() {
		return talkPlace;
	}

	public void setTalkPlace(String talkPlace) {
		this.talkPlace = talkPlace;
	}
	
	@Length(min=0, max=255, message="谈话内容长度必须介于 0 和 255 之间")
	public String getTalkDoc() {
		return talkDoc;
	}

	public void setTalkDoc(String talkDoc) {
		this.talkDoc = talkDoc;
	}
	
}