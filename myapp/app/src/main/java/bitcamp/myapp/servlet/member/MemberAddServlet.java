package bitcamp.myapp.servlet.member;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.mysql.MemberDaoImpl;
import bitcamp.myapp.vo.Member;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.TransactionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

  private TransactionManager txManager;
  //  DBConnectionPool connectionPool;
  private MemberDao memberDao;
  private AttachedFileDao attachedFileDao;

//  public MemberAddHandler(MemberDao memberDao, Prompt prompt) {
//    super(prompt);
//    this.memberDao = memberDao;
//  }

//  public MemberAddHandler(DBConnectionPool connectionPool, MemberDao memberDao) {
//    this.connectionPool = connectionPool;
//    this.memberDao = memberDao;
//  }

//  public MemberAddServlet() {
//    DBConnectionPool connectionPool = new DBConnectionPool(
//        "jdbc:mysql://localhost/studydb", "study", "1111"
////          "jdbc:mysql://db-ld29t-kr.vpc-pub-cdb.ntruss.com/studydb", "study", "Bitcamp!@#123"
//    );
//    this.memberDao = new MemberDaoImpl(connectionPool);
//
////    this.txManager = txManager;
////    this.memberDao = memberDao;
////    this.attachedFileDao = attachedFileDao;
//  }

  @Override
  public void init() throws ServletException {
    this.memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
  }

//  @Override
//  protected void action() {
//    Member member = new Member();
//    member.setTitle(this.prompt.input("제목? "));
//    member.setContent(this.prompt.input("내용? "));
//    member.setWriter(this.prompt.input("작성자? "));
//    member.setCreatedDate(new Date());
//
//    memberDao.add(member);
//  }


  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try{
    Member member = new Member();
    member.setEmail(request.getParameter("email"));
    member.setName(request.getParameter("name"));
    member.setPassword(request.getParameter("password"));

    memberDao.add(member);
//    out.println("<p>회원을 등록했습니다.</p>");
      response.sendRedirect("list");

    } catch (Exception e) {
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
      out.println("<h1>회원</h1>");

      out.println("<p>회원 등록 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");

      out.println("</html>");
      out.println("</body>");
    }
  }
}