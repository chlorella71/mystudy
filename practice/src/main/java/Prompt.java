import java.util.Scanner;

public class Prompt {

  static Scanner keyIn = new Scanner(System.in);

  static String input(String title) {
    System.out.println(" 번호를 입력하세요");
    System.out.printf(" %s>", title);
    return keyIn.nextLine();
  }

  static void close() {
    keyIn.close();
  }
}
