package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getEmail());
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void gotoHomePageAfterAddressBookModification() {
    click(By.linkText("home page"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedContactsFromContactList() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void deleteContactFromContactEditForm() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

  public void initContactModificationFromContactList() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void initContactModificationFromContactDetails() {
    click(By.name("modifiy"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void openContactDetails() {
    click(By.xpath("//img[@alt='Details']"));
  }
}
