package bitcamp.myapp.servlet.assignment;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.mysql.AssignmentDaoImpl;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.DBConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/assignment/list")
public class AssignmentListServlet extends HttpServlet {

//  private DBConnectionPool connectionPool;
  private AssignmentDao assignmentDao;

//  public AssignmentListHandler(AssignmentDao assignmentDao, Prompt prompt) {
//    super(prompt);
//    this.assignmentDao = assignmentDao;
//  }

//  public AssignmentListHandler(DBConnectionPool connectionPool, AssignmentDao assignmentDao) {
//    this.connectionPool = connectionPool;
//    this.assignmentDao = assignmentDao;
//  }

//  public AssignmentListServlet() {
//    DBConnectionPool connectionPool = new DBConnectionPool(
//        "jdbc:mysql://localhost/studydb", "study", "1111"
////          "jdbc:mysql://db-ld29t-kr.vpc-pub-cdb.ntruss.com/studydb", "study", "Bitcamp!@#123"
//    );
//
//    this.assignmentDao = new AssignmentDaoImpl(connectionPool);
//  }

  public void init() throws ServletException {
//    ServletContext 웹애플리케이션저장소 = this.getServletContext();
//    assignmentDao = (AssignmentDao) 웹애플리케이션저장소.getAttribute("assignmentDao");
    assignmentDao = (AssignmentDao) this.getServletContext().getAttribute("assignmentDao");
  }

//  @Override
//  protected void action() {
//    System.out.printf("%-4s\t%-20s\t%10s\t%s\n", "No", "Title", "Writer", "Date");
//
//    List<Assignment> list = assignmentDao.findAll();
//
//    for (Assignment assignment : list) {
//      System.out.printf("%-4d\t%-20s\t%10s\t%4$tY-%4$tm-%4$td\n",
//        assignment.getNo(),
//        assignment.getTitle(),
//        assignment.getWriter(),
//        assignment.getCreatedDate());
//    }
//  }


  @Override
  public void service(ServletRequest servletRequest, ServletResponse servletResponse)
      throws ServletException, IOException {

//    servletResponse.setContentType("text/plain;charset=UTF-8");
    servletResponse.setContentType("text/html;charset=UTF-8");
    PrintWriter out = servletResponse.getWriter();

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

    out.println("<a href='/assignment/form.html'>새 과제</a>");

    try {
//      con = connectionPool.getConnection();
//      out.printf("%-4s\t%-20s\t%10s\t%s\t%s\n", "No", "Title", "Writer", "Date", "Files");
      out.println("<table border='1'>");
      out.println("<thead>");
      out.println("<tr> <th>번호</th> <th>과제</th> <th>제출마감일</th> </tr>");
      out.println("</thead>");
      out.println("<tbody>");

      List<Assignment> list = assignmentDao.findAll();

      for (Assignment assignment : list) {
        out.printf("<tr> <td>%d</td> <td><a href='/assignment/view?no=%1$d'>%s</a></td> <td>%s</td> </tr>\n",
            assignment.getNo(),
            assignment.getTitle(),
            assignment.getDeadline());
      }

      out.println("</tbody>");
      out.println("</table>");

    } catch (Exception e) {
      out.println("<p>목록 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}