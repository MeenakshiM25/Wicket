package com.tavant.wicket.model;

import java.io.Serializable;

public class EmailVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String emailAddress;	
	private Boolean active;
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public EmailVO(String emailAddress,Boolean active){
		this.emailAddress = emailAddress;
		this.active = active;
	}
}
