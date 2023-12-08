package bitcamp.myapp.vo;

import bitcamp.myapp.Prompt;

public class Data {

  public Object title;
  public Object content;
  public Object deadline;

  public Data(Object title, Object content, Object deadline) {
    this.title = title;
    this.content = content;
    this.deadline = deadline;

  }

  public void add() {
    System.out.println("과제 등록:");
    title = Prompt.input("과제명? ");
    content = Prompt.input("내용? ");
    deadline = Prompt.input("제출 마감일? ");

  }

  public void view() {
    System.out.println("과제 조회:");
    System.out.printf("과제명: %s\n", title);
    System.out.printf("내용: %s\n", content);
    System.out.printf("제출 마감일: %s\n", deadline);
  }

  public void modify() {
    System.out.println("과제 변경:");
    title = Prompt.input("과제명(%s)? ", title);
    content = Prompt.input("내용(%s)? ", content);
    deadline = Prompt.input("제출 마감일(%s)? ", deadline);
  }

  public void delete() {
    System.out.println("과제 삭제: ");
    title = "";
    content = "";
    deadline = "";
  }
}

