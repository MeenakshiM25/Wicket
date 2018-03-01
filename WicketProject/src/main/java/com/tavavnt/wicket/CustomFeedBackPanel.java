package com.tavavnt.wicket;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class CustomFeedBackPanel  extends FeedbackPanel{

	private static final long serialVersionUID = 1L;

	public CustomFeedBackPanel(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected Component newMessageDisplayComponent(String id,
			FeedbackMessage message) {
		// TODO Auto-generated method stub
		Label label = new Label(id,message.getMessage().toString());
		label.setEscapeModelStrings(this.getEscapeModelStrings());
		return label;
	}
}
