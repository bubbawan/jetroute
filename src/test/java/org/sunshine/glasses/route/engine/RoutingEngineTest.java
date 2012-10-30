package org.sunshine.glasses.route.engine;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.sunshine.glasses.route.engine.RoutingEngine;
import org.sunshine.glasses.route.engine.RoutingException;
import org.sunshine.glasses.route.mapping.Route;
import static org.sunshine.glasses.route.TestHelper.*;


public class RoutingEngineTest {

	private static String INFO = "info";
	private static String PRIVACY = "private";
	private static String USER_DATA = "Data of ";
	private static String DATA_STATS = "Stats of";
	
	private RoutingEngine routingEngine;
	
	@Before
	public void setup() {
		routingEngine = new RoutingEngine();
		routingEngine.initialize(new HandlerFT());
	}
	
	@Test
	public void testRouting() {
		assertEquals(INFO,routingEngine.process("/getInfo"));
		assertEquals(INFO,routingEngine.process("/getInfo/"));
		assertEquals(PRIVACY,routingEngine.process("/privacy"));
		String user = "r2d2";
		assertEquals(USER_DATA+user,routingEngine.process("/getData/"+user));
		assertEquals(DATA_STATS+42,routingEngine.process("/getStats/"+42));
		assertEquals(DATA_STATS+Double.valueOf(42)+"double",routingEngine.process("/getDouble/"+42));
		
		// assert exceptions
		List<String> exceptionTargets = new ArrayList<String>();
		exceptionTargets.add("/getImportantData");
		exceptionTargets.add("/getInfoo/");
		exceptionTargets.add("/getInfoo");
		exceptionTargets.add("/getDataaaa/"+user);
		for (final String target : exceptionTargets) {
			assertException(RoutingException.class, new Runnable() {
				
				public void run() {
					routingEngine.process(target);
				}
			});
		}
	}
	
	
	public static class HandlerFT{
		
		@Route(target="/getInfo")
		public String info(){
			return INFO;
		}
		
		@Route(target="/privacy")
		private String privacy(){
			return PRIVACY;
		}
		
		@Route(target="/getData/<user>")
		public String getUserData(String user){
			return "Data of "+user;
		}
		
		@Route(target="/getStats/<dataId>")
		public String getStats(int dataId){
			return DATA_STATS+ dataId;
		}
		
		@Route(target="/getDouble/<dataId>")
		public String getStatsDouble(double dataId){
			return DATA_STATS+ dataId +"double";
		}
		
	}

}
