package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.List;
import bitcamp.util.Prompt;

public class AssignmentViewHandler extends AbstractMenuHandler {

  private List<Assignment> objectRepository;


  public AssignmentViewHandler(List<Assignment> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() {

    try {
      int index = this.prompt.inputInt("번호? ");
      Assignment assignment = (Assignment) this.objectRepository.get(index);
      /*if (assignment == null) {
        System.out.println("과제 번호가 유효하지 않습니다.");
        return;
      }*/
      System.out.printf("과제명: %s\n", assignment.getTitle());
      System.out.printf("내용: %s\n", assignment.getContent());
      System.out.printf("제출 마감일: %s\n", assignment.getDeadline());

    } catch (Exception e) {
      System.out.println("조회 오류!");
    }
  }
}