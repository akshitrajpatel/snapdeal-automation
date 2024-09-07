package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    // Locators for elements on the HomePage
    By searchBox = By.id("inputValEnter");
    By searchButton = By.cssSelector("button.searchformButton");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to search for a product
    public void searchProduct(String productName) {
        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).click();
    }
}
