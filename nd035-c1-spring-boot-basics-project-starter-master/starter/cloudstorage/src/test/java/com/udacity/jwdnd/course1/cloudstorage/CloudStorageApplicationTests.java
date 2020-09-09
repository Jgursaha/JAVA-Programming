package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {

	private String fName = "Jitesh";
	private String lName = "Gursahani";
	private String uName = "jgursaha";
	private String passWord = "latajj6";

	@LocalServerPort
	private int port;

	private WebDriver driver;
	public String baseURL;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		driver.get(baseURL = "http://localhost:" + port);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}


	@Test
	@Order(1)
	public void unauthorisedUserAccess() {

		//test login page is accessible by unauthorised user
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		//test home page is not accessible by unauthorised user
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());

		//test result page is not accessible by unauthorised user
		driver.get("http://localhost:" + this.port + "/result");
		Assertions.assertEquals("Login", driver.getTitle());

		//test file upload url is not accessible by unauthorised user
		driver.get("http://localhost:" + this.port + "/uploadFile");
		Assertions.assertEquals("Login", driver.getTitle());

		//test addNote url is not accessible by unauthorised user
		driver.get("http://localhost:" + this.port + "/addNote");
		Assertions.assertEquals("Login", driver.getTitle());

		//test addCredential url is not accessible by unauthorised user
		driver.get("http://localhost:" + this.port + "/addCredential");
		Assertions.assertEquals("Login", driver.getTitle());
	}


	@Test
	@Order(2)
	public void testSignupLogin() {

		//SignUp
		driver.get(baseURL + "/signup");
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.signUp(fName, lName, uName, passWord);

		//Login
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(uName, passWord);

		//Check home page can be accessed
		Assertions.assertEquals("Home", driver.getTitle());

		//Logout
		driver.get(baseURL + "/home");
		HomePage homePage = new HomePage(driver);
		homePage.logOut();

		//Check home page can't be accessed
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	@Order(3)
	public void testNoteCreation() {
		String[] results;
		System.out.println("testing note creation");
		String noteTitle = "Test Title";
		String noteDescription = "Test description.";

		//Login
		System.out.println("LOGGING IN");
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(uName, passWord);

		//create new note
		HomePage homePage = new HomePage(driver);
		homePage.createNote(driver, noteTitle, noteDescription);

		//navigate to home page
		driver.get(baseURL + "/home");

		//check values displayed for the new note
		results = homePage.retrieveNote(driver);

		assertEquals(noteTitle, results[0]);
		assertEquals(noteDescription, results[1]);

	}

	@Test
	@Order(4)
	public void testNoteEdit() {
		String[] results;
		System.out.println("testing note creation");
		String editedNoteTitle = "Edit Test Title";
		String editedNoteDescription = "Edit Test description.";

		//Login
		System.out.println("LOGGING IN");
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(uName, passWord);

		//navigate to home page
		driver.get(baseURL + "/home");

		//edit the newly created note
		HomePage homePage = new HomePage(driver);
		homePage.editNote(driver, editedNoteTitle, editedNoteDescription);

		//navigate to home page
		driver.get(baseURL + "/home");

		//check values displayed for the new note
		results = homePage.retrieveNote(driver);

		assertEquals(editedNoteTitle, results[0]);
		assertEquals(editedNoteDescription, results[1]);
	}

	@Test
	@Order(5)
	public void testNoteDelete() {
		boolean result;
		System.out.println("testing note creation");

		//Login
		System.out.println("LOGGING IN");
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(uName, passWord);

		//navigate to home page
		driver.get(baseURL + "/home");

		//delete the newly created note
		HomePage homePage = new HomePage(driver);
		homePage.deleteNote(driver);

		//navigate to home page
		driver.get(baseURL + "/home");

		//check values displayed for the new note
		result = homePage.doesNoteExist(driver);

		assertEquals(false, result);

	}

	@Test
	@Order(6)
	public void testCredentialCreation() {
		String[] results;
		String url = "test url";
		String username = "test username";
		String password = "password";

		//Login
		System.out.println("LOGGING IN");
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(uName, passWord);

		//create new credential
		HomePage homePage = new HomePage(driver);
		homePage.createCredential(driver, url, username, password);

		//navigate to home page
		driver.get(baseURL + "/home");

		//check values displayed for the new note
		results = homePage.retrieveCredential(driver);

		assertEquals(url, results[0]);
		assertEquals(username, results[1]);
		assertNotEquals(password, results[2]);
	}

	@Test
	@Order(7)
	public void testCredentialEdit() {
		String[] results;
		//String url = "test url";
		//String username = "test username";
		//String password = "password";
		String clearPassword;
		String editUrl = "test url edit";
		String editUsername = "edit test username";
		String editPassword = "editpassword";

		//Login
		System.out.println("LOGGING IN");
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(uName, passWord);

		//navigate to home page
		HomePage homePage = new HomePage(driver);
		driver.get(baseURL + "/home");

		//edit credential
		homePage.editCredential(driver, editUrl, editUsername, editPassword);

		//navigate to home page
		driver.get(baseURL + "/home");

		//retrieve clear password in edit mode
		clearPassword = homePage.retrieveClearTextPassword(driver);

		//navigate to home page
		driver.get(baseURL + "/home");

		//check values displayed for the new note
		results = homePage.retrieveCredential(driver);

		//test unencrypted password
		System.out.println("Clear Password is: "+clearPassword);

		assertEquals(clearPassword, editPassword);
		//test edited credential
		assertEquals(editUrl, results[0]);
		assertEquals(editUsername, results[1]);
		assertNotEquals(editPassword, results[2]);
	}

	@Test
	@Order(8)
	public void testCredentialDelete() {
		boolean result;

		String url = "test url";
		String username = "test username";
		String password = "password";

		//Login
		System.out.println("LOGGING IN");
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(uName, passWord);


		//navigate to home page
		driver.get(baseURL + "/home");

		//delete the newly created note
		HomePage homePage = new HomePage(driver);
		homePage.deleteCredential(driver);

		//navigate to home page
		driver.get(baseURL + "/home");

		//check values displayed for the new note
		result = homePage.doesCredentialExist(driver);

		assertEquals(false, result);

	}
}
