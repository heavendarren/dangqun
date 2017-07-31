package com.thinkgem.jeesite.modules.partyManage.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 谈话纪实Entity
 * @author psy
 * @version 2017-04-17
 */
public class SPmTalking extends DataEntity<SPmTalking> {
	
	private static final long serialVersionUID = 1L;
	private String proId;		// 流程数据id
	private String talker;		// 谈话人
	private Date talkTime;		// 谈话时间
	private String talkPlace;		// 谈话地点
	private String talkDoc;		// 谈话纪实
	
	
	
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public SPmTalking() {
		super();
	}

	public SPmTalking(String id){
		super(id);
	}

	@Length(min=0, max=64, message="谈话人长度必须介于 0 和 64 之间")
	public String getTalker() {
		return talker;
	}

	public void setTalker(String talker) {
		this.talker = talker;
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
	
	@Length(min=0, max=255, message="谈话纪实长度必须介于 0 和 255 之间")
	public String getTalkDoc() {
		return talkDoc;
	}

	public void setTalkDoc(String talkDoc) {
		this.talkDoc = talkDoc;
	}
	
}