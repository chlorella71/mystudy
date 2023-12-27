package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;
import java.util.ArrayList;

public class AssignmentListHandler extends AbstractMenuHandler {

  private ArrayList<Assignment> objectRepository;


  public AssignmentListHandler(ArrayList<Assignment> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() {

    System.out.printf("%-20s\t%s\n", "과제", "제출마감일");

    for (Object object : this.objectRepository.toArray()) {
      Assignment assignment = (Assignment) object;
      System.out.printf("%-20s\t%s\n", assignment.getTitle(),
        assignment.getDeadline());
    }
  }
}