package jeonghuijun.project.dao.mysql;

import jeonghuijun.project.dao.ReservationDao;
import jeonghuijun.project.dao.DaoException;
import jeonghuijun.project.vo.Reservation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReservationDaoImpl implements ReservationDao {

  Connection con;

  public ReservationDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public void add(Reservation reservation) {
    try {
      Statement stmt = con.createStatement();
      stmt.executeUpdate(String.format(
          "insert into reservations(cu_phone,cu_name,de_name,re_date,re_time,re_service,re_matter,re_desk) values('%d','%s','%s','%s','%s','%s','%s','%s')",
          reservation.getCuPhone(), reservation.getCuName(), reservation.getDeName(), reservation.getReDate(), reservation.getReTime(), reservation.getReService(), reservation.getReMatter(), reservation.getReDesk()));

    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int cuPhone) {
    try {
      Statement stmt = con.createStatement();
      return stmt.executeUpdate(String.format("delete from reservations where cu_phone=%d", cuPhone));

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<Reservation> findAll() {
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from reservations");

      ArrayList<Reservation> list = new ArrayList<>();

      while (rs.next()) {
        Reservation reservation = new Reservation();
        reservation.setCuPhone(rs.getInt("cu_phone"));
        reservation.setCuName(rs.getString("cu_name"));
        reservation.setDeName(rs.getString("de_name"));
        reservation.setReDate(rs.getDate("re_date"));
        reservation.setReTime(rs.getString("re_time"));
        reservation.setReService(rs.getString("re_service"));
        reservation.setReMatter(rs.getString("re_matter"));
        reservation.setReDesk(rs.getString("re_desk"));
        reservation.setReWhen(rs.getDate("re_when"));

        list.add(reservation);
      }
      return list;

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public Reservation findBy(int cuPhone) {
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from reservations where cuPhone = " + cuPhone);

      if (rs.next()) {
        Reservation reservation = new Reservation();
        reservation.setCuPhone(rs.getInt("cu_phone"));
        reservation.setCuName(rs.getString("cu_name"));
        reservation.setDeName(rs.getString("de_name"));
        reservation.setReDate(rs.getDate("re_date"));
        reservation.setReTime(rs.getString("re_time"));
        reservation.setReService(rs.getString("re_service"));
        reservation.setReMatter(rs.getString("re_matter"));
        reservation.setReDesk(rs.getString("re_desk"));
        reservation.setReWhen(rs.getDate("re_when"));

        return reservation;
      }
      return null;

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public int update(Reservation reservation) {
    try {
      Statement stmt = con.createStatement();
      return stmt.executeUpdate(String.format(
          "update reservations set de_name='%s', re_date='%s', re_time='%s', re_service='%s', re_matter='%s', re_desk='%s' where cu_phone=%d",
          reservation.getDeName(), reservation.getReDate(), reservation.getReTime(), reservation.getReService(), reservation.getReMatter(), reservation.getReDesk()));

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
