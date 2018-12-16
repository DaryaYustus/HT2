package com.epam.training.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.training.constants.Constants;

public class LoginPage extends AbstractPage implements Constants {

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);

	}
	
	@FindBy(xpath = "//*[@id='j_username']")
	private WebElement fillFormUserName;
	
	@FindBy(xpath = "//input[@name='j_password']")
	private WebElement fillFormPassword;
	
	@FindBy(xpath = "/html/body/div/div/form/div[3]/input")
	private WebElement pressButton;
	
	
	
	public void autorise(String userName, String userPassword) {
		//fillFormUserName.click();
		fillFormUserName.clear();
		fillFormUserName.sendKeys(userName);
		//fillFormPassword.click();
		fillFormPassword.clear();
		fillFormPassword.sendKeys(userPassword);
		pressButton.click();
		
		
		
	}

	

}
