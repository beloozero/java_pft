package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    initContactCreation();
    fillContactForm(new ContactData("Костя", "Автоматкин", "+73834567890", "89534563245", "kk@mail.ru"));
    submitContactCreation();
    gotoHomePage();
  }

}
