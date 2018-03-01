package com.tavant.wicket.common.component;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.tavant.wicket.model.DayLineVO;
import com.tavant.wicket.model.DropDownOption;


public class ConfigBusinessHoursPanel extends Panel {

	private static final long serialVersionUID = 1L;
	private List<DayLineVO> currentWorkingHoursList;
	private ListView<DayLineVO> configBusinessHoursListView;
	private List<DropDownOption> startDayOptions = new ArrayList<DropDownOption>();
	private List<DropDownOption> endDayOptions = new ArrayList<DropDownOption>();
	private List<DropDownOption> hours = new ArrayList<DropDownOption>();
	private List<DropDownOption> mins = new ArrayList<DropDownOption>();
	private List<DropDownOption> ampm = new ArrayList<DropDownOption>();
	

	public ConfigBusinessHoursPanel(String id, List<DayLineVO> dayLineVOList) {
		super(id);
		setCurrentWorkingHoursList(dayLineVOList);
		populateOptions();
		addConfigBusinessHoursListView();
	}

	private void populateOptions() {
	
		startDayOptions.add(new DropDownOption("---",  "---"));
		startDayOptions.add(new DropDownOption("Monday",  "Monday"));
		startDayOptions.add(new DropDownOption("Tuesday",  "Tuesday"));
		startDayOptions.add(new DropDownOption("Wednesday",  "Wednesday"));
		startDayOptions.add(new DropDownOption("Thursday",  "Thursday"));
		startDayOptions.add(new DropDownOption("Friday",  "Friday"));
		startDayOptions.add(new DropDownOption("Saturday",  "Saturday"));
		startDayOptions.add(new DropDownOption("Sunday",  "Sunday"));
		
		endDayOptions.add(new DropDownOption("---", ""));
		endDayOptions.add(new DropDownOption("Closed", "Closed"));
		endDayOptions.add(new DropDownOption("Monday", "Monday"));
		endDayOptions.add(new DropDownOption("Tuesday", "Tuesday"));
		endDayOptions.add(new DropDownOption("Wednesday", "Wednesday"));
		endDayOptions.add(new DropDownOption("Thursday", "Thursday"));
		endDayOptions.add(new DropDownOption("Friday", "Friday"));
		endDayOptions.add(new DropDownOption("Saturday", "Saturday"));
		endDayOptions.add(new DropDownOption("Sunday", "Sunday"));
		
		hours.add(new DropDownOption("--","--"));
		hours.add(new DropDownOption("01","01"));
		hours.add(new DropDownOption("02","02"));
		hours.add(new DropDownOption("03","03"));
		hours.add(new DropDownOption("04","04"));
		hours.add(new DropDownOption("05","05"));
		hours.add(new DropDownOption("06","06"));
		hours.add(new DropDownOption("07","07"));
		hours.add(new DropDownOption("08","08"));
		hours.add(new DropDownOption("09","09"));
		hours.add(new DropDownOption("10","10"));
		hours.add(new DropDownOption("11","11"));
		hours.add(new DropDownOption("12","12"));
		
		mins.add(new DropDownOption("--","--"));
		mins.add(new DropDownOption("00","00"));
		mins.add(new DropDownOption("30","30"));
	
		ampm.add(new DropDownOption("--","--"));
		ampm.add(new DropDownOption("AM","AM"));
		ampm.add(new DropDownOption("PM","PM"));
	
		
	}

