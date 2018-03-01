package com.tavavnt.wicket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.tavavnt.wicket.report.Report;
import com.tavavnt.wicket.session.MySession;

public class WicketApplication extends WebApplication {
	private List<Report> reports;

	public WicketApplication() {
		reports = new ArrayList<Report>();
		reports.add(new Report("John", "Homeowner", 1231, "11",234233L,"01/25/2014","01:23:11","01/25/2014","01:23:11",new BigDecimal("12.22"),"1.11"));
		reports.add(new Report("Andy", "America", 2322,"", 312312L,"01/25/2015",null,"01/25/2013","01:22:11",new BigDecimal("11.22"),"0"));
		reports.add(new Report("Suzi", "Builder", 5432,null, 78655L,null,"01:13:11","01/21/2014","01:21:11",new BigDecimal("2.22"),"765.3"));
		reports.add(new Report("Ami", "America", 7434,"233", null,"01/05/2014","01:03:11","03/25/2014","01:13:11",new BigDecimal("12.222"),"23.222"));
		reports.add(new Report("A", null, 3122, "098",134233L,"04/25/2014","01:23:17","01/25/2015","03:23:11",null,"344.11"));
		reports.add(new Report("Wicket", "America", null,"985", 512312L,"01/25/2012","01:23:21","01/25/2011","05:23:11",new BigDecimal("232.22"),""));
		reports.add(new Report("Man", "Builder", 5432, "53455",88655L,"01/25/2011","01:13:31",null,"01:13:01",new BigDecimal("15.22"),null));
		reports.add(new Report("Jetty", "JJ", 6434,"74666",97324L,"01/06/2014","01:23:41","06/15/2014",null,new BigDecimal("16.22"),"4.098"));
		
	}

	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
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

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public static WicketApplication get() {
		return (WicketApplication) WebApplication.get();
	}
    
	 @Override
	    public final Session newSession(Request request, Response response) {
	        return new MySession(request);
	    }
}
