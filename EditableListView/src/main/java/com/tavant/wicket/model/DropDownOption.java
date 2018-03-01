package com.tavant.wicket.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class DropDownOption implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String description;
	private String displayValue;
	private Integer displayOrder;
	private String updatedDate;
	private String updatedBy;
	private String status;
	private boolean isDuplicate = false;
	private boolean isEdited = false;
	
	public boolean isEdited() {
		return isEdited;
	}
	public void setEdited(boolean isEdited) {
		this.isEdited = isEdited;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDisplayValue() {
		return displayValue;
	}
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public DropDownOption(String code,String description,String displayValue,Integer displayOrder){
		this.id = UUID.randomUUID().toString();
		this.code = code;
		this.description = description;
		this.displayValue = displayValue;
		this.displayOrder = displayOrder;
		this.updatedBy = "Visitor";
		this.updatedDate = new Date().toString();
		this.status = "Active";
	}
	public DropDownOption(String code,String displayValue){
		this.code = code;
		this.displayValue = displayValue;
	}
	public DropDownOption(){
		this.status = "Active";
	}
	public boolean isDuplicate() {
		return isDuplicate;
	}
	public void setDuplicate(boolean isDuplicate) {
		this.isDuplicate = isDuplicate;
	}
}
