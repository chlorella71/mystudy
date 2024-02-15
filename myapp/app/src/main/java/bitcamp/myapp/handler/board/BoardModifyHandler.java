package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.Prompt;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.units.qual.A;

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

    Member loginUser = (Member) prompt.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      prompt.println("로그인하시기 바랍니다!");
      return;
    }

//    Connection con = null;
    try {
//      con = connectionPool.getConnection();
      int no = prompt.inputInt("번호? ");

      Board oldBoard = boardDao.findBy(no);
      if (oldBoard == null) {
        prompt.println("게시글 번호가 유효하지 않습니다.");
        return;
      } else if (oldBoard.getWriter().getNo() != loginUser.getNo()) {
        prompt.println("게시글 변경 권한이 없습니다!");
      }

      Board board = new Board();
      board.setNo(oldBoard.getNo()); // 기존 게시글의 번호를 그대로 설정한다.
      board.setTitle(prompt.input("제목(%s)? ", oldBoard.getTitle()));
      board.setContent(prompt.input("내용(%s)? ", oldBoard.getContent()));
//      board.setWriter(prompt.input("작성자(%s)? ", oldBoard.getWriter()));
      board.setCreatedDate(oldBoard.getCreatedDate());



      prompt.println("첨부파일:");
      List<AttachedFile> files = attachedFileDao.findAllByBoardNo(no);
      for (AttachedFile file : files) {
        if (prompt.input(" %s 삭제하시겠습니까? (y?N)", file.getFilePath()).equalsIgnoreCase("y")) {
          attachedFileDao.delete(file.getNo());
        }
      }

      List<AttachedFile> newFiles = new ArrayList<>();
      while (true) {
        String filepath = prompt.input("추가할 파일?(종료: 그냥 엔터) ");
        if (filepath.length() == 0) {
          break;
        }
//      AttachedFile file = new AttachedFile().filePath(filepath);
//      file.setFilePath(filepath);
        files.add(new AttachedFile().filePath(filepath).boardNo(no));
      }

      boardDao.update(board);
      attachedFileDao.addAll(newFiles);


      boardDao.update(board);
      prompt.println("게시글을 변경했습니다.");

    } catch (Exception e) {
      prompt.println("변경 오류!");

//    } finally {
//      connectionPool.returnConnection(con);
    }
  }
}