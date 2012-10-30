package org.sunshine.glasses.route.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.sunshine.glasses.route.engine.RoutingEngine;
import org.sunshine.glasses.route.engine.RoutingException;
import org.sunshine.glasses.route.mapping.Route;

public class JetRouteHandler extends AbstractHandler {

	private RoutingEngine routingEngine;

	/**
	 * Creates a new instance of the route handler
	 * 
	 * @param handler
	 *            - a pojo with annotated {@link Route} methods
	 */
	public JetRouteHandler(Object handler) {
		super();
		routingEngine = new RoutingEngine();
		routingEngine.initialize(handler);
	}

	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		baseRequest.setHandled(true);
		try {
			String result = routingEngine.process(target);
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println(result);
		} catch (RoutingException exc) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
	}

}
