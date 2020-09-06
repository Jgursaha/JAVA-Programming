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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	private String fName = "Jitesh";
	private String lName = "Gursahani";
	private String  uName = "jgursaha";
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
		driver.get(baseURL="http://localhost:"+ port);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}


	@Test
	public void unauthorisedUserAccess(){

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
	public void testSignupLogin(){

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
	public void testNoteCreation(){
		System.out.println("testing note creation");

		//SignUp
		driver.get(baseURL + "/signup");
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.signUp(fName, lName, uName, passWord);

		//Login
		System.out.println("LOGGING IN");
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(uName, passWord);

		String noteTitle = "Test Title";
		String noteDescription = "Test description.";


		//create new note
		HomePage homePage = new HomePage(driver);
		homePage.createNote(driver, noteTitle, noteDescription);


		//click navigation link back to home on result page

		//navigate to notes tab

		//check values displayed for the new note



	}
}
