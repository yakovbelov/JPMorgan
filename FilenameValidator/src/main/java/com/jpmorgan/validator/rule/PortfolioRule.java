package com.jpmorgan.validator.rule;

import com.jpmorgan.validator.util.ErrorMessageParameters;
import com.jpmorgan.validator.util.FilenameValidatorProperties;

/**
 * 
 * Portfolio rule check.
 */
public class PortfolioRule implements Rule {

	private static final String ALLOWED_PORTFOLIO_KEY = "ALLOWED_PORTFOLIO";
	private static final String DEFAULT_ALLOWED_PORTFOLIO = "A/B/C";
	private static final String PORTFOLIO_ERROR_MESSSAGE_KEY = "PORTFOLIO";
	private final ErrorMessageParameters errorMessageParameters = new ErrorMessageParameters();
	
	@Override
	public boolean valid(String input) {
		String allowedPortfolio = FilenameValidatorProperties.getTextValue(ALLOWED_PORTFOLIO_KEY, DEFAULT_ALLOWED_PORTFOLIO);
		if (allowedPortfolio.replaceAll("/", "").contains(input)) {
			errorMessageParameters.clear();
			return true;
		} else {
			errorMessageParameters.addParameters(allowedPortfolio, input);
			return false;
		}
	}

	@Override
	public String getErrorMessageKey() {
		return PORTFOLIO_ERROR_MESSSAGE_KEY;
	}

	@Override
	public Object[] getErrorMessageParameters() {
		return errorMessageParameters.getParameters();
	}

}
