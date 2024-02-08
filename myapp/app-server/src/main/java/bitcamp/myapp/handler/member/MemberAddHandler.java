package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;
import java.util.Date;

public class MemberAddHandler extends AbstractMenuHandler {

  private MemberDao memberDao;

//  public MemberAddHandler(MemberDao memberDao, Prompt prompt) {
//    super(prompt);
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
    Member member = new Member();
    member.setEmail(prompt.input("이메일? "));
    member.setName(prompt.input("이름? "));
    member.setPassword(prompt.input("암호? "));

    memberDao.add(member);
  }
}