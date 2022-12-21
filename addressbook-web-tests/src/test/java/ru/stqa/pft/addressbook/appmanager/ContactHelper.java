package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

import static ru.stqa.pft.addressbook.tests.TestBase.app;

public class ContactHelper extends BaseHelper {

  private Contacts contactCache = null;
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initCreation() {
    click(By.linkText("add new"));
  }

  public void fillForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("phone2"), contactData.getPhone2());
    if (creation) {
      if (contactData.getGroup() != null) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      } else if (contactData.getGroupId() != 0) {
        new Select(wd.findElement(By.name("new_group"))).selectByValue(Integer.toString(contactData.getGroupId()));
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
   wd.findElement(By.xpath(String.format("//tr[.//input[@id=%s]]//img[@title='Edit']", id))).click();
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

  public int count() {
    return wd.findElements(By.xpath("//tr[@name='entry']")).size();
  }

  public void create(ContactData contactData) {
    initCreation();
    fillForm(contactData, true);
    submitCreation();
    contactCache = null;
    gotoHomePageAfterAddressBookModification();
  }

  public void modifyFromContactDetails(ContactData newContact) {
    openDetailsById(newContact.getId());
    initModificationFromContactDetails();
    fillForm(newContact, false);
    submitModification();
    contactCache = null;
    gotoHomePageAfterAddressBookModification();
  }

  public void modifyFromContactList(ContactData newContact) {
    initModificationFromContactListById(newContact.getId());
    fillForm(newContact, false);
    submitModification();
    contactCache = null;
    gotoHomePageAfterAddressBookModification();
  }

  public void deleteFromContactList(ContactData contact) {
    selectById(contact.getId());
    initDeletionFromContactList();
    contactCache = null;
    app.goTo().homePage();
  }

  public void deleteFromContactEditForm(ContactData contact) {
    initModificationFromContactListById(contact.getId());
    initDeletionFromContactEditForm();
    contactCache = null;
    app.goTo().homePage();
  }

  public Contacts all() {
    if (contactCache == null) {
      contactCache = new Contacts();
      List<WebElement> contactRows = wd.findElements(By.name("entry"));
      for (WebElement cr : contactRows) {
        List<WebElement> contactFields = cr.findElements(By.tagName("td"));
        int id = Integer.parseInt(contactFields.get(0).findElement(By.tagName("input")).getAttribute("id"));
        String lastName = contactFields.get(1).getText();
        String firstName = contactFields.get(2).getText();
        String allPhones = contactFields.get(5).getText();
        String address = contactFields.get(3).getText();
        String allEmail = contactFields.get(4).getText();
        contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                .withAllPhones(allPhones).withAddress(address).withAllEmail(allEmail));
      }
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initModificationFromContactListById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String homePhone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workPhone = wd.findElement(By.name("work")).getAttribute("value");
    String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
            .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone).withPhone2(phone2)
            .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
  }

}
