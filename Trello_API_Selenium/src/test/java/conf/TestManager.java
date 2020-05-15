package conf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class TestManager {

    // ініціалізація драйверу
    // винесли з методу setUp, тому що невидно в методі openHomePage (область вдимости)
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @BeforeClass
    // містяться попередні налаштування
    // метод повинен бути static так як неповинні залежати від будь-чого
    public static void setUp(){
        // Webdriver - набір бібліотек, який доволяє керувати браузеров з програми
        // Перед ініціалізацією драйвера необхідно прописати шлях на сам драйвер - за допомогою setProperty. Необхідно додати chromedriver.exe в систему
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");

        // ініціалізація драйверу
        driver = new ChromeDriver();

        // вікно браузеру розширення на весь екран
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
        // закриття вікна браузера
        driver.quit();
    }

    // гетер, який буде повертати драйвер
    public static WebDriver getDriver(){
        return driver;
    }
}
