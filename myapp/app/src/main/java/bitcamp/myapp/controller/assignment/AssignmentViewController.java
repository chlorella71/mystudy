package bitcamp.myapp.controller.assignment;

import bitcamp.myapp.controller.PageController;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AssignmentViewController implements PageController {

  private AssignmentDao assignmentDao;

  public AssignmentViewController(AssignmentDao assignmentDao) {
    this.assignmentDao = assignmentDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));
    Assignment assignment = assignmentDao.findBy(no);
    if (assignment == null) {
      throw new Exception("과제 번호가 유효하지 않습니다.");
    }
      request.setAttribute("assignment", assignment);
      return "/assignment/view.jsp";
  }
}
