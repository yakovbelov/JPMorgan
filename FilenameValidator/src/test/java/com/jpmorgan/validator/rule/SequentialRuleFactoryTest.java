package com.jpmorgan.validator.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class SequentialRuleFactoryTest {

	private SequentialRuleFactory objectUnderTest = new SequentialRuleFactory();
	
	@Test
	public void getRule_one() {
		assertEquals(com.jpmorgan.validator.rule.PrefixRule.class, objectUnderTest.getRule(0).getClass());
	}

	@Test
	public void getRule_two() {
		assertEquals(com.jpmorgan.validator.rule.PortfolioRule.class, objectUnderTest.getRule(1).getClass());
	}
	
	@Test
	public void getRule_three() {
		assertEquals(com.jpmorgan.validator.rule.DateFormatRule.class, objectUnderTest.getRule(2).getClass());
	}
	
	@Test
	public void getRule_four() {
		assertEquals(com.jpmorgan.validator.rule.SequenceRule.class, objectUnderTest.getRule(3).getClass());
	}
	
	@Test
	public void getRule_five() {
		assertEquals(com.jpmorgan.validator.rule.ExtensionRule.class, objectUnderTest.getRule(4).getClass());
	}
	
	@Test
	public void getRule_wrongSequence() {
		assertNull(objectUnderTest.getRule(-1));
	}
	
	@Test
	public void getNumberOfRules() {
		assertEquals(5, ((SequentialRuleFactory)objectUnderTest).getNumberOfRules());
	}
}
