package teamproject.myapp.handler.board;

import teamproject.menu.Menu;
import teamproject.menu.MenuHandler;
import teamproject.myapp.vo.Board;
import teamproject.util.AnsiEscape;
import teamproject.util.ObjectRepository;

public class BoardListHandler implements MenuHandler {

  ObjectRepository objectRepository;

  public BoardListHandler(ObjectRepository objectRepository) {
    this.objectRepository = objectRepository;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s]\n" + AnsiEscape.ANSI_CLEAR, menu.getTitle());

    System.out.printf("%-20s\t%10s\t%s\n", "Title", "Writer", "Date");

    for (Object object : this.objectRepository.toArray()) {
      Board board = (Board) object;
      System.out.printf("%-20s\t%10s\t%s\n",
          board.title,
          board.writer,
          board.createdDate);
    }
  }
}
