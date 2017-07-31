/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 入党申请情况Entity
 * @author zhc
 * @version 2017-04-17
 */
public class SPmAppSituation extends DataEntity<SPmAppSituation> {
	
	private static final long serialVersionUID = 1L;
	private String politicalOutlook;		// 政治面貌
	private Date jionTime;		// 加入时间
	private String oldPost;		// 曾任职务
	private Date jionAppTime;		// 入党申请时间
	private String developmentType;		// 发展类型
	private String rewardPunishmentSituation;		// 奖惩情况
	private String nowPerformance;		// 现时表现
	private String jionContacts;		// 入党联系人
	private Date activistTime;		// 确定为积极分子时间
	private Date developmentTime;		// 确定为发展对象时间
	private String whetherHistorical;		// 是否为历史申请人员
	private Date historicalAppTime;		// 历史申请时间
	private Date preparationMemberTime;		// 确定为预备党员时间
	private Long historicalAppNum;		// 历史申请次数
	private String operator;		// 操作人
	private Date operatorTime;		// 操作时间
	private String proId;		// 流程数据id
	
	public SPmAppSituation() {
		super();
	}

	public SPmAppSituation(String id){
		super(id);
	}

	@Length(min=0, max=64, message="政治面貌长度必须介于 0 和 64 之间")
	public String getPoliticalOutlook() {
		return politicalOutlook;
	}

	public void setPoliticalOutlook(String politicalOutlook) {
		this.politicalOutlook = politicalOutlook;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getJionTime() {
		return jionTime;
	}

	public void setJionTime(Date jionTime) {
		this.jionTime = jionTime;
	}
	
	@Length(min=0, max=64, message="曾任职务长度必须介于 0 和 64 之间")
	public String getOldPost() {
		return oldPost;
	}

	public void setOldPost(String oldPost) {
		this.oldPost = oldPost;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getJionAppTime() {
		return jionAppTime;
	}

	public void setJionAppTime(Date jionAppTime) {
		this.jionAppTime = jionAppTime;
	}
	
	@Length(min=0, max=64, message="发展类型长度必须介于 0 和 64 之间")
	public String getDevelopmentType() {
		return developmentType;
	}

	public void setDevelopmentType(String developmentType) {
		this.developmentType = developmentType;
	}
	
	@Length(min=0, max=64, message="奖惩情况长度必须介于 0 和 64 之间")
	public String getRewardPunishmentSituation() {
		return rewardPunishmentSituation;
	}

	public void setRewardPunishmentSituation(String rewardPunishmentSituation) {
		this.rewardPunishmentSituation = rewardPunishmentSituation;
	}
	
	@Length(min=0, max=64, message="现时表现长度必须介于 0 和 64 之间")
	public String getNowPerformance() {
		return nowPerformance;
	}

	public void setNowPerformance(String nowPerformance) {
		this.nowPerformance = nowPerformance;
	}
	
	@Length(min=0, max=64, message="入党联系人长度必须介于 0 和 64 之间")
	public String getJionContacts() {
		return jionContacts;
	}

	public void setJionContacts(String jionContacts) {
		this.jionContacts = jionContacts;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActivistTime() {
		return activistTime;
	}

	public void setActivistTime(Date activistTime) {
		this.activistTime = activistTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDevelopmentTime() {
		return developmentTime;
	}

	public void setDevelopmentTime(Date developmentTime) {
		this.developmentTime = developmentTime;
	}
	
	@Length(min=0, max=64, message="是否为历史申请人员长度必须介于 0 和 64 之间")
	public String getWhetherHistorical() {
		return whetherHistorical;
	}

	public void setWhetherHistorical(String whetherHistorical) {
		this.whetherHistorical = whetherHistorical;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getHistoricalAppTime() {
		return historicalAppTime;
	}

	public void setHistoricalAppTime(Date historicalAppTime) {
		this.historicalAppTime = historicalAppTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPreparationMemberTime() {
		return preparationMemberTime;
	}

	public void setPreparationMemberTime(Date preparationMemberTime) {
		this.preparationMemberTime = preparationMemberTime;
	}
	
	public Long getHistoricalAppNum() {
		return historicalAppNum;
	}

	public void setHistoricalAppNum(Long historicalAppNum) {
		this.historicalAppNum = historicalAppNum;
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
	
	@Length(min=0, max=64, message="流程数据id长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
}