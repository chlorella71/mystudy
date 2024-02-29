package bitcamp.myapp.controller.assignment;

import bitcamp.myapp.controller.PageController;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.TransactionManager;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AssignmentUpdateController implements PageController {

  private AssignmentDao assignmentDao;

  public AssignmentUpdateController(AssignmentDao assignmentDao) {
    this.assignmentDao = assignmentDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));
    Assignment old = assignmentDao.findBy(no);
    if (old == null) {
      throw new Exception("과제 번호가 유효하지 않습니다.");
    }

    Assignment assignment = new Assignment();
    assignment.setNo(old.getNo());
    assignment.setTitle(request.getParameter("title"));
    assignment.setContent(request.getParameter("content"));
    assignment.setDeadline(Date.valueOf(request.getParameter("deadline")));

      assignmentDao.update(assignment);

      return "redirect:list";
  }
}