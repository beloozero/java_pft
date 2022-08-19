package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class TestBase {
  protected WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    wd.get("http://localhost/addressbook/");
    login("admin", "secret");
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
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

  protected void gotoGroupPage() {
   wd.findElement(By.linkText("groups")).click();
 }

  protected void initGroupCreation() {
   wd.findElement(By.name("new")).click();
 }

  protected void fillGroupForm(GroupData groupData) {
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

  protected void submitGroupCreation() {
   wd.findElement(By.name("submit")).click();
 }

  protected void returnToGroupPage() {
   wd.findElement(By.linkText("group page")).click();
 }

  protected void selectGroup() {
    wd.findElement(By.name("selected[]")).click();
  }

  protected void deleteSelectedGroups() {
    wd.findElement(By.name("delete")).click();
  }

  protected void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  protected void fillContactForm(ContactData contactData) {
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

  protected void submitContactCreation() {
    wd.findElement(By.name("submit")).click();
  }

  protected void gotoHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }
}
