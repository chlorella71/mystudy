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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {

  private TransactionManager txManager;
  //  DBConnectionPool connectionPool;
  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;

//  public BoardAddHandler(BoardDao boardDao, Prompt prompt) {
//    super(prompt);
//    this.boardDao = boardDao;
//  }

//  public BoardAddHandler(DBConnectionPool connectionPool, BoardDao boardDao) {
//    this.connectionPool = connectionPool;
//    this.boardDao = boardDao;
//  }

  public BoardUpdateServlet() {
    DBConnectionPool connectionPool = new DBConnectionPool(
        "jdbc:mysql://localhost/studydb", "study", "1111"
//          "jdbc:mysql://db-ld29t-kr.vpc-pub-cdb.ntruss.com/studydb", "study", "Bitcamp!@#123"
    );
    txManager = new TransactionManager(connectionPool);
    this.boardDao = new BoardDaoImpl(connectionPool, 1);
    this.attachedFileDao = new AttachedFileDaoImpl(connectionPool);

//    this.txManager = txManager;
//    this.boardDao = boardDao;
//    this.attachedFileDao = attachedFileDao;
  }

//  @Override
//  protected void action() {
//    Board board = new Board();
//    board.setTitle(this.prompt.input("제목? "));
//    board.setContent(this.prompt.input("내용? "));
//    board.setWriter(this.prompt.input("작성자? "));
//    board.setCreatedDate(new Date());
//
//    boardDao.add(board);
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
    out.println("<h1>게시글</h1>");

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      out.println("<p>로그인하시기 바랍니다!</p>");
      out.println("</body>");
      out.println("</html>");
      return;
    }
    
    try {
    int no = Integer.parseInt(request.getParameter("no"));

    Board board = boardDao.findBy(no);
    if (board == null) {
      out.println("<p>게시글 번호가 유효하지 않습니다.</p>");
      out.println("</body>");
      out.println("</html>");
      return;
    }
    
    board.setTitle(request.getParameter("title"));
    board.setContent(request.getParameter("content"));

    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

    String[] files = request.getParameterValues("files");
    if (files != null) {
      for (String file : files) {
        if (file.length() == 0) {
          continue;
        }
        attachedFiles.add(new AttachedFile().filePath(file));
      }
    }

//    Connection con = null;

      txManager.startTransaction();
//      con = connectionPool.getConnection();
//      con.setAutoCommit(false);

      boardDao.update(board);

      if (attachedFiles.size() > 0) {
      // 첨부파일 객체에 게시글 번호 저장
      for (AttachedFile attachedFile : attachedFiles) {
        attachedFile.setBoardNo(board.getNo());
      }

      attachedFileDao.addAll(attachedFiles);
      }

      txManager.commit();
//      con.commit();

      out.println("<p>게시글을 변경했습니다.</p>");

    } catch (Exception e) {
      try {
        txManager.rollback();
//        con.rollback();
      } catch (Exception e2) {
      }
      out.println("<p>게시글 등록 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}