package bitcamp.menu;

import bitcamp.myapp.vo.Member;
import bitcamp.util.AnsiEscape;
import bitcamp.util.Prompt;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// Composite 패턴에서 '복합 객체(composite object)' 역할을 하는 클래스
// - 다른 Menu 객체를 포함한다.
public class MenuGroup extends AbstractMenu {

  private List<Menu> menus = new LinkedList<>();

//  private MenuGroup(String title, Stack<String> breadcrumb) {
//    super(title, breadcrumb);
//  }
private MenuGroup(String title) {
  super(title);
}

  // GoF의 Factory Method 디자인패턴!
//  public static MenuGroup getInstance(String title) {
//    return new MenuGroup(title, new Stack<String>());
//  }
  public static MenuGroup getInstance(String title) {
    return new MenuGroup(title);
  }

  @Override // 인터페이스나 수퍼 클래스의 메서드를 정의하겠다고 컴파일러에게 알린다.
  public void execute(Prompt prompt) throws Exception {

//    Member loginUser = (Member) prompt.getSession().getAttribute("loginUser");
//    String loginUserName = null;
//    if (loginUser != null) {
//      loginUserName = AnsiEscape.ANSI_BOLD_RED + loginUser.getName() + ":" + AnsiEscape.ANSI_CLEAR;
//    } else {
//      loginUserName = "";
//    }

    // 메뉴를 실행할 때 메뉴의 제목을 breadcrumb 경로에 추가한다.
//    breadcrumb.push(this.title);
    prompt.pushPath(this.title);

    this.printMenu(prompt);

    while (true) {

//      String input = prompt.input("%s>", this.getMenuPath());
//      String input = prompt.input("%s%s>", loginUserName, prompt.getFullPath());
      String input = prompt.input("%s%s>", getLoginUsername(prompt), prompt.getFullPath());


      if (input.equals("menu")) {
        this.printMenu(prompt);
        continue;
      } else if (input.equals("0")) {
        break;
      }

      try {
        int menuNo = Integer.parseInt(input);
        if (menuNo < 1 || menuNo > this.menus.size()) {
          System.out.println("메뉴 번호가 옳지 않습니다.");
          continue;
        }

        this.menus.get(menuNo - 1).execute(prompt);

      } catch (Exception e) {
        System.out.println("메뉴가 옳지 않습니다!");
      }
    }

    // 메뉴를 나갈 때 breadcrumb 메뉴 경로에서 메뉴 제목을 제거한다.
//    breadcrumb.pop();
    prompt.popPath();
//    } catch (Exception e) {
//  }
  }

  private String getLoginUsername(Prompt prompt) {
    Member loginUser = (Member) prompt.getSession().getAttribute("loginUser");
//    String loginUserName = null;
//    if (loginUser != null) {
//      loginUserName = AnsiEscape.ANSI_BOLD_RED + loginUser.getName() + ":" + AnsiEscape.ANSI_CLEAR;
//    } else {
//      loginUserName = "";
//    }
    if (loginUser != null) {
      return AnsiEscape.ANSI_BOLD_RED + loginUser.getName() + ":" + AnsiEscape.ANSI_CLEAR;
    } else {
      return "";
    }
  }

//  private void printMenu() {
//    System.out.printf("[%s]\n", this.getTitle());
//
//    Iterator<Menu> iterator = this.menus.iterator();
//    int i = 1;
//    while (iterator.hasNext()) {
//      Menu menu = iterator.next();
//      System.out.printf("%d. %s\n", i++, menu.getTitle());
//    }
//
//    System.out.printf("0. %s\n", "이전");
//  }

  private void printMenu(Prompt prompt) {
    prompt.printf("[%s]\n", this.getTitle());

    Iterator<Menu> iterator = this.menus.iterator();
    int i = 1;
    while (iterator.hasNext()) {
      Menu menu = iterator.next();
      prompt.printf("%d. %s\n", i++, menu.getTitle());
    }

    prompt.printf("0. %s\n", "이전");
  }

  public void add(Menu menu) {
    this.menus.add(menu);
  }

//  public MenuItem addItem(String title, MenuHandler handler) {
//    MenuItem menuItem = new MenuItem(title, this.breadcrumb, handler);
//    this.add(menuItem);
//    return menuItem;
//  }
  public MenuItem addItem(String title, MenuHandler handler) {
    MenuItem menuItem = new MenuItem(title, handler);
    this.add(menuItem);
    return menuItem;
  }

//  public MenuGroup addGroup(String title) {
//    MenuGroup menuGroup = new MenuGroup(title, this.breadcrumb);
//    this.add(menuGroup);
//    return menuGroup;
//  }
public MenuGroup addGroup(String title) {
  MenuGroup menuGroup = new MenuGroup(title);
  this.add(menuGroup);
  return menuGroup;
}

  public void remove(Menu menu) {
    this.menus.remove(menu);
  }
}
