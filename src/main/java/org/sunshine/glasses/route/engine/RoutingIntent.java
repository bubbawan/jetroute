package org.sunshine.glasses.route.engine;

import org.sunshine.glasses.route.mapping.VariablesExtractor;

public class RoutingIntent {
	
	private String target;
	
	private String variable;

	private VariablesExtractor variablesExtractor;

	public RoutingIntent(String target, String variable, VariablesExtractor variablesExtractor) {
		this.target = target;
		this.variable = variable;
		this.variablesExtractor = variablesExtractor;
	}

	public String getTarget() {
		return target;
	}

	public String getVariable() {
		return variable;
	}
	
	public boolean containsVariable() {
		return variable != null;
	}
	
	public String getVariable(String calledTarget){
		return variablesExtractor.extractVariable(this, calledTarget);
	}
	
	
	
	

}
