package org.sunshine.glasses.route.mapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.sunshine.glasses.route.engine.RoutingIntent;


/**
 * Analyzes the target pojos and collects routing mappings to handler methods.
 * @author bubba
 *
 */
public class RoutingMapper {

	public Map<String, RouteEntry> map(Object pojo) {
		Map<String, RouteEntry> mappings = new HashMap<String,RouteEntry>();
		Class<?> clazz = pojo.getClass();
		for (Method method : clazz.getDeclaredMethods()) {
			if(!method.isAnnotationPresent(Route.class)){
				continue;
			}
			Route route = method.getAnnotation(Route.class);
			String target = route.target();
			RoutingIntent routingIntent = new VariablesExtractor().explodeConfiguredRoute(target);
			mappings.put(routingIntent.getTarget(), new  RouteEntry(target, method,routingIntent));
		}
		
		return mappings;
		
	}
	
}
