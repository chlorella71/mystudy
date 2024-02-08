package jeonghuijun.project.handler.reservation;

import jeonghuijun.menu.AbstractMenuHandler;
import jeonghuijun.project.dao.ReservationDao;
import jeonghuijun.util.Prompt;

public class ReservationDeleteHandler extends AbstractMenuHandler {

  private ReservationDao reservationDao;

  public ReservationDeleteHandler(ReservationDao reservationDao, Prompt prompt) {
    super(prompt);
    this.reservationDao = reservationDao;
  }

  @Override
  protected void action() {
    int cuPhone = this.prompt.inputInt("고객전화번호? ");
    if (reservationDao.delete(cuPhone) == 0) {
      System.out.println("전화번호가 유효하지 않습니다.");
    } else {
      System.out.println("삭제했습니다!");
    }
  }
}
