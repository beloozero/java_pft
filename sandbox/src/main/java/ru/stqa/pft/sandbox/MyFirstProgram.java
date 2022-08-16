package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

   hello("world");
   hello("user");

    Square sq = new Square(7);
    System.out.println("Площадь квадрата со стороной " + sq.l + " = " + sq.area());

    Rectangle re = new Rectangle(4.2, 3.4);
    System.out.println("Площадь прямоугольника со сторонами " + re.a + " и " + re.b + " = " + re.area());
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

}

