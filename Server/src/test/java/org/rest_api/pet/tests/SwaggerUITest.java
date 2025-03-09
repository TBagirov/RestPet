package org.rest_api.pet.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SwaggerUITest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {

        System.setProperty("webdriver.chrome.driver", "D:\\Java\\Spring\\RestPet\\Server\\src\\main\\resources\\chromedriver-win64\\chromedriver.exe");


        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");  // Комментируй, если нужен UI
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("http://localhost:8080/swagger-ui/index.html");


        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    @Test
    void testSwaggerUI() {

        WebElement swaggerTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'Swagger UI')]")));
        assertNotNull(swaggerTitle, "Swagger UI не загрузился!");


        WebElement postButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(., 'POST') and contains(., 'sensors/registration')]")
        ));
        assertNotNull(postButton, "Кнопка POST /sensors/registration не найдена!");


        postButton.click();

        WebElement tryItOutButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Try it out')]")
        ));
        tryItOutButton.click(); // Активируем ввод данных


        // Нажимаем "Execute"
        WebElement executeButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Execute')]")
        ));
        executeButton.click();

        // Ждем появления блока с ответом
        WebElement responseStatus = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//td[contains(text(),'200')]")  // Проверяем, что есть статус 200 OK
        ));
        assertNotNull(responseStatus, "Статус ответа не 200 OK!");

        System.out.println("Тест успешно пройден: регистрация сенсора выполнена!");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
