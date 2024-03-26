package teamproject.myapp.handler.member;

import teamproject.menu.Menu;
import teamproject.menu.MenuHandler;
import teamproject.myapp.vo.Member;
import teamproject.util.AnsiEscape;
import teamproject.util.ObjectRepository;
import teamproject.util.Prompt;

public class MemberViewHandler implements MenuHandler {

  Prompt prompt;
  ObjectRepository<Member> objectRepository;

  public MemberViewHandler(ObjectRepository<Member> objectRepository, Prompt prompt) {
    this.objectRepository = objectRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s\n]" + AnsiEscape.ANSI_CLEAR, menu.getTitle());

    int index = this.prompt.inputInt("번호? ");
    Member member = this.objectRepository.get(index);
    if (member == null) {
      System.out.println("회원 번호가 유효하지 않습니다.");
      return;
  }
    System.out.printf("이메일: %s\n", member.email);
    System.out.printf("이름: %s\n", member.name);
    System.out.printf("생년월일: %s\n", member.createdDate);
  }
}
