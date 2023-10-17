package mavenauto;

import org.testng.TestNG;

public class TestRunner {

	static TestNG testrunner;
	
	public static void main(String[] args) {
		testrunner = new TestNG();
		testrunner.setTestClasses(new Class[] {LoginTest.class, NegativeLoginTest.class});
		testrunner.run();
	}

}
