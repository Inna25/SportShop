package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    WebDriver webDriver;
    WebDriverWait wait;

    @FindBy(id = "divContinueSecurely")
    WebElement continueSecurelyButton;

    public CartPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30, 500);
        PageFactory.initElements(webDriver, this);
    }

    public void continueSecurely() {
        continueSecurelyButton.click();
    }
}
