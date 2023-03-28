package com.jpmorgan.validator.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FilenameValidatorPropertiesTest {

	@Test
	public void loadProperties() {
		FilenameValidatorProperties.loadProperties();
		
		assertTrue(true);
	}

	@Test
	public void getTextValueString() {
		FilenameValidatorProperties.loadProperties();
		
		assertNotNull(FilenameValidatorProperties.getTextValue("PROPERTIES_NUM"));
	}

	@Test
	public void getTextValueStringString() {
		FilenameValidatorProperties.loadProperties();
		
		assertNull(FilenameValidatorProperties.getTextValue("test"));
		
		assertEquals("default", FilenameValidatorProperties.getTextValue("test", "default"));
	}

	@Test
	public void getNumverValueMissing() {
		FilenameValidatorProperties.loadProperties();
		
		assertEquals(1, FilenameValidatorProperties.getNumber("USAGE", 1));
	}

	@Test
	public void getNumverValuePresent() {
		FilenameValidatorProperties.loadProperties();
		
		assertTrue(FilenameValidatorProperties.getNumber("PROPERTIES_NUM", 0) > 0);
	}
}
