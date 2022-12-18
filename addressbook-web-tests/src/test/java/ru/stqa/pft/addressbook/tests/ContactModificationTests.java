package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModificationFromContactDetails() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Маша", "ModFromDetails", "+73834567890", "89534563245", "kk@mail.ru", null));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    app.getContactHelper().openContactDetails(index);
    app.getContactHelper().initContactModificationFromContactDetails();
    ContactData newContact = new ContactData(before.get(index).getId(), "Bob", "Bobsky", "+73834567890", "89534563245", "kk@mail.ru", null);
    app.getContactHelper().fillContactForm(newContact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().gotoHomePageAfterAddressBookModification();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(newContact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testContactModificationFromContactList() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Маша", "ModFromContactList", "+73834567890", "89534563245", "kk@mail.ru", null));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    app.getContactHelper().initContactModificationFromContactList(index);
    ContactData newContact = new ContactData(before.get(index).getId(),"Кнопка Редач", "Редач", "+73834567890", "89534563245", "kk@mail.ru", null);
    app.getContactHelper().fillContactForm(newContact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().gotoHomePageAfterAddressBookModification();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(newContact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
