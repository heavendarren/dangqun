package com.thinkgem.jeesite.modules.partyManage.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 审核记录实体
 * @author xingtianpeng
 *
 */
public class SPmCheckLog extends DataEntity<SPmCheckLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String recordID; //受审人纪实ID
	private String recordName; //受审人姓名
	private String checkNodeID; //审核环节ID
	private String checkID; //审核人uerID
	private String checkName; //审核人姓名
	private String checkResult; //审核结果标记
	private Date checkDate; //审核时间
	private String backreason; //审核退回原由
	
	public String getRecordID() {
		return recordID;
	}
	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}
	public String getRecordName() {
		return recordName;
	}
	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}
	public String getCheckNodeID() {
		return checkNodeID;
	}
	public void setCheckNodeID(String checkNodeID) {
		this.checkNodeID = checkNodeID;
	}
	public String getCheckID() {
		return checkID;
	}
	public void setCheckID(String checkID) {
		this.checkID = checkID;
	}
	public String getCheckName() {
		return checkName;
	}
	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getBackreason() {
		return backreason;
	}
	public void setBackreason(String backreason) {
		this.backreason = backreason;
	}

}
