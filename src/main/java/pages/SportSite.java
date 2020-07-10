package pages;

import org.openqa.selenium.WebDriver;

public class SportSite {
    WebDriver webDriver;

    public SportSite(WebDriver driver) {
        webDriver = driver;
    }

    public MainPage mainPage() {
        return new MainPage(webDriver);
    }

    public SearchResultsPage searchResultsPage() {
        return new SearchResultsPage(webDriver);
    }

    public ProductDetailsPage productDetailsPage() {
        return new ProductDetailsPage(webDriver);
    }

    public CartPage cartPage() {
        return new CartPage(webDriver);
    }

    public LaunchPage launchPage() {
        return new LaunchPage(webDriver);
    }

    public DeliveryPage deliveryPage() {
        return new DeliveryPage(webDriver);
    }

    public PaymentPage paymentPage() {
        return new PaymentPage(webDriver);
    }
}
