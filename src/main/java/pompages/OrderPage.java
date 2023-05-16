package pompages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static helper.Helper.cleverWaiter;
import static helper.Helper.clickOn;

/*
 * Описание страницы заказа самоката https://qa-scooter.praktikum-services.ru/order
 * */

public class OrderPage {
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //На экране 'Для кого самокат' поле 'Имя'
    private By inputName = By.xpath("//input[@placeholder=\"* Имя\"]");

    //На экране 'Для кого самокат' поле 'Фамилия'
    private By inputSurname = By.xpath("//input[@placeholder=\"* Фамилия\"]");

    //На экране 'Для кого самокат' поле 'Адрес:куда привезти заказ'
    private By inputWhereToGo = By.xpath("//input[@placeholder=\"* Адрес: куда привезти заказ\"]");

    //На экране 'Для кого самокат' поле 'Станция метро'
    private By inputUndergroundStation = By.xpath("//input[@placeholder=\"* Станция метро\"]");

    //На экране 'Для кого самокат' выпадающий список в поле 'Станция метро'
    private By dropDownUnderground = By.xpath("//div[@class=\"select-search__select\"]");

    //На экране 'Для кого самокат' поле 'Телефон: на него позвонит курьер'
    private By inputPhone = By.xpath("//input[@placeholder=\"* Телефон: на него позвонит курьер\"]");

    //На экране 'Для кого самокат' кнопка 'Далее'
    private By buttonNext = By.xpath("//button[@class=\"Button_Button__ra12g Button_Middle__1CSJM\"]");


    //На экране 'Про аренду' поле 'Когда привезти самокат'
    private By inputDate = By.xpath("//input[@placeholder=\"* Когда привезти самокат\"]");

    //На экране 'Про аренду' поле 'Срок аренды'
    private By inputRentingPeriod = By.xpath("//div[@class=\"Dropdown-placeholder\"]");

    //На экране 'Про аренду' поле 'Комментарий для курьера'
    private By inputComment = By.xpath("//input[@placeholder=\"Комментарий для курьера\"]");


    //На экране 'Про аренду' чек-бокс 'Черный жемчуг'
    private By inputBlackColor = By.xpath("//label[@for=\"black\"]");


    //На экране 'Про аренду' чек-бокс 'Серая безысходность'
    private By inputGreyColor = By.xpath("//label[@for=\"grey\"]");

    //Кнопка 'Да' на окне 'Хотите оформить заказ?'
    private By orderModal = By.xpath("//div[text()=\"Хотите оформить заказ?\"]/..//div/button[text()=\"Да\"]");

    //Текст в онке 'Заказ оформлен'
    private By orderCreatedWindow = By.xpath("//div[text()=\"Заказ оформлен\"]/div");

    public OrderPage fillInInputName(String value) {
        driver.findElement(inputName).sendKeys(value);
        return this;
    }

    public OrderPage fillInInputSurname(String value) {
        driver.findElement(inputSurname).sendKeys(value);
        return this;
    }

    public OrderPage fillInInputWhereToGo(String value) {
        driver.findElement(inputWhereToGo).sendKeys(value);
        return this;
    }

    public OrderPage fillInInputUndergroundStation(String value) {
        driver.findElement(inputUndergroundStation).sendKeys(value);
        cleverWaiter(dropDownUnderground);
        driver.findElement(inputUndergroundStation).sendKeys(Keys.DOWN);
        driver.findElement(inputUndergroundStation).sendKeys(Keys.ENTER);
        clickOn(inputName);
        return this;
    }

    public OrderPage fillInInputPhone(String value) {
        driver.findElement(inputPhone).sendKeys(value);
        return this;
    }

    public OrderPage fillInInputDate(String value) {
        driver.findElement(inputDate).sendKeys(value);
        driver.findElement(inputDate).sendKeys(Keys.ENTER);
        return this;
    }

    public OrderPage fillInInputRentingPeriod(String value) {
        clickOn(inputRentingPeriod);
        clickOn(By.xpath("//div[@class='Dropdown-option' and text()='" + value + "']"));
        return this;
    }

    public OrderPage chooseColor(String value) {
        if (value.equalsIgnoreCase("черный")) {
            clickOn(inputBlackColor);
        } else if (value.equalsIgnoreCase("серый")) {
            clickOn(inputGreyColor);
        }
        return this;
    }

    public OrderPage fillInInputComment(String value) {
        driver.findElement(inputComment).sendKeys(value);
        return this;
    }

    public void clickOnButtonNext() {
        clickOn(buttonNext);
    }

    public void clickOnOrderModal() {
        clickOn(orderModal);
    }

    public String getTextFromOrderCreatedWindow() {
        return driver.findElement(orderCreatedWindow).getText();
    }
}