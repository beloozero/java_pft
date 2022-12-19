package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("Катя").withLastName("Котеночкина").withHomePhone("+73834567890")
              .withMobilePhone("89534563245").withEmail("kk@mail.ru"));
    }
  }

  @Test
  public void testContactModificationFromContactDetails() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData newContact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("Bob").withLastName("Bobsky")
            .withHomePhone("+73834567890").withMobilePhone("89534563245").withEmail("kk@mail.ru");
    app.contact().modifyFromContactDetails(newContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());
    before.remove(modifiedContact);
    before.add(newContact);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testContactModificationFromContactList() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData newContact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("Кнопка Редач").withLastName("Редач")
            .withHomePhone("+73834567890").withMobilePhone("89534563245").withEmail("kk@mail.ru");
    app.contact().modifyFromContactList(newContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());
    before.remove(modifiedContact);
    before.add(newContact);
    Assert.assertEquals(before, after);
  }

}
