package com.tavant.wicket.model;

import java.io.Serializable;

public class DayLineVO implements Serializable,Cloneable{
	
	private static final long serialVersionUID = 1L;
	private String startDay;
	private String endDay;
	private String startHour;
	private String startMin;
	private String StartAMPM;
	private String endHour;
	private String endMin;
	private String endAMPM;
	private int displayOrder;
	private String code;
	private int id;
	private boolean isSelected = false;
	public DayLineVO(){
		
	}
	public DayLineVO(String code,String startDay,String endDay,String startHour,String startMin,String StartAMPM,String endHour,String endMin,String endAMPM,int displayOrder){
		this.code = code;
		this.startDay = startDay;
		this.endDay = endDay;
		this.startHour = startHour;
		this.startMin = startMin;
		this.StartAMPM =StartAMPM;
		this.endHour = endHour;
		this.endMin = endMin;
		this.endAMPM = endAMPM;
		this.displayOrder = displayOrder;
	}
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getStartMin() {
		return startMin;
	}
	public void setStartMin(String startMin) {
		this.startMin = startMin;
	}
	public String getStartAMPM() {
		return StartAMPM;
	}
	public void setStartAMPM(String startAMPM) {
		StartAMPM = startAMPM;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getEndMin() {
		return endMin;
	}
	public void setEndMin(String endMin) {
		this.endMin = endMin;
	}
	public String getEndAMPM() {
		return endAMPM;
	}
	public void setEndAMPM(String endAMPM) {
		this.endAMPM = endAMPM;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
}
