package org.sunshine.glasses.route;

import java.util.Date;

import org.sunshine.glasses.route.mapping.Route;

public class HandlerPojo {
	
	@Route(target="/date")
	public String date() {
		return new Date().toString();
	}
	
	@Route(target="/millies")
	public String millies() {
		return String.valueOf(System.currentTimeMillis());
	}
	
	@Route(target="/hello/<user>")
	public String hello(String user) {
		return "Hello "+user;
	}

}
