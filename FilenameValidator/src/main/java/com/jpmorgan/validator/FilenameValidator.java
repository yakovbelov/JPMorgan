package com.jpmorgan.validator;

import java.util.logging.Logger;

import com.jpmorgan.validator.rule.Rule;
import com.jpmorgan.validator.rule.SequentialRuleFactory;
import com.jpmorgan.validator.util.FilenameValidatorProperties;

import static com.jpmorgan.validator.util.FilenameValidatorProperties.getTextValue;

/**
 * 
 * Main class. Splits the filename into sections and applies Rules for each section.
 */
public class FilenameValidator {

	private static final Logger logger = Logger.getLogger(FilenameValidator.class.getName());
	private static final SequentialRuleFactory sequentialRuleFactory = new SequentialRuleFactory();
	
	private static final String USAGE_KEY = "USAGE";
	private static final String DEFAULT_USAGE = "Usage: FilenameValidator [filename]";
	private static final String DELIMITER_KEY = "DELIMITER";
	private static final String DEFAULT_DELIMITER = "_|\\.";
	private static final String FILENAME_FORMAT_KEY = "FILENAME_FORMAT";
	private static final String DEFAULT_FILENAME_FORMAT = "Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv";

	private static final String VALIDATION_PASSED_KEY = "VALIDATION_PASSED";
	private static final String VALIDATION_FAILED_KEY = "VALIDATION_FAILED";
	private static final String BAD_FILENAME_FORMAT_KEY = "BAD_FILENAME_FORMAT";
	
	static {
		// this helps unit tests
		FilenameValidatorProperties.loadProperties();
	}
	
	public static void main(String ... args) {
		String output = new FilenameValidator().validate(args);
		logger.info(output);
		System.exit(0);
	}
	
	public String validate(String ... args) {
		String output;
		if (args == null || args.length == 0) {
			output = getTextValue(USAGE_KEY, DEFAULT_USAGE);
		} else {
			output = validateSequentially(args[0]);
		}
		return output;
	}
	
	private String validateSequentially(String filename) {
		try {
			String[] fileNameSections = getFilenameSections(filename);
			for (int i = 0; i < fileNameSections.length; i++) {
				Rule rule = getSequentialRuleFactory().getRule(i);
				if (!rule.valid(fileNameSections[i])) {
					return
						String.format(getTextValue(VALIDATION_FAILED_KEY), filename) + 
						String.format(getTextValue(rule.getErrorMessageKey()), rule.getErrorMessageParameters());
				}
			}
			return String.format(getTextValue(VALIDATION_PASSED_KEY), filename);
		} catch (NullPointerException | FilenameFormatException ex) {
			return
				String.format(getTextValue(VALIDATION_FAILED_KEY), filename) + 
				String.format(getTextValue(BAD_FILENAME_FORMAT_KEY), getTextValue(FILENAME_FORMAT_KEY, DEFAULT_FILENAME_FORMAT));
		}
	}

	private String[] getFilenameSections(String filename) throws FilenameFormatException {
		String[] fileNameSections = filename.split(getTextValue(DELIMITER_KEY, DEFAULT_DELIMITER));
		if (fileNameSections.length != getSequentialRuleFactory().getNumberOfRules()) {
			throw new FilenameFormatException();
		}
		return fileNameSections;
	}
	
	private SequentialRuleFactory getSequentialRuleFactory() {
		return sequentialRuleFactory;
	}
	
	private static class FilenameFormatException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6274406357912816347L;
	}
}
