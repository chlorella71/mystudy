package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.Prompt;
import java.sql.Connection;

public class BoardDeleteHandler extends AbstractMenuHandler {

//  private DBConnectionPool connectionPool;
  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;

//  public BoardDeleteHandler(BoardDao boardDao, Prompt prompt) {
//    super(prompt);
//    this.boardDao = boardDao;
//  }

//  public BoardDeleteHandler(DBConnectionPool connectionPool, BoardDao boardDao) {
//    this.connectionPool = connectionPool;
//    this.boardDao = boardDao;
//  }

  public BoardDeleteHandler(BoardDao boardDao, AttachedFileDao attachedFileDao) {
    this.boardDao = boardDao;
    this.attachedFileDao = attachedFileDao;
  }

//  @Override
//  protected void action() {
//    int no = this.prompt.inputInt("번호? ");
//    if (boardDao.delete(no) == 0) {
//      System.out.println("게시글 번호가 유효하지 않습니다.");
//    } else {
//      System.out.println("삭제했습니다!");
//    }
//  }

  @Override
  protected void action(Prompt prompt) {

    Member loginUser = (Member) prompt.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      prompt.println("로그인하시기 바랍니다!");
      return;
    }

//    Connection con = null;
    try {
//      con = connectionPool.getConnection();
    int no = prompt.inputInt("번호? ");

      Board board = boardDao.findBy(no);
      if (board == null) {
        prompt.println("게시글 번호가 유효하지 않습니다.");
        return;
      } else if (board.getWriter().getNo() != loginUser.getNo()) {
        prompt.println("게시글 삭제 권한이 없습니다!");
      }

    attachedFileDao.deleteAll(no);
//    if (boardDao.delete(no) == 0) {
//      prompt.println("게시글 번호가 유효하지 않습니다.");
//    } else {
//      prompt.println("삭제했습니다!");
//    }
      boardDao.delete(no);
      prompt.println("삭제했습니다!");

    } catch (Exception e) {
      prompt.println("삭제 오류!");

//    } finally {
//      connectionPool.returnConnection(con);
    }
  }
}