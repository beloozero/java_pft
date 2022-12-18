package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletionFromContactList() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Маша", "DelFromConList", "+73834567890", "89534563245", "kk@mail.ru", null));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    app.getContactHelper().selectContact(index);
    app.getContactHelper().deleteSelectedContactsFromContactList();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(index);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testContactDeletionFromContactEditForm() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Маша", "DelFromContactEditForm", "+73834567890", "89534563245", "kk@mail.ru", null));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    app.getContactHelper().initContactModificationFromContactList(index);
    app.getContactHelper().deleteContactFromContactEditForm();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(index);
    Assert.assertEquals(before, after);
  }
}
