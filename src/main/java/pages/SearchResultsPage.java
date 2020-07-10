package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {
    WebDriver webDriver;
    WebDriverWait wait;

    @FindBy(css = "span[data-filtername='Trainers']")
    WebElement checkBoxOfProductInGroup;

    @FindBy(xpath = "//li[@li-productid='27534932']//div")
    WebElement selectedProduct;

    public SearchResultsPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30, 50);
        PageFactory.initElements(webDriver, this);
    }

    public void filterByProductGroup() {
        checkBoxOfProductInGroup.click();
    }

    public void waitForSearchResults() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='selectedFilter']")));
    }

    public void openProduct() {
        selectedProduct.click();
    }
}
