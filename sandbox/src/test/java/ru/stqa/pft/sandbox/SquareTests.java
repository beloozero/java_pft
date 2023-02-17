package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class SquareTests {

  @Test
  public void testArea() {
    Square sq = new Square(5);
    Assert.assertEquals(sq.area(), 20.0);

  }
}
