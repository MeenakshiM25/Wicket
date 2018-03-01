package com.tavavnt.wicket.report;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.model.AbstractCheckBoxModel;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public abstract class CheckBoxColumn extends AbstractColumn<Report, String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CheckBoxColumn() {
		super(null);
	}

	@Override
	public void populateItem(Item<ICellPopulator<Report>> cellItem,
			String componentId, IModel<Report> rowModel) {
		final AbstractCheckBoxModel checkBoxModel = (AbstractCheckBoxModel) newCheckBoxModel(rowModel);
		cellItem.add(new CheckPanel(componentId, checkBoxModel) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onEvent(IEvent<?> event) {
				super.onEvent(event);
				if (event.getPayload() instanceof SelectEventPayload) {
					SelectEventPayload eventPayload = (SelectEventPayload) event
							.getPayload();
					checkBoxModel.setObject(eventPayload.isValue());
					eventPayload.getTarget().add(getCheckbox());
				}
			}
		});
	}

	protected abstract IModel<Boolean> newCheckBoxModel(IModel<Report> rowModel);

	public class CheckPanel extends Panel {
		
		private static final long serialVersionUID = 1L;
		private CheckBox checkbox;

		public CheckBox getCheckbox() {
			return checkbox;
		}

		public void setCheckbox(CheckBox checkbox) {
			this.checkbox = checkbox;
		}

		public CheckPanel(String id, AbstractCheckBoxModel model) {

			super(id, model);
			checkbox = new AjaxCheckBox("label", model) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {
				}

			};
			checkbox.setOutputMarkupId(true);
			add(checkbox);
		}

		protected void dispatchEvent(AjaxRequestTarget arg0, boolean isSelected) {
			send(this.getPage(), Broadcast.BREADTH, new SelectEventPayload(
					arg0, isSelected));
		}

		public CheckPanel(String id) {
			super(id);

			checkbox = new AjaxCheckBox("label", new Model<Boolean>()) {

				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {

					dispatchEvent(target, Boolean.TRUE.equals(getModelObject()));
				}

			};
			checkbox.setOutputMarkupId(true);
			add(checkbox);
		}

	}

	@Override
	public Component getHeader(String componentId) {
		CheckPanel panel = new CheckPanel(componentId);
		panel.setMarkupId("headCheck");
		return panel;

	}

}
