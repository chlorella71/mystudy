package jeonghuijun.project.handler.customer;

import jeonghuijun.menu.AbstractMenuHandler;
import jeonghuijun.project.dao.CustomerDao;
import jeonghuijun.project.vo.Customer;
import jeonghuijun.util.Prompt;
import java.util.List;

public class CustomerListHandler extends AbstractMenuHandler {

  private CustomerDao customerDao;

  public CustomerListHandler(CustomerDao customerDao, Prompt prompt) {
    super(prompt);
    this.customerDao = customerDao;
  }

  @Override
  protected void action() {
    System.out.printf("%-10s\t%30s\t%s\n", "이름", "전화번호", "성별");

    List<Customer> list = customerDao.findAll();

    for (Customer customer : list) {
      System.out.printf("%-10s\t%30s\t%s\n",
          customer.getName(),
          customer.getNumber(),
        customer.getSex());
    }
  }
}