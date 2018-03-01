package com.tavant.wicket.poc;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;

public class StaticImage extends WebComponent {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StaticImage(String id, IModel<?> model) {
	        super(id, model);
	    }
	 
	    protected void onComponentTag(ComponentTag tag) {
	        super.onComponentTag(tag);
	        checkComponentTag(tag, "img");
	        tag.put("src", getDefaultModelObjectAsString());
	        // since Wicket 1.4 you need to use getDefaultModelObjectAsString() instead of getModelObjectAsString()
	    }
	 
}