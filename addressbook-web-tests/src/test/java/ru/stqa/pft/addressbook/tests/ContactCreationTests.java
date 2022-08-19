package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.initContactCreation();
    app.fillContactForm(new ContactData("Костя", "Автоматкин", "+73834567890", "89534563245", "kk@mail.ru"));
    app.submitContactCreation();
    app.gotoHomePage();
  }

}
