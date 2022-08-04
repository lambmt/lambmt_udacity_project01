package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeTestObject {
    @FindBy(id = "logout-btn")
    private WebElement logoutBtn;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement noteSubmitBtn;

    @FindBy(id = "credential-url")
    private WebElement credUrl;

    @FindBy(id = "credential-username")
    private WebElement credUsername;

    @FindBy(id = "credential-password")
    private WebElement credPassword;

    @FindBy(id = "credentialSubmit")
    private WebElement credSubmitBtn;

    public HomeTestObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        logoutBtn.submit();
    }

    public void createNote(String noteTitle, String noteDescription) {
        this.noteTitle.sendKeys(noteTitle + "new");
        this.noteDescription.sendKeys(noteDescription + "new");
        noteSubmitBtn.submit();
    }

    public void editNote(String noteTitle, String noteDescription) {
        this.noteTitle.clear();
        this.noteTitle.sendKeys(noteTitle + "edit");
        this.noteDescription.clear();
        this.noteDescription.sendKeys(noteDescription + "edit");
        noteSubmitBtn.submit();
    }

    public void createCredential(String credUrl, String credUsername, String credPassword) {
        this.credUrl.sendKeys(credUrl);
        this.credUsername.sendKeys(credUsername);
        this.credPassword.sendKeys(credPassword);
        credSubmitBtn.submit();
    }

    public void editCredential(String credUrl, String credUsername, String credPassword) {
        this.credUrl.clear();
        this.credUrl.sendKeys(credUrl + ".vn");
        this.credUsername.clear();
        this.credUsername.sendKeys(credUsername + "edit");
        this.credPassword.clear();
        this.credPassword.sendKeys(credPassword + "edit");
        credSubmitBtn.submit();
    }
}
