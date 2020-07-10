package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver webDriver;
    private WebDriverWait wait;

    @FindBy(id = "advertPopup")
    WebElement bannerCloseButton;

    @FindBy(id = "txtSearch")
    WebElement searchInputField;

    @FindBy(id = "divBagTotalLink")
    WebElement checkoutButton;

    public MainPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30);
        PageFactory.initElements(webDriver, this);
    }

    public void closeBanner() {
        if (bannerCloseButton.isDisplayed()) bannerCloseButton.click();
    }

    public void waitForBannerClose() {
        wait.until(ExpectedConditions.invisibilityOf(bannerCloseButton));
        System.out.println("Banner close");
    }

    public void searchBrand(String text) {
        searchInputField.clear();
        searchInputField.sendKeys(text);
        searchInputField.sendKeys(Keys.RETURN);
    }

    public void goToCart() {
        checkoutButton.click();
    }

}
