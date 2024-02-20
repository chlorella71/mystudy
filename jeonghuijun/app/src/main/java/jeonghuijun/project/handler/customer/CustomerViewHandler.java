package jeonghuijun.project.handler.customer;

import jeonghuijun.menu.AbstractMenuHandler;
import jeonghuijun.project.dao.CustomerDao;
import jeonghuijun.project.vo.Customer;
import jeonghuijun.util.Prompt;

public class CustomerViewHandler extends AbstractMenuHandler {

  private CustomerDao customerDao;

  public CustomerViewHandler(CustomerDao customerDao, Prompt prompt) {
    super(prompt);
    this.customerDao = customerDao;
  }

  @Override
  protected void action() {
    try {
    int no = this.prompt.inputInt("고객번호? ");

    Customer customer = customerDao.findBy(no);
    if (customer == null) {
      System.out.println("존재하지 않는 고객번호입니다.");
      return;
    }

      System.out.printf("이름: %s\n", customer.getName());
      System.out.printf("전화번호: %s\n", customer.getNumber());
    System.out.printf("성별: %s\n", customer.getSex());
    System.out.printf("첫방문일: %s\n", customer.getfVisit());
    System.out.printf("최근방문일: %s\n", customer.getlVisit());
    System.out.printf("최근시술: %s\n", customer.getlService());
    System.out.printf("최근디자이너: %s\n", customer.getlDeisgner());
    System.out.printf("기록사항: %s\n", customer.getMemo());

  } catch (Exception e) {
      System.out.println("조회 오류!");
    }
    }
}