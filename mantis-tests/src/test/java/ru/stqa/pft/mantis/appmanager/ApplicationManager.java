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
  WebDriver wd;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));

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

  public void stop() {
    wd.quit();
  }

}
