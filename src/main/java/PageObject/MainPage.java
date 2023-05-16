package PageObject;


/*
 * Описание главной страницы https://qa-scooter.praktikum-services.ru/
 * */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static Helper.InteractWithElements.clickOn;
import static Helper.InteractWithElements.scrollToTheTarget;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Кнопка 'Заказать' верхняя
    private By firstOrderButton = By.xpath("//button[@class=\"Button_Button__ra12g\"]");

    //Кнопка 'Заказать' нижняя
    private By secondOrderButton = By.xpath("//button[@class=\"Button_Button__ra12g Button_Middle__1CSJM\"]");

    //Строки со стрелками в блоке 'Вопросы о важном'
    private By accordionBoxItems = By.xpath("//div[@class='accordion']/div");

    //Блок 'Вопросы о важном'
    private By accordionBox = By.xpath("//div[@class='accordion']");

    //Элемент в котором есть описание в drop down
    private By textInItem = By.xpath("//div[@aria-disabled='true']/../..//p");


    //Скролл до блока 'Вопросы о важном', получение всех строк со стрелками, нажатие на каждую строку
    //со стрелками и добавление в лист видимого текста, который появился после нажатия на строку со стрелкой
    public List<String> openAccordionItemsAndGetText() {
        scrollToTheTarget(driver.findElement(accordionBox));

        List<WebElement> list = driver.findElements(accordionBoxItems);
        List<String> stringList = new ArrayList<>();

        list.forEach(f -> {
            clickOn(f);
            stringList.add(driver.findElement(textInItem).getText());
        });

        return stringList;
    }

    public void clickOnFirstOrderButton(){
        clickOn(firstOrderButton);
    }

    public void clickOnSecondOrderButton(){
        scrollToTheTarget(secondOrderButton);
        clickOn(secondOrderButton);
    }

    public OrderPage clickOnLowerOrUpper(String whichOne){
        if(whichOne.equalsIgnoreCase("нижняя")){
            clickOnSecondOrderButton();
        }if(whichOne.equalsIgnoreCase("верхняя")){
            clickOnFirstOrderButton();
        }
        return new OrderPage(driver);
    }
}
