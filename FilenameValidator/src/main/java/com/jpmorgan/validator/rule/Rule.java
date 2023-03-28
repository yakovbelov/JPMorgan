package com.jpmorgan.validator.rule;

/**
 * 
 * Rule check
 */
public interface Rule {
	/**
	 * Rule validation
	 * 
	 * @param input
	 * @return
	 */
	boolean valid(String input);
	/**
	 * Error message key for the case when Rule is not followed
	 * 
	 * @return
	 */
	String getErrorMessageKey();
	/**
	 * Error message parameters for the case when Rule is not followed
	 * 
	 */
	Object[] getErrorMessageParameters();
}
