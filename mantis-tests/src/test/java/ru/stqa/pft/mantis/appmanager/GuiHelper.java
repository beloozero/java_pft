package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class GuiHelper extends BaseHelper {

  public GuiHelper(ApplicationManager app) {
    super(app);
  }

  public void startRegistration(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Signup']"));
  }

  public void finishRegistration(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    if (!wd.findElement(By.name("secure_session")).isSelected()) {
      click(By.name("secure_session"));
    }
    click(By.cssSelector("input[value='Login']"));
  }

  public void gotoMenuItem(String item) {
    click(By.xpath(String.format("//a[.='%s']", item)));
  }

  public void initUserUpdate(String username) {
    click(By.xpath(String.format("//tbody/tr[contains(@class, 'row')]//a[.='%s']", username)));
  }

  public void initPasswordReset() {
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void finishPasswordReset(String resetLink, String password) {
    wd.get(resetLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }

}
