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
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("Катя").withLastName("Котеночкина").withHomePhone("+73834567890")
              .withMobilePhone("89534563245").withEmail("kk@mail.ru")
              .withAddress("адрес Кати"));
    }
  }

  @Test
  public void testContactModificationFromContactDetails() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData modifiedContactInfo = app.contact().infoFromEditForm(modifiedContact);
    ContactData newContact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName("Bob")
            .withLastName("Bobsky")
            .withHomePhone("+73834567890")
            .withMobilePhone("89534563245")
            .withEmail("kk@mail.ru")
            ;
    app.contact().modifyFromContactDetails(newContact);
    newContact = newContact
            .withPhoto(modifiedContactInfo.getPhoto())
            .withWorkPhone(modifiedContactInfo.getWorkPhone())
            .withPhone2(modifiedContactInfo.getPhone2())
            .withAddress(modifiedContactInfo.getAddress())
            .withEmail2(modifiedContactInfo.getEmail2())
            .withEmail3(modifiedContactInfo.getEmail3())
            .withGroup(modifiedContactInfo.getGroup())
            .withGroupId(modifiedContactInfo.getGroupId());
    app.contact().fillAllPhones(newContact);
    app.contact().fillAllEmail(newContact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(newContact)));
  }

  @Test
  public void testContactModificationFromContactList() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData modifiedContactInfo = app.contact().infoFromEditForm(modifiedContact);
    ContactData newContact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName("Кнопка Редач")
            .withLastName("Редач")
            .withHomePhone("+73834567890")
            .withMobilePhone("89534563245")
            .withEmail("kk@mail.ru");
    app.contact().modifyFromContactList(newContact);
    newContact = newContact
            .withPhoto(modifiedContactInfo.getPhoto())
            .withWorkPhone(modifiedContactInfo.getWorkPhone())
            .withPhone2(modifiedContactInfo.getPhone2())
            .withAddress(modifiedContactInfo.getAddress())
            .withEmail2(modifiedContactInfo.getEmail2())
            .withEmail3(modifiedContactInfo.getEmail3())
            .withGroup(modifiedContactInfo.getGroup())
            .withGroupId(modifiedContactInfo.getGroupId());
    app.contact().fillAllPhones(newContact);
    app.contact().fillAllEmail(newContact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(newContact)));
  }

}
