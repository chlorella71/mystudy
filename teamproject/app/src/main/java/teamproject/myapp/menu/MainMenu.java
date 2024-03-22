package teamproject.myapp.menu;

import teamproject.util.AnsiEscape;
import teamproject.util.Prompt;

public class MainMenu {

  static final String APP_TITLE = AnsiEscape.ANSI_BOLD_RED + "[여행계획관리 시스템]" + AnsiEscape.ANSI_CLEAR;
  static final String[] MENUS = {
      "1. 게시판",
      "2. 회원",
      "3. 도움말",
      AnsiEscape.ANSI_RED + "0. 종료" + AnsiEscape.ANSI_CLEAR
  };

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

    BoardMenu boardMenu = new BoardMenu("게시판", this.prompt);
    MemberMenu memberMenu = new MemberMenu("회원", this.prompt);

    printMenu();

    while (true) {
      String input = this.prompt.input("메인>");

      switch (input) {
        case "1":
          boardMenu.execute();
          break;
        case "2":
          memberMenu.execute();
          break;
        case "0":
          System.out.println("종료합니다.");
          return;
        case "menu":
          printMenu();
          break;
        default:
          System.out.println("메뉴 번호가 옳지 않습니다.");
      }
    }
  }
}
