package com.jpmorgan.validator.rule;

/**
 * 
 * Rule check factory
 * 
 * @param <T>
 */
public interface RuleFactory<T> {

	Rule getRule(T input);
}
