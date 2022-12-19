package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("Катя").withLastName("Котеночкина").withHomePhone("+73834567890")
              .withMobilePhone("89534563245").withEmail("kk@mail.ru"));
    }
  }

  @Test
  public void testContactModificationFromContactDetails() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData newContact = new ContactData()
            .withId(before.get(index).getId()).withFirstName("Bob").withLastName("Bobsky")
            .withHomePhone("+73834567890").withMobilePhone("89534563245").withEmail("kk@mail.ru");
    app.contact().modifyFromContactDetails(index, newContact);
    List<ContactData> after = app.contact().list();
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
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData newContact = new ContactData()
            .withId(before.get(index).getId()).withFirstName("Кнопка Редач").withLastName("Редач")
            .withHomePhone("+73834567890").withMobilePhone("89534563245").withEmail("kk@mail.ru");
    app.contact().modifyFromContactList(index, newContact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(newContact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
