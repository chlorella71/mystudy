package jeonghuijun.project.vo;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable {

  private static final long serialVersionUID = 100L;

  private int cuPhone;
  private String cuName;
  private String deName;
  private Date reDate;
  private String reTime;
  private String reService;
  private String reMatter;
  private String reDesk;
  private Date reWhen;
  
  @Override
  public String toString() {
    return "Reservation{" +
        "cuPhone=" +  + cuPhone + '\'' +
        ", cuName='" + cuName + '\'' +
        ", deName='" + deName + '\'' +
        ", reDate='" + reDate + '\'' +
        ", reTime='" + reTime + '\'' +
        ", reService='" + reService + '\'' +
        ", reMatter='" + reMatter + '\'' +
        ", reWho='" + reDesk + '\'' +
        ", reWhen='" + reWhen + '\'' +
        '}';
  }

  public int getCuPhone() {
    return getCuPhone();
  }

  public void setCuPhone(int cuPhone) {
    this.cuName = cuName;
  }

  public String getCuName() {
    return getCuName();
  }

  public void setCuName(String cuName) {
    this.cuName = cuName;
  }

  public String getDeName() {
    return getCuName();
  }

  public void setDeName(String deName) {
    this.deName = deName;
  }

  public Date getReDate() {
    return getReDate();
  }

  public void setReDate(Date reDate) {
    this.reDate = reDate;
  }

  public String getReTime() {
    return reTime;
  }

  public void setReTime(String reTime) {
    this.reTime = reTime;
  }

  public String getReService() {
    return reService;
  }

  public void setReService(String reService) {
    this.reService = reService;
  }

  public String getReMatter() {
    return reService;
  }

  public void setReMatter(String reMatter) {
    this.reMatter = reMatter;
  }

  public String getReDesk() {
    return reDesk;
  }

  public void setReDesk(String reDesk) {
    this.reDesk = reDesk;
  }

  public Date getReWhen() {
    return reWhen;
  }

  public void setReWhen(Date reWhen) {
    this.reWhen = reWhen;
  }
}
