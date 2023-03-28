package com.jpmorgan.validator;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FilenameValidatorTest {

	private FilenameValidator target = new FilenameValidator();

	@Test
	public void validate_ok() {
		String expected = "File 'Test_A_07121987_22.csv' passed validation.";
		
		String actual = target.validate("Test_A_07121987_22.csv");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void validate_wrongPrefix() {
		String expected = "File 'Hello_A_07121987_22.csv' failed validation. Prefix for the file should be 'Test' found 'Hello'.";
		
		String actual = target.validate("Hello_A_07121987_22.csv");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void validate_wrongDate() {
		String expected = "File 'Test_A_13131987_22.csv' failed validation. Valuation Date '13131987' is not a valid date format 'ddmmyyyy'.";
		
		String actual = target.validate("Test_A_13131987_22.csv");
		
		assertEquals(expected, actual);
	}

	@Test
	public void validate_wrongPortfolio() {
		String expected = "File 'Test_E_07121987_22.csv' failed validation. PortfolioCode should be 'A/B/C' found 'E'.";
		
		String actual = target.validate("Test_E_07121987_22.csv");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void validate_wrongSequence() {
		String expected = "File 'Test_A_07121987_a1.csv' failed validation. Sequence is expected to be a 2 digits number found 'a1'.";
		
		String actual = target.validate("Test_A_07121987_a1.csv");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void validate_wrongSequence_tooManyDigits() {
		String expected = "File 'Test_B_07121987_123.csv' failed validation. Sequence is expected to be a 2 digits number found '123'.";
		
		String actual = target.validate("Test_B_07121987_123.csv");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void validate_wrongExtension() {
		String expected = "File 'Test_A_07121987_22.txt' failed validation. Invalid File extension. Expected 'csv' found 'txt'.";
		
		String actual = target.validate("Test_A_07121987_22.txt");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void validate_wrongFormat_emptyFilename() {
		String expected = "File '' failed validation. File format should be 'Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv'.";
		
		String actual = target.validate("");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void validate_wrongFormat() {
		String expected = "File 'Test.txt' failed validation. File format should be 'Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv'.";
		
		String actual = target.validate("Test.txt");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void validate_wrongFormat_nullInput() {
		String expected = "Usage: FilenameValidator [filename]";
		
		String[] args = null;
		String actual = target.validate(args);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void validate_wrongFormat_emptyArray() {
		String expected = "Usage: FilenameValidator [filename]";
		
		String[] args = {};
		String actual = target.validate(args);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void validate_wrongFormat_nullFilename() {
		String expected = "File 'null' failed validation. File format should be 'Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv'.";
		
		String[] args = {null};
		String actual = target.validate(args);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void validate_wrongFormat_emptyInput() {
		String expected = "Usage: FilenameValidator [filename]";
		
		String actual = target.validate();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void validate_wrongFormat_sequenceMissing() {
		String expected = "File 'Test_A_13121987.csv' failed validation. File format should be 'Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv'.";
		
		String actual = target.validate("Test_A_13121987.csv");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test() {
		String format = "something %1$s and something %2$s";
		System.out.println(String.format(format, "1", "2"));
	}
}
