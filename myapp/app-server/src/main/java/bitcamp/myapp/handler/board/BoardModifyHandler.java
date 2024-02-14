package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.Prompt;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BoardModifyHandler extends AbstractMenuHandler {

//  DBConnectionPool connectionPool;
  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;

//  public BoardModifyHandler(BoardDao boardDao, Prompt prompt) {
//    super(prompt);
//    this.boardDao = boardDao;
//  }

//  public BoardModifyHandler(DBConnectionPool connectionPool, BoardDao boardDao) {
//    this.connectionPool = connectionPool;
//    this.boardDao = boardDao;
//  }

  public BoardModifyHandler(BoardDao boardDao, AttachedFileDao attachedFileDao) {
    this.boardDao = boardDao;
    this.attachedFileDao = attachedFileDao;
  }

//  @Override
//  protected void action() {
//    int no = this.prompt.inputInt("번호? ");
//
//    Board oldBoard = boardDao.findBy(no);
//    if (oldBoard == null) {
//      System.out.println("게시글 번호가 유효하지 않습니다.");
//      return;
//    }
//
//    Board board = new Board();
//    board.setNo(oldBoard.getNo()); // 기존 게시글의 번호를 그대로 설정한다.
//    board.setTitle(this.prompt.input("제목(%s)? ", oldBoard.getTitle()));
//    board.setContent(this.prompt.input("내용(%s)? ", oldBoard.getContent()));
//    board.setWriter(this.prompt.input("작성자(%s)? ", oldBoard.getWriter()));
//    board.setCreatedDate(oldBoard.getCreatedDate());
//
//    boardDao.update(board);
//    System.out.println("게시글을 변경했습니다.");
//  }

  @Override
  protected void action(Prompt prompt) {
//    Connection con = null;
    try {
//      con = connectionPool.getConnection();
      int no = prompt.inputInt("번호? ");

      Board oldBoard = boardDao.findBy(no);
      if (oldBoard == null) {
        prompt.println("게시글 번호가 유효하지 않습니다.");
        return;
      }

      Board board = new Board();
      board.setNo(oldBoard.getNo()); // 기존 게시글의 번호를 그대로 설정한다.
      board.setTitle(prompt.input("제목(%s)? ", oldBoard.getTitle()));
      board.setContent(prompt.input("내용(%s)? ", oldBoard.getContent()));
      board.setWriter(prompt.input("작성자(%s)? ", oldBoard.getWriter()));
      board.setCreatedDate(oldBoard.getCreatedDate());

      List<AttachedFile> files = attachedFileDao.findAllByBoardNo(no);

      prompt.println("첨부파일:");

      for (AttachedFile file : files) {
        prompt.printf(" %s\n", file.getFilePath());
      }
      try {
        int no2 = prompt.inputInt(
            "첨부파일 추가(1)/삭제(2)? ");

        if (no2 ==1) {
          while (true) {
            String filepath = prompt.input("파일?(종료: 그냥 엔터) ");
            if (filepath.length() == 0) {
              break;
            }
//      AttachedFile file = new AttachedFile().filePath(filepath);
//      file.setFilePath(filepath);
            files.add(new AttachedFile().filePath(filepath));
          }

        }
      } catch (Exception e) {
        prompt.println("첨부파일 변경 오류!");
      }

      boardDao.update(board);
      prompt.println("게시글을 변경했습니다.");

    } catch (Exception e) {
      prompt.println("변경 오류!");

//    } finally {
//      connectionPool.returnConnection(con);
    }
  }
}