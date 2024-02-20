package jeonghuijun.project.handler.reservation;

import jeonghuijun.menu.AbstractMenuHandler;
import jeonghuijun.project.dao.ReservationDao;
import jeonghuijun.project.vo.Reservation;
import jeonghuijun.util.Prompt;
import java.util.Date;

public class ReservationAddHandler extends AbstractMenuHandler {

  private ReservationDao reservationDao;

  public ReservationAddHandler(ReservationDao reservationDao, Prompt prompt) {
    super(prompt);
    this.reservationDao = reservationDao;
  }

  @Override
  protected void action() {
    Reservation reservation = new Reservation();
    reservation.setCuPhone(this.prompt.inputInt("고객전화번호(010제외숫자만)? "));
    reservation.setCuName(this.prompt.input("고객이름? "));
    reservation.setDeName(this.prompt.input("디자이너? "));
    reservation.setReDate(this.prompt.inputDate("예약날짜(예:2024-05-22)? "));
    reservation.setReTime(this.prompt.input("예약시간? "));
    reservation.setReService(this.prompt.input("시술? "));
    reservation.setReMatter(this.prompt.input("요청사항? "));
    reservation.setReDesk(this.prompt.input("예약받은사람? "));
    reservation.setReWhen(new Date());

    reservationDao.add(reservation);
  }
}
