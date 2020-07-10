import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import pages.SportSite;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.By.cssSelector;

@TestMethodOrder(ProductSelection.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductSelection extends OrderAnnotation {
    public static final String BRAND = "Kappa";
    public static final String PRODUCT_TYPE = "Trainers";
    public static final String DRIVER_NAME = "webdriver.chrome.driver";
    public static final String PATH_TO_DRIVER = "D:/Install/chromedriver_win32/chromedriver.exe";
    public static final String SITE = "https://www.sportsdirect.com/";
    public static final String MY_BAG = "MY BAG";
    public static final String RECIPIENT = "Inna Orlova";
    public static final String SIZE = "38";

    WebDriver webDriver;
    SportSite website;
    private String str;

    @BeforeAll
    public void setup() {
        System.setProperty(DRIVER_NAME, PATH_TO_DRIVER);
        webDriver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(webDriver, 30, 500);
        website = new SportSite(webDriver);
        webDriver.get(SITE);
    }

    @Order(1)
    @Test
    public void testApplyFilterByBrand() {
        System.out.println("Step 1: Search specific brand");
        website.mainPage().searchBrand(BRAND);

        assertTrue(webDriver.findElement(By.xpath("//span[@id='lblCategorySearchCopy']/strong"))
                .getText().equals(BRAND));
    }

    @Order(2)
    @Test
    public void testApplyFilterByProductGroup() throws InterruptedException {
        boolean isTrainer = true;

        System.out.println("Step 2: Filter specific product group");
        website.searchResultsPage().filterByProductGroup();
        website.searchResultsPage().waitForSearchResults();
        TimeUnit.SECONDS.sleep(5);

        List<WebElement> kappaTrainers = webDriver.findElement(By.id("navlist")).
                findElements(By.tagName("li"));

        for (WebElement trainer : kappaTrainers) {
            if (!trainer.getText().contains(PRODUCT_TYPE)) {
                isTrainer = false;
                System.out.println("In the list is an item which isn't Trainers");
                break;
            }
        }
        assertTrue(isTrainer);
    }

    @Order(3)
    @Test
    public void testProductOpening() {

        System.out.println("Step 3: Open selected product");

        website.searchResultsPage().openProduct();
        assertTrue(webDriver.findElement(By.className("addToBag")).isDisplayed());
    }

    @Order(4)
    @Test
    public void testChoiceSize() {

        System.out.println("Step 4: Choose the required size");
        website.productDetailsPage().selectSize();

        assertTrue(webDriver.findElement(cssSelector(".sizeVariantHighlight span")).getText().contains(SIZE));

    }

    @Order(5)
    @Test
    public void testAdditionToCart() throws InterruptedException {

        System.out.println("Step 5: Add selected product to the cart");
        website.productDetailsPage().addToCart();
        TimeUnit.SECONDS.sleep(5);
        assertEquals(0, webDriver.findElement(By.id("bagQuantity")).getText().compareToIgnoreCase("1"));
    }

    @Order(6)
    @Test
    public void testPassToCart() {

        System.out.println("Step 6: Pass to cart");
        website.mainPage().goToCart();

        assertTrue(webDriver.findElement(By.id("BasketHeaderText")).getText().equals(MY_BAG));

        System.out.println("Only one product was selected");
        assertTrue(webDriver.findElements(By.cssSelector("tbody tr")).size() == 1);

        System.out.println("Title of product contains words: " + BRAND);
        assertTrue(webDriver.findElement(By.className("productTitle")).getText().contains(BRAND));

        System.out.println("Title of product contains words: " + PRODUCT_TYPE);
        assertTrue(webDriver.findElement(By.className("productTitle")).getText().contains(PRODUCT_TYPE));
    }

    @Order(7)
    @Test
    public void testContinueSecurely() {

        System.out.println("Step 7: Pass to next step to authorization");
        website.cartPage().continueSecurely();

        assertTrue(webDriver.findElement(By.xpath("//*[contains(text(),'Already Registered?')]"))
                .isDisplayed() == true);
    }

    @Order(8)
    @Test
    public void testPassToDeliveryChoices() {

        System.out.println("Step 8: Login and pass to choosing delivery method");
        website.launchPage().login();

        System.out.println("There is link - Change");
        assertTrue(webDriver.findElement(By.id("DifferentAddressLink")).getText().equals("Change"));

        System.out.println("The link Change should be displayed");
        assertTrue(webDriver.findElement(By.className("DifferentAddressLinkWrapper")).isDisplayed());

        System.out.println("The recipient should be " + RECIPIENT);
        assertTrue(webDriver.findElement(By.cssSelector("#CurrentAddress > span")).getText().equals(RECIPIENT));
    }

    @Order(9)
    @Test
    public void testChoosingDeliveryMethod() {

        System.out.println("Step 9: Choosing delivery method");
        website.deliveryPage().selectDeliveryMethod();

        website.deliveryPage().waitForButtonContinue();

        assertTrue(webDriver.findElement(By.xpath("//div[@class='AddressContainBut DeliveryContinueButton']" +
                "//input[@class='ContinueOn']")).isDisplayed());
        website.deliveryPage().continueBuying();

        assertTrue(webDriver.findElement(By.xpath("//*[contains(text(),'Payment Options')]"))
                .isDisplayed() == true);
    }

    @Order(10)
    @Test
    public void testChoosingPaymentMethod() {

        System.out.println("Step 10: Choosing payment method");
        website.paymentPage().selectPaymentByCard();

        assertTrue(webDriver.findElement(By.xpath("//*[contains(text(),'Enter your Card Details')]"))
                .isDisplayed() == true);
    }

    @AfterAll
    public void tearDown() {
        if (webDriver != null)
            webDriver.quit();
    }
}
