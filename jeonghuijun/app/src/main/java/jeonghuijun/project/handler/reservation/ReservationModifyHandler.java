package jeonghuijun.project.handler.reservation;

import java.util.Date;
import jeonghuijun.menu.AbstractMenuHandler;
import jeonghuijun.project.dao.ReservationDao;
import jeonghuijun.project.vo.Reservation;
import jeonghuijun.util.Prompt;

public class ReservationModifyHandler extends AbstractMenuHandler {

  private ReservationDao reservationDao;

  public ReservationModifyHandler(ReservationDao reservationDao, Prompt prompt) {
    super(prompt);
    this.reservationDao = reservationDao;
  }

  @Override
  protected void action() {
    int cuPhone = this.prompt.inputInt("고객전화번호? ");

    Reservation old = reservationDao.findBy(cuPhone);
    if (old == null) {
      System.out.println("예약내역이 없습니다.");
      return;
    }

    Reservation reservation = new Reservation();
    reservation.setCuPhone(old.getCuPhone()); // 기존 게시글의 번호를 그대로 설정한다.
    reservation.setCuName(old.getCuName());
    reservation.setDeName(this.prompt.input("디자이너(%s)? ", old.getDeName()));
    reservation.setReDate(this.prompt.inputDate("예약날짜(%s)? ", old.getReDate()));
    reservation.setReTime(this.prompt.input("예약시간(%s)? ", old.getReTime()));
    reservation.setReService(this.prompt.input("시술(%s)? ", old.getReService()));
    reservation.setReMatter(this.prompt.input("요청사항(%s)? ", old.getReMatter()));
    reservation.setReDesk(this.prompt.input("예약받은사람(%s)? ", old.getReDesk()));
    reservation.setReWhen(new Date());

    reservationDao.update(reservation);
    System.out.println("예약을 변경했습니다.");
  }
}
