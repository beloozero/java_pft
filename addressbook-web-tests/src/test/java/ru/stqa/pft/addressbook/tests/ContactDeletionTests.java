package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletionFromContactList() {
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContactsFromContactList();
    app.getNavigationHelper().gotoHomePage();
  }

  @Test
  public void testContactDeletionFromContactEditForm() {
    app.getContactHelper().initContactModificationFromContactList();
    app.getContactHelper().deleteContactFromContactEditForm();
    app.getNavigationHelper().gotoHomePage();
  }
}
