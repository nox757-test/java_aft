package ru.chibisov.aft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class ApplicationManager {

    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    private GroupHelper groupHelper;
    private NavigationHelper navigationHelper;

    private final String browserType;

    public ApplicationManager(String browserType) {
        this.browserType = browserType;
    }

    public void init() {

        driver = BrowserTypes.getType(browserType).create();

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
