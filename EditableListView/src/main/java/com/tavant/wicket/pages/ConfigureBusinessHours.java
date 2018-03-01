package com.tavant.wicket.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.tavant.wicket.common.AdminTopNavigator;
import com.tavant.wicket.common.component.ConfigBusinessHoursPanel;
import com.tavant.wicket.common.component.EmailDisplayPanel;
import com.tavant.wicket.common.component.TimeDisplayPanel;
import com.tavant.wicket.model.DayLineVO;
import com.tavant.wicket.model.DropDownOption;
import com.tavant.wicket.model.EmailVO;

public class ConfigureBusinessHours extends AdminTopNavigator{

	private static final long serialVersionUID = 1L;
	private List<DayLineVO> dayLineVOCollection = new ArrayList<DayLineVO>();
	private List<EmailVO> emailVOCollection;
	private DropDownChoice<DropDownOption> emailGroupDropDown;
	private DropDownOption selectedGroup;
	private WebMarkupContainer currentHoursContainer;
	private WebMarkupContainer currentEmailsContainer;
	private List<DayLineVO> currentHoursList;
	private List<DayLineVO> currentWorkingHoursList;
	
	private ListView<DayLineVO> currentHoursListView;
	private ListView<EmailVO> currentEmailsListView;
	private ConfigBusinessHoursPanel configBusinessHoursPanel;
		

	public ConfigureBusinessHours(){
	    populateDayLineVOCollection();
		addEmailGroupDropDown();
		populateCurrentHoursListForGroupSelected();
		addCurrentEmailsListView();
		addCurrentHoursListView();
		addConfigBusinessHoursListView();
	}
	
	private void addConfigBusinessHoursListView() {
		configBusinessHoursPanel = new ConfigBusinessHoursPanel("configBusinessHoursPanel", currentWorkingHoursList);
		configBusinessHoursPanel.setOutputMarkupId(true);
		add(configBusinessHoursPanel);
	}


	private void populateDayLineVOCollection() {
		/** this will be populated from DB **/
		/** HE **/
		dayLineVOCollection.add(new DayLineVO("HE", "Monday", "Tuesday", "08","00","AM","12","30","PM", 0));
		dayLineVOCollection.add(new DayLineVO("HE", "Wednesday", "Friday", "03","00","AM","04","30","PM", 1));
		dayLineVOCollection.add(new DayLineVO("HE", "Saturday", "", "05","00","AM","05","30","PM", 2));
		dayLineVOCollection.add(new DayLineVO("HE", "Sunday", "Closed", "","","","","","", 3));
		/** VF **/
		dayLineVOCollection.add(new DayLineVO("VF", "Monday", "Friday", "01","00","AM","06","30","PM", 0));
		dayLineVOCollection.add(new DayLineVO("VF", "Saturday", "Closed", "","","","","","", 2));
		dayLineVOCollection.add(new DayLineVO("VF", "Sunday", "Closed", "","","","","","", 3));
		/**UR **/
		dayLineVOCollection.add(new DayLineVO("UR", "Monday", "Friday", "02","30","AM","07","30","PM", 0));
		dayLineVOCollection.add(new DayLineVO("UR", "Saturday", "Closed", "","","","","","", 2));
		dayLineVOCollection.add(new DayLineVO("UR", "Sunday", "Closed", "","","","","","", 3));
	}



