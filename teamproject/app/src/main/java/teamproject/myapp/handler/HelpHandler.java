package teamproject.myapp.handler;


import teamproject.menu.Menu;
import teamproject.menu.MenuHandler;
import teamproject.util.AnsiEscape;
import teamproject.util.Prompt;

public class HelpHandler implements MenuHandler {

  @Override
  public void action(Menu menu) {
    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s]\n" + AnsiEscape.ANSI_CLEAR, menu.getTitle());

    System.out.println("도움말입니다.");
  }
}
