package com.epam.training.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.training.constants.Constants;
import com.epam.training.driver.Driver;

public class HomePage extends AbstractPage implements Constants{

	@FindBy(xpath = "//*[@id='tasks']/div[4]/a[2]")
	private WebElement manageJenkins;

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {

		driver.navigate().to(BASE_URL);
	}

	// get header text
	public void clickManageJenkins() {
		manageJenkins.click();
	}

}
