package com.tavant.wicket;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.tavant.wicket.common.AdminHomePage;
import com.tavant.wicket.poc.SiteUnavailable;
import com.tavant.wicket.session.MySession;

public class WicketApplication extends WebApplication {

	public WicketApplication() {
	
	}

	@Override
	public Class<? extends WebPage> getHomePage() {
		return AdminHomePage.class;
	}

	@Override
	public void init() {
		super.init();

		// resource guard which by default denies access to all resources and thus more secure
		IPackageResourceGuard guard = getResourceSettings().getPackageResourceGuard();
		if (guard instanceof SecurePackageResourceGuard) {
			((SecurePackageResourceGuard) guard).addPattern("+**.ttf");
			((SecurePackageResourceGuard) guard).addPattern("+**.woff");
			((SecurePackageResourceGuard) guard).addPattern("+**.svg");
			((SecurePackageResourceGuard) guard).addPattern("+**.eot");
			((SecurePackageResourceGuard) guard).addPattern("+**.css.map");

		}
		mountPage("/410", SiteUnavailable.class);
		 
	}

    
	 @Override
	    public final Session newSession(Request request, Response response) {
	        return new MySession(request);
	    }
}
