/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.partyManage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 基本情况Entity
 *
 * @author zhc
 * @version 2017-04-17
 */
public class SPmBasic extends DataEntity<SPmBasic> {

  private static final long serialVersionUID = 1L;
  private String name;    // 姓名
  private String oldName;    // 曾用名
  private String idcard;    // 身份证号
  private String registeredResidence;    // 户口所在地
  private String sex;    // 性别
  private String nation;    // 民族
  private String originPlace;    // 籍贯
  private String birthPlace;    // 出生地
  private String health;    // 健康状况
  private String family;    // 家庭出身
  private String workPlace;    // 工作单位
  private Date workTime;    // 工作时间
  private String personIdentity;    // 个人身份
  private String migrantWorkers;    // 农民工
  private String firstCondition;    // 一线情况
  private String newStratum;    // 新阶层
  private String democraticParties;    // 民主党派
  private String education;    // 学历
  private String academicDegree;    // 学位
  private String major;    // 专业
  private String graduatedUniversity;    // 毕业院校
  private String technicalPost;    // 技术职务
  private String jobLevel;    // 职务级别
  private String phoneNumber;    // 手机号码
  private Date retirementTime;    // 退休时间
  private Date departureTime;    // 离岗时间
  private String leaveReason;    // 离岗原因
  private String operator;    // 操作人
  private Date operatorTime;    // 操作时间
  private String proId;    // 流程数据id
  private Date birthTime; //出生年月


  public SPmBasic() {
    super();
  }

  public SPmBasic(String id) {
    super(id);
  }

  @Length(min = 0, max = 64, message = "姓名长度必须介于 0 和 64 之间")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Length(min = 0, max = 64, message = "曾用名长度必须介于 0 和 64 之间")
  public String getOldName() {
    return oldName;
  }

  public void setOldName(String oldName) {
    this.oldName = oldName;
  }

  @Length(min = 0, max = 36, message = "身份证号长度必须介于 0 和 36 之间")
  public String getIdcard() {
    return idcard;
  }

  public void setIdcard(String idcard) {
    this.idcard = idcard;
  }

  @Length(min = 0, max = 64, message = "户口所在地长度必须介于 0 和 64 之间")
  public String getRegisteredResidence() {
    return registeredResidence;
  }

  public void setRegisteredResidence(String registeredResidence) {
    this.registeredResidence = registeredResidence;
  }

  @Length(min = 0, max = 64, message = "性别长度必须介于 0 和 64 之间")
  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  @Length(min = 0, max = 64, message = "民族长度必须介于 0 和 64 之间")
  public String getNation() {
    return nation;
  }

  public void setNation(String nation) {
    this.nation = nation;
  }

  @Length(min = 0, max = 64, message = "籍贯长度必须介于 0 和 64 之间")
  public String getOriginPlace() {
    return originPlace;
  }

  public void setOriginPlace(String originPlace) {
    this.originPlace = originPlace;
  }

  @Length(min = 0, max = 64, message = "出生地长度必须介于 0 和 64 之间")
  public String getBirthPlace() {
    return birthPlace;
  }

  public void setBirthPlace(String birthPlace) {
    this.birthPlace = birthPlace;
  }

  @Length(min = 0, max = 64, message = "健康状况长度必须介于 0 和 64 之间")
  public String getHealth() {
    return health;
  }

  public void setHealth(String health) {
    this.health = health;
  }

  @Length(min = 0, max = 64, message = "家庭出身长度必须介于 0 和 64 之间")
  public String getFamily() {
    return family;
  }

  public void setFamily(String family) {
    this.family = family;
  }

  @Length(min = 0, max = 64, message = "工作单位长度必须介于 0 和 64 之间")
  public String getWorkPlace() {
    return workPlace;
  }

