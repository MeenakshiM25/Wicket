package com.tavavnt.wicket.session;

import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import com.tavavnt.wicket.report.Report;

public class MySession extends WebSession{

    private List<Report> reports;
 
    public MySession(Request request) {
        super(request);
    }
 
   
 
    // if you use java >= 1.5 you can make use of covariant return types
    public static MySession get() {
        return (MySession)Session.get();
    }



	public List<Report> getReports() {
		return reports;
	}



	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
}
