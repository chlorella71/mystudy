package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.Prompt;
import java.sql.Connection;
import java.util.List;

public class MemberListHandler extends AbstractMenuHandler {

//  private DBConnectionPool connectionPool;
  private MemberDao memberDao;

//  public MemberListHandler(MemberDao memberDao, Prompt prompt) {
//    super(prompt);
//    this.memberDao = memberDao;
//  }

//  public MemberListHandler(DBConnectionPool connectionPool, MemberDao memberDao) {
//    this.connectionPool = connectionPool;
//    this.memberDao = memberDao;
//  }

  public MemberListHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

//  @Override
//  protected void action() {
//    System.out.printf("%-4s\t%-10s\t%30s\t%s\n", "번호", "이름", "이메일", "가입일");
//
//    List<Member> list = memberDao.findAll();
//
//    for (Member member : list) {
//      System.out.printf("%-4d\t%-10s\t%30s\t%4$tY-%4$tm-%4$td\n",
//        member.getNo(),
//        member.getName(),
//        member.getEmail(),
//        member.getCreatedDate());
//    }
//  }

  @Override
  protected void action(Prompt prompt) {
//    Connection con = null;
    try {
//      con = connectionPool.getConnection();
      prompt.printf("%-4s\t%-10s\t%30s\t%s\n", "번호", "이름", "이메일", "가입일");

      List<Member> list = memberDao.findAll();

      for (Member member : list) {
        prompt.printf("%-4d\t%-10s\t%30s\t%4$tY-%4$tm-%4$td\n",
            member.getNo(),
            member.getName(),
            member.getEmail(),
            member.getCreatedDate());
      }
    } catch (Exception e) {
      prompt.println("목록 오류!");
//    } finally {
//      connectionPool.returnConnection(con);
    }
  }
}