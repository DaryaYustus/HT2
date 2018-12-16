package com.epam.training.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.training.constants.Constants;

public class UsersPage extends AbstractPage implements Constants {

	@FindBy(xpath = "//*[@id='tasks']/div[3]/a[2]")
	private WebElement createUser;

	@FindBy(xpath = "//table[@id='people']/tbody/tr/td[2]")
	private List<WebElement> users;

	public UsersPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		driver.navigate().to(CREATE_USER_URL);
	}

	public String getCreateUserText() {
		System.out.println("Text Create User " + createUser.getText());
		return createUser.getText();
	}

	public String getCreateUserHref() {
		return createUser.getAttribute("href");
	}

	public void clickCreateUser() {
		createUser.click();
	}

	public List<String> usersNameList() {
		List usersName = new ArrayList<String>();
		for (WebElement user : users) {
			usersName.add(user.getText());
		}
		return usersName;
	}

	public boolean findUser(String username) {
		String href = "user/" + username.toLowerCase() + "/delete";
		String xpath = "//a[@href = '" + href + "']";
		By delete = By.xpath(xpath);
		return driver.findElement(delete) == null ? true : false;
	}

	public void deleteUser(String username) {
		String href = "user/" + username.toLowerCase() + "/delete";
		String xpath = "//a[@href = '" + href + "']";
		By delete = By.xpath(xpath);
		driver.findElement(delete).click();

	}

}
