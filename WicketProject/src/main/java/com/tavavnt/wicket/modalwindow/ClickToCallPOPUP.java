package com.tavavnt.wicket.modalwindow;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.PatternValidator;

public class ClickToCallPOPUP extends Panel {

	private static final long serialVersionUID = 1L;
	private Form<ClickToCallPOPUP> callBackForm;
	private TextField<String> firstName;
	private WebMarkupContainer callMeLaterContainer;
	private AjaxButton doneButton;
	private CustomModalWindow modalwindow;
	private WebMarkupContainer cilckToCallErrorContainer;
	private Label errorMessage;
	private Label digitalAnalyticsHeadLabel;
	private Label digitalAnalyticsBodyLabel;
	private String firstNameModel;
	public ClickToCallPOPUP(String id, final CustomModalWindow modalWindow) {
		super(id);
		this.modalwindow = modalWindow;
		callBackForm = new Form<ClickToCallPOPUP>("callBackForm");
		add(callBackForm);
		addFeedbackPanel();
		addCallMeLaterContainer();
		addSubmitButton();
		digitalAnalyticsLabels();
	}

	/**
	 * Create feedback panel to display errors.
	 */
	private void addFeedbackPanel() {
		cilckToCallErrorContainer = new WebMarkupContainer("cilckToCallErrorContainer");
		cilckToCallErrorContainer.setMarkupId("cilckToCallErrorContainer");
		cilckToCallErrorContainer.setOutputMarkupPlaceholderTag(true);
//		cilckToCallErrorContainer.setVisible(false);
		cilckToCallErrorContainer.add(new FeedbackPanel("cilckToCallFeedbackPanel"));
		callBackForm.add(cilckToCallErrorContainer);
		errorMessage = new Label("cilckToCallReviewErrorLabel", "Please address the fields highlighted in red below.");
		errorMessage.setMarkupId("reviewErrorLabel");
		errorMessage.setOutputMarkupId(true);
		cilckToCallErrorContainer.add(errorMessage);
	}

	private void addCallMeLaterContainer() {
		callMeLaterContainer = new WebMarkupContainer("callMeLaterContainer");
		callMeLaterContainer.setOutputMarkupPlaceholderTag(true);
		callBackForm.add(callMeLaterContainer);

		firstName = new TextField<String>("firstName", new PropertyModel<String>(this, "firstNameModel"));
		firstName.add(new PatternValidator("^[A-Za-z ]+$"));
		firstName.setMarkupId("firstName");
		callMeLaterContainer.add(firstName);
		firstName.setRequired(true);
	}

	private void addSubmitButton() {
		doneButton = new AjaxButton("doneButton") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				modalwindow.close(target);
				onClose(target);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				super.onError(target, form);		
				target.add(cilckToCallErrorContainer);
			}
		};
		callMeLaterContainer.add(doneButton);

	}

	protected void onClose(AjaxRequestTarget target) {

	}
	
	private void digitalAnalyticsLabels () {
        /*digitalAnalyticsHeadLabel = new Label("digitalAnalyticsClickToCallHeadLabel", new Model<String>(executeDigitalAnalyticsTags("https://www-qa.cb.info53.com/apps/static/mb2c/meta-about-you.html")));*/
		digitalAnalyticsHeadLabel = new Label("digitalAnalyticsClickToCallHeadLabel", new Model<String>());
        digitalAnalyticsHeadLabel.setEscapeModelStrings(false);
        digitalAnalyticsHeadLabel.setOutputMarkupId(true);
        digitalAnalyticsHeadLabel.setMarkupId("digitalAnalyticsClickToCallHeadLabel");
        add(digitalAnalyticsHeadLabel);
              
        digitalAnalyticsBodyLabel = new Label("digitalAnalyticsClickToCallBodyLabel", new Model<String>(executeDigitalAnalyticsTags("https://www-qa.cb.info53.com/apps/static/mb2c/html-about-you.html")));
        digitalAnalyticsBodyLabel.setEscapeModelStrings(false);
        digitalAnalyticsBodyLabel.setOutputMarkupId(true);
        digitalAnalyticsBodyLabel.setMarkupId("digitalAnalyticsClickToCallBodyLabel");
        add(digitalAnalyticsBodyLabel);
              
	}
     public String executeDigitalAnalyticsTags(String urlArray){
    	 String response = "";
            if(null != urlArray){
                   StringBuilder stringBuilder = new StringBuilder();
                   String[] urls = urlArray.split(",");
                   for(String url:urls){
                         String textData = null;
                         HttpClient httpClient = new HttpClient();
                         GetMethod get = new GetMethod(url);      
                         try{
                                if(hasValidContent(url)){
                                       int statusCode = httpClient.executeMethod(get);
                                       if (statusCode == HttpStatus.SC_OK) {
                                              textData = get.getResponseBodyAsString();
                                              if(null != textData){
                                                     stringBuilder.append(textData);
                                              }
                                       }
                                }
                         }catch(Exception e){
                                e.printStackTrace();
                         }finally{
                                       get.releaseConnection();
                         }
                   }
                   if(!stringBuilder.toString().isEmpty()){
                	   response =  stringBuilder.toString();
                   }
            }
            return response;
     }
     
     public static boolean hasValidContent(String str) {
 		if (null != str && !"".equals(str.trim()))
 			return true;
 		else
 			return false;
 	}

	public String getFirstNameModel() {
		return firstNameModel;
	}

	public void setFirstNameModel(String firstNameModel) {
		this.firstNameModel = firstNameModel;
	}


}
