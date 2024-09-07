package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    WebDriver driver;

    // Locators for elements on the ProductPage
    By firstProduct = By.cssSelector(".product-tuple-listing .dp-widget-link");
    By addToCartButton = By.cssSelector("#add-cart-button-id");

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to select the first product in the list
    public void selectFirstProduct() {
        driver.findElement(firstProduct).click();
    }

    // Method to add the product to the cart
    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }
}
