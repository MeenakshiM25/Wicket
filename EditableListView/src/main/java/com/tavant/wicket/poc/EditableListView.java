package com.tavant.wicket.poc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.extensions.model.AbstractCheckBoxModel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import com.tavant.wicket.model.DropDownOption;

public class EditableListView extends WebPage {

	private static final long serialVersionUID = 1L;
	private Form<DropDownOption> form;
	private  List<DropDownOption> dropDownOptions;
	private WebMarkupContainer errorContainer ;
	private WebMarkupContainer loanTypePanel;
	private  ListView<DropDownOption>	listView;
	public EditableListView(){
		addForm();
		createFeedbackPanel();
		addListView();
		addButtons();
	}

	private void addForm() {
		form = new Form<DropDownOption>("form");
		add(form);
		loanTypePanel = new WebMarkupContainer("listviewpanel");
		loanTypePanel.setOutputMarkupId(true);
		form.add(loanTypePanel);
	}
	 
	private void createFeedbackPanel() {
		errorContainer = new WebMarkupContainer("errorContainer");
		errorContainer.setMarkupId("errorContainer");
		errorContainer.setOutputMarkupPlaceholderTag(true);
		errorContainer.setVisible(false);
		errorContainer.add(new FeedbackPanel("feedback"));
		form.addOrReplace(errorContainer);
	}
	private void addListView() {
	  dropDownOptions = new ArrayList<DropDownOption>();
	  populateDropDownOptions();
	  listView = new ListView<DropDownOption>("listView",dropDownOptions){
		private static final long serialVersionUID = 1L;

		@Override
		protected void populateItem(ListItem<DropDownOption> item) {
			final DropDownOption dropDownOption = item.getModelObject();
			final Label status = new Label("status",new PropertyModel<String>(dropDownOption , "status"));
			status.setOutputMarkupId(true);
			IModel<Boolean> checkBoxModel = new AbstractCheckBoxModel(){
				private static final long serialVersionUID = 1L;
				@Override
				public boolean isSelected() {
					return ("Active".equalsIgnoreCase(dropDownOption.getStatus())?true:false);
				}
				@Override
				public void select() {
					dropDownOption.setStatus("Active");
				}
				@Override
				public void unselect() {
					dropDownOption.setStatus("Inactive");
				}
			};
			item.add(new AjaxCheckBox("statuschkbox",checkBoxModel){
				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					dropDownOption.setEdited(true);
					target.add(status);
				}
				
			});
			TextField<String> codeField = new TextField<String>("code",new PropertyModel<String>(dropDownOption , "code")){
				private static final long serialVersionUID = 1L;
				@Override
				public boolean isEnabled() {
					return StringUtils.isEmpty(dropDownOption.getId())? true:false;
				}
	
			};
			codeField.add(new AjaxFormComponentUpdatingBehavior("onchange") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					dropDownOption.setEdited(true);
				}
			});
			codeField.setRequired(true);
			codeField.setOutputMarkupId(true);
			item.add(codeField);
			TextField<String> displayValueField = new TextField<String>("displayValue",new PropertyModel<String>(dropDownOption , "displayValue"));
			displayValueField.add(new AjaxFormComponentUpdatingBehavior("onchange") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					dropDownOption.setEdited(true);
				}
			});
			displayValueField.setRequired(true);
			item.add(displayValueField);
			TextField<String> descriptionField = new TextField<String>("description",new PropertyModel<String>(dropDownOption , "description"));
			descriptionField.setRequired(true);
			descriptionField.add(new AjaxFormComponentUpdatingBehavior("onchange") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					dropDownOption.setEdited(true);
				}
			});
			item.add(descriptionField);
			TextField<Integer> displayOrderField = new TextField<Integer>("displayOrder",new PropertyModel<Integer>(dropDownOption , "displayOrder"));
			displayOrderField.add(new AjaxFormComponentUpdatingBehavior("onchange") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					dropDownOption.setEdited(true);
				}
			});
			displayOrderField.setRequired(true);
			item.add(displayOrderField);
			item.add(new Label("updatedDate",new PropertyModel<String>(dropDownOption , "updatedDate")));
			item.add(new Label("updatedBy",new PropertyModel<String>(dropDownOption , "updatedBy")));
			item.add(status);
			item.add(new AttributeModifier("Class", new Model<String>(){
				private static final long serialVersionUID = 1L;

				@Override
				public String getObject() {
					return (dropDownOption.isDuplicate())? "error-border":"";
				}
			}));
		}
		  
	  };
	  listView.setOutputMarkupId(true);
	  listView.setReuseItems(true);
	  loanTypePanel.add(listView);
	}
	

	private void populateDropDownOptions() {
		dropDownOptions.add(new DropDownOption("EASY_HOME_REFI","easy home refinance","easy home refinance",1));
		dropDownOptions.add(new DropDownOption("EQUITY_FLEX_LINE","equity flex line","equity flex line",2));
	}

	private void addButtons() {
		AjaxButton addButton = new AjaxButton("addBtn"){
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onError(AjaxRequestTarget target,Form<?> form) {
				super.onError();				
				if(form.hasError()){
					addErrors(target,form);
					target.add(form);		
				}			
			}
			@Override
			public void onSubmit(AjaxRequestTarget target,Form<?> form) {
				super.onSubmit(target, form);
				if(hasDuplicate(target,form)){
					return;
				}
				updateDropDownOptions();
				dropDownOptions.add(new DropDownOption());
				errorContainer.setVisible(false);
				target.add(errorContainer);
				target.add(loanTypePanel);
			}
		};
		form.add(addButton);
		AjaxButton applyButton = new AjaxButton("applyBtn"){
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onError(AjaxRequestTarget target,
					Form<?> form) {
				super.onError();				
				if(form.hasError()){
					addErrors(target,form);
					target.add(form);			
				}		
			}
			@Override
			public void onSubmit(AjaxRequestTarget target,
					Form<?> form) {
				super.onSubmit(target, form);
				if(hasDuplicate(target,form)){
					return;
				}
				updateDropDownOptions();
				errorContainer.setVisible(false);
				target.add(errorContainer);
				target.add(loanTypePanel);
			}
		};
		
		form.add(applyButton);
	}
	
	private void updateDropDownOptions(){
		for(DropDownOption dropDownOption:dropDownOptions){
			if(StringUtils.isEmpty(dropDownOption.getId())){
				dropDownOption.setId(UUID.randomUUID().toString());
				dropDownOption.setUpdatedBy("VISITOR");
				dropDownOption.setUpdatedDate(new Date().toString());
			}
		}
	}
	
	
	
	protected boolean hasDuplicate(AjaxRequestTarget target, Form<?> form) {
		boolean hasError = false;
		CollectionUtils.forAllDo(dropDownOptions, new Closure(){
			@Override
			public void execute(Object input) {
				 DropDownOption option = (DropDownOption) input;
				 option.setDuplicate(false);
			}
			
		});
		for(DropDownOption dropDownOption : dropDownOptions){
			for(DropDownOption dropDownOption1 : dropDownOptions){
				if(!dropDownOption.equals(dropDownOption1) && (dropDownOption.getCode().equals(dropDownOption1.getCode()) || 
						dropDownOption.getDisplayOrder().equals(dropDownOption1.getDisplayOrder()))){
					dropDownOption.setDuplicate(true);
					dropDownOption1.setDuplicate(true);
					hasError = true;
				}
			}
		}
		if(hasError){
			form.error("Please make sure that code/display order is unique for the rows highlighted.");
			errorContainer.setVisible(true);
			target.add(errorContainer);
			target.add(form);
		}
		return hasError;
	}

	
	private void addErrors(final AjaxRequestTarget target, Form<?> form){
		errorContainer.setVisible(true);
		target.add(errorContainer);
		
		form.visitFormComponents(new IVisitor<FormComponent<?>, Void>() {
			public void component(final FormComponent<?> field, IVisit<Void> visit) {
				if (field.hasFeedbackMessage()) {
					target.appendJavaScript("addError('" + field.getMarkupId() + "');");
				}else{
					target.appendJavaScript("removeError('" + field.getMarkupId() +"');");
				}
			}
		});
		target.add(form);
	}

}
