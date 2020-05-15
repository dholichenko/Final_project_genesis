package conf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class TestManager {


    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @BeforeClass

    public static void setUp(){

        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");

        // ініціалізація драйверу
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        // Явні та неявні очікування (explicit/implicit waits)
        // Explicit Wait - явне очікування використовується "тут і зараз" на один конкретний метод.
        // Implicit Wait - неявне (приховане) очікування встановлюється один раз в коді поза методом та діє до зміни. Значення по замовчуванню - 0, тобто ніякого очікування.
        // Implicit Wait застосовується до усіх операцій неявно (тобто приховано, без вказання напряму в методі).
        // Назначення неявного очікування
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 1000);
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
