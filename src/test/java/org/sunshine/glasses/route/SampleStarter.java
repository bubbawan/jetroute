package org.sunshine.glasses.route;

import org.eclipse.jetty.server.Server;
import org.sunshine.glasses.route.handler.JetRouteHandler;

public class SampleStarter {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		server.setHandler(new JetRouteHandler(new HandlerPojo()));
		server.start();
		server.join();
	}
}
