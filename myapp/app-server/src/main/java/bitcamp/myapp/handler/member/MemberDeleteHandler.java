package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.Prompt;
import java.sql.Connection;

public class MemberDeleteHandler extends AbstractMenuHandler {

//  private DBConnectionPool connectionPool;
  private MemberDao memberDao;

//  public MemberDeleteHandler(MemberDao memberDao, Prompt prompt) {
//    super(prompt);
//    this.memberDao = memberDao;
//  }

//  public MemberDeleteHandler(DBConnectionPool connectionPool, MemberDao memberDao) {
//    this.connectionPool = connectionPool;
//    this.memberDao = memberDao;
//  }

  public MemberDeleteHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

//  @Override
//  protected void action() {
//    int no = this.prompt.inputInt("번호? ");
//    if (memberDao.delete(no) == -1) {
//      System.out.println("회원 번호가 유효하지 않습니다!");
//    } else {
//      System.out.println("회원을 삭제했습니다.");
//    }
//  }

  @Override
  protected void action(Prompt prompt) {
//    Connection con = null;
    try {
//      con = connectionPool.getConnection();
    int no = prompt.inputInt("번호? ");
    if (memberDao.delete(no) == -1) {
      prompt.println("회원 번호가 유효하지 않습니다!");
    } else {
      prompt.println("회원을 삭제했습니다.");
    }
    } catch (Exception e) {
      prompt.println("삭제 오류!");
//      } finally {
//      connectionPool.returnConnection(con);
    }
  }
}