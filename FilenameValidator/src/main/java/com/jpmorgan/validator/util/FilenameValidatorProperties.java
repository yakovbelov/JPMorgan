package com.jpmorgan.validator.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Class to store properties where text constants are loaded from a text file. Not multi-threading safe.
 */
public class FilenameValidatorProperties {

	private static final Logger logger = Logger.getLogger(FilenameValidatorProperties.class.getName());
	private static final Properties PROPS = new Properties();

	private static final String PROPERTIES_LOCATION_KEY = "com.jpmorgan.validator.util.properties";
	private static final String DEFAULT_PROPERTIES_LOCATION = "com/jpmorgan/validator/util/FilenameValidator.properties";
	private static final String PROPERTIES_NOT_CONFIGURED = "properties not configured";
	
	// this property is used for properties integrity check and includes itself; 
    // can be maintained in FilenameValidator.properties; 
	// this property is overridden by the one in code.
	private static final String PROPERTIES_NUM_KEY = "PROPERTIES_NUM";
	private static final int DEFAULT_PROPERTIES_NUM = 9;
	
	public static void loadProperties() {
		String propertiesLocation = System.getProperty(PROPERTIES_LOCATION_KEY, DEFAULT_PROPERTIES_LOCATION);
		try (InputStream input = FilenameValidatorProperties.class.getClassLoader().getResourceAsStream(propertiesLocation)) {
			PROPS.clear();
			PROPS.load(input);
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
		if (PROPS.size() < FilenameValidatorProperties.getNumber(PROPERTIES_NUM_KEY, DEFAULT_PROPERTIES_NUM)) {
			logger.severe(PROPERTIES_NOT_CONFIGURED);
			System.exit(1);
		}
	}
	
	public static String getTextValue(String textKey) {
		return PROPS.getProperty(textKey);
	}
	
	public static String getTextValue(String textKey, String textValueDefault) {
		return PROPS.getProperty(textKey, textValueDefault);
	}
	
	public static int getNumber(String numberKey, int defaultNumber) {
		int result = defaultNumber;
		try {
			result = Integer.valueOf(FilenameValidatorProperties.getTextValue(numberKey));
		} catch (NumberFormatException nfe) {
			// could be due to a missing or misconfigured property
		}
		return result;
	}
}
