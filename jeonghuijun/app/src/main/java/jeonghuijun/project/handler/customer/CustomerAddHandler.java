package jeonghuijun.project.handler.customer;

import java.util.Date;
import jeonghuijun.menu.AbstractMenuHandler;
import jeonghuijun.project.dao.CustomerDao;
import jeonghuijun.project.vo.Customer;
import jeonghuijun.util.Prompt;

public class CustomerAddHandler extends AbstractMenuHandler {

  private CustomerDao customerDao;

  public CustomerAddHandler(CustomerDao customerDao, Prompt prompt) {
    super(prompt);
    this.customerDao = customerDao;
  }

  @Override
  protected void action() {
    try {
      Customer customer = new Customer();
      customer.setName(this.prompt.input("이름? "));
      customer.setNumber(this.prompt.input("전화번호?(예: 010-1234-5678) "));
      customer.setSex(this.prompt.input("성별?(남/여) "));
      customer.setfVisit(new Date());
//      customer.setlVisit(this.prompt.inputDate("최근 방문일?(예: 2024-05-22) "));
//      customer.setlService(this.prompt.input("최근시술? "));
      customer.setMemo(this.prompt.input("기록사항? "));

      customerDao.add(customer);

    } catch (Exception e) {
      System.out.println("고객 등록 중 오류 발생!");
      System.out.println("다시 시도하시기 바랍니다.");
    }
  }
}