  public void setWorkPlace(String workPlace) {
    this.workPlace = workPlace;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public Date getWorkTime() {
    return workTime;
  }

  public void setWorkTime(Date workTime) {
    this.workTime = workTime;
  }

  @Length(min = 0, max = 64, message = "个人身份长度必须介于 0 和 64 之间")
  public String getPersonIdentity() {
    return personIdentity;
  }

  public void setPersonIdentity(String personIdentity) {
    this.personIdentity = personIdentity;
  }

  @Length(min = 0, max = 64, message = "农民工长度必须介于 0 和 64 之间")
  public String getMigrantWorkers() {
    return migrantWorkers;
  }

  public void setMigrantWorkers(String migrantWorkers) {
    this.migrantWorkers = migrantWorkers;
  }

  @Length(min = 0, max = 64, message = "一线情况长度必须介于 0 和 64 之间")
  public String getFirstCondition() {
    return firstCondition;
  }

  public void setFirstCondition(String firstCondition) {
    this.firstCondition = firstCondition;
  }

  @Length(min = 0, max = 64, message = "新阶层长度必须介于 0 和 64 之间")
  public String getNewStratum() {
    return newStratum;
  }

  public void setNewStratum(String newStratum) {
    this.newStratum = newStratum;
  }

  @Length(min = 0, max = 64, message = "民主党派长度必须介于 0 和 64 之间")
  public String getDemocraticParties() {
    return democraticParties;
  }

  public void setDemocraticParties(String democraticParties) {
    this.democraticParties = democraticParties;
  }

  @Length(min = 0, max = 64, message = "学历长度必须介于 0 和 64 之间")
  public String getEducation() {
    return education;
  }

  public void setEducation(String education) {
    this.education = education;
  }

  @Length(min = 0, max = 64, message = "学位长度必须介于 0 和 64 之间")
  public String getAcademicDegree() {
    return academicDegree;
  }

  public void setAcademicDegree(String academicDegree) {
    this.academicDegree = academicDegree;
  }

  @Length(min = 0, max = 64, message = "专业长度必须介于 0 和 64 之间")
  public String getMajor() {
    return major;
  }

  public void setMajor(String major) {
    this.major = major;
  }

  @Length(min = 0, max = 64, message = "毕业院校长度必须介于 0 和 64 之间")
  public String getGraduatedUniversity() {
    return graduatedUniversity;
  }

  public void setGraduatedUniversity(String graduatedUniversity) {
    this.graduatedUniversity = graduatedUniversity;
  }

  @Length(min = 0, max = 64, message = "技术职务长度必须介于 0 和 64 之间")
  public String getTechnicalPost() {
    return technicalPost;
  }

  public void setTechnicalPost(String technicalPost) {
    this.technicalPost = technicalPost;
  }

  @Length(min = 0, max = 64, message = "职务级别长度必须介于 0 和 64 之间")
  public String getJobLevel() {
    return jobLevel;
  }

  public void setJobLevel(String jobLevel) {
    this.jobLevel = jobLevel;
  }

  @Length(min = 0, max = 128, message = "手机号码长度必须介于 0 和 128 之间")
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public Date getRetirementTime() {
    return retirementTime;
  }

  public void setRetirementTime(Date retirementTime) {
    this.retirementTime = retirementTime;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public Date getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(Date departureTime) {
    this.departureTime = departureTime;
  }

  @Length(min = 0, max = 64, message = "离岗原因长度必须介于 0 和 64 之间")
  public String getLeaveReason() {
    return leaveReason;
  }

  public void setLeaveReason(String leaveReason) {
    this.leaveReason = leaveReason;
  }

  @Length(min = 0, max = 64, message = "操作人长度必须介于 0 和 64 之间")
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

  @Length(min = 0, max = 64, message = "流程数据id长度必须介于 0 和 64 之间")
  public String getProId() {
    return proId;
  }

  public void setProId(String proId) {
    this.proId = proId;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public Date getBirthTime() {
    return birthTime;
  }

  public void setBirthTime(Date birthTime) {
    this.birthTime = birthTime;
  }
}