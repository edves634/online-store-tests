package org.example;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Проверьте правильность пути к вашим feature-файлам
        glue = "org.example.steps", // Убедитесь, что ваши шаги находятся в этом пакете
        plugin = {
                "pretty", // Читаемый вывод в консоль
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:target/cucumber-reports/report.html", // Генерация HTML отчета
                "json:target/cucumber-reports/cucumber.json" // Генерация JSON отчета
        },
        tags = "@покупка_платья", // Убедитесь, что этот тег есть в ваших сценариях
        monochrome = true // Читаемый вывод
)
public class TestRunner {
}


