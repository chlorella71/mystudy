package bitcamp.myapp.servlet.member;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.mysql.MemberDaoImpl;
import bitcamp.myapp.vo.Member;
import bitcamp.util.DBConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/view")
public class MemberViewServlet extends HttpServlet {

//  private DBConnectionPool connectionPool;
  private MemberDao memberDao;

//  public MemberViewHandler(MemberDao memberDao, Prompt prompt) {
//    super(prompt);
//    this.memberDao = memberDao;
//  }

//  public MemberViewHandler(DBConnectionPool connectionPool, MemberDao memberDao) {
//    this.connectionPool = connectionPool;
//    this.memberDao = memberDao;
//  }

//  public MemberViewServlet() {
//    DBConnectionPool connectionPool = new DBConnectionPool(
//        "jdbc:mysql://localhost/studydb", "study", "1111"
////          "jdbc:mysql://db-ld29t-kr.vpc-pub-cdb.ntruss.com/studydb", "study", "Bitcamp!@#123"
//    );
//    this.memberDao = new MemberDaoImpl(connectionPool);
//
//
////    this.memberDao = memberDao;
////    this.attachedFileDao = attachedFileDao;
//  }

  public void init() throws ServletException {
    this.memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
  }

//  @Override
//  protected void action() {
//    int no = this.prompt.inputInt("번호? ");
//
//    Member member = memberDao.findBy(no);
//    if (member == null) {
//      System.out.println("회원 번호가 유효하지 않습니다.");
//      return;
//    }
//
//    System.out.printf("번호: %d\n", member.getNo());
//    System.out.printf("제목: %s\n", member.getTitle());
//    System.out.printf("내용: %s\n", member.getContent());
//    System.out.printf("작성자: %s\n", member.getWriter());
//    System.out.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", member.getCreatedDate());
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

    request.getRequestDispatcher("/header").include(request, response);

    out.println("<h1>회원</h1>");


//    Connection con = null;
    try {
//      con = connectionPool.getConnection();
    int no = Integer.parseInt(request.getParameter("no"));

    Member member = memberDao.findBy(no);
    if (member == null) {
      out.println("<p>회원 번호가 유효하지 않습니다.</p>");

      request.getRequestDispatcher("/footer").include(request, response);


      out.println("</body>");
      out.println("</html>");
      return;
    }


      out.println("<form action='/member/update' method='post'>");
      out.println("<div>");
      out.printf("번호: <input readonly name='no' type='text' value='%s'>\n", member.getNo());
      out.println("</div>");
      out.println("<div>");
      out.printf("이메일: <input name='email' type='text' value='%s'>\n", member.getEmail());
      out.println("</div>");
      out.println("<div>");
      out.printf("이름: <input name='name' type='text' value='%s'>\n", member.getName());
      out.println("</div>");
      out.println("<div>");
      out.printf("암호: <input name='password' type='password'>");
      out.println("</div>");
      out.println("<div?");
      out.printf("가입일: <input readonly type='text' value='%s'>\n", member.getCreatedDate());
      out.println("<div>");
      out.println("<button>변경</button>");
      out.printf("<a href='/member/delete?no=%d'>[삭제]</a>", no);
      out.println("</div>");
      out.println("</form>");

  } catch (Exception e) {
      out.println("<p>조회 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }

    request.getRequestDispatcher("/footer").include(request, response);

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