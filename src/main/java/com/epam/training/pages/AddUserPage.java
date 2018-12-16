package com.epam.training.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.training.constants.Constants;

public class AddUserPage extends AbstractPage implements Constants {

	@FindBy(xpath = "//input[@id='username']")
	private WebElement username;

	@FindBy(xpath = "//input[@name='password1']")
	private WebElement password1;

	@FindBy(xpath = "//input[@name='password2']")
	private WebElement password2;

	@FindBy(xpath = "//input[@name='email']")
	private WebElement email;

	@FindBy(xpath = "//input[@name='fullname']")
	private WebElement fullname;

	@FindBy(xpath = "//*[@id='yui-gen2-button']")
	private WebElement createUserButtin;

	public AddUserPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		driver.navigate().to(ADD_USER_URL);
	}

	public String getUserName() {
		return username.getText();
	}

	public String getUserNameType() {
		return username.getAttribute("type");
	}

	public String getPassword1() {
		return password1.getText();
	}

	public String getPassword1Type() {
		return password1.getAttribute("type");
	}

	public String getPassword2() {
		return password2.getText();
	}

	public String getPassword2Type() {
		return password2.getAttribute("type");
	}

	public void setUsername(String username) {
		this.username.sendKeys(username);
		;
	}

	public void setPassword1(String password) {
		password1.sendKeys(password);
	}

	public void setPassword2(String password) {
		password2.sendKeys(password);
	}

	public void setFullname(String fullname) {
		this.fullname.sendKeys(fullname);
	}

	public void setEmail(String email) {
		this.email.sendKeys(email);
	}

	public void clickCreateUserButton() {
		createUserButtin.click();
	}

}
