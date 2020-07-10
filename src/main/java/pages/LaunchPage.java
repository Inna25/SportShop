package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LaunchPage {
    public static final String EMAIL = "inna2004@inbox.ru";
    public static final String PASSWORD = "Zgxvgvzn25";

    private WebDriver webDriver;
    private WebDriverWait wait;

    @FindBy(id = "Login_EmailAddress")
    WebElement emailInputField;

    @FindBy(id = "Login_Password")
    WebElement passwordInputField;

    @FindBy(xpath = "//section[contains(@class,'existingCustomer')]//a")
    WebElement signButton;

    public LaunchPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30, 500);
        PageFactory.initElements(webDriver, this);
    }

    public void login() {
        emailInputField.clear();
        emailInputField.sendKeys(EMAIL);

        passwordInputField.clear();
        passwordInputField.sendKeys(PASSWORD);

        signButton.click();
    }
}
