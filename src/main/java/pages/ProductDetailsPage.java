package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage {

    WebDriver webDriver;
    WebDriverWait wait;

    @FindBy(xpath = "//div[@data-prompt='Please Select']//li[@data-text='5 (38)']//span")
    WebElement selectedSize;

    @FindBy(xpath = "//span[@class='addToBagInner']")
    WebElement addToCartButton;

    public ProductDetailsPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30, 500);
        PageFactory.initElements(webDriver, this);
    }

    public void selectSize() {
        selectedSize.click();
    }

    public void addToCart() {
        addToCartButton.click();
    }
}
