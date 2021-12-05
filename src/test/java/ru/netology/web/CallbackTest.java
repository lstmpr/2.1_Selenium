package ru.netology.web;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CallbackTest {

    private WebDriver driver;

    @BeforeAll
    public void setUpAll(){
        System.setProperty("webdriver.chrome.driver","./driver/mac/chromedriver");

    }

    @BeforeEach
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterAll
    public void tearDown(){
        driver.quit();
        driver = null;

    }

    @Test
    public void shouldSendForm() {
        driver.get("http://localhost:9999");
        System.out.println("");

    }

}
