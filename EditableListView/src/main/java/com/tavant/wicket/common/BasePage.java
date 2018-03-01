package com.tavant.wicket.common;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import com.tavant.wicket.WicketApplication;

public abstract class BasePage extends WebPage{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BasePage(){
		
	}

	@Override
	public void renderHead(IHeaderResponse response) {
//		response.render(CssHeaderItem.forReference(new CssResourceReference(WicketApplication.class, "css/bootstrap.css")));
//		response.render(CssHeaderItem.forReference(new CssResourceReference(WicketApplication.class, "css/bootstrap-theme.min.css")));
		response.render(CssHeaderItem.forReference(new CssResourceReference(WicketApplication.class, "css/style.css")));
//		response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(WicketApplication.class, "js/jq.min.js")));
//		response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(WicketApplication.class, "js/bootstrap.js")));
		response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(WicketApplication.class, "js/html5.js")));
		response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(WicketApplication.class, "js/custom.js")));
		super.renderHead(response);
	}
}
