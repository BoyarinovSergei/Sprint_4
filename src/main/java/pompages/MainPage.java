package pompages;

/*
 * Описание главной страницы https://qa-scooter.praktikum-services.ru/
 * */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helper.Helper.*;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Кнопка 'Заказать' верхняя
    private By firstOrderButton = By.xpath("//button[@class=\"Button_Button__ra12g\"]");

    //Кнопка 'Заказать' нижняя
    private By secondOrderButton = By.xpath("//button[@class=\"Button_Button__ra12g Button_Middle__1CSJM\"]");

    //Блок 'Вопросы о важном'
    private By accordionBox = By.xpath("//div[@class='accordion']");

    //Элемент в котором есть описание в drop down
    private By textInItem = By.xpath("//div[@aria-disabled='true']/../..//p");

    //Скролл до блока 'Вопросы о важном', открытие строго заданной строки и получение из нее текста.
    public String getTextOfCertainOpenedDropDown(int numberOfLine) {
        scrollToTheTarget(driver.findElement(accordionBox));
        openCertainDropDown(numberOfLine);
        cleverWaiter(By.xpath("//div[@class='accordion']/div[" + numberOfLine + "]//div[@class=\"accordion__panel\"]"));
        return driver.findElement(textInItem).getText();
    }

    private MainPage openCertainDropDown(int numberOfLine) {
        clickOn(By.xpath("//div[@class='accordion']/div[" + numberOfLine + "]"));
        return this;
    }

    public void clickOnFirstOrderButton() {
        clickOn(firstOrderButton);
    }

    public void clickOnSecondOrderButton() {
        scrollToTheTarget(secondOrderButton);
        clickOn(secondOrderButton);
    }

    public OrderPage clickOnLowerOrUpper(String whichOne) {
        if (whichOne.equalsIgnoreCase("нижняя")) {
            clickOnSecondOrderButton();
        }
        if (whichOne.equalsIgnoreCase("верхняя")) {
            clickOnFirstOrderButton();
        }
        return new OrderPage(driver);
    }
}
