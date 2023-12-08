package bitcamp.myapp;

public class AssignmentMenu {
  
  static AssignmentData d1 = new AssignmentData("", "", "");
  
  static void printMenu() {
    System.out.println("[게시글]");
    System.out.println("1. 등록");
    System.out.println("2. 조회");
    System.out.println("3. 변경");
    System.out.println("4. 삭제");
    System.out.println("0. 이전");
  }
  
  static void execute() {
    
    while (true) {
      printMenu();
      String input = Prompt.input("메인/과제> ");
      
      switch (input) {
        case "1":
          add();
          break;
        case "2":
          view();
          break;
        case "3":
          modify();
          break;
        case "4":
          delete();
          break;
        case "0":
          return;
        case "menu":
          break;
        default:
          System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }
  
  static void add() {
    System.out.println("과제 등록:");
    d1 = new AssignmentData(
        Prompt.input("과제명? "),
        Prompt.input("내용? "),
        Prompt.input("제출마감일? ")
    );
    
  }
  
  static void view() {
    System.out.println("과제 조회");
    System.out.printf("과제명: %s\n", d1.title);
    System.out.printf("내용: %s\n", d1.content);
    System.out.printf("제출마감일: %s\n", d1.deadline);
  }
  
  static void modify() {
    System.out.println("과제 변경");
    d1.title = Prompt.input("과제명(%s)? ", d1.title);
    d1.content = Prompt.input("내용(%s)? ", d1.content);
    d1.deadline = Prompt.input("제출마감일(%s)? ", d1.deadline);
  }
  
  static void delete() {
    System.out.println("과제 삭제");
    d1.title = "";
    d1.content = "";
    d1.deadline = "";
  }
}
