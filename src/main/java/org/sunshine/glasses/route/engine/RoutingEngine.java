package org.sunshine.glasses.route.engine;

import java.util.Map;
import java.util.Set;

import org.sunshine.glasses.route.mapping.RouteEntry;
import org.sunshine.glasses.route.mapping.RoutingMapper;

/**
 * The {@link RoutingEngine} processes each request and dispatches handler
 * invocations for specific routes to the {@link HandlerInvoker}.
 * 
 * @author bubba
 * 
 */
public class RoutingEngine {

	private Map<String, RouteEntry> mappings;
	private HandlerInvoker handlerInvoker;
	private Object handler;

	/**
	 * Initializes the engine and creates mappings for urls using the internal
	 * {@link RoutingMapper}
	 * 
	 * @param handler
	 *            - the handler pojo
	 */
	public void initialize(Object handler) {
		this.handler = handler;
		mappings = new RoutingMapper().map(handler);
		handlerInvoker = new HandlerInvoker();
	}

	public String process(String calledTarget) throws RoutingException {
		Set<String> keySet = mappings.keySet();
		for (String targetKey : keySet) {
			RouteEntry routeEntry = null;
			if (checkBlankURL(calledTarget, targetKey)) {
				routeEntry = mappings.get(targetKey);
			} else if (calledTarget.startsWith(targetKey + "/")) {
				routeEntry = mappings.get(targetKey);
				if (!routeEntry.getRoutingIntent().containsVariable()) {
					routeEntry = null;
				}
			}
			if (routeEntry != null) {
				return handlerInvoker.invoke(routeEntry, handler, calledTarget);
			}
		}
		throw new RoutingException();
	}

	private boolean checkBlankURL(String target, String targetKey) {
		return target.equals(targetKey) || target.equals(targetKey + "/");
	}
}
