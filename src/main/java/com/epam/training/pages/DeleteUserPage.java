package com.epam.training.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.training.constants.Constants;

public class DeleteUserPage extends AbstractPage {

	@FindBy(xpath = "//*[@id='main-panel']/form")
	private WebElement text;

	@FindBy(id = "yui-gen2-button")
	private WebElement button;

	public DeleteUserPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
	}

	public String getDeleteUserText() {
		return text.getText().substring(0, text.getText().indexOf('?') + 1);
	}

	public void deleteUser() {
		button.click();
	}
}
