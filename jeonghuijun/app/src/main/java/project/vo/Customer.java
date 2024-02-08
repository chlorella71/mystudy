package jeonghuijun.project.vo;

import java.io.Serializable;
import java.sql.Date;

public class Customer implements Serializable {

  private static final long serialVersionUID = 100L;

  private int cNo;
  private String name;
  private String number;
  private String sex;
  private java.util.Date fVisit;
 
  private Date lVisit;

  private String lService;
  private String lDeisgner;
  private String memo;

  @Override
  public String toString() {
    return "Customer{" +
        "no=" + cNo +
        ", name='" + name + '\'' +
        ", number='" + number + '\'' +
        ", sex='" + sex + '\'' +
        ", fVisit=" + fVisit + '\'' +
        ", lVisit=" + lVisit + '\'' +
        ", lService='" + lService + '\'' +
        ", lService='" + lDeisgner + '\'' +
        ", memo='" + memo + '\'' +
        '}';
  }

  public int getcNo() {
    return cNo;
  }

  public void setcNo(int cNo) {
    this.cNo = cNo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public java.util.Date getfVisit() {
    return fVisit;
  }

  public void setfVisit(java.util.Date fVisit) {
    this.fVisit = fVisit;
  }

  public Date getlVisit() {
    return lVisit;
  }

  public void setlVisit(Date lVisit) {
    this.lVisit = lVisit;
  }

  public String getlService() {
    return lService;
  }

  public void setlService(String lService) {
    this.lService = lService;
  }

  public String getlDeisgner() {
    return lDeisgner;
  }

  public void setlDeisgner(String lDeisgner) {
    this.lDeisgner = lDeisgner;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }
}
