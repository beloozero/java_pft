package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData newContact = new ContactData("Маша", "Аппараткина", "+73834567890", "89534563245", "kk@mail.ru", "Таш группа");
    app.getContactHelper().createContact(newContact);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(newContact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
