package bitcamp.myapp.controller.board;

import bitcamp.myapp.controller.PageController;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.TransactionManager;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardDeleteController implements PageController {

//  private DBConnectionPool connectionPool;
private TransactionManager txManager;

  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;
  private String uploadDir;

  public BoardDeleteController(TransactionManager txManager, BoardDao boardDao,
      AttachedFileDao attachedFileDao, String uploadDir) {
    this.txManager = txManager;
    this.boardDao = boardDao;
    this.attachedFileDao = attachedFileDao;
    this.uploadDir = uploadDir;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int category = Integer.valueOf(request.getParameter("category"));
    String boardName = category == 1 ? "게시글" : "가입인사";

try {
Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인하시기 바랍니다!");
    }

      int no = Integer.parseInt(request.getParameter("no"));
      Board board = boardDao.findBy(no);
      if (board == null) {
        throw new Exception("번호가 유효하지 않습니다.");

      } else if (board.getWriter().getNo() != loginUser.getNo()) {
        throw new Exception("권한이 없습니다.");
    }
      List<AttachedFile> files = attachedFileDao.findAllByBoardNo(no);

      txManager.startTransaction();
      attachedFileDao.deleteAll(no);
      boardDao.delete(no);
      txManager.commit();

      for (AttachedFile file : files) {
        new File(this.uploadDir + "/" + file.getFilePath()).delete();
      }

      return "redirect:list?category=" + category;
//      response.sendRedirect("/board/list?category=" + category);

//      out.println("<script>");
////      out.println("history.back();");
////      out.println("location.href = '/board/list'");
//      out.printf("location.href = '/board/list?category=%d';\n", category);
//      out.println("</script>");

  } catch (Exception e) {
      try {
        txManager.rollback();
//        con.rollback();
      } catch (Exception e2) {
      }
      throw e;
    }
  }
}