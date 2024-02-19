package bitcamp.myapp.servlet.assignment;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.mysql.AttachedFileDaoImpl;
import bitcamp.myapp.dao.mysql.AssignmentDaoImpl;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Assignment;
import bitcamp.myapp.vo.Member;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.TransactionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/assignment/update")
public class AssignmentUpdateServlet extends HttpServlet {

  private TransactionManager txManager;
  //  DBConnectionPool connectionPool;
  private AssignmentDao assignmentDao;
  private AttachedFileDao attachedFileDao;

//  public AssignmentAddHandler(AssignmentDao assignmentDao, Prompt prompt) {
//    super(prompt);
//    this.assignmentDao = assignmentDao;
//  }

//  public AssignmentAddHandler(DBConnectionPool connectionPool, AssignmentDao assignmentDao) {
//    this.connectionPool = connectionPool;
//    this.assignmentDao = assignmentDao;
//  }

//  public AssignmentUpdateServlet() {
//    DBConnectionPool connectionPool = new DBConnectionPool(
//        "jdbc:mysql://localhost/studydb", "study", "1111"
////          "jdbc:mysql://db-ld29t-kr.vpc-pub-cdb.ntruss.com/studydb", "study", "Bitcamp!@#123"
//    );
//    txManager = new TransactionManager(connectionPool);
//    this.assignmentDao = new AssignmentDaoImpl(connectionPool);
//    this.attachedFileDao = new AttachedFileDaoImpl(connectionPool);
//
////    this.txManager = txManager;
////    this.assignmentDao = assignmentDao;
////    this.attachedFileDao = attachedFileDao;
//  }

  public void init() throws ServletException {
//    ServletContext 웹애플리케이션저장소 = this.getServletContext();
//    assignmentDao = (AssignmentDao) 웹애플리케이션저장소.getAttribute("assignmentDao");
    assignmentDao = (AssignmentDao) this.getServletContext().getAttribute("assignmentDao");
  }

//  @Override
//  protected void action() {
//    Assignment assignment = new Assignment();
//    assignment.setTitle(this.prompt.input("제목? "));
//    assignment.setContent(this.prompt.input("내용? "));
//    assignment.setWriter(this.prompt.input("작성자? "));
//    assignment.setCreatedDate(new Date());
//
//    assignmentDao.add(assignment);
//  }


  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
//    out.println("<html lang=\"en\">");
    out.println("<html lang='en'>");
    out.println("<head>");
//    out.println("<meta charset=\"UTF-8\">");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>비트캠프 데브옵스 5기</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>과제</h1>");

    try {
    int no = Integer.parseInt(request.getParameter("no"));

    Assignment old = assignmentDao.findBy(no);
    if (old == null) {
      out.println("<p>과제 번호가 유효하지 않습니다.</p>");
      out.println("</body>");
      out.println("</html>");
      return;
    }

    Assignment assignment = new Assignment();
    assignment.setNo(old.getNo());
    assignment.setTitle(request.getParameter("title"));
    assignment.setContent(request.getParameter("content"));
    assignment.setDeadline(Date.valueOf(request.getParameter("deadline")));

      assignmentDao.update(assignment);
      out.println("<p>과제을 변경했습니다.</p>");

    } catch (Exception e) {
      out.println("<p>과제 변경 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}