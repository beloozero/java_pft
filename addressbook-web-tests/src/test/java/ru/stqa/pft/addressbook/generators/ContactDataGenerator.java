package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-df", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jcommander = new JCommander(generator);
    try {
      jcommander.parse(args);
    } catch (ParameterException ex)
    {
      jcommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      ContactData newContact = new ContactData()
              .withFirstName(String.format("имя %s", i))
              .withLastName(String.format("фамилия %s", i));
      if (i % 2 == 0) {
        newContact = newContact
                .withHomePhone(String.format("8 945 %s%s%s%s%s%s%s", i, i, i, i, i, i, i))
                .withAddress(String.format("654354 Россия, Москва, улица Пушкина, дом %s", i));
      }

      if (i % 3 == 0) {
        newContact = newContact
                .withEmail(String.format("email1%s@mail.ru", i));
      }

      if (i % 5 == 0) {
        newContact = newContact
                .withWorkPhone(String.format("8383%s%s%s%s%s%s%s", i, i, i, i, i, i, i))
                .withEmail2(String.format("email2%s@mail.ru", i));
      }

      if (i % 7 == 0) {
        newContact = newContact
                .withMobilePhone(String.format("+7 (947) %s%s%s-%s%s-%s%s", i, i, i, i, i, i, i))
                .withEmail3(String.format("email3%s@mail.ru", i));
      }

      if (i % 9 == 0) {
        newContact = newContact
                .withPhone2(String.format("+7 966 %s%s%s %s%s %s%s", i, i, i, i, i, i, i));
      }
      contacts.add(newContact);
    }
    return contacts;
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

}
