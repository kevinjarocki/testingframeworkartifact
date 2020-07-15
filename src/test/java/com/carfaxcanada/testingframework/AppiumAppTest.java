package com.carfaxcanada.testingframework;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import com.microsoft.appcenter.appium.Factory;
import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import org.junit.rules.TestWatcher;
import org.junit.Rule;
public class AppiumAppTest {
	
	@Rule
	public TestWatcher watcher = Factory.createWatcher();
	 
	private static EnhancedAndroidDriver<MobileElement> driver;
	
	public static EnhancedAndroidDriver<MobileElement> startApp() throws MalformedURLException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");

		// Used when testing locally. You have to specify where the APK is located that you want to run.
		//capabilities.setCapability(MobileCapabilityType.APP, "C:/Users/kjarocki/Desktop/Maven/swiftnotes.apk"); 

		// Used when testing locally. You have to specify which device that you want to test on. This is a Pixel 2XL AVD
		//capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554"); 

		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 7913);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

		// Used to connect to your Appium Client when testing locally.
		URL url = new URL("http://localhost:4723/wd/hub"); 

		return Factory.createAndroidDriver(url, capabilities);
	}
	
    @Test
    public void sampleTest() throws MalformedURLException, InterruptedException {
		
	  System.out.println("Sample test started.");
	  driver = startApp();
	  MobileElement elem = Util.findByByOrName(driver, By.id("com.moonpi.swiftnotes:id/newNote"), "+");
      elem.click();
      
    }
	@After
	public void after() throws Exception {
	      if (driver != null) {
	            driver.label("Stopping App");
	            driver.quit();
	        }
	}
}