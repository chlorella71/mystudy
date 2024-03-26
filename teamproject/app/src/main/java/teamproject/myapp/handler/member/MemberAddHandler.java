package teamproject.myapp.handler.member;

import teamproject.menu.Menu;
import teamproject.menu.MenuHandler;
import teamproject.myapp.vo.Member;
import teamproject.util.AnsiEscape;
import teamproject.util.ObjectRepository;
import teamproject.util.Prompt;

public class MemberAddHandler implements MenuHandler {

  Prompt prompt;
  ObjectRepository<Member> objectRepository;

  public MemberAddHandler(ObjectRepository<Member> objectRepository, Prompt prompt) {
    this.objectRepository = objectRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s]\n" + AnsiEscape.ANSI_CLEAR, menu.getTitle());
    
    Member member = new Member();
    member.email = this.prompt.input("이메일? ");
    member.name = this.prompt.input("이름?" );
    member.password = this.prompt.input("암호? ");
    member.createdDate = this.prompt.input("가입일? ");

    this.objectRepository.add(member);
  }
}
