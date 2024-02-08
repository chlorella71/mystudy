package jeonghuijun.project.handler.customer;

import jeonghuijun.menu.AbstractMenuHandler;
import jeonghuijun.project.dao.CustomerDao;
import jeonghuijun.util.Prompt;

public class CustomerDeleteHandler extends AbstractMenuHandler {

  private CustomerDao customerDao;

  public CustomerDeleteHandler(CustomerDao customerDao, Prompt prompt) {
    super(prompt);
    this.customerDao = customerDao;
  }

  @Override
  protected void action() {
    try {
      int no = this.prompt.inputInt("고객번호? ");

      if (customerDao.delete(no) == -1) {
        System.out.println("존재하지 않는 고객번호입니다.");
      } else {
        System.out.println("고객정보를 삭제했습니다.");
      }
    } catch (Exception e) {
      System.out.println("삭제 오류!");
    }
  }
}