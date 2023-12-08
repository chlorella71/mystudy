package bitcamp.myapp;

import bitcamp.myapp.menu.MainMenu;
import bitcamp.util.Prompt;

public class App {

  public static void main(String[] args) {
    Prompt prompt = new Prompt(System.in);
    //MainMenu mainMenu = new MainMenu(prompt);
    //mainMenu.execute(); //합칠 수 있다.
    new MainMenu(prompt).execute();
    prompt.close();
  }


}
