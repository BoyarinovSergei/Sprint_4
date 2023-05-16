package sources;

import java.util.HashMap;
import java.util.Map;

/*
* Интерфейс с дефолтной мапой для адресов.
* */

public interface URLs {
    Map<String, String> URL_MAP = new HashMap<>() {{
        put("mainPage", "https://qa-scooter.praktikum-services.ru/");
    }};
}
