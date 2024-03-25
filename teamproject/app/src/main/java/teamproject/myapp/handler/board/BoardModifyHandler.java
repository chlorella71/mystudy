package teamproject.myapp.handler.board;

import teamproject.menu.Menu;
import teamproject.menu.MenuHandler;
import teamproject.myapp.vo.Board;
import teamproject.util.AnsiEscape;
import teamproject.util.ObjectRepository;
import teamproject.util.Prompt;

public class BoardModifyHandler implements MenuHandler {

  ObjectRepository objectRepository;
  Prompt prompt;

  public BoardModifyHandler(ObjectRepository objectRepository, Prompt prompt) {
    this.objectRepository = objectRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s]\n" + AnsiEscape.ANSI_CLEAR, menu.getTitle());

    int index = this.prompt.inputInt("번호? ");
    Board oldBoard = (Board) this.objectRepository.get(index);
    if (oldBoard == null) {
      System.out.println("게시글 번호가 유효하지 않습니다.");
      return;
    }

    Board board = new Board();
    board.title = this.prompt.input("제목(%s)? ", board.title);
    board.content = this.prompt.input("내용(%s)? ", board.content);
    board.writer = this.prompt.input("작성자(%s)", board.writer);
    board.createdDate = this.prompt.input("작성일(%s) ?", board.createdDate);

    this.objectRepository.set(index, board);
  }
}
