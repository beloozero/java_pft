package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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

  @Test
  public void testAddingContactToGroup() {
    ContactData modContact = app.db().contacts().iterator().next();
    Groups contactGroupsBefore = modContact.getGroups();
    Groups allGroups = app.db().groups();
    Groups groupsNotLinkedToContact = new Groups(allGroups);
    groupsNotLinkedToContact.removeAll(contactGroupsBefore);
    if (groupsNotLinkedToContact.size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Для добавления контакта").withHeader("test"));
      app.goTo().homePage();
      groupsNotLinkedToContact = app.db().groups();
      groupsNotLinkedToContact.removeAll(contactGroupsBefore);
      assertThat(groupsNotLinkedToContact.size(), equalTo(1));
    }
    GroupData groupForContact = groupsNotLinkedToContact.iterator().next();
    Contacts groupContactsBefore = groupForContact.getContacts();
    app.contact().addToGroup(modContact, groupForContact);
    app.group().goToGroupContactList();
    assertThat(app.contact().isThereAContactById(modContact.getId()), equalTo(true));
    Groups contactGroupsAfter = app.db().contacts(modContact.getId()).iterator().next().getGroups();
    assertThat(contactGroupsAfter.size(), equalTo(contactGroupsBefore.size() + 1));
    assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.withAdded(groupForContact)));
    Contacts groupContactsAfter = app.db().groups(groupForContact.getId()).iterator().next().getContacts();
    assertThat(groupContactsAfter.size(), equalTo(groupContactsBefore.size() + 1));
    app.contact().selectContactGroupToShow("[all]");
  }

  @Test
  public void testDeletingContactFromGroup() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Delete").withHeader("test"));
      app.goTo().homePage();
    }
    GroupData modGroup = app.db().groups().iterator().next();
    Contacts groupContactsBefore = modGroup.getContacts();
    if (groupContactsBefore.size() == 0) {
      ContactData addContact = app.db().contacts().iterator().next();
      app.contact().addToGroup(addContact, modGroup);
      app.goTo().homePage();
      modGroup = app.db().groups(modGroup.getId()).iterator().next();
      groupContactsBefore = modGroup.getContacts();
      assertThat(groupContactsBefore.size(), equalTo(1));
    }
    ContactData delContact = groupContactsBefore.iterator().next();
    Groups delContactGroupsBefore = delContact.getGroups();
    app.contact().deleteFromGroup(delContact, modGroup);
    app.group().goToGroupContactList();
    assertThat(app.contact().isThereAContactById(delContact.getId()), equalTo(false));
    Contacts groupContactsAfter = app.db().groups(modGroup.getId()).iterator().next().getContacts();
    assertThat(groupContactsAfter.size(), equalTo(groupContactsBefore.size() - 1));
    assertThat(groupContactsAfter, equalTo(groupContactsBefore.without(delContact)));
    Groups delContactGroupsAfter = app.db().contacts(delContact.getId()).iterator().next().getGroups();
    assertThat(delContactGroupsAfter.size(), equalTo(delContactGroupsBefore.size() - 1));
    app.contact().selectContactGroupToShow("[all]");
  }

}
