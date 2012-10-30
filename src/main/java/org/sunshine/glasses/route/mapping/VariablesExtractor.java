package org.sunshine.glasses.route.mapping;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sunshine.glasses.route.engine.RoutingIntent;

public class VariablesExtractor {
	
	private static final Pattern VARIABLE = Pattern.compile("(<)(\\w*)(>)");
	
	public RoutingIntent explodeConfiguredRoute(String configuredTargetURL){
		Matcher matcher = VARIABLE.matcher(configuredTargetURL);
		if(!matcher.find()){
			return new RoutingIntent(configuredTargetURL, null,this);
		}
		return new RoutingIntent(configuredTargetURL.substring(0, matcher.start()-1), matcher.group(2),this);
	}
	
	public String extractVariable(RoutingIntent intent, String targetURL){
		if(!intent.containsVariable()){
			return null;
		}
		return targetURL.substring(intent.getTarget().length(), targetURL.length()).replace("/", "");
	}

}
