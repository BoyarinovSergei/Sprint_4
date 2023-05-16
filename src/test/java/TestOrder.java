import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Cookie;
import pompages.MainPage;
import pompages.OrderPage;

import java.util.ArrayList;
import java.util.List;

import static setup.SetDriver.*;
import static sources.URLs.URL_MAP;

@RunWith(Parameterized.class)
public class TestOrder {
    public TestOrder(String button, String name, String surname, String address, String metroStation, String phoneNumber, String date, String period, String color, String comment) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }

    private final String button;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String period;
    private final String color;
    private final String comment;

    private MainPage mainPage = new MainPage(driver);
    private OrderPage orderPage = new OrderPage(driver);

    @Parameterized.Parameters
    public static Object[][] twoArraysOfTestData() {
        return new Object[][]{
                {"нижняя", "Имя", "Фамилия", "Какой-то адрес", "Черкизовская", "88005553535", "20.05.2023", "сутки", "черный", "Комментарий для курьера"},
                {"верхняя", "Иванов", "Иван", "Москва, Автозаводская ул., 18", "Но", "89166609090", "23.05.2023", "трое суток", "серый", ""}
        };
    }

    private static List<Cookie> allCookies = new ArrayList<>() {{
        add(new Cookie("Cartoshka-legacy", "true"));
        add(new Cookie("Cartoshka", "true"));
    }};

    @BeforeClass
    public static void setUp() {
        chosenDriverIs("chrome");
    }

    @Before
    public void start() {
        openPage(URL_MAP.get("mainPage"));
        addCookie(allCookies.get(0));
        addCookie(allCookies.get(1));
        refreshPage();
    }

    @Test
    public void makeOrderUsingBothButtonsWithDifferentDate() {
        mainPage.clickOnLowerOrUpper(button)
                .fillInInputName(name)
                .fillInInputSurname(surname)
                .fillInInputWhereToGo(address)
                .fillInInputUndergroundStation(metroStation)
                .fillInInputPhone(phoneNumber)
                .clickOnButtonNext();

        orderPage.fillInInputDate(date)
                .fillInInputRentingPeriod(period)
                .chooseColor(color)
                .fillInInputComment(comment)
                .clickOnButtonNext();

        orderPage.clickOnOrderModal();

        Assert.assertTrue(orderPage.getTextFromOrderCreatedWindow().contains("Номер заказа"));
    }

    @AfterClass
    public static void quitDriver() {
        shutDown();
    }
}
