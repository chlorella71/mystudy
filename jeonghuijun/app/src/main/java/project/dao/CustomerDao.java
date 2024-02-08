package jeonghuijun.project.dao;

import jeonghuijun.project.vo.Customer;
import java.util.List;

public interface CustomerDao {

  void add(Customer customer);

  int delete(int no);

  List<Customer> findAll();

  Customer findBy(int no);

  int update(Customer customer);

}
