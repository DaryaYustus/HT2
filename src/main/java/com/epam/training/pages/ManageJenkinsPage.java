package com.epam.training.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.training.constants.Constants;

public class ManageJenkinsPage extends AbstractPage implements Constants {

	@FindBy(xpath = "//*[@id='main-panel']/div[16]/a/dl/dt")
	private WebElement manageUsersLink;

	@FindBy(xpath = "//*[@id='main-panel']/div[16]/a/dl/dd[1]")
	private WebElement manageUsersDescription;

	@FindBy(xpath = "//*[@id='main-panel']/div[16]/a")
	private WebElement linkManageUsers;

	public ManageJenkinsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		driver.navigate().to(MANAGE_URL);
	}

	public String getManageUserLinkText() {
		System.out.println("Link = " + manageUsersLink.getText());
		return manageUsersLink.getText();
	}

	public String getManageUserDescriptionText() {
		System.out.println("Description = " + manageUsersDescription.getText());
		return manageUsersDescription.getText();
	}

	public void clickLinkManageUsers() {
		linkManageUsers.click();

	}
}
