package ru.netology.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallbackTest {

    private WebDriver driver;

    @BeforeAll
    public static void setUpAll() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;

    }

    @Test
    public void shouldSendCorrectForm() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type = 'text']")).sendKeys("Анна Игнатьева");
        driver.findElement(By.cssSelector("[type = 'tel']")).sendKeys("+79993321232");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector(".button")).click();
        String actualText = driver.findElement(By.cssSelector("[data-test-id = 'order-success']")).getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expected, actualText);
    }

    @Test
    public void shouldSendCorrectFormWithDoubleName() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type = 'text']")).sendKeys("Анна-Мария Петрова");
        driver.findElement(By.cssSelector("[type = 'tel']")).sendKeys("+79993321232");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector(".button")).click();
        String actualText = driver.findElement(By.cssSelector("[data-test-id = 'order-success']")).getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expected, actualText);
    }

    @Test
    public void shouldSendCorrectFormWithDoubleNameWithSpace() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type = 'text']")).sendKeys("Мария Антуанетта Петрова");
        driver.findElement(By.cssSelector("[type = 'tel']")).sendKeys("+79993321232");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector(".button")).click();
        String actualText = driver.findElement(By.cssSelector("[data-test-id = 'order-success']")).getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expected, actualText);
    }

    @Test
    public void shouldSendCorrectFormWithoutNameAndSurname() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type = 'text']")).sendKeys("");
        driver.findElement(By.cssSelector("[type = 'tel']")).sendKeys("+79993321232");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector(".button")).click();
        String actualText = driver.findElement(By.cssSelector(".input_invalid")).getText().trim();
        String expected = "Фамилия и имя\n" +
                "Поле обязательно для заполнения";
        assertEquals(expected, actualText);
    }

    @Test
    public void shouldSendCorrectFormWithoutTelNumb() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type = 'text']")).sendKeys("Мария Авдеева");
        driver.findElement(By.cssSelector("[type = 'tel']")).sendKeys("");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector(".button")).click();
        String actualText = driver.findElement(By.cssSelector(".input_invalid")).getText().trim();
        String expected = "Мобильный телефон\n" +
                "Поле обязательно для заполнения";
        assertEquals(expected, actualText);
    }


}
