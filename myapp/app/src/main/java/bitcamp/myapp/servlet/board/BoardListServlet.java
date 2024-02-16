package bitcamp.myapp.servlet.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.mysql.BoardDaoImpl;
import bitcamp.myapp.vo.Board;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.Prompt;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/board/list")
public class BoardListServlet extends GenericServlet {

//  private DBConnectionPool connectionPool;
  private BoardDao boardDao;

//  public BoardListHandler(BoardDao boardDao, Prompt prompt) {
//    super(prompt);
//    this.boardDao = boardDao;
//  }

//  public BoardListHandler(DBConnectionPool connectionPool, BoardDao boardDao) {
//    this.connectionPool = connectionPool;
//    this.boardDao = boardDao;
//  }

  public BoardListServlet() {
    DBConnectionPool connectionPool = new DBConnectionPool(
        "jdbc:mysql://localhost/studydb", "study", "1111"
//          "jdbc:mysql://db-ld29t-kr.vpc-pub-cdb.ntruss.com/studydb", "study", "Bitcamp!@#123"
    );

    this.boardDao = new BoardDaoImpl(connectionPool, 1);
  }

//  @Override
//  protected void action() {
//    System.out.printf("%-4s\t%-20s\t%10s\t%s\n", "No", "Title", "Writer", "Date");
//
//    List<Board> list = boardDao.findAll();
//
//    for (Board board : list) {
//      System.out.printf("%-4d\t%-20s\t%10s\t%4$tY-%4$tm-%4$td\n",
//        board.getNo(),
//        board.getTitle(),
//        board.getWriter(),
//        board.getCreatedDate());
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
    out.println("<h1>게시글</h1>");

    out.println("<a href='/board/form.html'>새 글</a>");

    try {
//      con = connectionPool.getConnection();
//      out.printf("%-4s\t%-20s\t%10s\t%s\t%s\n", "No", "Title", "Writer", "Date", "Files");
      out.println("<table border='1'>");
      out.println("<thead>");
      out.println("<tr> <th>번호</th> <th>제목</th> <th>작성자</th> <th>등록일</th> <th>첨부파일</th> </tr>");
      out.println("</thead>");
      out.println("<tbody>");

      List<Board> list = boardDao.findAll();

      for (Board board : list) {
        out.printf("<tr> <td>%d</td> <td><a href='/board/view?no=%1$d'>%s</td> <td>%s</td> <td>%s</td> <td>%d</td> </tr>\n",
            board.getNo(),
            board.getTitle(),
            board.getWriter().getName(),
            board.getCreatedDate(),
            board.getFileCount());
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