package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInformationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0
    ) {
      app.contact().create(new ContactData()
              .withFirstName("Вася").withLastName("Васильков")
              .withHomePhone("+7 383 456-78-90").withMobilePhone("+7 945 654-87-98").withWorkPhone("8(953) 456 32 45").withPhone2("998 97 4567865")
              .withAddress("630111, Новосибирск, переулок Булгакова, д 67, кв 4")
              .withEmail("kk@mail.ru").withEmail2("second@gmail.com").withEmail3("third@yandex.ru"));
    }
  }

  @Test
  public void testContactInformation() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(contact.getAllEmail(), equalTo(mergeEmail(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getPhone2())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactInformationTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmail(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
