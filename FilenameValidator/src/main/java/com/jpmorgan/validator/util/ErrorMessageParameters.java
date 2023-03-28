package com.jpmorgan.validator.util;

/**
 * 
 * Helper class useful for storing and error message parameters used for error message creation.
 */
public class ErrorMessageParameters {

	private Object[] errorMessageParameters;
	
	public void clear() {
		errorMessageParameters = null;
	}
	
	public void addParameters(Object ... parameters) {
		errorMessageParameters = parameters;
	}
	
	public Object[] getParameters() {
		return errorMessageParameters;
	}
}
