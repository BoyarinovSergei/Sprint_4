package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static setup.SetDriver.driver;
import static setup.SetDriver.webDriverWait;

/*
 * Класс помогалка
 * */

public class Helper {

    public static void scrollToTheTarget(Object type) {
        if (type instanceof By) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement((By) type));
        } else if (type instanceof WebElement) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", (WebElement) type);
        }
    }

    public static void clickOn(Object o) {
        if (o instanceof By) {
            cleverWaiter(o);
            driver.findElement((By) o).click();
        } else if (o instanceof WebElement) {
            cleverWaiter(o);
            ((WebElement) o).click();
        }
    }

    public static void cleverWaiter(Object o) {
        if (o instanceof By) {
            webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By) o));
        } else if (o instanceof WebElement) {
            webDriverWait.until(ExpectedConditions.visibilityOf((WebElement) o));
        }
    }
}