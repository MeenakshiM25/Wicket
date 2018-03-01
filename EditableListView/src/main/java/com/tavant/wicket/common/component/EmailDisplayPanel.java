package com.tavant.wicket.common.component;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.tavant.wicket.model.EmailVO;

public class EmailDisplayPanel extends Panel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailDisplayPanel(String id, IModel<EmailVO> model) {
		super(id, model);
		Label emailText = new Label("emailText",new Model<String>(){
			private static final long serialVersionUID = 1L;
			@Override
			public String getObject() {
				return getEmailText();
			}
		});
		emailText.setEscapeModelStrings(false);
		emailText.setOutputMarkupId(true);
		add(emailText);
	}

	protected String getEmailText() {
		EmailVO emailVO = (EmailVO) this.getDefaultModelObject();
		return "<B>"+ emailVO.getEmailAddress() +"</B>";
	}

}
