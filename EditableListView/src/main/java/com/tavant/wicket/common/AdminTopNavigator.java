package com.tavant.wicket.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.PropertyModel;

import com.tavant.wicket.model.DropDownOption;
import com.tavant.wicket.session.MySession;

public abstract class AdminTopNavigator extends AdminSideNavigator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdminTopNavigator() {
		addApplicationTypeDropDown();
	}

	private DropDownChoice<DropDownOption> applicationTypeDropDown;
	private DropDownOption selectedType;

	private void addApplicationTypeDropDown() {
		selectedType = MySession.get().getSelectedApplicationType();
		applicationTypeDropDown = new DropDownChoice<DropDownOption>(
				"applicationType", new PropertyModel<DropDownOption>(this,
						"selectedType"), getChoices(),
				new ChoiceRenderer<DropDownOption>("displayValue", "code"));
		applicationTypeDropDown.add(new AjaxFormComponentUpdatingBehavior("onChange") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				MySession.get().setSelectedApplicationType(selectedType);
				setResponsePage(AdminHomePage.class);
			}
		});
		add(applicationTypeDropDown);
	}

	private List<DropDownOption> getChoices() {
		List<DropDownOption> choices = new ArrayList<DropDownOption>();
		choices.add(new DropDownOption("CLB2C","Consumer Lending B2C"));
		choices.add(new DropDownOption("MB2C","Mortgage B2C"));
		choices.add(new DropDownOption("CWP","Collections Web Portal"));
		return choices;
	}

}
