package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends BaseHelper {

  private Groups groupCache = null;
  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void initCreation() {
    click(By.name("new"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void submitCreation() {
    click(By.name("submit"));
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  private void selectById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initDeletion() {
    click(By.name("delete"));
  }

  public void initModification() {
    click(By.name("edit"));
  }

  public void submitModification() {
    click(By.name("update"));
  }


  public void create(GroupData groupData) {
    initCreation();
    fillGroupForm(groupData);
    submitCreation();
    groupCache = null;
    returnToGroupPage();
  }

  public void modify(GroupData newGroup) {
    selectById(newGroup.getId());
    initModification();
    fillGroupForm(newGroup);
    submitModification();
    groupCache = null;
    returnToGroupPage();
  }

  public void delete(GroupData group) {
    selectById(group.getId());
    initDeletion();
    groupCache = null;
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public boolean checkGroupByName(String name) {
    return isElementPresent(By.xpath(String.format("//span[@class='group'][.='%s']", name)));
  }

  public int count() {
    return wd.findElements(By.className("group")).size();
  }


  public Groups all() {
    if (groupCache == null) {
      groupCache = new Groups();
      List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
      for (WebElement element : elements) {
        String name = element.getText();
        int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
        groupCache.add(new GroupData().withId(id).withName(name));
      }
    }
    return new Groups(groupCache);
  }

}
