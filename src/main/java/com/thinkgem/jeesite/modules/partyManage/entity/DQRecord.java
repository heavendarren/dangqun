package com.thinkgem.jeesite.modules.partyManage.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;

/**
 * 发展纪实实体类
 * @author xingtianpeng
 * 4-11
 */
public class DQRecord extends DataEntity<DQRecord> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String IDCard; //申请人身份证号
	private String name; //申请人姓名
	private String orgID; //所属党组织ID
	private String orgName; //所属党组织名称
	private Date applicationTime; //申请入党时间
	private Date activistTime; //确定积极分子时间
	private Date objectTime; //确定发展对象时间
	
	public String getIDCard() {
		return IDCard;
	}
	
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOrgID() {
		return orgID;
	}
	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public Date getApplicationTime() {
		return applicationTime;
	}
	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}
	
	public Date getActivistTime() {
		return activistTime;
	}
	public void setActivistTime(Date activistTime) {
		this.activistTime = activistTime;
	}
	
	public Date getObjectTime() {
		return objectTime;
	}
	public void setObjectTime(Date objectTime) {
		this.objectTime = objectTime;
	}
	
	public Date getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}
	
	public Date getFormalTime() {
		return formalTime;
	}
	public void setFormalTime(Date formalTime) {
		this.formalTime = formalTime;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	
	public String getReviewerID() {
		return reviewerID;
	}
	public void setReviewerID(String reviewerID) {
		this.reviewerID = reviewerID;
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
	
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	
	private Date reserveTime; //确定预备党员时间
	private Date formalTime; //党员转正时间
	private String state; //当前流程状态
	private String stage; //当前阶段
	private String reviewerID; //入党审核人id
	private String createby; //创建人
	private Date createDate; //创建时间
	private String updateby; //更新人
	private Date updateDate; //更新时间
	private String delflag; //删除标记
	
	private Office office;
	private Role role;
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	private String userID;

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	private String makeupflag; //补录标记

	public String getMakeupflag() {
		return makeupflag;
	}

	public void setMakeupflag(String makeupflag) {
		this.makeupflag = makeupflag;
	}
	
	private String endflag; //流程完成标记

	public String getEndflag() {
		return endflag;
	}

	public void setEndflag(String endflag) {
		this.endflag = endflag;
	}
	
	private String makenode; // 补录到节点

	public String getMakenode() {
		return makenode;
	}

	public void setMakenode(String makenode) {
		this.makenode = makenode;
	}
	
	private String nowNode; // 当前节点

	public void setNowNode(String nowNode) {
		this.nowNode = nowNode;
	}

	public String getNowNode() {
		return nowNode;
	}

}
