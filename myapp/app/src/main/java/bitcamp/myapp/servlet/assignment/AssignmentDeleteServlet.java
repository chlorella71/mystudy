package bitcamp.myapp.servlet.assignment;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.mysql.AttachedFileDaoImpl;
import bitcamp.myapp.dao.mysql.AssignmentDaoImpl;
import bitcamp.myapp.vo.Assignment;
import bitcamp.myapp.vo.Member;
import bitcamp.util.DBConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/assignment/delete")
public class AssignmentDeleteServlet extends HttpServlet {

//  private DBConnectionPool connectionPool;
  private AssignmentDao assignmentDao;


//  public AssignmentViewHandler(AssignmentDao assignmentDao, Prompt prompt) {
//    super(prompt);
//    this.assignmentDao = assignmentDao;
//  }

//  public AssignmentViewHandler(DBConnectionPool connectionPool, AssignmentDao assignmentDao) {
//    this.connectionPool = connectionPool;
//    this.assignmentDao = assignmentDao;
//  }

//  public AssignmentDeleteServlet() {
//    DBConnectionPool connectionPool = new DBConnectionPool(
//        "jdbc:mysql://localhost/studydb", "study", "1111"
////          "jdbc:mysql://db-ld29t-kr.vpc-pub-cdb.ntruss.com/studydb", "study", "Bitcamp!@#123"
//    );
//    this.assignmentDao = new AssignmentDaoImpl(connectionPool);
//
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
//    int no = this.prompt.inputInt("번호? ");
//
//    Assignment assignment = assignmentDao.findBy(no);
//    if (assignment == null) {
//      System.out.println("과제 번호가 유효하지 않습니다.");
//      return;
//    }
//
//    System.out.printf("번호: %d\n", assignment.getNo());
//    System.out.printf("제목: %s\n", assignment.getTitle());
//    System.out.printf("내용: %s\n", assignment.getContent());
//    System.out.printf("작성자: %s\n", assignment.getWriter());
//    System.out.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", assignment.getCreatedDate());
//  }


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

//    Connection con = null;
    try {
//      con = connectionPool.getConnection();
      int no = Integer.parseInt(request.getParameter("no"));

      if (assignmentDao.delete(no) == 0) {
        out.println("<p>과제 번호가 유효하지 않습니다.</p>");
        response.setHeader("Refresh", "1;url=list");
      } else {
//        out.println("<p>과제를 삭제했습니다.</p>");
        response.sendRedirect("list");
        return;
      }
  } catch (Exception e) {
      out.println("<p>삭제 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}

/*
[조회]
번호? 7
번호: 7
제목: aaa2
내용: aaa2
작성자: aaa2
작성일: 2024-02-14 00:00:00
첨부파일:
  a1.gif
  a2.gif
  a3.gif
 */