package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {

  static final long serialVersionUID = 200L;

  private String email;
  private String name;
  private String password;
  private Date createdDate;

  //팩토리 메서드
  public static Member createFromCsv(String csv) {
    String[] values = csv.split(","); // "aaa,aaa,2023-1-1" ==> {"aaa","aaaa","2023-01-01"}
    Member obj = new Member();
    obj.setEmail(values[0]);
    obj.setName(values[1]);
    obj.setPassword(values[2]);
    obj.setCreatedDate(new Date(Long.valueOf(values[3])));
    return obj;
  }

  //  public void setFromCsv(String csv) {
//    String[] values = csv.split(","); // "aaa,aaa,2023-1-1" ==> {"aaa","aaaa","2023-01-01"}
//    this.email = values[0];
//    this.name= values[1];
//    this.password = values[2];
//    this.createdDate = new Date(Long.valueOf(values[3]));
//    return obj;
//  }

  @Override
  public String toString() {
    return "Member{" +
      "email='" + email + '\'' +
      ", name='" + name + '\'' +
      ", password='" + password + '\'' +
      ", createdDate=" + createdDate +
      '}';
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
}