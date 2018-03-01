package com.tavavnt.wicket;

//import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.http.WebResponse;

public class SiteUnavailable extends WebPage{
	
	private static final long serialVersionUID = 1L;

	public SiteUnavailable(){
		
	}
	
	@Override
	protected void configureResponse(WebResponse arg0) {
		super.configureResponse(arg0);
//		((HttpServletResponse)getRequestCycle().getResponse().getContainerResponse()).setStatus(HttpServletResponse.SC_GONE);
	}
	
	@Override
	public boolean isVersioned() {
		return false;
	}
	
	@Override
	public boolean isErrorPage() {
		return true;
	}
}
