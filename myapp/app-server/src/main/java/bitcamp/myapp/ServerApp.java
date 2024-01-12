package bitcamp.myapp;

import bitcamp.myapp.dao.json.AssignmentDaoImpl;
import bitcamp.myapp.dao.json.BoardDaoImpl;
import bitcamp.myapp.dao.json.MemberDaoImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class ServerApp {

  //ArrayList<Object> daoList = 하려다가 HashMap사용함
  HashMap<String, Object> daoMap = new HashMap<>();
  Gson gson;

//  BoardDao boardDao = new BoardDaoImpl("board.json");
//  BoardDao greetingDao = new BoardDaoImpl("greeting.json");
//  AssignmentDao assignmentDao = new AssignmentDaoImpl("assignment.json");
//  MemberDao memberDao = new MemberDaoImpl("member.json");
  // => HashMap 사용

  public ServerApp() {
    daoMap.put("board", new BoardDaoImpl("board.json"));
    daoMap.put("greeting", new BoardDaoImpl("greeting.json"));
    daoMap.put("assignment", new AssignmentDaoImpl("assignment.json"));
    daoMap.put("member", new MemberDaoImpl("member.json"));

    gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
  }

  public static void main(String[] args) {
    new ServerApp().run();
  }

  void run() {
    System.out.println("[과제관리 서버시스템]");

    try {
      //1)(네트워크 장비와 연결하기 위한 데이터를 준비한다.)
      //랜카드 연결 정보를 준비한다.
      //=> 랜카드와 연결하는 것은 실제 OS가 수행한다.
      //=> JVM은 OS가 작업한 결과를 가져온다.
      //new ServerSocket(포트번호)
      //=> 포트번호: 랜카드로 들어온 데이터를 받을 때 사용할 식별 번호. 중복 불가.
      ServerSocket serverSocket = new ServerSocket(8888);
      System.out.println("서버 실행!");

      //2)클라이언트의 연결을 기다림
      //=> 클라이언트 대기 목록에서 먼저 연결된 순서대로 꺼낸다.
      //=> 클라이언트 대기 목록에 아무것도 없다면 연결이 될 때까지 리턴하지 않고 기다린다.
      System.out.println("클라이언트 연결을 기다리는 중...");
      Socket socket = serverSocket.accept();
      System.out.println("대기 목록에서 클라이언트 연결 정보를 꺼냈음!");

      //3)클라이언트와 통신
      DataInputStream in = new DataInputStream(socket.getInputStream());
      DataOutputStream out = new DataOutputStream(socket.getOutputStream());
      System.out.println("입출력 준비 완료!");

      while (true) {

        System.out.println("------------------------");

//      System.out.println("10초 동안 잠시 기다림!");
//      Thread.sleep(10000);  //10초 후에 데이터를 읽음

//        System.out.println("클라이언트가 보낸 데이터 읽음!");
        String dataName = in.readUTF();
        String command = in.readUTF();
        String value = in.readUTF();
        System.out.println("클라이언트 요청:");

//      System.out.println(dataName);
//      System.out.println(command);
//      System.out.println(value);

        //dataName으로 DAO를 찾는다.
        Object dao = daoMap.get(dataName);
        System.out.printf("데이터: %s\n", dataName);

        //command 이름으로 메서드를 찾는다.
        Method[] methods = dao.getClass()
          .getDeclaredMethods(); // dao의 클래스정보를 알아내서 그 클래스의 현재 선언된 메서드이름을 알아낸다.
        Method commandHandler = null;
        for (Method m : methods) {  //메서드를 반복하며
          if (m.getName().equals(command)) {  //만약 메서드이름이 커맨드와 같다면
            commandHandler = m; //그 값을 commandHandler에 넣고
            break;  //반복문을 종료한다.
          }
        }
        System.out.printf("메서드: %s\n", commandHandler.getName());

        //메서드의 파라미터 정보를 알아낸다.
        Parameter[] params = commandHandler.getParameters();
        System.out.printf("파마리터 개수: %s\n", params.length);

        //메서드를 호출할 때 넘겨줄 파라미터 데이터를 담을 배열을 준비한다.
        Object[] args = new Object[params.length];

        //아규먼트 값 준비하기
        //=> 현재 모든 DAO의 메서드는 파라미터가 최대 1개만 있다.
        //=> 1개만 있다는 가정하에서 아규먼트 값을 준비한다.
        if (params.length > 0) {
          //파라미터 타입을 알아낸다.
          Class<?> paramType = params[0].getType();

          //클라이언트가 보낸 JSON 문자열을 해당 파라미터 타입 객체로 변환한다.
          Object paramValue = gson.fromJson(value, paramType);

          //아규먼트 배열에 저장한다.
          args[0] = paramValue;
        }

        //메서드의 리턴 타입을 알아낸다.
        Class<?> returnType = commandHandler.getReturnType();
        System.out.printf("리턴: %s\n", returnType.getName());

        //메서드를 호출한다.
        Object returnValue = commandHandler.invoke(dao, args);

        out.writeUTF("200");

//      List<Board> list = boardDao.findAll();
//      String json = new Gson().toJson(list);

//      String json = new GsonBuilder().setDateFormat("yyyy-MM-dd").create()
//        .toJson(boardDao.findAll());
//      String json = gson.toJson(returnValue);

//      System.out.println("10초 동안 잠시 기다림!");
//      Thread.sleep(10000);  //10초 후에 데이터를 클라이언트로 보냄

//      out.writeUTF(json);
        out.writeUTF(gson.toJson(returnValue));

        System.out.println("클라이언트에게 응답 완료!");

      }

    } catch (Exception e) {
      System.out.println("통신 오류!");
      e.printStackTrace();
    }
  }
}