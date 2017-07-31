package com.thinkgem.jeesite.modules.partyManage.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 流程进度实体
 * @author xingtianpeng
 *
 */
public class ProcessData  extends DataEntity<ProcessData> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String stage; // 流程阶段
	
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateby() {
		return updateby;
	}
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	private String node; //流程节点
	private String status; //是否为当前进行节点
	private String appID;// 发展纪实id
	private String createby; //创建人
	private Date createDate; //创建时间
	private String updateby; //更新人
	private Date updateDate; //更新时间
	private String remarks; //备注标记
	private String delflag; //删除标记
	private String fillflag; //填报标记
	private String submitflag; // 提交标记
	private String checkflag; // 审核结果标记
	private String checkdel; // 审核结果删除标记

	public String getCheckdel() {
		return checkdel;
	}
	public void setCheckdel(String checkdel) {
		this.checkdel = checkdel;
	}
	public String getCheckflag() {
		return checkflag;
	}
	public void setCheckflag(String checkflag) {
		this.checkflag = checkflag;
	}
	public String getFillflag() {
		return fillflag;
	}
	public void setFillflag(String fillflag) {
		this.fillflag = fillflag;
	}
	public String getSubmitflag() {
		return submitflag;
	}
	public void setSubmitflag(String submitflag) {
		this.submitflag = submitflag;
	}

	private Date checkDate; //审核时间
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	private int childNum; //子环节数
	public int getChildNum() {
		return childNum;
	}
	public void setChildNum(int childNum) {
		this.childNum = childNum;
	}
	public String getOfficID() {
		return officID;
	}
	public void setOfficID(String officID) {
		this.officID = officID;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	private String officID; //代办人组织ID
	private String officeName; // 代办人组织名称
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	private String roleID; //代办人角色ID
	private String makeupflag; //补录标记
	private String nowState; //当前操作权限

	public String getNowState() {
		return nowState;
	}
	public void setNowState(String nowState) {
		this.nowState = nowState;
	}
	public String getMakeupflag() {
		return makeupflag;
	}
	public void setMakeupflag(String makeupflag) {
		this.makeupflag = makeupflag;
	}

}
