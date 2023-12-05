package bitcamp.myapp;

import java.util.Scanner;

public class Prompt {

  public static Scanner keyIn = new Scanner(System.in);

  public static String input(String title, Object... args) {
    System.out.print(String.format(title, args));
    return keyIn.nextLine();
  }

  public static void close() {
    keyIn.close();
  }
}
