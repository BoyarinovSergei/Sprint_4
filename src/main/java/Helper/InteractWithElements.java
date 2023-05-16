package Helper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static SetUp.SetDriver.driver;

/*
 * Класс помогалка
 * */

public class InteractWithElements {

    public static void scrollToTheTarget(Object type) {
        if (type instanceof By) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement((By) type));
        } else if (type instanceof WebElement) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", (WebElement) type);
        }
    }

    public static void clickOn(Object type) {
        if (type instanceof By) {
            driver.findElement((By) type).click();
        } else if (type instanceof WebElement) {
            ((WebElement) type).click();
        }

        waitALittleBit(300);
    }

    public static void waitALittleBit(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
