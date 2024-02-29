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

public class AssignmentAddController implements PageController {


  private AssignmentDao assignmentDao;

  public AssignmentAddController(AssignmentDao assignmentDao) {
    this.assignmentDao = assignmentDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      return "/member/form.jsp";
    }

    Assignment assignment = new Assignment();
    assignment.setTitle(request.getParameter("title"));
    assignment.setContent(request.getParameter("content"));
    assignment.setDeadline(Date.valueOf(request.getParameter("deadline")));

    assignmentDao.add(assignment);

    return "redirect:list";
  }
}