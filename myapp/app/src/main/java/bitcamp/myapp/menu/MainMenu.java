package bitcamp.myapp.menu;

import bitcamp.util.AnsiEscape;
import bitcamp.util.Prompt;

public class MainMenu {

  static final String APP_TITLE = AnsiEscape.ANSI_BOLD_RED + "[과제관리 시스템]" + AnsiEscape.ANSI_CLEAR;
  static final String[] MENUS = {
      "1. 과제",
      "2. 게시글",
      "3. 회원",
      "4. 가입인사",
      "5. 도움말",
      AnsiEscape.ANSI_RED + "0. 종료" + AnsiEscape.ANSI_CLEAR
  };

  //의존 객체(Dependency Object ==> dependency);
  // - 클래스가 작업을 수행할 때 사용하는 객체
  Prompt prompt;

  public MainMenu(Prompt prompt) {
    this.prompt = prompt;
  }

  static void printMenu() {
    System.out.println(APP_TITLE);
    System.out.println();
    for (String menu : MENUS) {
      System.out.println(menu);
    }
  }

  public void execute() {

    BoardMenu boardMenu = new BoardMenu("게시판", this.prompt); // non static 변수를 heap이라는 메모리에 만든다.
    //boardMenu.title = "게시판"; => 생성자 사용
    BoardMenu greetingMenu = new BoardMenu("가입인사", this.prompt); //따로 선언하였기 떄문에 위의 배열과 다른 것임.
    AssignmentMenu assignmentMenu = new AssignmentMenu("과제", this.prompt);
    MemberMenu memberMenu = new MemberMenu("회원", this.prompt);
    printMenu();

    while (true) {
      String input = prompt.input("메인> ");

      switch (input) {
        case "1":
          assignmentMenu.execute();
          break;
        case "2":
          boardMenu.execute();
          break;
        case "3":
          memberMenu.execute();
          break;
        case "4":
          greetingMenu.execute();
          break;
        case "5":
          System.out.println("도움말입니다.");
          break;
        case "0":
          System.out.println("종료합니다.");
          return;
        case "menu":
          // 코드를 기능 단위로 묶어 메서드로 정의하면
          // 메서드의 이름을 통해 해당 기능을 쉽게 유추할 수 있어 유지보수에 좋다.
          printMenu();
          break;
        default:
          System.out.println("메뉴 번호가 옳지 않습니다.");
      }
    }
  }
}
