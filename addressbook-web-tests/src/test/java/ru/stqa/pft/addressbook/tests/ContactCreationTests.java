package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  private int groupIdForContactCreation = 0;

  @BeforeMethod
  public void ensurePreconditions() {

    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("Группа для контакта").withHeader("test"));
    }
    groupIdForContactCreation = app.group().all().iterator().next().getId();
    app.goTo().homePage();
  }

  @Test()
  public void testContactCreation() {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/cat.png");
    ContactData newContact = new ContactData()
            .withFirstName("Маша").withLastName("Аппараткина").withHomePhone("+73834567890")
            .withMobilePhone("89534563245").withEmail("kk@mail.ru").withGroupId(groupIdForContactCreation)
            .withPhoto(photo);
    app.contact().create(newContact);
    groupIdForContactCreation = 0;
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(newContact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}
