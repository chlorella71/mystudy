package teamproject.myapp.handler.member;

import teamproject.menu.Menu;
import teamproject.menu.MenuHandler;
import teamproject.myapp.vo.Member;
import teamproject.util.AnsiEscape;
import teamproject.util.ObjectRepository;
import teamproject.util.Prompt;

public class MemberModifyHandler implements MenuHandler {

  Prompt prompt;
  ObjectRepository<Member> objectRepository;

  public MemberModifyHandler(ObjectRepository<Member> objectRepository, Prompt prompt) {
    this.objectRepository = objectRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s\n]" + AnsiEscape.ANSI_CLEAR, menu.getTitle());

    int index = this.prompt.inputInt("번호? ");
    Member old = this.objectRepository.get(index);
    if (old == null) {
      System.out.println("회원 번호가 유효하지 않습니다.");
      return;
    }

    Member member = new Member();
    member.email = this.prompt.input("이메일? ", member.email);
    member.name = this.prompt.input("이름(%s)? ", member.name);
    member.password = this.prompt.input("비밀번호? ");
    member.createdDate = this.prompt.input("생년월일? ", member.createdDate);

    this.objectRepository.set(index, member);
  }
}
