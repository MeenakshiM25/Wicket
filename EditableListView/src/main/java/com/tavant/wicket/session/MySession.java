package com.tavant.wicket.session;

import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import com.tavant.wicket.model.DropDownOption;

public class MySession extends WebSession {

	private static final long serialVersionUID = 1L;

	public MySession(Request request) {
		super(request);
	}

	// if you use java >= 1.5 you can make use of covariant return types
	public static MySession get() {
		return (MySession) Session.get();
	}

	private List<DropDownOption> dropDownOptions;

	private DropDownOption selectedApplicationType;
	

	public List<DropDownOption> getDropDownOptions() {
		return dropDownOptions;
	}

	public void setDropDownOptions(List<DropDownOption> dropDownOptions) {
		this.dropDownOptions = dropDownOptions;
	}

	public DropDownOption getSelectedApplicationType() {
		return selectedApplicationType;
	}

	public String getApplicationType() {
		return ((null != selectedApplicationType) ? selectedApplicationType
				.getCode() : null);
	}

	public void setSelectedApplicationType(
			DropDownOption selectedApplicationType) {
		this.selectedApplicationType = selectedApplicationType;
	}

}
