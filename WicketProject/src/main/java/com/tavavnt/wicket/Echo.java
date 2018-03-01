package com.tavavnt.wicket;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.http.flow.AbortWithHttpErrorCodeException;
/*Example that clearly demonstrates the diff betwn model and property model */
public class Echo extends WebPage{
	private static final long serialVersionUID = 1L;
	private String dateString = "";
	public Echo(){
			final SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy hh:mm:ss");
			dateString = sdf.format(new Date());
			Label label = new Label("msg", new PropertyModel<String>(this , "dateString"));
//			final Label label = new Label("msg", new Model<String>(dateString));
			label.setOutputMarkupId(true);
	        add(label);
	        add(new Link<Object>("link"){
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick() {
					dateString = sdf.format(new Date());
					throw new AbortWithHttpErrorCodeException(410, "Page Deleted ");
				}
	        	
	        });
	       
	}
	}
