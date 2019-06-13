package com.toogood.data.transform.validation;

import com.toogood.data.transform.input.Input;

public class ValidateResults {
	private boolean valid;
	private String errorMessage;
	private Input invalidInput;
	
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Input getInValidInput() {
		return invalidInput;
	}
	public void setInvalidInput(Input invalidInput) {
		this.invalidInput = invalidInput;
	}	
}
