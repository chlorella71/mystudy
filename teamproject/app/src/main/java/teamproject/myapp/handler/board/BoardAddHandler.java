package teamproject.myapp.handler.board;

import teamproject.menu.Menu;
import teamproject.menu.MenuHandler;
import teamproject.myapp.vo.Board;
import teamproject.util.AnsiEscape;
import teamproject.util.ObjectRepository;
import teamproject.util.Prompt;

public class BoardAddHandler implements MenuHandler {

  Prompt prompt;
  ObjectRepository objectRepository;

  public BoardAddHandler(ObjectRepository objectRepository, Prompt prompt) {
    this.objectRepository = objectRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s]\n" + AnsiEscape.ANSI_CLEAR, menu.getTitle());

    Board board = new Board();
    board.title = this.prompt.input("제목?");
    board.content = this.prompt.input("내용?");
    board.writer = this.prompt.input("작성자? ");
    board.createdDate = this.prompt.input("등록일?");

    objectRepository.add(board);
  }
}
