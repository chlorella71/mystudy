package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Date;

public class Board implements Serializable {

  static final long serialVersionUID = 200L;

  public Date createdDate;
  private String title;
  private String content;
  private String writer;

  // 팩토리 메서드
  public static Board createFromCsv(String csv) {
    String[] values = csv.split(","); // "aaa,aaa,2023-1-1" ==> {"aaa","aaaa","2023-01-01"}
    Board obj = new Board();
    obj.setTitle(values[0]);
    obj.setContent(values[1]);
    obj.setWriter(values[2]);
    obj.setCreatedDate(new Date(Long.valueOf(values[3])));
    return obj;
  }

  @Override
  public String toString() {
    return "Board{" +
      "createdDate=" + createdDate +
      ", title='" + title + '\'' +
      ", content='" + content + '\'' +
      ", writer='" + writer + '\'' +
      '}';
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getWriter() {
    return writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
}