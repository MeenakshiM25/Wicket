package com.tavavnt.wicket.modalwindow;

import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import com.tavavnt.wicket.WicketApplication;

public class CustomModalWindow extends ModalWindow {
	
	private static final long serialVersionUID = 1136474447403199917L;
	ResourceReference cssReference = new CssResourceReference(WicketApplication.class, "modal-style.css");
	ResourceReference javascriptReference = new JavaScriptResourceReference(WicketApplication.class, "modal.js");

	public CustomModalWindow(String id) {
		super(id);
		this.setInitialHeight(200);
	    this.setInitialWidth(536);
		this.setCssClassName("modal-popup");
		this.setMaskType(ModalWindow.MaskType.SEMI_TRANSPARENT);
		this.setResizable(false);
	}

	public CustomModalWindow(String id, IModel<?> model) {
		super(id, model);
		this.setInitialHeight(200);
	    this.setInitialWidth(536);
		this.setCssClassName("modal-popup");
		this.setMaskType(ModalWindow.MaskType.SEMI_TRANSPARENT);
		this.setResizable(false);
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		if (cssReference != null)
		{
			response.render(CssHeaderItem.forReference(cssReference));
		}
		response.render(JavaScriptHeaderItem.forReference(javascriptReference));
	}

}
