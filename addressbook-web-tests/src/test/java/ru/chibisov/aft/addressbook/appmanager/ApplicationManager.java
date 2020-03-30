package ru.chibisov.aft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class ApplicationManager {

    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    private GroupHelper groupHelper;
    private NavigationHelper navigationHelper;

    public void init() {
        System.setProperty("webdriver.chrome.driver", "D:\\WorkCovid\\git\\java_aft\\addressbook-web-tests\\src\\test\\resources\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        sessionHelper = new SessionHelper(driver);
        contactHelper = new ContactHelper(driver);
        groupHelper = new GroupHelper(driver);
        navigationHelper = new NavigationHelper(driver);

        driver.get("http://localhost/addressbook/");
        sessionHelper.login("admin", "secret");
    }

    public void quit() {
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
