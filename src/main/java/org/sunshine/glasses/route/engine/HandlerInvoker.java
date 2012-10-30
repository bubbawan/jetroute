package org.sunshine.glasses.route.engine;

import java.lang.reflect.Method;

import org.sunshine.glasses.route.mapping.RouteEntry;

/**
 * Invokes the associated routing methods of the handler pojo.
 * @author bubba
 *
 */
public class HandlerInvoker {
	
	/**
	 * Invoke routing method
	 * @param routeEntry - describing the route and the handler method
	 * @param handler - the handler pojo
	 * @return the code to be rendered in the browser
	 * @throws RoutingException 
	 */
	public String invoke(RouteEntry routeEntry, Object handler,String calledURL) throws RoutingException{
		Method method = routeEntry.getHandler();
		method.setAccessible(true);
		try {
			RoutingIntent routingIntent = routeEntry.getRoutingIntent();
			boolean containsVariable = routingIntent.containsVariable();
			Object[] variables = new Object[containsVariable? 1:0];
			if(containsVariable && method.getParameterTypes().length ==1){
				variables[0] = constructParameterType(routingIntent.getVariable(calledURL),method);
			}
			return (String) method.invoke(handler,variables);
		} catch (Exception e) {
			 throw new RoutingException();
		} 
	}
	
	protected Object constructParameterType(String stringRep, Method method){
		Class<?> parameterType = method.getParameterTypes()[0];
		 if(parameterType == Integer.class || parameterType == int.class){
			return Integer.valueOf(stringRep);
		}else if(parameterType == Double.class || parameterType == double.class){
			return Double.valueOf(stringRep);
		}
		return stringRep;
		
	}

}
