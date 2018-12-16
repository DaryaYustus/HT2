package com.epam.training.jenkins;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.training.constants.Constants;
import com.epam.training.driver.Driver;
import com.epam.training.pages.AddUserPage;
import com.epam.training.pages.DeleteUserPage;
import com.epam.training.pages.UsersPage;
import com.epam.training.pages.HomePage;
import com.epam.training.pages.LoginPage;
import com.epam.training.pages.ManageJenkinsPage;

public class JenkinsAutomationTests implements Constants {
	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private ManageJenkinsPage manageJenkinsPage;
	private UsersPage usersPage;
	private AddUserPage addUserPage;
	private DeleteUserPage deleteUserPage;

	private final String userName = "admin";
	private final String userPassword = "admin123";

	@BeforeClass()
	public void login() {
		driver = Driver.startWebDriver();
		loginPage = new LoginPage(driver);
		loginPage.openPage();
		loginPage.autorise(userName, userPassword);
		homePage = new HomePage(driver);
		manageJenkinsPage = new ManageJenkinsPage(driver);
		usersPage = new UsersPage(driver);
		addUserPage = new AddUserPage(driver);
		deleteUserPage = new DeleteUserPage(driver);

	}

	@BeforeMethod()
	public void init() {
		homePage.openPage();

	}

	@Test(testName = "����� ����� �� ������ �Manage Jenkins� �� �������� ���������� ������� dt � ������� �Manage Users� � ������� dd � ������� �Create/delete/modify users that can log in to this Jenkins�.", enabled = true)
	public void tstManageUsersLink() {
		homePage.clickManageJenkins();
		assertEquals(manageJenkinsPage.getManageUserLinkText(), "Manage Users");
		assertEquals(manageJenkinsPage.getManageUserDescriptionText(),
				"Create/delete/modify users that can log in to this Jenkins");
	}

	@Test(testName = "����� ����� �� ������, ������ ������� ���������� ������� dt � ������� �Manage Users�, ���������� �������� ������ �Create User�.", enabled = true)
	public void tstCreateUser() {
		homePage.clickManageJenkins();
		manageJenkinsPage.clickLinkManageUsers();
		System.out.println(usersPage.getCreateUserHref());
		assertNotNull(usersPage.getCreateUserHref());
		assertEquals(usersPage.getCreateUserText(), "Create User");
	}

	@Test(testName = "����� ����� �� ������ �Create User� ���������� ����� � ����� ������ ���� text � ����� ������ ���� password, ������ ��� ���� ������ ���� �������.", enabled = true)
	public void tstAddUserEmptyFields() {
		homePage.clickManageJenkins();
		manageJenkinsPage.clickLinkManageUsers();
		usersPage.clickCreateUser();

		assertEquals(addUserPage.getUserName(), "");
		assertEquals(addUserPage.getUserNameType(), "text");
		assertEquals(addUserPage.getPassword1(), "");
		assertEquals(addUserPage.getPassword1Type(), "password");
		assertEquals(addUserPage.getPassword2(), "");
		assertEquals(addUserPage.getPassword2Type(), "password");
	}

	@Test(testName = "����� ���������� ����� ����� (�Username� = �someuser�, �Password� = �somepassword�, �Confirm password� = �somepassword�, �Full name� = �Some Full Name�, �E-mail address� = �some@addr.dom�) � ����� �� ������ � �������� �Create User� �� �������� ���������� ������ ������� (������� tr), � ������� ���� ������ (������� td) � ������� �someuser�.", enabled = true)
	public void tstAddUser() {
		homePage.clickManageJenkins();
		manageJenkinsPage.clickLinkManageUsers();
		usersPage.clickCreateUser();
		addUserPage.setUsername(USERNAME);
		addUserPage.setPassword1(PASSWORD);
		addUserPage.setPassword2(CONFIRM_PASSWORD);
		addUserPage.setFullname(FULL_NAME);
		addUserPage.setEmail(EMAIL);
		addUserPage.clickCreateUserButton();
		assertTrue(usersPage.usersNameList().contains(USERNAME));
	}

	@Test(testName = "����� ����� �� ������ � ��������� href ������ �user/someuser/delete� ���������� ����� �Are you sure about deleting the user from Jenkins?�.", enabled = true, dependsOnMethods = "tstAddUser")
	public void tstDeleteUserText() {
		homePage.clickManageJenkins();
		manageJenkinsPage.clickLinkManageUsers();
		usersPage.deleteUser(USERNAME);
		System.out.println(deleteUserPage.getDeleteUserText());
		assertEquals(deleteUserPage.getDeleteUserText(),
				"Are you sure about deleting the user from Jenkins?");

	}

	@Test(testName = "����� ����� �� ������ � �������� �Yes� �� �������� ����������� ������ ������� (������� tr), � ������� (������� td) � ������� �someuser�. �� �������� ����������� ������ � ��������� href ������ �user/someuser/delete�.", enabled = true, dependsOnMethods = "tstDeleteUserText", expectedExceptions = NoSuchElementException.class)
	public void tstDeleteUser() {
		homePage.clickManageJenkins();
		manageJenkinsPage.clickLinkManageUsers();
		usersPage.deleteUser(USERNAME);
		deleteUserPage.deleteUser();
		assertFalse(usersPage.usersNameList().contains(USERNAME));
		assertFalse(usersPage.findUser(USERNAME));

	}

	@Test(testName = "�� ��� �� ��������, ��� ���������� ����� �� �� �� ���� ��������}. �� �������� ����������� ������ � ��������� href ������ �user/admin/delete�", enabled = true, expectedExceptions = NoSuchElementException.class)
	public void tstTestAdminUserNotPresented() {
		String username = "TESTADMIN";
		homePage.clickManageJenkins();
		manageJenkinsPage.clickLinkManageUsers();
		assertFalse(usersPage.usersNameList().contains(username));
		assertFalse(usersPage.findUser(username));

	}

	@AfterClass
	public void stopBrowser() {
		Driver.stopWebDriver();
	}

}
