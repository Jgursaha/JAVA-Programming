package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class HomePage {
    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement submitNote;

    @FindBy(id = "submitButton")
    private WebElement submitButton;

    @FindBy(id = "saveNoteChanges")
    private WebElement saveNoteChanges;

    @FindBy(id = "newNoteButton")
    private WebElement newNoteButton;

    @FindBy(id = "editNoteButton")
    private WebElement editNoteButton;

    @FindBy(id = "deleteNoteButton")
    private WebElement deleteNoteButton;

    @FindBy(id = "editCredentialButton")
    private WebElement editCredentialButton;

    @FindBy(id = "deleteCredentialButton")
    private WebElement deleteCredentialButton;

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "noteTitlePostSubmit")
    private WebElement noteTitlePostSubmit;

    @FindBy(id = "noteDescriptionPostSubmit")
    private WebElement noteDescriptionPostSubmit;



    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "credential-url")
    private WebElement credentialURL;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "credentialURLPostSubmit")
    private WebElement credentialURLPostSubmit;

    @FindBy(id = "credentialUsernamePostSubmit")
    private WebElement credentialUsernamePostSubmit;

    @FindBy(id = "credentialPasswordPostSubmit")
    private WebElement credentialPasswordPostSubmit;

    @FindBy(id = "saveCredentialChanges")
    private WebElement saveCredentialChanges;

    @FindBy(id = "newCredentialButton")
    private WebElement newCredentialButton;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logOut(){
        System.out.println("In logout method");
        logoutButton.click();

    }

    public void createNote(WebDriver webDriver, String nTitle, String nDescription) {


        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(notesTab)).click();
        System.out.println("Clicked note tab - SUCCESS");
        wait.until(ExpectedConditions.elementToBeClickable(newNoteButton)).click();
        System.out.println("Clicked new note button - SUCCESS");
        wait.until(ExpectedConditions.elementToBeClickable(noteTitle)).sendKeys(nTitle);
        System.out.println("sent note title - SUCCESS");
        wait.until(ExpectedConditions.elementToBeClickable(noteDescription)).sendKeys(nDescription);
        System.out.println("sent note description - SUCCESS");
        wait.until(ExpectedConditions.elementToBeClickable(saveNoteChanges)).click();
        System.out.println("submitted new note - SUCCESS");
    }

    public String[] retrieveNote(WebDriver webDriver){
        String[] results = new String[2];
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(notesTab)).click();
        System.out.println("Clicked note tab - SUCCESS");

        results[0] = wait.until(ExpectedConditions.elementToBeClickable(noteTitlePostSubmit)).getText();
        System.out.println("Got Title - SUCCESS");
        results[1] = wait.until(ExpectedConditions.elementToBeClickable(noteDescriptionPostSubmit)).getText();
        System.out.println("Got Description - SUCCESS");

        return results;
    }

    public void editNote(WebDriver webDriver, String nTitle, String nDescription) {


        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(notesTab)).click();
        System.out.println("Clicked note tab - SUCCESS");

        wait.until(ExpectedConditions.elementToBeClickable(editNoteButton)).click();
        System.out.println("Clicked edit note button - SUCCESS");

        wait.until(ExpectedConditions.elementToBeClickable(noteTitle)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(noteTitle)).sendKeys(nTitle);
        System.out.println("sent note title - SUCCESS");

        wait.until(ExpectedConditions.elementToBeClickable(noteDescription)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(noteDescription)).sendKeys(nDescription);
        System.out.println("sent note description - SUCCESS");

        wait.until(ExpectedConditions.elementToBeClickable(saveNoteChanges)).click();
        System.out.println("submitted new note - SUCCESS");
    }

    public void deleteNote(WebDriver webDriver){
        //click navigation link back to home on result page
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(notesTab)).click();
        System.out.println("Clicked note tab - SUCCESS");

        wait.until(ExpectedConditions.elementToBeClickable(deleteNoteButton)).click();
        System.out.println("Clicked delete note button - SUCCESS");

        return;
    }

    public boolean doesNoteExist(WebDriver webDriver){

        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(notesTab)).click();
        return (doesElementExist(webDriver,"noteTitlePostSubmit") && doesElementExist(webDriver, "noteDescriptionPostSubmit"));

    }

    public boolean doesElementExist(WebDriver driver, String identifier){
        try{
            driver.findElement(By.id(identifier));
            return true;
        } catch(NoSuchElementException e){
            System.out.println(identifier + " -> element not found");
            return false;
        }
    }

    public void createCredential(WebDriver webDriver, String url, String username, String password) {

        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(credentialsTab)).click();
        System.out.println("Clicked credential tab - SUCCESS");
        wait.until(ExpectedConditions.elementToBeClickable(newCredentialButton)).click();
        System.out.println("Clicked new credential button - SUCCESS");
        wait.until(ExpectedConditions.elementToBeClickable(credentialURL)).sendKeys(url);
        System.out.println("sent credential url - SUCCESS");
        wait.until(ExpectedConditions.elementToBeClickable(credentialUsername)).sendKeys(username);
        System.out.println("sent credential username - SUCCESS");
        wait.until(ExpectedConditions.elementToBeClickable(credentialPassword)).sendKeys(password);
        System.out.println("sent credential password - SUCCESS");
        wait.until(ExpectedConditions.elementToBeClickable(saveCredentialChanges)).click();
        System.out.println("submitted new note - SUCCESS");
    }

    public String[] retrieveCredential(WebDriver webDriver){
        String[] results = new String[3];
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(credentialsTab)).click();
        System.out.println("Clicked credentials tab - SUCCESS");

        results[0] = wait.until(ExpectedConditions.elementToBeClickable(credentialURLPostSubmit)).getText();
        System.out.println("Got URL - SUCCESS");

        results[1] = wait.until(ExpectedConditions.elementToBeClickable(credentialUsernamePostSubmit)).getText();
        System.out.println("Got Username - SUCCESS");

        results[2] = wait.until(ExpectedConditions.elementToBeClickable(credentialPasswordPostSubmit)).getText();
        System.out.println("Got Password - SUCCESS");

        return results;
    }

    public String retrieveClearTextPassword(WebDriver webDriver){
        return "password";
    }

    public void editCredential(WebDriver webDriver, String url, String username, String password) {


        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(credentialsTab)).click();
        System.out.println("Clicked note tab - SUCCESS");

        wait.until(ExpectedConditions.elementToBeClickable(editCredentialButton)).click();
        System.out.println("Clicked edit note button - SUCCESS");

        wait.until(ExpectedConditions.elementToBeClickable(credentialURL)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(credentialURL)).sendKeys(url);
        System.out.println("sent url - SUCCESS");

        wait.until(ExpectedConditions.elementToBeClickable(credentialUsername)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(credentialUsername)).sendKeys(username);
        System.out.println("sent username - SUCCESS");

        wait.until(ExpectedConditions.elementToBeClickable(credentialPassword)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(credentialPassword)).sendKeys(password);
        System.out.println("sent password - SUCCESS");

        wait.until(ExpectedConditions.elementToBeClickable(saveCredentialChanges)).click();
        System.out.println("submitted new credential - SUCCESS");
    }
}
