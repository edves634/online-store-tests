package org.example.steps;

import io.cucumber.java.*;
import io.cucumber.java.ru.*;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class DressSteps {
    private static final Map<String, Boolean> inventory = new HashMap<>();
    private boolean searchResult;
    private boolean requestSent;
    private String lastSearchedKey;

    // Hooks
    @Before
    public void beforeScenario() {
        System.out.println("=== Начало сценария ===");
        initInventory(); // Инициализируем инвентарь перед каждым сценарием
    }

    @After
    public void afterScenario() {
        System.out.println("=== Конец сценария ===\n");
    }

    @Before("@покупка")
    public void beforePurchaseScenario() {
        System.out.println("Подготовка к сценарию покупки");
    }

    private void initInventory() {
        inventory.clear();
        inventory.put("красное-62", true);
        inventory.put("синее-64", false);
        inventory.put("черное-60", false);
        inventory.put("розовое-70", false);
        System.out.println("Инициализирован инвентарь: " + inventory);
    }

    @Дано("виртуальный магазин имеет ассортимент платьев")
    public void givenInventoryExists() {
        // Инвентарь уже инициализирован в beforeScenario
        System.out.println("Проверка ассортимента платьев");
    }

    @Дано("я нахожусь на главной странице магазина")
    public void openHomePage() {
        System.out.println("Открыта главная страница магазина");
    }

    @Когда("я ищу платье цвета {string} размера {string}")
    public void searchDress(String color, String size) {
        lastSearchedKey = color + "-" + size;
        searchResult = inventory.getOrDefault(lastSearchedKey, false);
        System.out.printf("Поиск платья: цвет %s, размер %s. Найдено: %b (ключ: %s)%n",
                color, size, searchResult, lastSearchedKey);
    }

    @Тогда("результат поиска должен быть {string}")
    public void checkSearchResult(String expectedResult) {
        boolean expected = expectedResult.equals("найдено");
        Assert.assertEquals(
                String.format("Ожидалось '%s', получено '%s' (ключ: %s)",
                        expectedResult, searchResult ? "найдено" : "не найдено", lastSearchedKey),
                expected, searchResult);
    }

    @Когда("я отправляю запрос на добавление этого платья")
    public void sendRequest() {
        requestSent = !inventory.getOrDefault(lastSearchedKey, false);
        System.out.printf("Отправка запроса на добавление платья %s. Запрос %s%n",
                lastSearchedKey, requestSent ? "отправлен" : "не отправлен");
    }

    @Тогда("я получаю подтверждение о получении запроса")
    public void verifyRequestConfirmed() {
        Assert.assertTrue("Подтверждение не получено", requestSent);
        System.out.println("Подтверждение получено");
    }
}
