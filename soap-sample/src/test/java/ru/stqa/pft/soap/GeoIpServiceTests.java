package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("255.1.1.1");
    Assert.assertEquals(geoIp, "<GeoIP><Country>US</Country><State>CA</State></GeoIP>");
  }
}
