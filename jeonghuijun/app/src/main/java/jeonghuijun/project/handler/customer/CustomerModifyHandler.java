package jeonghuijun.project.handler.customer;

import jeonghuijun.menu.AbstractMenuHandler;
import jeonghuijun.project.dao.CustomerDao;
import jeonghuijun.project.vo.Customer;
import jeonghuijun.util.Prompt;


public class CustomerModifyHandler extends AbstractMenuHandler {

  private CustomerDao customerDao;

  public CustomerModifyHandler(CustomerDao customerDao, Prompt prompt) {
    super(prompt);
    this.customerDao = customerDao;
  }

  @Override
  protected void action() {
    try {
    int no = this.prompt.inputInt("고객번호? ");

    Customer old = customerDao.findBy(no);
    if (old == null) {
      System.out.println("존재하지 않는 고객번호입니다.");
      return;
    }
      System.out.println("이름: " + old.getName());
      System.out.println("전화번호: "+ old.getNumber());
      System.out.println("성별: " + old.getSex());

      Customer customer = new Customer();
      customer.setcNo(old.getcNo());
      customer.setName(old.getName());
      customer.setNumber(this.prompt.input("전화번호(%s)? " , old.getNumber()));
      customer.setSex(old.getSex());
      customer.setfVisit(old.getfVisit());
    customer.setlVisit(this.prompt.inputDate("방문일(%s)? ", old.getlVisit()));
    customer.setlService(this.prompt.input("시술내역(%s)? ", old.getlService()));
    customer.setlDeisgner(this.prompt.input("시술디자이너(%s)? ", old.getlDeisgner()));
    customer.setMemo(this.prompt.input("기록사항(%s)? ", old.getMemo()));

    customerDao.update(customer);
    System.out.println("고객정보를 변경했습니다.");

  } catch (NumberFormatException e) {
      System.out.println("숫자만 입력하세요!");

    } catch (IllegalArgumentException e) {
      System.out.println("정보 변경 오류!");
      System.out.println("다시 시도 하세요.");

    } catch (Exception e) {
      System.out.println("실행 오류!");
      e.printStackTrace();
    }
  }
}