import PageObject.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;

import static SetUp.SetDriver.*;

/*
* Класс для проверки AccordionItem.
* Сравнение текста который появляется поле нажатия на стрлеки.
* Блок: 'Вопросы о важном'.
* */

public class TestAccordionItem {
    private MainPage mainPage = new MainPage(driver);

    private List<String> targetTextInAccordion = new ArrayList<>(){{
        add("Сутки — 400 рублей. Оплата курьеру — наличными или картой.");
        add("Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.");
        add("Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.");
        add("Только начиная с завтрашнего дня. Но скоро станем расторопнее.");
        add("Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.");
        add("Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.");
        add("Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.");
        add("Да, обязательно. Всем самокатов! И Москве, и Московской области.");
    }};

    private static List<Cookie> allCookies = new ArrayList<>(){{
        add(new Cookie("Cartoshka-legacy", "true"));
        add(new Cookie("Cartoshka", "true"));
    }};

    @BeforeClass
    public static void setUp() {
        chosenDriverIs("chrome");
        openPage("https://qa-scooter.praktikum-services.ru/");
        addCookie(allCookies.get(0));
        addCookie(allCookies.get(1));
        refreshPage();
    }

    @Test
    public void openAccordionItemAndCompareTextInside() {
        List<String> lIst = mainPage.openAccordionItemsAndGetText();

        Assert.assertEquals(lIst, targetTextInAccordion);
    }

    @After
    public void quitDriver() {
        shutDown();
    }
}
