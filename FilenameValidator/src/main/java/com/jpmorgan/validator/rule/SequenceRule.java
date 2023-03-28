package com.jpmorgan.validator.rule;

import com.jpmorgan.validator.util.ErrorMessageParameters;
import com.jpmorgan.validator.util.FilenameValidatorProperties;

/**
 * 
 * Sequence rule check.
 */
public class SequenceRule implements Rule {

	private static final String SEQUENCE_NUM_DIGITS_KEY = "SEQUENCE_NUM_DIGITS";
	private static final int DEFAULT_SEQUENCE_NUM_DIGITS = 2;
	private static final String SEQUENCE_ERROR_MESSSAGE_KEY = "SEQUENCE";
	private final ErrorMessageParameters errorMessageParameters = new ErrorMessageParameters();
	
	@Override
	public boolean valid(String input) {
		int numberOfDigits = FilenameValidatorProperties.getNumber(SEQUENCE_NUM_DIGITS_KEY, DEFAULT_SEQUENCE_NUM_DIGITS);
		try {
			validateSequence(input, numberOfDigits);
		} catch (NumberFormatException | SequenceException mfe) {
			errorMessageParameters.addParameters(numberOfDigits, input);
			return false;
		}
		errorMessageParameters.clear();
		return true;
	}

	protected void validateSequence(String input, int numberOfDigits) throws SequenceException {
		int sequence = Integer.valueOf(input);
		if (!matchesNumberOfDigits(sequence, numberOfDigits)) {
			throw new SequenceException();
		}
	}

	@Override
	public String getErrorMessageKey() {
		return SEQUENCE_ERROR_MESSSAGE_KEY;
	}

	@Override
	public Object[] getErrorMessageParameters() {
		return errorMessageParameters.getParameters();
	}

	private boolean matchesNumberOfDigits(int number, int numOfDigits) {
		if (numOfDigits < 1) {
			// assume this is the case of any number of digits allowwed
			return true;
		}
		if (numOfDigits == 1 && number == 0) {
			// zero does not satisfy the simple formula below for the case of 1 digit 
			return true;
		}
		return number >= Math.pow(10, numOfDigits - 1) && number <= Math.pow(10, numOfDigits) - 1;
	}
	
	protected static class SequenceException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 5313376754400386243L;
	}
}
