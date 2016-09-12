package com.nbl.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gcs
 * @createdate 2016年9月2日	
 * @version 1.0
 * 门户后台用户信息
 */

public class PersonAndUser implements Serializable {
	
	private static final long serialVersionUID = 2861107706695271677L;
	// 登录名称
	private String loginName;
	// 登录密码
	private String password;
	// 手机号码
	private String mobile;
	// 用户类型 CP：个人 CB：企业
	private String custType;
	// 注册渠道编码
	private String regChanCode;
	// ip
	private String ip;
	// 最后一次更新密码时间
	private Date lastUpdatePasswordTime;
	// 最近一次登录失败锁定时间
	private Date lastLoginLockTime;
	
	private String id;

    private String payPassword;

    private String registeredType;

    private String name;

    private String credentialsType;

    private String identityCardNumber;

    private Date certExpiryDate;

    private String countryId;

    private String cityCode;

    private String cityName;

    private String nickname;

    private String gender;

    private Date birthday;

    private String maritalStatus;

    private String ethnicGroup;

    private String partyAffiliation;

    private String bloodType;

    private Long height;

    private Float weight;

    private String profession;

    private String investmentExp;

    private String annualIncome;

    private String status;

    private String certStatus;

    private Date createdTime;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getRegChanCode() {
		return regChanCode;
	}

	public void setRegChanCode(String regChanCode) {
		this.regChanCode = regChanCode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getRegisteredType() {
		return registeredType;
	}

	public void setRegisteredType(String registeredType) {
		this.registeredType = registeredType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCredentialsType() {
		return credentialsType;
	}

	public void setCredentialsType(String credentialsType) {
		this.credentialsType = credentialsType;
	}

	public String getIdentityCardNumber() {
		return identityCardNumber;
	}

	public void setIdentityCardNumber(String identityCardNumber) {
		this.identityCardNumber = identityCardNumber;
	}

	public Date getCertExpiryDate() {
		return certExpiryDate;
	}

	public void setCertExpiryDate(Date certExpiryDate) {
		this.certExpiryDate = certExpiryDate;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getEthnicGroup() {
		return ethnicGroup;
	}

	public void setEthnicGroup(String ethnicGroup) {
		this.ethnicGroup = ethnicGroup;
	}

	public String getPartyAffiliation() {
		return partyAffiliation;
	}

	public void setPartyAffiliation(String partyAffiliation) {
		this.partyAffiliation = partyAffiliation;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getInvestmentExp() {
		return investmentExp;
	}

	public void setInvestmentExp(String investmentExp) {
		this.investmentExp = investmentExp;
	}

	public String getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCertStatus() {
		return certStatus;
	}

	public void setCertStatus(String certStatus) {
		this.certStatus = certStatus;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastUpdatePasswordTime() {
		return lastUpdatePasswordTime;
	}

	public void setLastUpdatePasswordTime(Date lastUpdatePasswordTime) {
		this.lastUpdatePasswordTime = lastUpdatePasswordTime;
	}

	public Date getLastLoginLockTime() {
		return lastLoginLockTime;
	}

	public void setLastLoginLockTime(Date lastLoginLockTime) {
		this.lastLoginLockTime = lastLoginLockTime;
	}

	@Override
	public String toString() {
		return "PersonAndUser [loginName=" + loginName + ", password=" + password + ", mobile=" + mobile + ", custType="
				+ custType + ", regChanCode=" + regChanCode + ", ip=" + ip + ", lastUpdatePasswordTime="
				+ lastUpdatePasswordTime + ", lastLoginLockTime=" + lastLoginLockTime + ", id=" + id + ", payPassword="
				+ payPassword + ", registeredType=" + registeredType + ", name=" + name + ", credentialsType="
				+ credentialsType + ", identityCardNumber=" + identityCardNumber + ", certExpiryDate=" + certExpiryDate
				+ ", countryId=" + countryId + ", cityCode=" + cityCode + ", cityName=" + cityName + ", nickname="
				+ nickname + ", gender=" + gender + ", birthday=" + birthday + ", maritalStatus=" + maritalStatus
				+ ", ethnicGroup=" + ethnicGroup + ", partyAffiliation=" + partyAffiliation + ", bloodType=" + bloodType
				+ ", height=" + height + ", weight=" + weight + ", profession=" + profession + ", investmentExp="
				+ investmentExp + ", annualIncome=" + annualIncome + ", status=" + status + ", certStatus=" + certStatus
				+ ", createdTime=" + createdTime + "]";
	}

}
