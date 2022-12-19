package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("Маша").withLastName("Машечкина").withHomePhone("+73834567890")
              .withMobilePhone("89534563245").withEmail("kk@mail.ru"));
    }
  }

  @Test
  public void testContactDeletionFromContactList() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().deleteFromContactList(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(index);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testContactDeletionFromContactEditForm() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().deleteFromContactEditForm(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(index);
    Assert.assertEquals(before, after);
  }
}
