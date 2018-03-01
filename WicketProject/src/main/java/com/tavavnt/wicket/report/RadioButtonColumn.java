package com.tavavnt.wicket.report;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public abstract  class RadioButtonColumn extends AbstractColumn<Report, String> {
	private static final long serialVersionUID = 1L;

	public RadioButtonColumn() {
		super(null);
	}

	@Override
	public void populateItem(Item<ICellPopulator<Report>> cellItem,
			String componentId, IModel<Report> rowModel) {
		cellItem.add(new RadioPanel(componentId, new Model<Report>(rowModel.getObject())) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onEvent(IEvent<?> event) {
				super.onEvent(event);
				
			}
		});
	}
	protected  abstract void updateRadioGroup(AjaxRequestTarget target,Object object) ;


	public  class RadioPanel extends Panel {
		
		private static final long serialVersionUID = 1L;
		private Radio<Report> radioButton;


		public Radio<Report> getRadioButton() {
			return radioButton;
		}

		public void setRadioButton(Radio<Report> radioButton) {
			this.radioButton = radioButton;
		}

		public RadioPanel(String id, Model<Report> model) {
			
			super(id, model);
			radioButton = new Radio<Report>("radio", model);
			radioButton.add(new AjaxEventBehavior("click"){
				private static final long serialVersionUID = 1L;

				@Override
				protected void onEvent(AjaxRequestTarget target) {
					System.out.println("radio Button clicked");
					System.out.println(getDefaultModelObject());
					updateRadioGroup(target,getDefaultModelObject());
				}
				
			});
			radioButton.setOutputMarkupId(true);
			add(radioButton);
		}

		


	}


}
