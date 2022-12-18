package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Collections {

  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "Python", "PHP"};

    for (String l : langs) { // l - элемент массива
      System.out.println("Я хочу выучить " + l);
    }

    /*
    List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("С#");
    languages.add("PHP");
    */

    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");

    for (String l : languages) { // l - элемент коллекции
      System.out.println("Я хочу выучить " + l);
    }

    /*
    for (int i = 0; i < languages.size(); i++) {
      System.out.println("Я хочу выучить " + languages.get(i));
    }
    */

  }
}
