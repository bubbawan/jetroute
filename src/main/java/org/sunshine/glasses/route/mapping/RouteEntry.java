package org.sunshine.glasses.route.mapping;

import java.lang.reflect.Method;

import org.sunshine.glasses.route.engine.RoutingIntent;

/**
 * Represents simple route entries mapping to {@link Method}s.
 * @author bubba
 *
 */
public class RouteEntry {
	
	private String route;
	
	private Method handler;

	private RoutingIntent routingIntent;

	public RouteEntry(String route, Method handler, RoutingIntent routingIntent) {
		super();
		this.route = route;
		this.handler = handler;
		this.routingIntent = routingIntent;
	}

	public String getRoute() {
		return route;
	}

	public Method getHandler() {
		return handler;
	}
	
	public RoutingIntent getRoutingIntent() {
		return routingIntent;
	}
	
	

}
