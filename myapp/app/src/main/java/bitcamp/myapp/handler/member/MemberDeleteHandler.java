package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.List;
import bitcamp.util.Prompt;

public class MemberDeleteHandler extends AbstractMenuHandler {

  private List<Member> objectRepository;

  public MemberDeleteHandler(List<Member> List, Prompt prompt) {
    super(prompt);
    this.objectRepository = List;
  }

  @Override
  protected void action() {
    int index = this.prompt.inputInt("번호? ");
    this.objectRepository.remove(index);


  }
}