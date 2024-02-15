package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.Prompt;
import java.sql.Connection;
import java.util.Date;

public class MemberAddHandler extends AbstractMenuHandler {

//  private DBConnectionPool connectionPool;
  private MemberDao memberDao;

//  public MemberAddHandler(MemberDao memberDao, Prompt prompt) {
//    super(prompt);
//    this.memberDao = memberDao;
//  }

//  public MemberAddHandler(DBConnectionPool connectionPool, MemberDao memberDao) {
//    this.connectionPool = connectionPool;
//    this.memberDao = memberDao;
//  }

  public MemberAddHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

//  @Override
//  protected void action() {
//    Member member = new Member();
//    member.setEmail(this.prompt.input("이메일? "));
//    member.setName(this.prompt.input("이름? "));
//    member.setPassword(this.prompt.input("암호? "));
//    member.setCreatedDate(new Date());
//
//    memberDao.add(member);
//  }

  @Override
  protected void action(Prompt prompt) {
//    Connection con = null;
    try {
//      con = connectionPool.getConnection();
      Member member = new Member();
      member.setEmail(prompt.input("이메일? "));
      member.setName(prompt.input("이름? "));
      member.setPassword(prompt.input("암호? "));
      member.setCreatedDate(new Date());

      memberDao.add(member);
    } catch (Exception e) {
      prompt.println("등록 오류!");

//    } finally {
//      connectionPool.returnConnection(con);
    }
  }
}