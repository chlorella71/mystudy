package teamproject.menu;

import teamproject.util.Prompt;

public interface Menu {

  public abstract void execute(Prompt prompt);

  public abstract String getTitle();
}
