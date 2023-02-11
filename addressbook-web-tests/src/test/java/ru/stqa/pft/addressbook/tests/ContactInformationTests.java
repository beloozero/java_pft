package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInformationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.db().contacts().size() == 0
    ) {
      app.contact().create(new ContactData()
              .withFirstName("Вася").withLastName("Васильков")
              .withHomePhone("+7 383 456-78-90").withMobilePhone("+7 945 654-87-98").withWorkPhone("8(953) 456 32 45").withPhone2("998 97 4567865")
              .withAddress("630111, Новосибирск, переулок Булгакова, д 67, кв 4")
              .withEmail("kk@mail.ru").withEmail2("second@gmail.com").withEmail3("third@yandex.ru"));
    }
  }

  @Test
  public void testContactInformationFromListAndEditForm() {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(app.contact().mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(contact.getAllEmail(), equalTo(app.contact().mergeEmail(contactInfoFromEditForm)));
  }

  @Test
  public void testContactInformationFromEditForm() {
    ContactData contact = app.db().contacts().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat((contact.getFirstName()), equalTo(contactInfoFromEditForm.getFirstName()));
    assertThat((contact.getLastName()), equalTo(contactInfoFromEditForm.getLastName()));
    assertThat((contact.getHomePhone()), equalTo(contactInfoFromEditForm.getHomePhone()));
    assertThat((contact.getMobilePhone()), equalTo(contactInfoFromEditForm.getMobilePhone()));
    assertThat((contact.getWorkPhone()), equalTo(contactInfoFromEditForm.getWorkPhone()));
    assertThat((contact.getPhone2()), equalTo(contactInfoFromEditForm.getPhone2()));
    assertThat((contact.getAddress()), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat((contact.getEmail()), equalTo(contactInfoFromEditForm.getEmail()));
    assertThat((contact.getEmail2()), equalTo(contactInfoFromEditForm.getEmail2()));
    assertThat((contact.getEmail3()), equalTo(contactInfoFromEditForm.getEmail3()));

  }


}
