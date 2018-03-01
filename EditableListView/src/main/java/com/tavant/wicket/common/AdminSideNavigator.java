package com.tavant.wicket.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;

import com.tavant.wicket.pages.ConfigureBusinessHours;
import com.tavant.wicket.pages.DropDownManagement;
import com.tavant.wicket.session.MySession;


public abstract class AdminSideNavigator extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public AdminSideNavigator(){
		 addSideNavigator();
	 }

	private void addSideNavigator() {
		WebMarkupContainer clb2cSideNavigator = new WebMarkupContainer("clb2cSideNav"){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				String appType = MySession.get().getApplicationType();
				return ((StringUtils.isNotEmpty(appType) && "CLB2C".equals(appType))? true: false);
			}
		};
		
		clb2cSideNavigator.setOutputMarkupId(true);
		add(clb2cSideNavigator);
		
		AjaxLink<?> loanTypeLink = new AjaxLink<Object>("loanType"){
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(DropDownManagement.class);
			}
			
		};
		clb2cSideNavigator.add(loanTypeLink);
		AjaxLink<?> configureBusinessHours = new AjaxLink<Object>("configureBusinessHours"){
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(ConfigureBusinessHours.class);
			}
			
		};
		clb2cSideNavigator.add(configureBusinessHours);
	}

}
