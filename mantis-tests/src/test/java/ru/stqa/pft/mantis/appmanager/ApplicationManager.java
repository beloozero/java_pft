package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
  private final Properties properties;
  private WebDriver wd;
  private final String browser;
  private GuiHelper guiHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private JamesHelper jamesHelper;
  private DbHelper dbHelper;
  private SoapHelper soapHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
  }

  public void stop() {
    if (wd != null) {
      wd.quit();
    }
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public GuiHelper gui() {
    if (guiHelper == null) {
      guiHelper = new GuiHelper(this);
    }
    return guiHelper;
  }

  public FtpHelper ftp() {
    if (ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  public WebDriver getDriver() {
    if (wd == null) {
      if (browser.equals(Browser.CHROME.browserName())) {
        System.setProperty("webdriver.chrome.driver", properties.getProperty("chrome.driver.path"));
        wd = new ChromeDriver();
      } else if (browser.equals(Browser.FIREFOX.browserName())) {
        System.setProperty("webdriver.gecko.driver", properties.getProperty("firefox.driver.path"));
        wd = new FirefoxDriver(new FirefoxOptions().setBinary(properties.getProperty("firefox.driver.binary")));
      }
      wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
      wd.get(properties.getProperty("web.baseUrl"));
    }
    return wd;
  }

  public MailHelper mail() {
    if (mailHelper == null) {
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public JamesHelper james() {
    if (jamesHelper == null) {
      jamesHelper = new JamesHelper(this);
    }
    return jamesHelper;
  }

  public DbHelper db() {
    if (dbHelper == null) {
      dbHelper = new DbHelper();
    }
    return dbHelper;
  }

  public SoapHelper soap() {
    if (soapHelper == null) {
      soapHelper = new SoapHelper(this);
    }
    return soapHelper;
  }


}
