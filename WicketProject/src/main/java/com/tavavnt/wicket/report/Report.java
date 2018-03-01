package com.tavavnt.wicket.report;

import java.io.Serializable;
import java.math.BigDecimal;

public class Report implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isSelected;
	private boolean isRadioSelected;
	private String firstName;
	private String lastName;
	private Integer leadNo;
	private Long hcNo;
	private String appid;
	private String createdDate;
	private String updatedDate;
	private String createdTime;
	private String updatedTime;
	private BigDecimal ltv;
	private String appraisalValue;
	

	public Report(String firstName, String lastName, Integer leadNo,String appid,
			Long hcNo,String createdDate,String createdTime,String updateDate,String updatedTime,BigDecimal ltv,String appraisalValue) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.leadNo = leadNo;
		this.appid = appid;
		this.hcNo = hcNo;
		this.createdDate = createdDate;
		this.createdTime = createdTime;
		this.updatedDate = updateDate;
		this.updatedTime = updatedTime;
		this.ltv = ltv;
		this.appraisalValue = appraisalValue;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getLeadNo() {
		return leadNo;
	}

	public void setLeadNo(Integer leadNo) {
		this.leadNo = leadNo;
	}

	public Long getHcNo() {
		return hcNo;
	}

	public void setHcNo(Long hcNo) {
		this.hcNo = hcNo;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public BigDecimal getLtv() {
		return ltv;
	}

	public void setLtv(BigDecimal ltv) {
		this.ltv = ltv;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppraisalValue() {
		return appraisalValue;
	}

	public void setAppraisalValue(String appraisalValue) {
		this.appraisalValue = appraisalValue;
	}

	public void setRadioSelected(boolean isRadioSelected) {
		this.isRadioSelected = isRadioSelected;
	}

	public boolean isRadioSelected() {
		return isRadioSelected;
	}

}
