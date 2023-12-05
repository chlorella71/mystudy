import java.util.Scanner;

public class AssignmentMenu {

  static void printAssignment() {
    Scanner keyIn = new Scanner(System.in);

    while (true) {
      String input = Prompt.input("메인/과제");

      switch (input) {
        case "1":
          System.out.println("등록입니다.");
          break;
        case "2":
          System.out.println("조회입니다.");
          break;
        case "3":
          System.out.println("수정입니다.");
          break;
        case "4":
          System.out.println("삭제입니다.");
          break;
        case "0":
          MainMenu.printMenu();
          return;
        case "menu":
          MainMenu.printMenu2();
          break;
        default:
          System.out.println("메뉴 번호가 옳지 않습니다.");
      }

    }
  }

}
