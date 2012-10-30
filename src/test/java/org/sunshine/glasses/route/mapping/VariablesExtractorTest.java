package org.sunshine.glasses.route.mapping;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.sunshine.glasses.route.engine.RoutingIntent;
import org.sunshine.glasses.route.mapping.VariablesExtractor;

public class VariablesExtractorTest {

	private VariablesExtractor extractor;

	@Before
	public void setup() {
		extractor = new VariablesExtractor();
	}

	@Test
	public void testExplodeConfiguredRoute() throws Exception {
		String configuredURL = "/test";
		RoutingIntent intent = extractor.explodeConfiguredRoute(configuredURL);
		assertEquals("/test", intent.getTarget());
		assertNull(intent.getVariable());

		configuredURL = "/test/<user>";
		intent = extractor.explodeConfiguredRoute(configuredURL);
		assertEquals("/test", intent.getTarget());
		assertEquals("user", intent.getVariable());
	}

	@Test
	public void testExtractVariable() throws Exception {
		String configuredURL = "/test/<user>";
		RoutingIntent intent = extractor.explodeConfiguredRoute(configuredURL);
		assertEquals("r2d2", extractor.extractVariable(intent, "/test/r2d2"));
		
	}

}
