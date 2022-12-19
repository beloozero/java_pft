package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.stqa.pft.addressbook.tests.TestBase.app;

public class ContactHelper extends BaseHelper {
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initCreation() {
    click(By.linkText("add new"));
  }

  public void fillForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getEmail());
    if (creation) {
      if (contactData.getGroup() != null) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitCreation() {
    click(By.name("submit"));
  }

  public void gotoHomePageAfterAddressBookModification() {
    click(By.linkText("home page"));
  }

  private void selectById(int id) {
    wd.findElement(By.xpath("//input[@id='"+id + "']")).click();
  }

  public void initDeletionFromContactList() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void initDeletionFromContactEditForm() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

  private void initModificationFromContactListById(int id) {
    wd.findElement(By.xpath("//input[@id='"+ id + "']/../..//img[@title='Edit']")).click();
  }

  public void modifyFromContactDetails(ContactData newContact) {
    openDetailsById(newContact.getId());
    initModificationFromContactDetails();
    fillForm(newContact, false);
    submitModification();
    gotoHomePageAfterAddressBookModification();
  }

  public void modifyFromContactList(ContactData newContact) {
    initModificationFromContactListById(newContact.getId());
    fillForm(newContact, false);
    submitModification();
    gotoHomePageAfterAddressBookModification();
  }

  public void deleteFromContactList(ContactData contact) {
    selectById(contact.getId());
    initDeletionFromContactList();
    app.goTo().homePage();
  }

  public void deleteFromContactEditForm(ContactData contact) {
    initModificationFromContactListById(contact.getId());
    initDeletionFromContactEditForm();
    app.goTo().homePage();
  }

  public void initModificationFromContactDetails() {
    click(By.name("modifiy"));
  }

  public void submitModification() {
    click(By.name("update"));
  }

  private void openDetailsById(int id) {
    wd.findElement(By.xpath("//input[@id='"+ id + "']/../..//img[@title='Details']")).click();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void create(ContactData contactData) {
    initCreation();
    fillForm(contactData, true);
    submitCreation();
    gotoHomePageAfterAddressBookModification();
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<>();
    List<WebElement> contactRows = wd.findElements(By.name("entry"));
    for (WebElement cr : contactRows) {
      List<WebElement> contactFields = cr.findElements(By.tagName("td"));
      String lastName = contactFields.get(1).getText();
      String firstName = contactFields.get(2).getText();
      int id = Integer.parseInt(cr.findElement(By.name("selected[]")).getAttribute("id"));
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
    }
    return contacts;
  }

}
