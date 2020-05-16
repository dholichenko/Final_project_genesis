package conf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class TestManager {

    // ініціалізація драйверу
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @BeforeClass
    // містяться попередні налаштування
    public static void setUp(){
        // Webdriver - набір бібліотек, який доволяє керувати браузеров з програми
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");

        // ініціалізація драйверу
        driver = new ChromeDriver();

        // вікно браузеру розширення на весь екран
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public static void tearDown(){
        // закриття вікна браузера
        driver.quit();
    }

    // гетер, який буде повертати драйвер
    public static WebDriver getDriver(){
        return driver;
    }
}
