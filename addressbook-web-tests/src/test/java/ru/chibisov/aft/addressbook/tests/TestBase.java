package ru.chibisov.aft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.chibisov.aft.addressbook.appmanager.ApplicationManager;
import ru.chibisov.aft.addressbook.appmanager.BrowserTypes;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(BrowserTypes.YANDEX.name());

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        app.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        app.quit();
    }

}
