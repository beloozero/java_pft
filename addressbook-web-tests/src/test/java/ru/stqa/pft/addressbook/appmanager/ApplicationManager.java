package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.time.Duration;

public class ApplicationManager {
  public WebDriver wd;

  public void init() {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    wd.get("http://localhost/addressbook/");
    login("admin", "secret");
  }

  public void stop() {
    wd.findElement(By.linkText("Logout")).click();
    wd.quit();
  }

  private void login(String username, String password) {
   wd.findElement(By.name("user")).click();
   wd.findElement(By.name("user")).clear();
   wd.findElement(By.name("user")).sendKeys(username);
   wd.findElement(By.name("pass")).click();
   wd.findElement(By.name("pass")).clear();
   wd.findElement(By.name("pass")).sendKeys(password);
   wd.findElement(By.xpath("//input[@value='Login']")).click();
 }

  public void gotoGroupPage() {
   wd.findElement(By.linkText("groups")).click();
 }

  public void initGroupCreation() {
   wd.findElement(By.name("new")).click();
 }

  public void fillGroupForm(GroupData groupData) {
   wd.findElement(By.name("group_name")).click();
   wd.findElement(By.name("group_name")).clear();
   wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
   wd.findElement(By.name("group_header")).click();
   wd.findElement(By.name("group_header")).clear();
   wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
   wd.findElement(By.name("group_footer")).click();
   wd.findElement(By.name("group_footer")).clear();
   wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
 }

  public void submitGroupCreation() {
   wd.findElement(By.name("submit")).click();
 }

  public void returnToGroupPage() {
   wd.findElement(By.linkText("group page")).click();
 }

  public void selectGroup() {
    wd.findElement(By.name("selected[]")).click();
  }

  public void deleteSelectedGroups() {
    wd.findElement(By.name("delete")).click();
  }

  public void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void fillContactForm(ContactData contactData) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
    wd.findElement(By.name("home")).click();
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys(contactData.getHomePhone());
    wd.findElement(By.name("mobile")).click();
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys(contactData.getMobilePhone());
    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
  }

  public void submitContactCreation() {
    wd.findElement(By.name("submit")).click();
  }

  public void gotoHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }
}
