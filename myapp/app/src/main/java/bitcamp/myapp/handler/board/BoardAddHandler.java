package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.TransactionManager;
import java.sql.Connection;
import java.util.ArrayList;
import bitcamp.myapp.vo.Member;

public class BoardAddHandler extends AbstractMenuHandler {

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

  public BoardAddHandler(TransactionManager txManager, BoardDao boardDao, AttachedFileDao attachedFileDao) {
    this.txManager = txManager;
    this.boardDao = boardDao;
    this.attachedFileDao = attachedFileDao;
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

    Member loginUser = (Member) prompt.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      prompt.println("로그인하시기 바랍니다!");
      return;
    }

    Board board = new Board();

    board.setTitle(prompt.input("제목? "));
    board.setContent(prompt.input("내용? "));
    board.setWriter(loginUser);

    ArrayList<AttachedFile> files = new ArrayList<>();
    while (true) {
      String filepath = prompt.input("파일?(종료: 그냥 엔터) ");
      if (filepath.length() == 0) {
        break;
      }
//      AttachedFile file = new AttachedFile().filePath(filepath);
//      file.setFilePath(filepath);
      files.add(new AttachedFile().filePath(filepath));
    }

//    Connection con = null;
    try {
      txManager.startTransaction();
//      con = connectionPool.getConnection();
//      con.setAutoCommit(false);

      boardDao.add(board);

      if (files.size() > 0) {
      // 첨부파일 객체에 게시글 번호 저장
      for (AttachedFile file : files) {
        file.setBoardNo(board.getNo());
      }

      attachedFileDao.addAll(files);
      }
//      boardDao.add(board);
//
//      Thread.sleep(10000);

//      boardDao.add(board);

      txManager.commit();
//      con.commit();

    } catch (Exception e) {
      try {
        txManager.rollback();
//        con.rollback();
      } catch (Exception e2) {
      }
      prompt.println("게시글 등록 오류!");
//    } finally {
//        try {
//          con.setAutoCommit(true);
//        } catch (Exception e) {
//          connectionPool.returnConnection(con);
//        }
    }
  }
}