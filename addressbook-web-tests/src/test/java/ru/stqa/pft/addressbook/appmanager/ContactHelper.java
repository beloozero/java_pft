package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
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

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void gotoHomePageAfterAddressBookModification() {
    click(By.linkText("home page"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContactsFromContactList() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void deleteContactFromContactEditForm() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

  public void initContactModificationFromContactList(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void initContactModificationFromContactDetails() {
    click(By.name("modifiy"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void openContactDetails(int index) {
    wd.findElements(By.xpath("//img[@alt='Details']")).get(index).click();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createContact(ContactData contactData) {
    initContactCreation();
    fillContactForm(contactData, true);
    submitContactCreation();
    gotoHomePageAfterAddressBookModification();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> contactRows = wd.findElements(By.name("entry"));
    for (WebElement cr : contactRows) {
      List<WebElement> contactFields = cr.findElements(By.tagName("td"));
      String lastName = contactFields.get(1).getText();
      String firstName = contactFields.get(2).getText();
      int id = Integer.parseInt(cr.findElement(By.name("selected[]")).getAttribute("id"));
      contacts.add(new ContactData(id, firstName, lastName, null, null, null, null));
    }
    return contacts;
  }
}
