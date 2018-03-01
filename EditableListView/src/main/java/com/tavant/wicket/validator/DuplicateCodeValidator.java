package com.tavant.wicket.validator;

import java.util.List;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;

import com.tavant.wicket.model.DropDownOption;
import com.tavant.wicket.session.MySession;

public class DuplicateCodeValidator implements IValidator<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DropDownOption selectedOption;

	public DuplicateCodeValidator(DropDownOption dropDownOption) {
		selectedOption = dropDownOption;
	}
	@Override
	public void validate(IValidatable<String> validatable) {
	List<DropDownOption> options = MySession.get().getDropDownOptions();
	 String value = validatable.getValue();
	for(DropDownOption option: options){
		if(!option.equals(selectedOption)){
			
		}
		
	}
	 
	  
		
	}

}
