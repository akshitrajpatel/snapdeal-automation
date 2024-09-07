package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapdealTest {
    WebDriver driver;
    HomePage homePage;
    ProductPage productPage;

    // Set up method, initializing WebDriver and opening Snapdeal
    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.snapdeal.com");

        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
    }

    // Test case: search for a product and add it to the cart
    @Test
    public void searchAndAddToCart() {
        homePage.searchProduct("Mobile Phone"); // Search for a product
        productPage.selectFirstProduct();       // Select the first product
        productPage.addToCartUsingJS();         // Add product to the cart using JavaScriptExecutor
    }

    // Teardown method to close the browser
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
