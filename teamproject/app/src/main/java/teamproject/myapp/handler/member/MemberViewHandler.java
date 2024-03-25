package teamproject.myapp.handler.member;

import teamproject.menu.Menu;
import teamproject.menu.MenuHandler;
import teamproject.myapp.vo.Member;
import teamproject.util.AnsiEscape;
import teamproject.util.Prompt;

public class MemberViewHandler implements MenuHandler {

  Prompt prompt;
  MemberRepository memberRepository;

  public MemberViewHandler(MemberRepository memberRepository, Prompt prompt) {
    this.memberRepository = memberRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s\n]" + AnsiEscape.ANSI_CLEAR, menu.getTitle());

    int index = this.prompt.inputInt("번호? ");
    if (index < 0 || index >= this.memberRepository.length) {
      System.out.println("회원 번호가 유효하지 않습니다.");
      return;
  }

    Member member = this.memberRepository.members[index];
    System.out.printf("이메일: %s\n", member.email);
    System.out.printf("이름: %s\n", member.name);
    System.out.printf("생년월일: %s\n", member.createdDate);
  }
}
