package com.jpmorgan.validator.rule;

public class StringSequenceRule extends SequenceRule {

	@Override
	protected void validateSequence(String input, int numberOfDigits) throws SequenceException {
		if (input == null || input.length() != numberOfDigits) {
			throw new SequenceException();
		}
		Integer.valueOf(input);
	}
}
