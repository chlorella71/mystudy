package bitcamp.myapp;

import bitcamp.menu.MenuGroup;
import bitcamp.myapp.handler.HelpHandler;
import bitcamp.myapp.handler.assignment.AssignmentAddHandler;
import bitcamp.myapp.handler.assignment.AssignmentDeleteHandler;
import bitcamp.myapp.handler.assignment.AssignmentListHandler;
import bitcamp.myapp.handler.assignment.AssignmentModifyHandler;
import bitcamp.myapp.handler.assignment.AssignmentViewHandler;
import bitcamp.myapp.handler.board.BoardAddHandler;
import bitcamp.myapp.handler.board.BoardDeleteHandler;
import bitcamp.myapp.handler.board.BoardListHandler;
import bitcamp.myapp.handler.board.BoardModifyHandler;
import bitcamp.myapp.handler.board.BoardViewHandler;
import bitcamp.myapp.handler.member.MemberAddHandler;
import bitcamp.myapp.handler.member.MemberDeleteHandler;
import bitcamp.myapp.handler.member.MemberListHandler;
import bitcamp.myapp.handler.member.MemberModifyHandler;
import bitcamp.myapp.handler.member.MemberViewHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class App {

  Prompt prompt = new Prompt(System.in);

  List<Board> boardRepository = new LinkedList<>();
  List<Assignment> assignmentRepository = new LinkedList<>();
  List<Member> memberRepository = new ArrayList<>();
  List<Board> greetingRepository = new ArrayList<>();

  MenuGroup mainMenu;

  App() {
//    loadData("assignment.data", assignmentRepository);
//    loadData("member.data", memberRepository);
//    loadData("board.data", boardRepository);
//    loadData("greeting.data", greetingRepository);
    assignmentRepository = loadData("assignment.json", Assignment.class);
    memberRepository = loadData("member.json", Member.class);
    boardRepository = loadData("board.json", Board.class);
    greetingRepository = loadData("greeting.json", Board.class);
    prepareMenu();
  }

  public static void main(String[] args) throws Exception {

    new App().run();
    //App app = new App();
    //app.run();

    //app.close();

    //new MainMenu(prompt).execute();

    // 메뉴의 경로를 저장할 스택 객체 준비
    //Stack<String> breadcrumb = new Stack<>();

    // 프로그램을 실행하다가 어느 지점에서 예외가 발생했을 때 해당 위치에서 적절한 조치를 취할 것이다.
    // 다만 그에 벗어나서 조치가 되지 않은 예외가 보고 되는 경우를 대비해
    // 마지막 보루인 main()에서는 예외를 처리해야 한다.
    // main()에서 마저 처리하지 않는다면 JVM에게 보고될 것이고,
    // JVM은 개발자나 알아 볼 메시지를 출력하고 종료할 것이다.
    //

  }

  void prepareMenu() {

    mainMenu = MenuGroup.getInstance("메인");

    MenuGroup assignmentMenu = mainMenu.addGroup("과제");
    assignmentMenu.addItem("등록", new AssignmentAddHandler(assignmentRepository, prompt));
    assignmentMenu.addItem("조회", new AssignmentViewHandler(assignmentRepository, prompt));
    assignmentMenu.addItem("변경", new AssignmentModifyHandler(assignmentRepository, prompt));
    assignmentMenu.addItem("삭제", new AssignmentDeleteHandler(assignmentRepository, prompt));
    assignmentMenu.addItem("목록", new AssignmentListHandler(assignmentRepository, prompt));
    //mainMenu.addItem(assignmentMenu);

    MenuGroup boardMenu = mainMenu.addGroup("게시글");
    boardMenu.addItem("등록", new BoardAddHandler(boardRepository, prompt));
    boardMenu.addItem("조회", new BoardViewHandler(boardRepository, prompt));
    boardMenu.addItem("변경", new BoardModifyHandler(boardRepository, prompt));
    boardMenu.addItem("삭제", new BoardDeleteHandler(boardRepository, prompt));
    boardMenu.addItem("목록", new BoardListHandler(boardRepository, prompt));
    //mainMenu.addItem(boardMenu);

    MenuGroup memberMenu = mainMenu.addGroup("회원");
    memberMenu.addItem("등록", new MemberAddHandler(memberRepository, prompt));
    memberMenu.addItem("조회", new MemberViewHandler(memberRepository, prompt));
    memberMenu.addItem("변경", new MemberModifyHandler(memberRepository, prompt));
    memberMenu.addItem("삭제", new MemberDeleteHandler(memberRepository, prompt));
    memberMenu.addItem("목록", new MemberListHandler(memberRepository, prompt));
    //mainMenu.addItem(memberMenu);

    MenuGroup greetingMenu = mainMenu.addGroup("가입인사");
    greetingMenu.addItem("등록", new BoardAddHandler(greetingRepository, prompt));
    greetingMenu.addItem("조회", new BoardViewHandler(greetingRepository, prompt));
    greetingMenu.addItem("변경", new BoardModifyHandler(greetingRepository, prompt));
    greetingMenu.addItem("삭제", new BoardDeleteHandler(greetingRepository, prompt));
    greetingMenu.addItem("목록", new BoardListHandler(greetingRepository, prompt));
    //mainMenu.addItem(greetingMenu);

    mainMenu.addItem("도움말", new HelpHandler(prompt));

  }

  void run() {
    while (true) {
      try {
        mainMenu.execute(prompt);
        prompt.close();
        break;
      } catch (Exception e) {
        System.out.println("예외 발생!");
      }
    }

    saveData("assignment.json", assignmentRepository);
    saveData("member.json", memberRepository);
    saveData("board.json", boardRepository);
    saveData("greeting.json", greetingRepository);
  }

  /*<E> void loadData(String filepath, List<E> dataList) {
    try (ObjectInputStream in = new ObjectInputStream(
      new BufferedInputStream(new FileInputStream(filepath)))) {

      //long start = System.currentTimeMillis();
      //int size = in.readInt();

      List<E> list = (List<E>) in.readObject(); //가비지가 생기는 단점
      dataList.addAll(list);

      //List<Assignment> list = (List<Assignment>) in.readObject();
      //assignmentRepository.addAll(list);

      //for (int i = 0; i < size; i++) {

      //Assignment assignment = (Assignment) in.readObject();

      //Assignment assignment = new Assignment();
      //assignment.setTitle(in.readUTF());
      //assignment.setContent(in.readUTF());
      //assignment.setDeadline(Date.valueOf(in.readUTF()));
      //assignmentRepository.add(assignment);
      //}

      //long end = System.currentTimeMillis();
      //System.out.printf("걸린 시간: %d\n", end - start);

    } catch (Exception e) {
      assignmentRepository = new LinkedList<>();
      System.out.printf("%s 파일 로딩 중 오류 발생!\n", filepath);
      e.printStackTrace();
    }
  }*/

  <E> List<E> loadData(String filepath, Class<E> clazz) {

    try (BufferedReader in = new BufferedReader(new FileReader(filepath))) {
      //파일에서 JSON 문자열을 모두 읽어서 버퍼에 저장한다
      StringBuilder strBuilder = new StringBuilder();
      String str;
      while ((str = in.readLine()) != null) {
        strBuilder.append(str);
      }
      //버퍼에 저장된 JSON 문자열을 가지고 컬렉션 객체를 생성한다.
      return (List<E>) new GsonBuilder().setDateFormat("yyyy-MM-dd").create().fromJson(
        strBuilder.toString(),
        TypeToken.getParameterized(ArrayList.class, clazz));

    } catch (Exception e) {
      System.out.printf("%s 파일 로딩 중 오류 발생!\n", filepath);
      e.printStackTrace();
    }
    //return new ArrayList<E>(); //ArrayList만 생기는 단점
    return new ArrayList<>();
  }

//  void saveData(String filepath, List<?> dataList) {
//    try (ObjectOutputStream out = new ObjectOutputStream(
//      new BufferedOutputStream(new FileOutputStream(filepath)))) {
//
//      out.writeObject(dataList);
//
//    } catch (Exception e) {
//      System.out.printf("%s 파일 저장 중 오류 발생!\n", filepath);
//      e.printStackTrace();
//    }
//  }

  void saveData(String filepath, List<?> dataList) {
    try (BufferedWriter out = new BufferedWriter(new FileWriter(filepath))) {

      //Gson gson = new Gson();
      //String gsonData = gson.toJson(dataList);
      out.write(new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(dataList));

      // 저장할 데이터 개수를 2바이트로 출력한다.

      //long start = System.currentTimeMillis();
      //out.writeInt(assignmentRepository.size());

      //for (Assignment assignment : assignmentRepository) {
      // assignment 객체에서 값을 꺼내 바이트 배열로 만든 다음에 출력한다.

      //out.writeObject(assignment);

      //out.writeUTF(assignment.getTitle());
      //out.writeUTF(assignment.getContent());
      //out.writeUTF(assignment.getDeadline().toString());
      //}

      //long end = System.currentTimeMillis();
      //System.out.printf("걸린 시간: %d\n", end - start);

    } catch (Exception e) {
      System.out.printf("%s 파일 저장 중 오류 발생!\n", filepath);
      e.printStackTrace();
    }
  }


  /*void saveMember() {
    try (ObjectOutputStream out = new ObjectOutputStream(
      new BufferedOutputStream(new FileOutputStream("member.data")))) {

      out.writeObject(memberRepository);

    } catch (Exception e) {
      System.out.println("회원 데이터 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void loadMember() {
    try (ObjectInputStream in = new ObjectInputStream(
      new BufferedInputStream(new FileInputStream("member.data")))) {

      memberRepository = (List<Member>) in.readObject();

    } catch (Exception e) {
      memberRepository = new ArrayList<>();
      System.out.println("회원 데이터 로딩 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void saveBoard() {
    try (DataOutputStream out = new DataOutputStream(
      new BufferedOutputStream(new FileOutputStream("board.data")))) {

      out.write(boardRepository.size());

      for (Board board : boardRepository) {
        out.writeUTF(board.getTitle());
        out.writeUTF(board.getContent());
        out.writeUTF(board.getWriter());
        out.writeLong(board.getCreatedDate().getTime());
      }

    } catch (Exception e) {
      System.out.println("게시글 데이터 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void loadBoard() {
    try (DataInputStream in = new DataInputStream(
      new BufferedInputStream(new FileInputStream("board.data")))) {
      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        Board board = new Board();
        board.setTitle(in.readUTF());
        board.setContent(in.readUTF());
        board.setWriter(in.readUTF());
        board.setCreatedDate(new java.util.Date(in.readLong()));
        boardRepository.add(board);
      }
    } catch (Exception e) {
      System.out.println("회원 데이터 로딩 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void saveGreeting() {
    try (FileOutputStream in0 = new FileOutputStream("greeting.data");
      BufferedOutputStream in1 = new BufferedOutputStream(in0);
      DataOutputStream out = new DataOutputStream(in1)) {
      out.write(greetingRepository.size());

      for (
        Board board : greetingRepository) {
        out.writeUTF(board.getTitle());
        out.writeUTF(board.getContent());
        out.writeUTF(board.getWriter());
        out.writeLong(board.getCreatedDate().getTime());
      }

    } catch (Exception e) {
      System.out.println("가입인사 데이터 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void loadGreeting() {
    try (FileInputStream in0 = new FileInputStream("greeting.data");
      BufferedInputStream in1 = new BufferedInputStream(in0);
      DataInputStream in = new DataInputStream(in1)) {
      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        Board board = new Board();
        board.setTitle(in.readUTF());
        board.setContent(in.readUTF());
        board.setWriter(in.readUTF());
        board.setCreatedDate(new java.util.Date(in.readLong()));
        greetingRepository.add(board);
      }
    } catch (Exception e) {
      System.out.println("가입인사 데이터 로딩 중 오류 발생!");
      e.printStackTrace();
    }
  }*/

    /*FileOutputStream out = null;
    try {
      out = new FileOutputStream("assignment.data");

    } catch (Exception e) {
      System.out.println("과제 데이터 저장 중 오류 발생!");

    } finally {
      try {
        out.close();
      } catch (Exception e) {
        // 파일을 닫다가 오류가 발생한다? 무시하자.
      }
    }*/
}