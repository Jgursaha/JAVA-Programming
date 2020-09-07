package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.NoSuchElementException;

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

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "noteTitlePostSubmit")
    private WebElement noteTitlePostSubmit;

    @FindBy(id = "noteDescriptionPostSubmit")
    private WebElement noteDescriptionPostSubmit;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logOut(){
        System.out.println("In logout method");
        logoutButton.click();

    }

    public void createNote(WebDriver webDriver, String nTitle, String nDescription) {

        //click navigation link back to home on result page
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

        //click navigation link back to home on result page
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

        return (doesElementExist(webDriver,"noteTitlePostSubmit") && doesElementExist(webDriver, "noteDescriptionPostSubmit"));

        //try{
        //    webDriver.findElement(By.id("noteDescriptionPostSubmit"));
        //    return true;
        //} catch(NoSuchElementException e){
        //    System.out.println("Note description element not found");
        //    return false;
        //}
    }

    public boolean doesElementExist(WebDriver driver, String identifier){
        try{
            driver.findElement(By.id(identifier));
            return true;
        } catch(NoSuchElementException e){
            System.out.println("Note title element not found");
            return false;
        }
    }

}
