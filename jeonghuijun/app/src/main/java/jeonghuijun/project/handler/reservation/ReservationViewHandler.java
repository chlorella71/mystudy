package jeonghuijun.project.handler.reservation;

import jeonghuijun.menu.AbstractMenuHandler;
import jeonghuijun.project.dao.ReservationDao;
import jeonghuijun.project.vo.Reservation;
import jeonghuijun.util.Prompt;

public class ReservationViewHandler extends AbstractMenuHandler {

  private ReservationDao reservationDao;

  public ReservationViewHandler(ReservationDao reservationDao, Prompt prompt) {
    super(prompt);
    this.reservationDao = reservationDao;
  }

  @Override
  protected void action() {
    int cuPhone = this.prompt.inputInt("전화번호? ");

    Reservation reservation = reservationDao.findBy(cuPhone);
    if (reservation == null) {
      System.out.println("예약 내역이 없습니다.");
      return;
    }

    System.out.printf("전화번호: %d\n", reservation.getCuPhone());
    System.out.printf("고객이름: %s\n", reservation.getCuName());
    System.out.printf("담당디자이너: %s\n", reservation.getDeName());
    System.out.printf("예약날짜: %s\n", reservation.getReDate());
    System.out.printf("예약시간: %s\n", reservation.getReTime());
    System.out.printf("요청시술: %s\n", reservation.getReService());
    System.out.printf("요청사항: %s\n", reservation.getReMatter());
    System.out.printf("예약받은사람: %s\n", reservation.getReDesk());
    System.out.printf("예약등록일시: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", reservation.getReWhen());
  }
}
