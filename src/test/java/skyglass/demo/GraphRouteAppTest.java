package skyglass.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class GraphRouteAppTest {

	@Test
	public void testingHelloWorld() {
		assertEquals("Here is test for Hello World String: ", "Hello + World", helloWorld());
	}

	public String helloWorld() {
		String helloWorld = "Hello +" + " World";
		return helloWorld;
	}

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(GraphRouteAppTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if (result.wasSuccessful()) {
			System.out.println("Both Tests finished successfully...");
		}
	}
}
