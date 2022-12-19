package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("Катя").withLastName("Котеночкина").withHomePhone("+73834567890")
              .withMobilePhone("89534563245").withEmail("kk@mail.ru"));
    }
  }

  @Test
  public void testContactModificationFromContactDetails() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData newContact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("Bob").withLastName("Bobsky")
            .withHomePhone("+73834567890").withMobilePhone("89534563245").withEmail("kk@mail.ru");
    app.contact().modifyFromContactDetails(newContact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(newContact)));
  }

  @Test
  public void testContactModificationFromContactList() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData newContact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("Кнопка Редач").withLastName("Редач")
            .withHomePhone("+73834567890").withMobilePhone("89534563245").withEmail("kk@mail.ru");
    app.contact().modifyFromContactList(newContact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(newContact)));
  }

}
