package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModificationFromContactDetails() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Маша", "ModFromDetails", "+73834567890", "89534563245", "kk@mail.ru", "Таш группа"));
    }
    app.getContactHelper().openContactDetails();
    app.getContactHelper().initContactModificationFromContactDetails();
    app.getContactHelper().fillContactForm(new ContactData("Детали", "Редач", "+73834567890", "89534563245", "kk@mail.ru", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().gotoHomePageAfterAddressBookModification();
  }

  @Test
  public void testContactModificationFromContactList() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Маша", "ModFromContactList", "+73834567890", "89534563245", "kk@mail.ru", "Таш группа"));
    }
    app.getContactHelper().initContactModificationFromContactList();
    app.getContactHelper().fillContactForm(new ContactData("Кнопка Редач", "Редач", "+73834567890", "89534563245", "kk@mail.ru", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().gotoHomePageAfterAddressBookModification();
  }
}
