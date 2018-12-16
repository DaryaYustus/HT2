package com.epam.training.driver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.epam.training.constants.Constants;

public class Driver {

	private static WebDriver driver;

	public static WebDriver startWebDriver() {
		if (driver == null) {
			System.setProperty(Constants.WEBDRIVER, Constants.WEBDRIVER_EXE);
			driver = new ChromeDriver();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// driver.manage().window().maximize();
		}
		return driver;

	}

	public static void stopWebDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}

	}

}
