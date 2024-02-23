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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/assignment/add")
public class AssignmentAddServlet extends HttpServlet {

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

  @Override
  public void init() throws ServletException {
//    ServletContext 웹애플리케이션저장소 = this.getServletContext();
//    assignmentDao = (AssignmentDao) 웹애플리케이션저장소.getAttribute("assignmentDao");
    assignmentDao = (AssignmentDao) this.getServletContext().getAttribute("assignmentDao");
  }

//  public AssignmentAddServlet() {
//    DBConnectionPool connectionPool = new DBConnectionPool(
//        "jdbc:mysql://localhost/studydb", "study", "1111"
////          "jdbc:mysql://db-ld29t-kr.vpc-pub-cdb.ntruss.com/studydb", "study", "Bitcamp!@#123"
//    );
//    this.assignmentDao = new AssignmentDaoImpl(connectionPool);
//
////    this.txManager = txManager;
////    this.assignmentDao = assignmentDao;
////    this.attachedFileDao = attachedFileDao;
//  }

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
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html lang='en'>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>비트캠프 데브옵스 5기</title>");
    out.println("</head>");
    out.println("<body>");

    request.getRequestDispatcher("/header").include(request, response);

    out.println("<h1>과제 관리 시스템</h1>");

    out.println("<h2>과제</h2>");

    out.println("<form action='/assignment/add' method='post'>");
    out.println("<div>");
    out.println("과제: <input type='text' name='title'>");
    out.println("</div>");
    out.println("<div>");
    out.println("내용: <textarea name='content'></textarea>");
    out.println("</div>");
    out.println("<div>");
    out.println("제출 마감일: <input name='deadline' type='date'>");
    out.println("</div>");
    out.println("<div>");
    out.println("<button>등록</button>");
    out.println("</div>");

    out.println("</form>");

    request.getRequestDispatcher("/footer").include(request, response);

    out.println("</body>");
    out.println("</html>");

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
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

    request.getRequestDispatcher("/header").include(request, response);

    out.println("<h1>과제</h1>");

    try{
    Assignment assignment = new Assignment();
    assignment.setTitle(request.getParameter("title"));
    assignment.setContent(request.getParameter("content"));
    assignment.setDeadline(Date.valueOf(request.getParameter("deadline")));

    assignmentDao.add(assignment);

//    out.println("<p>과제를 등록했습니다.</p>");
      response.sendRedirect("/assignment/list");
      return;

    } catch (Exception e) {
      out.println("<p>과제 등록 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }

    request.getRequestDispatcher("/footer").include(request, response);

    out.println("</body>");
    out.println("</html>");
  }
}