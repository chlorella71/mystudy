package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;
import java.util.List;

public class AssignmentListHandler extends AbstractMenuHandler {

  private AssignmentDao assignmentDao;

  public AssignmentListHandler(AssignmentDao assignmentDao, Prompt prompt) {
    super(prompt);
    this.assignmentDao = assignmentDao;
  }

  @Override
  protected void action() {
    System.out.printf("%-4s\t%-20s\t%s\n", "번호", "과제", "제출마감일");

    List<Assignment> list = assignmentDao.findAll();

    for (Assignment assignment : list) {
      System.out.printf("%-4d\t%-20s\t%s\n",
        assignment.getNo(),
        assignment.getTitle(),
        assignment.getDeadline());
    }
  }
}