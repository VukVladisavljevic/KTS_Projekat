package com.kits.project.e2e.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private WebDriver driver;

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPass;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement registerButton;

    @FindBy(id = "emailValidError")
    private WebElement emailValidError;

    @FindBy(id = "passwordMinimumLengthError")
    private WebElement weakPasswordError;

    @FindBy(id = "usernameTakenError")
    private WebElement usernameTakenError;

    @FindBy(id = "usernameContainSpaceError")
    private WebElement usernameContainSpaceError;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getFirstName() {
        return firstName;
    }

    public void setFirstName(WebElement firstName) {
        this.firstName = firstName;
    }

    public WebElement getLastName() {
        return lastName;
    }

    public void setLastName(WebElement lastName) {
        this.lastName = lastName;
    }

    public WebElement getEmail() {
        return email;
    }

    public void setEmail(WebElement email) {
        this.email = email;
    }

    public WebElement getUsername() {
        return username;
    }

    public void setUsername(WebElement username) {
        this.username = username;
    }

    public WebElement getPassword() {
        return password;
    }

    public void setPassword(WebElement password) {
        this.password = password;
    }

    public WebElement getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(WebElement confirmPass) {
        this.confirmPass = confirmPass;
    }

    public WebElement getEmailValidError() {
        return emailValidError;
    }

    public void setEmailValidError(WebElement emailValidError) {
        this.emailValidError = emailValidError;
    }

    public WebElement getWeakPasswordError() {
        return weakPasswordError;
    }

    public void setWeakPasswordError(WebElement weakPasswordError) {
        this.weakPasswordError = weakPasswordError;
    }

    public WebElement getUsernameTakenError() {
        return usernameTakenError;
    }

    public void setUsernameTakenError(WebElement usernameTakenError) {
        this.usernameTakenError = usernameTakenError;
    }

    public WebElement getUsernameContainSpaceError() {
        return usernameContainSpaceError;
    }

    public void setUsernameContainSpaceError(WebElement usernameContainSpaceError) {
        this.usernameContainSpaceError = usernameContainSpaceError;
    }

    public void ensureIsClickable() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(registerButton));
    }

    public void ensureFirstNameIsDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(firstName));
    }

    public void ensureLastNameIsDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(lastName));
    }

    public void ensureEmailIsDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(email));
    }

    public void ensureUsernameIsDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(username));
    }

    public void ensurePasswordIsDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(password));
    }

    public void ensureConfirmPasswordIsDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(confirmPass));
    }

    public void registerAs(String firstName, String lastName, String email, String username, String password, String confirmPassword) {
        this.firstName.clear();
        this.lastName.clear();
        this.email.clear();
        this.username.clear();
        this.password.clear();
        this.confirmPass.clear();
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.email.sendKeys(email);
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.confirmPass.sendKeys(confirmPassword);
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(registerButton));
        this.registerButton.click();
    }
}
