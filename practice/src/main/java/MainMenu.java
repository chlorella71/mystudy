public class MainMenu {

  static void printMenu() {
    System.out.println("1. 과제");
    System.out.println("2. 게시글");
    System.out.println("3. 도움말");
    System.out.println("\033[31m0. 종료\033[0m");
  }

  static void printMenu2() {
    System.out.println("1. 등록");
    System.out.println("2. 조회");
    System.out.println("3. 수정");
    System.out.println("4. 삭제");
    System.out.println("\033[31m0. 이전\033[0m");
  }
    static void execute() {
      System.out.println("\033[01;031m[과제관리시스템]\033[0m");
      System.out.println();
      printMenu();

      while (true) {
        String input = Prompt.input("메인");

        switch (input) {
          case "1":
            System.out.println("\033[01;31m[과제]\033[00m");
            printMenu2();
            AssignmentMenu.printAssignment();
            break;
          case "2":
            System.out.println("\033[01;31m[게시글]\033[0m");
            printMenu2();
            BoardMenu.printBoard();
            break;
          case "3":
            System.out.println("도움말입니다.");
            break;
          case "0":
            System.out.println("과제관리시스템을 종료합니다.");
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

