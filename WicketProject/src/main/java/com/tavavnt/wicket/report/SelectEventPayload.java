package com.tavavnt.wicket.report;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class SelectEventPayload {
	private AjaxRequestTarget target;
	private boolean value;

	public SelectEventPayload(AjaxRequestTarget target,boolean value) {
		super();
		this.target = target;
		this.value = value;
	}

	public AjaxRequestTarget getTarget() {
		return target;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}
}
