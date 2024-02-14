package bitcamp.myapp;

import bitcamp.menu.MenuGroup;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
//import bitcamp.myapp.dao.DaoProxyGenerator;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.mysql.AssignmentDaoImpl;
import bitcamp.myapp.dao.mysql.AttachedFileDaoImpl;
import bitcamp.myapp.dao.mysql.BoardDaoImpl;
import bitcamp.myapp.dao.mysql.MemberDaoImpl;
import bitcamp.myapp.handler.AboutHandler;
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
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.util.Prompt;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.TransactionManager;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerApp {

  ExecutorService executorService = Executors.newCachedThreadPool();

  TransactionManager txManager;
  DBConnectionPool connectionPool;

  BoardDao boardDao;
  BoardDao greetingDao;
  AssignmentDao assignmentDao;
  MemberDao memberDao;
  AttachedFileDao attachedFileDao;

  MenuGroup mainMenu;

//  Socket socket;
//  DataInputStream in;
//  DataOutputStream out;

  ServerApp() {
//    prepareNetwork();
    prepareDatabase();
    prepareMenu();
  }

  public static void main(String[] args) {
    System.out.println("과제관리 시스템 서버 실행!");

    new ServerApp().run();
  }

  void prepareDatabase() {
    try {
//      socket = new Socket("localhost", 8888);
//      //Socket socket = new Socket("127.0.0.1", 8888);
//      System.out.println("서버와 연결되었음!");
//
//      in = new DataInputStream(socket.getInputStream());
//      out = new DataOutputStream(socket.getOutputStream());

//      DaoProxyGenerator daoGenerator = new DaoProxyGenerator("localhost", 8888);
      //네트워크 DAO 구현체 준비
//      boardDao = daoGenerator.create(BoardDao.class, "board");
//      greetingDao = daoGenerator.create(BoardDao.class, "greeting");
//      assignmentDao = daoGenerator.create(AssignmentDao.class, "assignment");
//      memberDao = daoGenerator.create(MemberDao.class, "member");
//

      // JVM이 JDBC 드라이버 파일(.jar)에 설정된대로 자동으로 처리한다.
//      Driver driver = new com.mysql.cj.jdbc.Driver();
//      DriverManager.registerDriver(driver);

//      Connection con = DriverManager.getConnection(
////          "jdbc:mysql://localhost/studydb", "study", "1111");
//          "jdbc:mysql://db-ld29t-kr.vpc-pub-cdb.ntruss.com/studydb", "study", "Bitcamp!@#123");

//      boardDao = new BoardDaoImpl(con, 1);
//      greetingDao = new BoardDaoImpl(con, 2);
//      assignmentDao = new AssignmentDaoImpl(con);
//      memberDao = new MemberDaoImpl(con);

//      ThreadConnection threadConnection = new ThreadConnection(
      connectionPool = new DBConnectionPool(
                 "jdbc:mysql://localhost/studydb", "study", "1111"
//          "jdbc:mysql://db-ld29t-kr.vpc-pub-cdb.ntruss.com/studydb", "study", "Bitcamp!@#123"
      );
      txManager = new TransactionManager(connectionPool);

      boardDao = new BoardDaoImpl(connectionPool, 1);
      greetingDao = new BoardDaoImpl(connectionPool, 2);
      assignmentDao = new AssignmentDaoImpl(connectionPool);
      memberDao = new MemberDaoImpl(connectionPool);
      attachedFileDao = new AttachedFileDaoImpl(connectionPool);



    } catch (Exception e) {
      System.out.println("통신 오류!");
      e.printStackTrace();
    }
  }

  void prepareMenu() {
    mainMenu = MenuGroup.getInstance("메인");

//    MenuGroup assignmentMenu = mainMenu.addGroup("과제");
//    assignmentMenu.addItem("등록", new AssignmentAddHandler(assignmentDao, prompt));
//    assignmentMenu.addItem("조회", new AssignmentViewHandler(assignmentDao, prompt));
//    assignmentMenu.addItem("변경", new AssignmentModifyHandler(assignmentDao, prompt));
//    assignmentMenu.addItem("삭제", new AssignmentDeleteHandler(assignmentDao, prompt));
//    assignmentMenu.addItem("목록", new AssignmentListHandler(assignmentDao, prompt));

    MenuGroup assignmentMenu = mainMenu.addGroup("과제");
    assignmentMenu.addItem("등록", new AssignmentAddHandler(txManager, assignmentDao));
    assignmentMenu.addItem("조회", new AssignmentViewHandler(assignmentDao));
    assignmentMenu.addItem("변경", new AssignmentModifyHandler(assignmentDao));
    assignmentMenu.addItem("삭제", new AssignmentDeleteHandler(assignmentDao));
    assignmentMenu.addItem("목록", new AssignmentListHandler(assignmentDao));

//    MenuGroup boardMenu = mainMenu.addGroup("게시글");
//    boardMenu.addItem("등록", new BoardAddHandler(boardDao, prompt));
//    boardMenu.addItem("조회", new BoardViewHandler(boardDao, prompt));
//    boardMenu.addItem("변경", new BoardModifyHandler(boardDao, prompt));
//    boardMenu.addItem("삭제", new BoardDeleteHandler(boardDao, prompt));
//    boardMenu.addItem("목록", new BoardListHandler(boardDao, prompt));

    MenuGroup boardMenu = mainMenu.addGroup("게시글");
    boardMenu.addItem("등록", new BoardAddHandler(txManager, boardDao, attachedFileDao));
    boardMenu.addItem("조회", new BoardViewHandler(boardDao, attachedFileDao));
    boardMenu.addItem("변경", new BoardModifyHandler(boardDao));
    boardMenu.addItem("삭제", new BoardDeleteHandler(boardDao));
    boardMenu.addItem("목록", new BoardListHandler(boardDao));

//    MenuGroup memberMenu = mainMenu.addGroup("회원");
//    memberMenu.addItem("등록", new MemberAddHandler(memberDao, prompt));
//    memberMenu.addItem("조회", new MemberViewHandler(memberDao, prompt));
//    memberMenu.addItem("변경", new MemberModifyHandler(memberDao, prompt));
//    memberMenu.addItem("삭제", new MemberDeleteHandler(memberDao, prompt));
//    memberMenu.addItem("목록", new MemberListHandler(memberDao, prompt));

    MenuGroup memberMenu = mainMenu.addGroup("회원");
    memberMenu.addItem("등록", new MemberAddHandler(memberDao));
    memberMenu.addItem("조회", new MemberViewHandler(memberDao));
    memberMenu.addItem("변경", new MemberModifyHandler(memberDao));
    memberMenu.addItem("삭제", new MemberDeleteHandler(memberDao));
    memberMenu.addItem("목록", new MemberListHandler(memberDao));

//    MenuGroup greetingMenu = mainMenu.addGroup("가입인사");
//    greetingMenu.addItem("등록", new BoardAddHandler(greetingDao, prompt));
//    greetingMenu.addItem("조회", new BoardViewHandler(greetingDao, prompt));
//    greetingMenu.addItem("변경", new BoardModifyHandler(greetingDao, prompt));
//    greetingMenu.addItem("삭제", new BoardDeleteHandler(greetingDao, prompt));
//    greetingMenu.addItem("목록", new BoardListHandler(greetingDao, prompt));

    MenuGroup greetingMenu = mainMenu.addGroup("가입인사");
    greetingMenu.addItem("등록", new BoardAddHandler(txManager, greetingDao, attachedFileDao));
    greetingMenu.addItem("조회", new BoardViewHandler(greetingDao, attachedFileDao));
    greetingMenu.addItem("변경", new BoardModifyHandler(greetingDao));
    greetingMenu.addItem("삭제", new BoardDeleteHandler(greetingDao));
    greetingMenu.addItem("목록", new BoardListHandler(greetingDao));


    mainMenu.addItem("도움말", new HelpHandler());
    mainMenu.addItem("...대하여", new AboutHandler());
  }

  void run() {
    try (ServerSocket serverSocket = new ServerSocket(8888)) {

      while (true) {
        Socket socket = serverSocket.accept();
        executorService.execute(() -> processRequest(socket));
      }

    } catch (Exception e) {
      System.out.println("서버 소켓 생성 오류!");
      e.printStackTrace();

    } finally {
      connectionPool.closeAll();

    }
  }

  void processRequest(Socket socket) {
    try (Socket s = socket;
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        DataInputStream in = new DataInputStream(s.getInputStream());
        Prompt prompt = new Prompt(in, out)) {

          while (true) {
      try {
        mainMenu.execute(prompt);
        prompt.print("[[quit!]]");
        prompt.end();
//        prompt.close();
//        close();
        break;
      } catch (Exception e) {
        System.out.println("예외 발생!");
        e.printStackTrace();
      }
    }

//  prompt.println("[과제관리 시스템]");
//  prompt.println("환영합니다!");
//  prompt.println("반가워요");
//  prompt.end();
//
//  String request = prompt.input();
//  System.out.println(request);

//      out.writeUTF("[과제관리 시스템]");
//      String request = in.readUTF();
//      if (request.equals("quit")) {
//        out.writeUTF("[[quit!]]");
//      }
//      System.out.println(request);

    } catch (Exception e) {
      System.out.println("클라이언트 통신 오류!");
      e.printStackTrace();

    } finally {
//      threadConnection.remove();
    }
  }

//  void run() {
//    while (true) {
//      try {
//        mainMenu.execute(prompt);
//        prompt.close();
////        close();
//        break;
//      } catch (Exception e) {
//        System.out.println("예외 발생!");
//      }
//    }
//  }

//  void close() {
//    try (Socket socket = this.socket;
//      DataInputStream in = this.in;
//      DataOutputStream out = this.out;) {
//
//      out.writeUTF("quit");
//      System.out.println(in.readUTF());
//
//    } catch (Exception e) {
//      // 서버와 연결을 끊는 과정에서 예외가 발생한 경우 무시한다.
//      // 왜? 따로 처리할 것이 없다.
//    }
//  }

}