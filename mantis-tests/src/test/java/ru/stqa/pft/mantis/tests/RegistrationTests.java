package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }
  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

  @Test
  public void testRegistration() throws MessagingException, IOException {
    long now = System.currentTimeMillis();
    String email = String.format("user%s@localhost.localdomain", now);
    String username = String.format("user%s", now);
    String password = String.format("pass%s", now);
    app.gui().startRegistration(username, email);
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.gui().finishRegistration(confirmationLink, password);
    assertTrue(app.newSession().login(username, password));
  }


  @Test(enabled = false)
  public void testRegistrationJames() throws MessagingException, IOException {
    long now = System.currentTimeMillis();
    String email = String.format("user%s@localhost", now);//
    String username = String.format("user%s", now);
    String password = String.format("pass%s", now);
    app.james().createUser(username, password);
    app.gui().startRegistration(username, email);
    List<MailMessage> mailMessages = app.james().waitForMail(username, password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.gui().finishRegistration(confirmationLink, password);
    assertTrue(app.newSession().login(username, password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

}
