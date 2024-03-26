package teamproject.myapp.handler.board;

import teamproject.menu.Menu;
import teamproject.menu.MenuHandler;
import teamproject.myapp.vo.Board;
import teamproject.util.AnsiEscape;
import teamproject.util.ObjectRepository;
import teamproject.util.Prompt;

public class BoardDeleteHandler implements MenuHandler {

  ObjectRepository<Board> objectRepository;
  Prompt prompt;

  public BoardDeleteHandler(ObjectRepository<Board> objectRepository, Prompt prompt) {
    this.objectRepository = objectRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s]\n" + AnsiEscape.ANSI_CLEAR, menu.getTitle());

    int index = this.prompt.inputInt("번호? ");
    if (this.objectRepository.remove(index) == null) {
      System.out.println("게시글 번호가 유효하지 않습니다.");
    }
  }
}
