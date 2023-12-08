package bitcamp.myapp.menu;

import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardMenu {

  Prompt prompt;
  String title;
  Board[] boards = new Board[3];
  int length = 0;

  //BoardMenu 인스턴스를 생성할 때 반드시 게시판 제목을 설정하도록 강요한다.
  //생성자란(constructor)?
  //=> 인스턴스를 사용하기 전에 유효한 상태로 설정하는 작업을 수행하는 메소드
  public BoardMenu(String title, Prompt prompt) {
    this.title = title;
    this.prompt = prompt;
  }

  void printMenu() {
    System.out.printf("[%s]\n", this.title);
    System.out.println("1. 등록");
    System.out.println("2. 조회");
    System.out.println("3. 변경");
    System.out.println("4. 삭제");
    System.out.println("5. 목록");
    System.out.println("0. 이전");
  }

  void execute() { //excute가 받는 인스턴스를 그대로 넘겨준다?
    this.printMenu(); //this를 생략할 수 있다?
    while (true) {
      String input = this.prompt.input("메인/%s> ", this.title);

      switch (input) {
        case "1":
          this.add();
          break;
        case "2":
          this.view();
          break;
        case "3":
          this.modify();
          break;
        case "4":
          this.delete();
          break;
        case "5":
          this.list();
          break;
        case "0":
          return;
        case "menu":
          printMenu(); //같은 클래스 안에 있으므로 BoardMenu.printMenu();로 할 필요 없다.
          break;
        default:
          System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }

  void add() {
    System.out.println("게시글 등록:");

    if (this.length == this.boards.length) {
      //System.out.println("게시글을 더이상 등록할 수 없습니다.");
      int oldSize = boards.length;
      //int newSize = oldSize + (oldSize / 2);
      int newSize = oldSize + (oldSize >> 1);//'>>1'비트이동연산자, '/2'를 의미한다.

      Board[] arr = new Board[newSize];
      for (int i = 0; i < oldSize; i++) {
        arr[i] = this.boards[i];
      }

      this.boards = arr;
    }

    Board board = new Board();
    board.title = this.prompt.input("제목? ");
    board.content = this.prompt.input("내용? ");
    board.writer = this.prompt.input("작성자? ");
    board.createdDate = this.prompt.input("작성일? ");

    //boards[length] = board;
    //length++;
    this.boards[this.length++] = board;
  }

  void view() {
    System.out.println("게시글 조회:");

    int index = Integer.parseInt(this.prompt.input("번호? "));
    if (index < 0 || index >= this.length) {
      System.out.println("게시글 번호가 유효하지 않습니다.");
      return;
    }

    Board board = this.boards[index];
    System.out.printf("제목: %s\n", board.title);
    System.out.printf("내용: %s\n", board.content);
    System.out.printf("작성자: %s\n", board.writer);
    System.out.printf("작성일: %s\n", board.createdDate);
  }

  void modify() {
    System.out.println("게시글 변경:");

    int index = Integer.parseInt(this.prompt.input("번호? "));
    if (index < 0 || index >= this.length) {
      System.out.println("과제 번호가 유효하지 않습니다.");
      return;
    }

    Board board = this.boards[index];
    board.title = this.prompt.input("제목(%s)? ", board.title);
    board.content = this.prompt.input("내용(%s)? ", board.content);
    board.writer = this.prompt.input("작성자(%s)? ", board.writer);
    board.createdDate = this.prompt.input("작성일(%s)? ", board.createdDate);
  }

  void delete() {
    System.out.println("게시글 삭제:");

    int index = Integer.parseInt(this.prompt.input("번호? "));
    if (index < 0 || index >= this.length) {
      System.out.println("게시글 번호가 유효하지 않습니다.");
      return;
    }

    for (int i = index; i < (this.length - 1); i++) {
      this.boards[i] = this.boards[i + 1];
    }
    // length--;
    //boards[length] = null;
    this.boards[--this.length] = null; //전위감소연산자를 이용하여 합침?
  }

  void list() { // non-static method == instance method (논 스태틱 메서드 == 인스턴스 메서드)
    System.out.println("게시글 목록:");
    System.out.printf("%-20s\t%10s\t%s\n", "제목", "작성자", "게시일");

    for (int i = 0; i < this.length; i++) {
      Board board = this.boards[i];
      System.out.printf("%-20s\t%10s\t%s\n", board.title, board.writer, board.createdDate);
    }

  }
}