	private void addConfigBusinessHoursListView() {
		configBusinessHoursListView = new ListView<DayLineVO>("configBusinessHoursListView",new PropertyModel<List<DayLineVO>>(this,"currentWorkingHoursList")) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<DayLineVO> item) {
				final DayLineVO dayLineVO = item.getModelObject();
				item.add(new AjaxCheckBox("selectBox", new Model<Boolean>(dayLineVO.isSelected())) {
					private static final long serialVersionUID = 1L;
					@Override
					protected void onUpdate(AjaxRequestTarget target) {
						 
						
					}
				});
				
				item.add(getStartDayOption(dayLineVO));
				item.add(getEndDayOption(dayLineVO));
				item.add(getStartHourOption(dayLineVO));
				item.add(getStartMinsOption(dayLineVO));
				item.add(getStartAMPMOption(dayLineVO));
				item.add(getEndHourOption(dayLineVO));
				
				item.add(getEndMinsOption(dayLineVO));
				item.add(getEndAMPMOption(dayLineVO));
				
			}

		
		};
		configBusinessHoursListView.setOutputMarkupId(true);
		add(configBusinessHoursListView);
	}
	
	protected DropDownChoice<DropDownOption> getEndAMPMOption(final DayLineVO dayLineVO) {
		final DropDownChoice<DropDownOption> endAMPMOption = getDropDown("endAMPM", dayLineVO.getEndAMPM(), ampm);
	
		endAMPMOption.add(new AjaxFormComponentUpdatingBehavior("onchange"){
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				DropDownOption option = endAMPMOption.getModelObject();
				if(null != option){
					dayLineVO.setEndAMPM(option.getCode());
				}
			}
		});
		return endAMPMOption;
	}

	protected DropDownChoice<DropDownOption> getEndMinsOption(final DayLineVO dayLineVO) {
		final DropDownChoice<DropDownOption> endMinsOption = getDropDown("endMins",dayLineVO.getEndMin(),mins); 
		endMinsOption.add(new AjaxFormComponentUpdatingBehavior("onchange"){
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				DropDownOption option = endMinsOption.getModelObject();
				if(null != option){
					dayLineVO.setEndMin(option.getCode());
				}
			}
		});
		return endMinsOption;
	}

	protected DropDownChoice<DropDownOption> getEndHourOption(final DayLineVO dayLineVO) {
		final DropDownChoice<DropDownOption> endHourOption = 	getDropDown("endHour",dayLineVO.getEndHour(),hours); 
	
		endHourOption.add(new AjaxFormComponentUpdatingBehavior("onchange"){
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				DropDownOption option = endHourOption.getModelObject();
				if(null != option){
					dayLineVO.setEndHour(option.getCode());
				}
			}
		});
		return endHourOption;
	}

	protected  DropDownChoice<DropDownOption> getStartAMPMOption(final DayLineVO dayLineVO) {
		final DropDownChoice<DropDownOption> startAMPMOption = getDropDown("startAMPM",dayLineVO.getStartAMPM(),ampm);
		startAMPMOption.add(new AjaxFormComponentUpdatingBehavior("onchange"){
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				DropDownOption option = startAMPMOption.getModelObject();
				if(null != option){
					dayLineVO.setStartAMPM(option.getCode());
				}
			}
		});
		
		return startAMPMOption;
	}
	
	protected  DropDownChoice<DropDownOption> getStartMinsOption(final DayLineVO dayLineVO) {
		final DropDownChoice<DropDownOption> startMinsOption = getDropDown("startMins",dayLineVO.getStartMin(),mins);
		startMinsOption.add(new AjaxFormComponentUpdatingBehavior("onchange"){
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				DropDownOption option = startMinsOption.getModelObject();
				if(null != option){
					dayLineVO.setStartMin(option.getCode());
				}
			}
		});
		
		return startMinsOption;
	}

	protected DropDownChoice<DropDownOption>  getStartHourOption(final DayLineVO dayLineVO) {
		final DropDownChoice<DropDownOption> startHourOption =getDropDown("startHour",dayLineVO.getStartHour(),hours);
		startHourOption.add(new AjaxFormComponentUpdatingBehavior("onchange"){
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				DropDownOption option = startHourOption.getModelObject();
				if(null != option){
					dayLineVO.setStartHour(option.getCode());
				}
			}
		});
		return startHourOption;
	}

	protected DropDownChoice<DropDownOption> getEndDayOption(final DayLineVO dayLineVO) {
		final DropDownChoice<DropDownOption> endDayOption =getDropDown("endDay",dayLineVO.getEndDay(),endDayOptions);
		endDayOption.add(new AjaxFormComponentUpdatingBehavior("onchange"){
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				DropDownOption option = endDayOption.getModelObject();
				if(null != option){
					dayLineVO.setEndDay(option.getCode());
				}
			}
		});
		return endDayOption;
	}

	private DropDownChoice<DropDownOption> getStartDayOption(final DayLineVO dayLineVO){
		final DropDownChoice<DropDownOption> startDayDropDown = getDropDown("startDay",dayLineVO.getStartDay(),startDayOptions);
			
		startDayDropDown.add(new AjaxFormComponentUpdatingBehavior("onchange"){
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				DropDownOption option = startDayDropDown.getModelObject();
				if(null != option){
					dayLineVO.setStartDay(option.getCode());
				}
			}
		});
		return startDayDropDown;
	}
		
	private DropDownChoice<DropDownOption> getDropDown(String id,final String selectedValue,final List<DropDownOption> options){
		final DropDownChoice<DropDownOption> dropDown = new DropDownChoice<DropDownOption>(id, new Model<DropDownOption>(){
			private static final long serialVersionUID = 1L;
			@Override
			public DropDownOption getObject() {
				return getSelectedOption(selectedValue,options);
			}
		}, options,	new ChoiceRenderer<DropDownOption>("displayValue", "code"));
		return dropDown;
	}
	protected DropDownOption getSelectedOption(String value,List<DropDownOption> options) {
		if(null != value){
			for(DropDownOption dropDown:options){
				if(dropDown.getCode().equals(value)){
					return dropDown;
				}
			}
		}
		return options.get(0);
	}

	public List<DayLineVO> getCurrentWorkingHoursList() {
		return currentWorkingHoursList;
	}

	public void setCurrentWorkingHoursList(List<DayLineVO> currentWorkingHoursList) {
		this.currentWorkingHoursList = currentWorkingHoursList;
	}

	
}
