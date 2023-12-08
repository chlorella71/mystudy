package bitcamp.myapp;

public class BoardMenu {
  
  static BoardData d2 = new BoardData("", "", "", "");
  
  static void printMenu() {
    System.out.println("[게시글]");
    System.out.println("1. 등록");
    System.out.println("2. 조회");
    System.out.println("3. 변경");
    System.out.println("4. 삭제");
    System.out.println("0. 이전");
  }
  
  static void execute() {
    printMenu();
    while (true) {
      String input = Prompt.input("메인/게시글> ");
      
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
          printMenu();
          break;
        default:
          System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }
  
  static void add() {
    System.out.println("게시글 등록:");
    d2 = new BoardData(
        Prompt.input("제목? "),
        Prompt.input("내용? "),
        Prompt.input("작성자? "),
        Prompt.input("작성일? ")
    );
  }
  
  static void view() {
    System.out.println("게시글 조회:");
    System.out.printf("제목: %s\n", d2.title);
    System.out.printf("내용: %s\n", d2.content);
    System.out.printf("작성자: %s\n", d2.writer);
    System.out.printf("작성일: %s\n", d2.createDate);
  }
  
  static void modify() {
    System.out.println("게시글 수정:");
    d2.title = Prompt.input("제목(%s)? ", d2.title);
    d2.content = Prompt.input("내용(%s)? ", d2.content);
    d2.writer = Prompt.input("작성자(%s)? ", d2.writer);
    d2.createDate = Prompt.input("작성일(%s)? ", d2.createDate);
  }
  
  static void delete() {
    System.out.println("게시글 삭제:");
    d2.title = "";
    d2.content = "";
    d2.writer = "";
    d2.createDate = "";
  }
  
}
