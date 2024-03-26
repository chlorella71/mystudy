package teamproject.myapp.handler.member;

import teamproject.menu.Menu;
import teamproject.menu.MenuHandler;
import teamproject.myapp.vo.Member;
import teamproject.util.AnsiEscape;
import teamproject.util.ObjectRepository;

public class MemberListHandler implements MenuHandler {

  ObjectRepository<Member> objectRepository;

  public MemberListHandler(ObjectRepository<Member> objectRepository) {
    this.objectRepository = objectRepository;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s]\n" + AnsiEscape.ANSI_CLEAR, menu.getTitle());

    System.out.printf("%-10s\t%30s\t%s\n", "이름", "이메일", "가입일");

    for (Object object : this.objectRepository.toArray()) {
      Member member = (Member) object;
      System.out.printf("%-10s\t%30s\t%s\n", member.name, member.email, member.createdDate);
    }
  }
}
