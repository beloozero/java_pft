package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletionFromContactList() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Маша", "DelFromConList", "+73834567890", "89534563245", "kk@mail.ru", null));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContactsFromContactList();
    app.getNavigationHelper().gotoHomePage();
  }

  @Test
  public void testContactDeletionFromContactEditForm() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Маша", "DelFromContactEditForm", "+73834567890", "89534563245", "kk@mail.ru", null));
    }
    app.getContactHelper().initContactModificationFromContactList();
    app.getContactHelper().deleteContactFromContactEditForm();
    app.getNavigationHelper().gotoHomePage();
  }
}
