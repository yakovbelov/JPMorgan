package com.jpmorgan.validator.rule;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.jpmorgan.validator.util.ErrorMessageParameters;
import com.jpmorgan.validator.util.FilenameValidatorProperties;

/**
 * 
 * Date Format rule check.
 */
public class DateFormatRule implements Rule {

	private static final String ALLOWED_DATE_FORMAT_KEY = "ALLOWED_DATE_FORMAT";
	private static final String DEFAULT_ALLOWED_DATE_FORMAT = "ddMMyyyy";
	private static final String DATE_FORMAT_ERROR_MESSSAGE_KEY = "DATE_FORMAT";
	private final ErrorMessageParameters errorMessageParameters = new ErrorMessageParameters();
	
	@Override
	public boolean valid(String input) {
		String allowedDateFormat = FilenameValidatorProperties.getTextValue(ALLOWED_DATE_FORMAT_KEY, DEFAULT_ALLOWED_DATE_FORMAT);
		DateFormat sdf = new SimpleDateFormat(allowedDateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(input);
        } catch (ParseException e) {
        	errorMessageParameters.addParameters(input, allowedDateFormat.toLowerCase());
            return false;
        }
        errorMessageParameters.clear();
        return true;
	}

	@Override
	public String getErrorMessageKey() {
		return DATE_FORMAT_ERROR_MESSSAGE_KEY;
	}

	@Override
	public Object[] getErrorMessageParameters() {
		return errorMessageParameters.getParameters();
	}
}
