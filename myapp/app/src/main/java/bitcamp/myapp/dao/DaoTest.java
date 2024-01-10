package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Board;
import java.io.File;

public class DaoTest extends AbstractDao<Board> {

  public static void main(String[] agrs) {
    File f = new File(".");
    System.out.println(f.getAbsolutePath());
    DaoTest obj = new DaoTest();
    obj.loadData("app/board.json");

    for (Board board : obj.list) {
      System.out.println(board);
    }

  }
}