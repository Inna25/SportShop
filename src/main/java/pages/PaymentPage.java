package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPage {
    WebDriver webDriver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[@class='CardsIcons PaymentMethodSelectionLink']")
    WebElement cardPayment;

    @FindBy(xpath = "//input[@id='card_number']")
    WebElement cardNumber;

    @FindBy(id = "exp_month")
    WebElement expiryMonth;

    @FindBy(id = "exp_year_2digit")
    WebElement expiryYear;

    @FindBy(id = "cv2_number")
    WebElement securityCode;

    @FindBy(id = "continue")
    WebElement continueButton;


    public PaymentPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30, 500);
        PageFactory.initElements(webDriver, this);
    }

    public void selectPaymentByCard() {
        cardPayment.click();
    }

    public void inputCardNumber(String number) {
        cardNumber.clear();
        cardNumber.sendKeys(number);
    }

    public void inputExpiryMonth() {
        expiryMonth.clear();
        expiryMonth.sendKeys("01");
    }

    public void inputExpiryYear() {
        expiryYear.clear();
        expiryYear.sendKeys("22");
    }

    public void inputsecurityCode() {
        securityCode.clear();
        securityCode.sendKeys("123");
    }

    public void clickContinue() {
        continueButton.click();
    }
}
