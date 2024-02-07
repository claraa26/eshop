package id.ac.ui.cs.advprog.eshop.funtional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void productListPage_isCorrect(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(baseUrl + "/product/list");
        String pageTitle = driver.getTitle();
        // Verify
        assertEquals("Product List", pageTitle);
    }

    @Test
    void createProduct_andVerifyInProductList(ChromeDriver driver) throws Exception {
        // Exercise: Navigate to create product page
        driver.get(baseUrl + "/product/list");

        // Simulate user interaction to add a product
        driver.findElement(By.linkText("Create Product")).click();
        driver.findElement(By.name("productName")).sendKeys("Cabai Merah");
        driver.findElement(By.name("productQuantity")).sendKeys("100");
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

        // Verify: Check if the new product is in the product list
        String newProduct = driver.findElement(By.tagName("td")).getText();
        assertEquals("Cabai Merah", newProduct);
    }
}
