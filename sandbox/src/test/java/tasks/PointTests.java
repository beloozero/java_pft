package tasks;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistancePositiveAndNegativeCoordinates() {
    Point p1 = new Point(2.1, 1);
    Point p2 = new Point(-2.1, -1);

    Assert.assertEquals(p1.distance(p2), 4.651881339845203);

  }

 @Test
  public void testDistancePositiveCoordinates() {
    Point p1 = new Point(4, 1);
    Point p2 = new Point(4, 6);

    Assert.assertEquals(p1.distance(p2), 5.0);

  }

  @Test
  public void testDistanceNegativeCoordinates() {
    Point p1 = new Point(-3, -6);
    Point p2 = new Point(-5, -6);

    Assert.assertEquals(p1.distance(p2), 2.0);

  }
}
