package jeonghuijun.project.dao;

import jeonghuijun.project.vo.Reservation;
import java.util.List;

public interface ReservationDao {

  void add(Reservation reservation);

  int delete(int cuPhone);

  List<Reservation> findAll();

  Reservation findBy(int cuPhone);

  int update(Reservation reservation);

}
