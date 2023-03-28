package com.jpmorgan.validator.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ErrorMessageParametersTest {

	private ErrorMessageParameters target = new ErrorMessageParameters();
	
	@Test
	public void test() {
		assertNull(target.getParameters());
		target.addParameters("blah");
		assertEquals(1, target.getParameters().length);
		target.clear();
		assertNull(target.getParameters());
	}
}
