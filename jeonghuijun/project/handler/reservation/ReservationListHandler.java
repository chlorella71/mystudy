package jeonghuijun.project.handler.reservation;

import jeonghuijun.menu.AbstractMenuHandler;
import jeonghuijun.project.dao.ReservationDao;
import jeonghuijun.project.vo.Reservation;
import jeonghuijun.util.Prompt;
import java.util.List;

public class ReservationListHandler extends AbstractMenuHandler {

  private ReservationDao reservationDao;

  public ReservationListHandler(ReservationDao reservationDao, Prompt prompt) {
    super(prompt);
    this.reservationDao = reservationDao;
  }

  @Override
  protected void action() {
    System.out.printf("%s\t%s\t%s\t%s\t%d\n", "예약일", "예약시간", "지정디자이너", "희망시술", "고객번호");

    List<Reservation> list = reservationDao.findAll();

    for (Reservation reservation : list) {
      System.out.printf("%s\t%s\t%s\t%s\t%d\n",
          reservation.getReDate(),
          reservation.getReTime(),
          reservation.getDeName(),
          reservation.getReService(),
          reservation.getCuPhone());
    }
  }
}
