package ru.chibisov.aft.addressbook.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.chibisov.aft.addressbook.appmanager.ApplicationManager;
import ru.chibisov.aft.addressbook.appmanager.BrowserTypes;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(BrowserTypes.YANDEX.name());

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.quit();
    }

}
