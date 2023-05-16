package setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


/*
 * Класс для настройки WebDriver
 * */
public class SetDriver {
    public static WebDriver driver;
    public static WebDriverWait webDriverWait;

    public static void chosenDriverIs(String browsersName) {
        if (browsersName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browsersName.equalsIgnoreCase("firefox")) {
            //Драйвер версии 33 для мазилы от версии 113.
            System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
            driver = new FirefoxDriver();
        }

        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public static void addCookie(Cookie cookie) {
        driver.manage().addCookie(cookie);
    }

    public static void openPage(String URL) {
        driver.get(URL);
    }

    public static void shutDown() {
        driver.quit();
        driver = null;
    }

    public static void refreshPage() {
        driver.navigate().refresh();
    }
}
