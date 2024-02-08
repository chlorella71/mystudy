package jeonghuijun.project.dao.mysql;

import jeonghuijun.project.dao.DaoException;
import jeonghuijun.project.dao.CustomerDao;
import jeonghuijun.project.vo.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

  Connection con;

  public CustomerDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public void add(Customer customer) {
    try {
      Statement stmt = con.createStatement();
      stmt.executeUpdate(String.format(
          "insert into customers(name,number,sex,memo) values('%s','%s','%s','%s')",
          customer.getName(), customer.getNumber(), customer.getSex(), customer.getMemo()));

    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    try {
      Statement stmt = con.createStatement();
      return stmt.executeUpdate(String.format("delete from customers where c_no=%d", no));

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<Customer> findAll() {
    try {
     Statement stmt = con.createStatement();
     ResultSet rs = stmt.executeQuery("select * from customers");

    ArrayList<Customer> list = new ArrayList<>();

    while (rs.next()) {
      Customer customer = new Customer();
      customer.setName(rs.getString("name"));
      customer.setNumber(rs.getString("number"));
      customer.setSex(rs.getString("sex"));

      list.add(customer);
    }
    return list;

    } catch (Exception e) {
      throw new DaoException("데이처 가져오기 오류", e);
    }
  }

  @Override
  public Customer findBy(int no) {
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from customers where c_no = " + no);

      ArrayList<Customer> list = new ArrayList<>();

      if (rs.next()) {
        Customer customer = new Customer();
        customer.setName(rs.getString("name"));
        customer.setNumber(rs.getString("phone"));
        customer.setSex(rs.getString("sex"));
        customer.setfVisit(rs.getDate("f_visit"));
        customer.setlVisit(rs.getDate("l_visit"));
        customer.setlService(rs.getString("l_service"));
        customer.setlDeisgner(rs.getString("l_designer"));
        customer.setMemo(rs.getString("memo"));

        return customer;
      }
      return null;

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public int update(Customer customer) {
    try {
      Statement stmt = con.createStatement();
      return stmt.executeUpdate(String.format(
          "update customers set number='%s', l_visit='%s', l_service='%s', memo='%s' where c_no=%d",
          customer.getNumber(), customer.getlVisit(), customer.getlService(), customer.getMemo(), customer.getcNo()));

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
