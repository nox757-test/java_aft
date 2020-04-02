package ru.chibisov.aft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import ru.chibisov.aft.addressbook.appmanager.db.DbHelper;
import ru.chibisov.aft.addressbook.appmanager.db.SessionUtil;
import ru.chibisov.aft.addressbook.appmanager.ui.ContactHelper;
import ru.chibisov.aft.addressbook.appmanager.ui.GroupHelper;
import ru.chibisov.aft.addressbook.appmanager.ui.NavigationHelper;
import ru.chibisov.aft.addressbook.appmanager.ui.SessionHelper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.fail;

public class ApplicationManager {

    private final Properties properties;

    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    private GroupHelper groupHelper;
    private NavigationHelper navigationHelper;
    private DbHelper dbHelper;

    private final String browserType;

    public ApplicationManager(String browserType) {
        this.browserType = browserType;
        this.properties = new Properties();
    }

    public void init() throws IOException {
        String local = System.getProperty("target", "src/test/resources/properties/stand.properties");
        properties.load(new FileReader(new File(local)));

        driver = BrowserTypes.getType(browserType).create();

        sessionHelper = new SessionHelper(driver);
        contactHelper = new ContactHelper(driver);
        groupHelper = new GroupHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        dbHelper = new DbHelper();

        driver.get(properties.getProperty("web.baseUrl"));
        sessionHelper.login(properties.getProperty("web.user"), properties.getProperty("web.pass"));
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

    public SessionHelper session() {
        return sessionHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }
}
