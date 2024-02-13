package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;
import bitcamp.util.DBConnectionPool;
import java.sql.Connection;

public class BoardAddHandler extends AbstractMenuHandler {

  DBConnectionPool connectionPool;
  private BoardDao boardDao;

//  public BoardAddHandler(BoardDao boardDao, Prompt prompt) {
//    super(prompt);
//    this.boardDao = boardDao;
//  }

  public BoardAddHandler(DBConnectionPool connectionPool, BoardDao boardDao) {
    this.connectionPool = connectionPool;
    this.boardDao = boardDao;
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
  protected void action(Prompt prompt) {

    Board board = new Board();
    board.setTitle(prompt.input("제목? "));
    board.setContent(prompt.input("내용? "));
    board.setWriter(prompt.input("작성자? "));

    Connection con = null;
    try {
      con = connectionPool.getConnection();
      con.setAutoCommit(false);

      boardDao.add(board);
      boardDao.add(board);

      Thread.sleep(10000);

      boardDao.add(board);

      con.commit();

    } catch (Exception e) {
      try {
        con.rollback();
      } catch (Exception e2) {
      }
    } finally {
        try {
          con.setAutoCommit(true);
        } catch (Exception e) {
          connectionPool.returnConnection(con);
        }
      }
    }
  }