package com.jpmorgan.validator.rule;

import com.jpmorgan.validator.util.ErrorMessageParameters;
import com.jpmorgan.validator.util.FilenameValidatorProperties;

/**
 * 
 * Prefix rule check.
 */
public class PrefixRule implements Rule {

	private static final String ALLOWED_PREFIX_KEY = "ALLOWED_PREFIX";
	private static final String DEFAULT_ALLOWED_PREFIX = "Test";
	private static final String PREFIX_ERROR_MESSSAGE_KEY = "PREFIX";
	private final ErrorMessageParameters errorMessageParameters = new ErrorMessageParameters();
	
	@Override
	public boolean valid(String input) {
		String prefix = FilenameValidatorProperties.getTextValue(ALLOWED_PREFIX_KEY, DEFAULT_ALLOWED_PREFIX);
		if (prefix.equals(input)) {
			errorMessageParameters.clear();
			return true;
		} else {
			errorMessageParameters.addParameters(prefix, input);
			return false;
		}
	}

	@Override
	public String getErrorMessageKey() {
		return PREFIX_ERROR_MESSSAGE_KEY;
	}

	@Override
	public Object[] getErrorMessageParameters() {
		return errorMessageParameters.getParameters();
	}

}
