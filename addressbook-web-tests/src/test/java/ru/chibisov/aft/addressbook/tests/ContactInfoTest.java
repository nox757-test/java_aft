package ru.chibisov.aft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTest extends TestBase {

    private ContactData contactData;

    @BeforeMethod
    public void generatePreconditions() {
        contactData = new ContactData().setMiddleName("mid_name2")
                .setLastName("last_name2")
                .setNickName("nickname2")
                .setGroupName("[none]")
                .setHomePhone("+7 916 208 102")
                .setMobilePhone("12 22")
                .setWorkPhone("33-33")
                .setEmail1("mail11@m.ru")
                .setEmail3("mail3@m.ru")
                .setPostAddress("jdkfjajdfkjas\ndksjafjskjeii2");
        app.contact().createNew(contactData);
    }

    @Test
    public void checkPhonesContactTest() {
        app.goTo().contactPage();

        ContactData contactTableInfo = app.contact().getRowById(contactData.getId());
        ContactData contactEditInfo = app.contact().infoFromEditForm(contactData);

        String allEditPhones = Stream.of(contactEditInfo.getHomePhone(), contactEditInfo.getMobilePhone(), contactEditInfo.getWorkPhone())
                .filter(s -> s != null && !s.isEmpty())
                .map(s -> s.replaceAll("[- )(]", ""))
                .collect(Collectors.joining("\n"));
        MatcherAssert.assertThat(contactTableInfo.getAllPhones(),
                CoreMatchers.equalTo(allEditPhones));

    }

    @Test
    public void checkEmailsContactTest() {
        app.goTo().contactPage();

        ContactData contactTableInfo = app.contact().getRowById(contactData.getId());
        ContactData contactEditInfo = app.contact().infoFromEditForm(contactData);

        MatcherAssert.assertThat(contactTableInfo.getAllEmails(), CoreMatchers.equalTo(contactEditInfo.getAllEmails()));

    }

    @Test
    public void checkAddressContactTest() {
        app.goTo().contactPage();

        ContactData contactTableInfo = app.contact().getRowById(contactData.getId());
        ContactData contactEditInfo = app.contact().infoFromEditForm(contactData);

        MatcherAssert.assertThat(contactTableInfo.getPostAddress(), CoreMatchers.equalTo(contactEditInfo.getPostAddress()));

    }
}
