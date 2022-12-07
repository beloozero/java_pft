package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Маша", "Аппараткина", "+73834567890", "89534563245", "kk@mail.ru"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().gotoHomePageAfterAddressBookModification();
  }

}
