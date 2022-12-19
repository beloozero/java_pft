package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Set<ContactData> before = app.contact().all();
    ContactData newContact = new ContactData()
            .withFirstName("Маша").withLastName("Аппараткина").withHomePhone("+73834567890")
            .withMobilePhone("89534563245").withEmail("kk@mail.ru").withGroup("Таш группа");
    app.contact().create(newContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    newContact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(newContact);
    Assert.assertEquals(before, after);
  }

}
