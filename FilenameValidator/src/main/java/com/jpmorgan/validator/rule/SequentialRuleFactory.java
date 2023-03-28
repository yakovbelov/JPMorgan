package com.jpmorgan.validator.rule;

/**
 * 
 * Rule check factory where checks are based on some numeric sequence index.
 */
public class SequentialRuleFactory implements RuleFactory<Integer> {

	private Rule[] rules = new Rule[] {
		new PrefixRule(),
		new PortfolioRule(),
		new DateFormatRule(),
		new StringSequenceRule(),
		new ExtensionRule()
	};
			
	@Override
	public Rule getRule(Integer input) {
		try {
			return rules[input];
		} catch (Exception ex) {
			return null;
		}
	}

	public int getNumberOfRules() {
		return rules.length;
	}
 }
