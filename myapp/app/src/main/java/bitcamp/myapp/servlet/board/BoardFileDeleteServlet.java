package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.mysql.AttachedFileDaoImpl;
import bitcamp.myapp.dao.mysql.BoardDaoImpl;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.TransactionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/file/delete")
public class BoardFileDeleteServlet extends HttpServlet {

//  private DBConnectionPool connectionPool;
  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;

//  public BoardViewHandler(BoardDao boardDao, Prompt prompt) {
//    super(prompt);
//    this.boardDao = boardDao;
//  }

//  public BoardViewHandler(DBConnectionPool connectionPool, BoardDao boardDao) {
//    this.connectionPool = connectionPool;
//    this.boardDao = boardDao;
//  }

//  public BoardFileDeleteServlet() {
//    DBConnectionPool connectionPool = new DBConnectionPool(
//        "jdbc:mysql://localhost/studydb", "study", "1111"
////          "jdbc:mysql://db-ld29t-kr.vpc-pub-cdb.ntruss.com/studydb", "study", "Bitcamp!@#123"
//    );
//    this.boardDao = new BoardDaoImpl(connectionPool);
//    this.attachedFileDao = new AttachedFileDaoImpl(connectionPool);
//
////    this.boardDao = boardDao;
////    this.attachedFileDao = attachedFileDao;
//  }

  @Override
  public void init() throws ServletException {
    this.boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    this.attachedFileDao = (AttachedFileDao) this.getServletContext().getAttribute("attachedFileDao");
  }

//  @Override
//  protected void action() {
//    int no = this.prompt.inputInt("번호? ");
//
//    Board board = boardDao.findBy(no);
//    if (board == null) {
//      System.out.println("게시글 번호가 유효하지 않습니다.");
//      return;
//    }
//
//    System.out.printf("번호: %d\n", board.getNo());
//    System.out.printf("제목: %s\n", board.getTitle());
//    System.out.printf("내용: %s\n", board.getContent());
//    System.out.printf("작성자: %s\n", board.getWriter());
//    System.out.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", board.getCreatedDate());
//  }


  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int category = Integer.valueOf(request.getParameter("category"));
    String title = category == 1 ? "게시글" : "가입인사";

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
    out.printf("<h1>%s</h1>\n", title);

Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      out.println("<p>로그인하시기 바랍니다!</p>");
      out.println("</body>");
      out.println("</html>");
      return;
    }

//    Connection con = null;
    try {
//      con = connectionPool.getConnection();
    int fileNo = Integer.parseInt(request.getParameter("no"));

    AttachedFile file =attachedFileDao.findByNo(fileNo);
    if (file == null) {
      out.println("<p>첨부파일 번호가 유효하지 않습니다.</p>");
      out.println("</body>");
      out.println("</html>");
      return;
    }

    Member writer = boardDao.findBy(file.getBoardNo()).getWriter();
    if (writer.getNo() != loginUser.getNo()) {
      out.println("<p>권한이 없습니다.</p>");
      out.println("</body>");
      out.println("</html>");
      return;
    }

      attachedFileDao.delete(fileNo);
      out.println("<script>");
//      out.println("history.back();");
      out.println("location.href = document.referrer;");
      out.println("</script>");
//      out.println("<p>첨부파일을 삭제했습니다!</p>");

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