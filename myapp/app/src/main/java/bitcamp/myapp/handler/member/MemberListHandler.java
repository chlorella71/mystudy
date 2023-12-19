package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;
import java.util.ArrayList;

public class MemberListHandler extends AbstractMenuHandler {

  private ArrayList<Member> objectRepository;

  public MemberListHandler(ArrayList<Member> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() {
    System.out.printf("%-10s\t%30s\t%s\n", "이름", "이메일", "가입일");

    for (Object object : this.objectRepository.toArray()) {
      Member member = (Member) object;
      System.out.printf("%-10s\t%30s\t%s\n", member.getName(), member.getEmail(),
        member.getCreatedDate());
    }
  }
}