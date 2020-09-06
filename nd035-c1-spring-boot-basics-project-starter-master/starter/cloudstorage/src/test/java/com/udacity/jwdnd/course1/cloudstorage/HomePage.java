package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement submitNote;

    @FindBy(id = "submitButton")
    private WebElement submitButton;

    @FindBy(id = "newNoteButton")
    private WebElement newNoteButton;

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logOut(){
        System.out.println("In logout method");
        logoutButton.click();

    }

    public void createNote(WebDriver webDriver, String nTitle, String nDescription){

        //navigate to notes tab
        //notesTab.click();


        //create a new note
        //newNoteButton.click();
        //noteTitle.sendKeys(nTitle);
        //noteDescription.sendKeys(nDescription);
        //submitNote.click();

        //click navigation link back to home on result page
        WebDriverWait wait = new WebDriverWait(webDriver, 8);
        wait.until(ExpectedConditions.elementToBeClickable(notesTab)).click();
        System.out.println("Clicked note tab - SUCCESS");
        wait.until(ExpectedConditions.elementToBeClickable(newNoteButton)).click();
        System.out.println("Clicked new note button - SUCCESS");
        wait.until(ExpectedConditions.elementToBeClickable(noteTitle)).sendKeys(nTitle);
        System.out.println("sent note title - SUCCESS");
        wait.until(ExpectedConditions.elementToBeClickable(noteDescription)).sendKeys(nDescription);
        System.out.println("sent note description - SUCCESS");
        wait.until(ExpectedConditions.elementToBeClickable(submitNote)).click();
        System.out.println("submitted new note - SUCCESS");
        //webDriver.navigate().to(HOME_URL);
        //webDriver.findElement(By.id("nav-notes-tab")).click();


    }

}
