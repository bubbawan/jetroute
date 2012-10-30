package org.sunshine.glasses.route;

import org.junit.Assert;

public class TestHelper {

	public static void assertException(Class<? extends Exception> exceptionClass, Runnable runnable){
		try{
			runnable.run();
			Assert.fail("Expected exception of type " +exceptionClass +" but nothing went wrong.");
		}catch(Exception exc){
			Assert.assertTrue(exc.getClass() == exceptionClass);
		}
	}
	
}
