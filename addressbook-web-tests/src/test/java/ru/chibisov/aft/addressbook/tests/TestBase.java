package ru.chibisov.aft.addressbook.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.chibisov.aft.addressbook.appmanager.ApplicationManager;
import ru.chibisov.aft.addressbook.appmanager.BrowserTypes;

import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserTypes.YANDEX.name()));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.quit();
    }

}
