package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("Катя").withLastName("Котеночкина").withHomePhone("+73834567890")
              .withMobilePhone("89534563245").withEmail("kk@mail.ru")
              .withAddress("адрес Кати"));
    }
  }

  @Test
  public void testContactModificationFromContactDetails() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
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
            .withPhoto(modifiedContact.getPhoto())
            .withWorkPhone(modifiedContact.getWorkPhone())
            .withPhone2(modifiedContact.getPhone2())
            .withAddress(modifiedContact.getAddress())
            .withEmail2(modifiedContact.getEmail2())
            .withEmail3(modifiedContact.getEmail3());
    for (GroupData groupData : modifiedContact.getGroups()) {
      newContact = newContact.inGroup(groupData);
    }
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(newContact)));
    verifyContactListInUI();
  }

  @Test
  public void testContactModificationFromContactList() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData newContact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName("Кнопка Редач")
            .withLastName("Редач")
            .withHomePhone("+73834567890")
            .withMobilePhone("89534563245")
            .withEmail("kk@mail.ru");
    app.contact().modifyFromContactDetails(newContact);
    newContact = newContact
            .withPhoto(modifiedContact.getPhoto())
            .withWorkPhone(modifiedContact.getWorkPhone())
            .withPhone2(modifiedContact.getPhone2())
            .withAddress(modifiedContact.getAddress())
            .withEmail2(modifiedContact.getEmail2())
            .withEmail3(modifiedContact.getEmail3());
    for (GroupData groupData : modifiedContact.getGroups()) {
      newContact = newContact.inGroup(groupData);
    }
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(newContact)));
    verifyContactListInUI();
  }

}
