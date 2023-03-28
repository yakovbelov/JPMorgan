package com.jpmorgan.validator.rule;

import com.jpmorgan.validator.util.ErrorMessageParameters;
import com.jpmorgan.validator.util.FilenameValidatorProperties;

/**
 * 
 * Extension rule check.
 */
public class ExtensionRule implements Rule {

	private static final String ALLOWED_EXTENSION_KEY = "ALLOWED_EXTENSION";
	private static final String DEFAULT_ALLOWED_EXTENSION = "csv";
	private static final String EXTENSION_ERROR_MESSSAGE_KEY = "EXTENSION";
	private final ErrorMessageParameters errorMessageParameters = new ErrorMessageParameters();
	
	@Override
	public boolean valid(String input) {
		String allowedExtension = FilenameValidatorProperties.getTextValue(ALLOWED_EXTENSION_KEY, DEFAULT_ALLOWED_EXTENSION);
		if (allowedExtension.equalsIgnoreCase(input)) {
			errorMessageParameters.clear();
			return true;
		} else {
			errorMessageParameters.addParameters(allowedExtension, input);
			return false;
		}
	}

	@Override
	public String getErrorMessageKey() {
		return EXTENSION_ERROR_MESSSAGE_KEY;
	}

	@Override
	public Object[] getErrorMessageParameters() {
		return errorMessageParameters.getParameters();
	}
}
