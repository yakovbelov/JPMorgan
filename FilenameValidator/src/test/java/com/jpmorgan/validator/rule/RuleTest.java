package com.jpmorgan.validator.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RuleTest {

	private Rule objectUnderTest;
	
	@Test
	public void prefix_getErrorMessageKey() {
		objectUnderTest = new PrefixRule();
		assertEquals("PREFIX", objectUnderTest.getErrorMessageKey());
	}
	
	@Test
	public void validate_prefix_valid() {
		objectUnderTest = new PrefixRule();
		assertTrue(objectUnderTest.valid("Test"));
		assertNull(objectUnderTest.getErrorMessageParameters());
	}
	
	@Test
	public void validate_prefix_invalid() {
		objectUnderTest = new PrefixRule();
		assertFalse(objectUnderTest.valid("Tes"));
		assertEquals("Test", objectUnderTest.getErrorMessageParameters()[0]);
		assertEquals("Tes", objectUnderTest.getErrorMessageParameters()[1]);
	}
	
	@Test
	public void portfolio_getErrorMessageKey() {
		objectUnderTest = new PortfolioRule();
		assertEquals("PORTFOLIO", objectUnderTest.getErrorMessageKey());
	}
	
	@Test
	public void validate_portfolioCode_valid() {
		objectUnderTest = new PortfolioRule();
		assertTrue(objectUnderTest.valid("A"));
		assertNull(objectUnderTest.getErrorMessageParameters());
	}
	
	@Test
	public void validate_portfolioCode_invalid() {
		objectUnderTest = new PortfolioRule();
		assertFalse(objectUnderTest.valid("E"));
		assertEquals("A/B/C", objectUnderTest.getErrorMessageParameters()[0]);
		assertEquals("E", objectUnderTest.getErrorMessageParameters()[1]);
	}
	
	@Test
	public void dateFormat_getErrorMessageKey() {
		objectUnderTest = new DateFormatRule();
		assertEquals("DATE_FORMAT", objectUnderTest.getErrorMessageKey());
	}
	
	@Test
	public void validate_dateFormat_valid() {
		objectUnderTest = new DateFormatRule();
		assertTrue(objectUnderTest.valid("12122001"));
		assertNull(objectUnderTest.getErrorMessageParameters());
	}
	
	@Test
	public void validate_dateFormat_invalid() {
		objectUnderTest = new DateFormatRule();
		assertFalse(objectUnderTest.valid("12132001"));
		assertEquals("ddmmyyyy", objectUnderTest.getErrorMessageParameters()[1]);
		assertEquals("12132001", objectUnderTest.getErrorMessageParameters()[0]);
	}
	
	@Test
	public void extension_getErrorMessageKey() {
		objectUnderTest = new ExtensionRule();
		assertEquals("EXTENSION", objectUnderTest.getErrorMessageKey());
	}
	
	@Test
	public void validate_extension_valid() {
		objectUnderTest = new ExtensionRule();
		assertTrue(objectUnderTest.valid("csv"));
		assertNull(objectUnderTest.getErrorMessageParameters());
	}
	
	@Test
	public void validate_extension_invalid() {
		objectUnderTest = new ExtensionRule();
		assertFalse(objectUnderTest.valid("txt"));
		assertEquals("txt", objectUnderTest.getErrorMessageParameters()[1]);
		assertEquals("csv", objectUnderTest.getErrorMessageParameters()[0]);
	}
	
	@Test
	public void sequence_getErrorMessageKey() {
		objectUnderTest = new SequenceRule();
		assertEquals("SEQUENCE", objectUnderTest.getErrorMessageKey());
	}
	
	@Test
	public void string_sequence_getErrorMessageKey() {
		objectUnderTest = new StringSequenceRule();
		assertEquals("SEQUENCE", objectUnderTest.getErrorMessageKey());
	}
	
	@Test
	public void validate_sequence_valid() {
		objectUnderTest = new SequenceRule();
		assertTrue(objectUnderTest.valid("22"));
		assertNull(objectUnderTest.getErrorMessageParameters());
	}
	
	@Test
	public void validate_sequence_leadingZero_invalid() {
		objectUnderTest = new SequenceRule();
		assertFalse(objectUnderTest.valid("02"));
		assertEquals(2, objectUnderTest.getErrorMessageParameters()[0]);
		assertEquals("02", objectUnderTest.getErrorMessageParameters()[1]);
	}
	
	@Test
	public void validate_string_sequence_leadingZero_valid() {
		objectUnderTest = new StringSequenceRule();
		assertTrue(objectUnderTest.valid("02"));
		assertNull(objectUnderTest.getErrorMessageParameters());
	}
	
	@Test
	public void validate_string_sequence_valid() {
		objectUnderTest = new StringSequenceRule();
		assertTrue(objectUnderTest.valid("22"));
		assertNull(objectUnderTest.getErrorMessageParameters());
	}
	
	@Test
	public void validate_sequence_invalid_tooManyDigits() {
		objectUnderTest = new SequenceRule();
		assertFalse(objectUnderTest.valid("223"));
		assertEquals(2, objectUnderTest.getErrorMessageParameters()[0]);
		assertEquals("223", objectUnderTest.getErrorMessageParameters()[1]);
	}
	
	@Test
	public void validate_sequence_invalid() {
		objectUnderTest = new SequenceRule();
		assertFalse(objectUnderTest.valid("a3"));
		assertEquals(2, objectUnderTest.getErrorMessageParameters()[0]);
		assertEquals("a3", objectUnderTest.getErrorMessageParameters()[1]);
	}
	
	@Test
	public void validate_string_sequence_invalid_tooManyDigits() {
		objectUnderTest = new StringSequenceRule();
		assertFalse(objectUnderTest.valid("223"));
		assertEquals(2, objectUnderTest.getErrorMessageParameters()[0]);
		assertEquals("223", objectUnderTest.getErrorMessageParameters()[1]);
	}
	
	@Test
	public void validate_string_sequence_invalid() {
		objectUnderTest = new StringSequenceRule();
		assertFalse(objectUnderTest.valid("a3"));
		assertEquals(2, objectUnderTest.getErrorMessageParameters()[0]);
		assertEquals("a3", objectUnderTest.getErrorMessageParameters()[1]);
	}
}
