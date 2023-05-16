import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Cookie;
import pompages.MainPage;

import java.util.ArrayList;
import java.util.List;

import static setup.SetDriver.*;
import static sources.URLs.URL_MAP;

/*
 * Класс для проверки AccordionItem.
 * Сравнение текста который появляется поле нажатия на стрлеки.
 * Блок: 'Вопросы о важном'.
 * */

@RunWith(Parameterized.class)
public class TestAccordionItem {

    private MainPage mainPage = new MainPage(driver);

    private final int numberOfLine;
    private final String mustBeText;

    public TestAccordionItem(int numberOfLine, String mustBeText) {
        this.numberOfLine = numberOfLine;
        this.mustBeText = mustBeText;
    }

    @Parameterized.Parameters
    public static Object[][] twoArraysOfTestData() {
        return new Object[][]{
                {1, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {2, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {3, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {4, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {5, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {6, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {7, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {8, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    private static List<Cookie> allCookies = new ArrayList<>() {{
        add(new Cookie("Cartoshka-legacy", "true"));
        add(new Cookie("Cartoshka", "true"));
    }};

    @BeforeClass
    public static void setUp() {
        chosenDriverIs("chrome");
        openPage(URL_MAP.get("mainPage"));
        addCookie(allCookies.get(0));
        addCookie(allCookies.get(1));
        refreshPage();
    }

    @Test
    public void openAccordionItemAndCompareTextInside() {
        Assert.assertEquals(mustBeText, mainPage.getTextOfCertainOpenedDropDown(numberOfLine));
    }

    @AfterClass
    public static void quitDriver() {
        shutDown();
    }
}