	private void addEmailGroupDropDown() {
		
		selectedGroup = (null == selectedGroup)? new DropDownOption("HE","Home Equity"):selectedGroup;
		
		emailGroupDropDown = new DropDownChoice<DropDownOption>(
				"emailGroup", new PropertyModel<DropDownOption>(this,
						"selectedGroup"), getEmailGroupChoices(),
				new ChoiceRenderer<DropDownOption>("displayValue", "code"));
		emailGroupDropDown.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				populateCurrentHoursListForGroupSelected();
				populateEmails();
				configBusinessHoursPanel.setCurrentWorkingHoursList(currentWorkingHoursList);
				target.add(currentHoursContainer);
				target.add(currentEmailsContainer);
				target.add(configBusinessHoursPanel);
			}
		});
		add(emailGroupDropDown);
	}

	private List<DropDownOption> getEmailGroupChoices() {
		List<DropDownOption> choices = new ArrayList<DropDownOption>();
		choices.add(new DropDownOption("HE","Home Equity"));
		choices.add(new DropDownOption("VF","Vehicle Financing"));
		choices.add(new DropDownOption("UR","User Registration"));
		return choices;
	}
	
	private void addCurrentHoursListView() {
		currentHoursContainer = new WebMarkupContainer("currentHoursContainer");
		currentHoursContainer.setOutputMarkupId(true);
		add(currentHoursContainer);
		Label currentHoursText = new Label("currentHours",new Model<String>(){
			private static final long serialVersionUID = 1L;
			@Override
			public String getObject() {
				return "Current hours displayed at "+ (selectedGroup.getDisplayValue()) + " locations:";
			
		}});
		currentHoursText.setOutputMarkupId(true);
		currentHoursContainer.add(currentHoursText);
		currentHoursListView = new ListView<DayLineVO>("currentHoursListItem",new PropertyModel<List<DayLineVO>>(this, "currentHoursList")) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(ListItem<DayLineVO> item) {
				item.add(new TimeDisplayPanel("timeDisplayPanel", new Model<DayLineVO>(item.getModelObject())));
			}
		};
		currentHoursListView.setOutputMarkupId(true);
		currentHoursContainer.add(currentHoursListView);
	}
	
	private void addCurrentEmailsListView() {
		currentEmailsContainer = new WebMarkupContainer("currentEmailsContainer");
		currentEmailsContainer.setOutputMarkupId(true);
		add(currentEmailsContainer);
		Label currentEmailsText = new Label("currentEmails",new Model<String>(){
			private static final long serialVersionUID = 1L;
			@Override
			public String getObject() {
				return  (selectedGroup.getDisplayValue()) + " hours applies to the following:";
			
		}});
		currentEmailsText.setOutputMarkupId(true);
		currentEmailsContainer.add(currentEmailsText);
		populateEmails();
		currentEmailsListView = new ListView<EmailVO>("currentEmailsListItem",new PropertyModel<List<EmailVO>>(this, "emailVOCollection")) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(ListItem<EmailVO> item) {
				item.add(new EmailDisplayPanel("emailDisplayPanel", new Model<EmailVO>(item.getModelObject())));
			}
		};
		currentEmailsListView.setOutputMarkupId(true);
		currentEmailsContainer.add(currentEmailsListView);
	}

	private void populateCurrentHoursListForGroupSelected() {
		currentWorkingHoursList = new ArrayList<DayLineVO>();
		currentHoursList = new ArrayList<DayLineVO>();
		if(null != selectedGroup){
			for(DayLineVO dayLineVO :dayLineVOCollection){
				if(dayLineVO.getCode().equals(selectedGroup.getCode())){
					currentWorkingHoursList.add(dayLineVO);
					try {
						currentHoursList.add((DayLineVO) dayLineVO.clone());
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	private void populateEmails() {
		emailVOCollection = new ArrayList<EmailVO>();
		/** this will be populated from constants **/
		if("HE".equals(selectedGroup.getCode())){
			emailVOCollection.add(new EmailVO("HE 10 Day Reminder Email", true));
			emailVOCollection.add(new EmailVO("HE Application Thank You Email", true));
			emailVOCollection.add(new EmailVO("HE Application Denied Email", true));
			emailVOCollection.add(new EmailVO("HE Application Approved Email", true));
			emailVOCollection.add(new EmailVO("HE Save and Return Email", true));
			emailVOCollection.add(new EmailVO("HE Click to Call Pop-up", true));
		}else if("VF".equals(selectedGroup.getCode())){
			emailVOCollection.add(new EmailVO("VF 10 Day Reminder Email", true));
			emailVOCollection.add(new EmailVO("VF Application Thank You Email", true));
			emailVOCollection.add(new EmailVO("VF Application Denied Email", true));
			emailVOCollection.add(new EmailVO("VF Application Approved Email", true));
			emailVOCollection.add(new EmailVO("VF Save and Return Email", true));
			emailVOCollection.add(new EmailVO("VF Click to Call Pop-up", true));
		}else if("UR".equals(selectedGroup.getCode())){
			emailVOCollection.add(new EmailVO("Forgot Login Email", true));
			emailVOCollection.add(new EmailVO("Forgot Password Email", true));
			emailVOCollection.add(new EmailVO("Registration Email", true));
		}
	}
}
