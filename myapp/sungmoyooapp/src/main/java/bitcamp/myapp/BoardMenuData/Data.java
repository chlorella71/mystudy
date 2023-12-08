package bitcamp.myapp.BoardMenuData;

import bitcamp.myapp.Prompt;

public class Data {

  String title;
  String content;
  String writer;
  String createdDate;


  public void add() {
    System.out.println("게시글 등록:");
    title = Prompt.input("제목? ");
    content = Prompt.input("내용? ");
    writer = Prompt.input("작성자? ");
    createdDate = Prompt.input("작성일? ");
  }

  public void view() {
    System.out.println("게시글 조회:");
    System.out.printf("제목: %s\n", title);
    System.out.printf("내용: %s\n", content);
    System.out.printf("작성자: %s\n", writer);
    System.out.printf("작성일: %s\n", createdDate);
  }

  public void modify() {
    System.out.println("게시글 변경:");
    title = Prompt.input("제목(%s)? ", title);
    content = Prompt.input("내용(%s)? ", content);
    writer = Prompt.input("작성자(%s)? ", writer);
    createdDate = Prompt.input("작성일(%s)? ", createdDate);
  }

  public void delete() {
    System.out.println("게시글 삭제:");
    title = "";
    content = "";
    writer = "";
    createdDate = "";
  }
}
