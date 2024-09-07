package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators using XPath for elements on the ProductPage
    By firstProduct = By.xpath("//div[@class='product-tuple-listing']//a[@class='dp-widget-link']");
    By addToCartButton = By.xpath("//div[@class='btn-segment clearfix']//button[contains(@id, 'add-cart-button-id')]");

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait for 10 seconds
    }

    // Method to select the first product in the list
    public void selectFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
    }

    // Standard Method to add the product to the cart
    public void addToCart() {
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCart.click();
    }

    // Method to add the product to the cart using JavaScriptExecutor
    public void addToCartUsingJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement addToCartButtonElement = driver.findElement(addToCartButton);
        js.executeScript("arguments[0].click();", addToCartButtonElement);
    }
}
