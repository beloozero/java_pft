package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

  @Test
  public void testChangePassword() throws MessagingException, IOException {
    String newPassword = String.format("pass%s", System.currentTimeMillis());
    app.gui().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.gui().gotoMenuItem("Manage Users");
    Users users = app.db().users();
    assertTrue(users.size() > 0);
    users = new Users(users.stream().filter((u) -> !u.getUsername().equals(app.getProperty("web.adminLogin"))).collect(Collectors.toSet()));
    assertTrue(users.size() > 0);
    UserData modUser = users.iterator().next();
    app.gui().initUserUpdate(modUser.getUsername());
    app.gui().initPasswordReset();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String resetPasswordLink = findResetPasswordLink(mailMessages, modUser.getEmail());
    app.gui().finishPasswordReset(resetPasswordLink, newPassword);
    assertTrue(app.newSession().login(modUser.getUsername(), newPassword));
  }

  private String findResetPasswordLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

}
