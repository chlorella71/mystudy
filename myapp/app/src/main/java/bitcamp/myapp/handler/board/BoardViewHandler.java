package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.Prompt;
import java.sql.Connection;
import java.util.List;

public class BoardViewHandler extends AbstractMenuHandler {

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

  public BoardViewHandler(BoardDao boardDao, AttachedFileDao attachedFileDao) {
    this.boardDao = boardDao;
    this.attachedFileDao = attachedFileDao;
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
  protected void action(Prompt prompt) {
//    Connection con = null;
    try {
//      con = connectionPool.getConnection();
    int no = prompt.inputInt("번호? ");

    Board board = boardDao.findBy(no);
    if (board == null) {
      prompt.println("게시글 번호가 유효하지 않습니다.");
      return;
    }

      List<AttachedFile> files = attachedFileDao.findAllByBoardNo(no);

    prompt.printf("번호: %d\n", board.getNo());
    prompt.printf("제목: %s\n", board.getTitle());
    prompt.printf("내용: %s\n", board.getContent());
    prompt.printf("작성자: %s\n", board.getWriter().getName());
    prompt.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", board.getCreatedDate());
    prompt.println("첨부파일:");

    for (AttachedFile file : files) {
      prompt.printf(" %s\n", file.getFilePath());
    }


  } catch (Exception e) {
      prompt.println("조회 오류!");

//    } finally {
//      connectionPool.returnConnection(con);
    }
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