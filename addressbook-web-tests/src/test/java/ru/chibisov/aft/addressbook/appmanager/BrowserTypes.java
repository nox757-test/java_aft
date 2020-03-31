package ru.chibisov.aft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public enum BrowserTypes {

    CHROME {
        public WebDriver create() {
            System.setProperty("webdriver.chrome.driver", "D:\\WorkCovid\\git\\java_aft\\addressbook-web-tests\\src\\test\\resources\\driver\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return driver;
        }
    },
    YANDEX {
        public WebDriver create() {
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Users\\nox757\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            System.setProperty("webdriver.chrome.driver", "D:\\WorkCovid\\git\\java_aft\\addressbook-web-tests\\src\\test\\resources\\driver\\chromedriver.exe");
            WebDriver driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return driver;
        }
    };

    public WebDriver create() {
        return null;
    }

    public static BrowserTypes getType(String value) {
        List<BrowserTypes> list = Arrays.asList(BrowserTypes.values());
        return list.stream().filter(m -> m.name().toLowerCase().equals(value.toLowerCase())).findAny().orElse(null);
    }
}
