package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeliveryPage {
    WebDriver webDriver;
    WebDriverWait wait;

    @FindBy(className="RadioBut")
    WebElement deliveryMethodRadioBtn;

    @FindBy(xpath = "//div[@class='AddressContainBut DeliveryContinueButton']//input[@class='ContinueOn']")
    WebElement continueButton;

    public DeliveryPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30, 500);
        PageFactory.initElements(webDriver, this);
    }

    public void selectDeliveryMethod(){
        deliveryMethodRadioBtn.click();
    }

    public void waitForButtonContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
    }

    public void continueBuying() {
        continueButton.click();
    }
}
