package com.thinkgem.jeesite.modules.partyManage.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 确定入党介绍人Entity
 * @author psy
 * @version 2017-04-25
 */
public class SPmParthMenber extends DataEntity<SPmParthMenber> {
	
	private static final long serialVersionUID = 1L;
	private String contactNum;		// 介绍人编号
	private String name;		// 姓名
	private String sex;		// 性别
	private String originPlace;		// 籍贯
	private Date birthTime;		// 出生年月
	private String nation;		// 民族
	private Date jionTime;		// 入党时间
	private String educationDegree;		// 学历学位
	private String partyBranch;		// 所在党支部名称
	private String postPlace;		// 工作单位及职务
	private String personResume;		// 个人简历
	private String proId;		// 流程编号
	private String contactId; //联系人编号
	
	private String sexName;
	private String originPlaceName;
	private String nationName;					   
	private SPmContactSettings sPmContactSettings; //培养联系人
	
	public SPmParthMenber() {
		super();
	}

	public SPmParthMenber(String id){
		super(id);
	}

	@Length(min=0, max=64, message="介绍人编号长度必须介于 0 和 64 之间")
	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	
	@Length(min=0, max=64, message="姓名长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="性别长度必须介于 0 和 64 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=64, message="籍贯长度必须介于 0 和 64 之间")
	public String getOriginPlace() {
		return originPlace;
	}

	public void setOriginPlace(String originPlace) {
		this.originPlace = originPlace;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(Date birthTime) {
		this.birthTime = birthTime;
	}
	
	@Length(min=0, max=64, message="民族长度必须介于 0 和 64 之间")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getJionTime() {
		return jionTime;
	}

	public void setJionTime(Date jionTime) {
		this.jionTime = jionTime;
	}
	
	@Length(min=0, max=64, message="学历学位长度必须介于 0 和 64 之间")
	public String getEducationDegree() {
		return educationDegree;
	}

	public void setEducationDegree(String educationDegree) {
		this.educationDegree = educationDegree;
	}
	
	@Length(min=0, max=64, message="所在党支部名称长度必须介于 0 和 64 之间")
	public String getPartyBranch() {
		return partyBranch;
	}

	public void setPartyBranch(String partyBranch) {
		this.partyBranch = partyBranch;
	}
	
	@Length(min=0, max=64, message="工作单位及职务长度必须介于 0 和 64 之间")
	public String getPostPlace() {
		return postPlace;
	}

	public void setPostPlace(String postPlace) {
		this.postPlace = postPlace;
	}
	
	@Length(min=0, max=255, message="个人简历长度必须介于 0 和 255 之间")
	public String getPersonResume() {
		return personResume;
	}

	public void setPersonResume(String personResume) {
		this.personResume = personResume;
	}
	
	@Length(min=0, max=64, message="流程编号长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getOriginPlaceName() {
		return originPlaceName;
	}

	public void setOriginPlaceName(String originPlaceName) {
		this.originPlaceName = originPlaceName;
	}

	public String getNationName() {
		return nationName;
	}

	public void setNationName(String nationName) {
		this.nationName = nationName;
	}

	public SPmContactSettings getsPmContactSettings() {
		return sPmContactSettings;
	}

	public void setsPmContactSettings(SPmContactSettings sPmContactSettings) {
		this.sPmContactSettings = sPmContactSettings;
	}
	
	
}