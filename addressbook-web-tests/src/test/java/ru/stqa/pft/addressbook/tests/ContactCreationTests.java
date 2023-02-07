package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.xml"))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }


  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
  }

  @Test()
  public void testContactCreationWithPhotoAndGroup() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("Группа для контакта").withHeader("test"));
    }
    int groupIdForContactCreation = app.group().all().iterator().next().getId();
    app.goTo().homePage();

    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/cat.png");
    ContactData newContact = new ContactData()
            .withFirstName("Маша").withLastName("Аппараткина").withHomePhone("+73834567890")
            .withMobilePhone("89534563245").withEmail("kk@mail.ru").withGroupId(groupIdForContactCreation)
            .withPhoto(photo);
    app.contact().create(newContact);
    app.contact().fillAllPhones(newContact);
    app.contact().fillAllEmail(newContact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(newContact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactCreationWithDifferentData(ContactData contactData) {
    Contacts before = app.contact().all();
    app.contact().create(contactData);
    app.contact().fillAllPhones(contactData);
    app.contact().fillAllEmail(contactData);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contactData.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}
