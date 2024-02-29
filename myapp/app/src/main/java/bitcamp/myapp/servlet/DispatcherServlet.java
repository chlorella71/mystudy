package bitcamp.myapp.servlet;

import bitcamp.myapp.controller.HomeController;
import bitcamp.myapp.controller.PageController;
import bitcamp.myapp.controller.assignment.AssignmentAddController;
import bitcamp.myapp.controller.assignment.AssignmentDeleteController;
import bitcamp.myapp.controller.assignment.AssignmentListController;
import bitcamp.myapp.controller.assignment.AssignmentUpdateController;
import bitcamp.myapp.controller.assignment.AssignmentViewController;
import bitcamp.myapp.controller.auth.LoginController;
import bitcamp.myapp.controller.auth.LogoutController;
import bitcamp.myapp.controller.board.BoardAddController;
import bitcamp.myapp.controller.board.BoardDeleteController;
import bitcamp.myapp.controller.board.BoardFileDeleteController;
import bitcamp.myapp.controller.board.BoardListController;
import bitcamp.myapp.controller.board.BoardUpdateController;
import bitcamp.myapp.controller.board.BoardViewController;
import bitcamp.myapp.controller.member.MemberAddController;
import bitcamp.myapp.controller.member.MemberDeleteController;
import bitcamp.myapp.controller.member.MemberListController;
import bitcamp.myapp.controller.member.MemberUpdateController;
import bitcamp.myapp.controller.member.MemberViewController;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.util.TransactionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/app/*")
public class DispatcherServlet extends HttpServlet {

  private Map<String, PageController> controllerMap = new HashMap<>();

  @Override
  public void init() throws ServletException {
    ServletContext ctx = this.getServletContext();
    TransactionManager txManager = (TransactionManager) ctx.getAttribute("txManager");
    BoardDao boardDao = (BoardDao) ctx.getAttribute("boardDao");
    MemberDao memberDao = (MemberDao) ctx.getAttribute("memberDao");
    AssignmentDao assignmentDao = (AssignmentDao) ctx.getAttribute("assignmentDao");
    AttachedFileDao attachedFileDao = (AttachedFileDao) ctx.getAttribute("attachedFileDao");

    controllerMap.put("/home", new HomeController());

    String memberUploadDir = this.getServletContext().getRealPath("/upload");
    controllerMap.put("/member/list", new MemberListController(memberDao));
    controllerMap.put("/member/view", new MemberViewController(memberDao));
    controllerMap.put("/member/add", new MemberAddController(memberDao, memberUploadDir));
    controllerMap.put("/member/update", new MemberUpdateController(memberDao, memberUploadDir));
    controllerMap.put("/member/delete", new MemberDeleteController(memberDao, memberUploadDir));

    controllerMap.put("/assignment/list", new AssignmentListController(assignmentDao));
    controllerMap.put("/assignment/view", new AssignmentViewController(assignmentDao));
    controllerMap.put("/assignment/add", new AssignmentAddController(assignmentDao));
    controllerMap.put("/assignment/update", new AssignmentUpdateController(assignmentDao));
    controllerMap.put("/assignment/delete", new AssignmentDeleteController(assignmentDao));

    String boardUploadDir = this.getServletContext().getRealPath("/upload/board");
    controllerMap.put("/board/list", new BoardListController(boardDao));
    controllerMap.put("/board/view", new BoardViewController(boardDao, attachedFileDao));
    controllerMap.put("/board/add", new BoardAddController(txManager, boardDao, attachedFileDao, boardUploadDir));
    controllerMap.put("/board/update", new BoardUpdateController(txManager, boardDao, attachedFileDao, boardUploadDir));
    controllerMap.put("/board/delete", new BoardDeleteController(txManager, boardDao, attachedFileDao, boardUploadDir));
    controllerMap.put("/board/file/delete", new BoardFileDeleteController(boardDao, attachedFileDao, boardUploadDir));

    controllerMap.put("/auth/login", new LoginController(memberDao));
    controllerMap.put("/auth/logout", new LogoutController());
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

//    System.out.println(request.getServletPath());
//    System.out.println(request.getPathInfo());

//    // URL에서 요청한 페이지 컨트롤러를 실행한다.
//    request.getRequestDispatcher(request.getPathInfo()).include(request, response);
    PageController controller = controllerMap.get(request.getPathInfo());
    if (controller == null) {
      throw new ServletException(request.getPathInfo() + " 요청 페이지를 찾을 수 없습니다.");
    }

    try {
//      controller.execute(request, response);
      String viewUrl = controller.execute(request, response);

//      // 페이지 컨트롤러에서 오류가 발생했으면 오류페이지로 포워딩한다.
//      Throwable exception = (Throwable) request.getAttribute("exception");
//      if (exception != null) {
////        request.setAttribute("message", request.getPathInfo() + " 실행 오류!");
////
////        StringWriter stringWriter = new StringWriter();
////        PrintWriter printWriter = new PrintWriter(stringWriter);
////        exception.printStackTrace(printWriter);
////        request.setAttribute("detail", stringWriter.toString());
////
////        request.getRequestDispatcher("/error.jsp").forward(request, response);
////        return;
//      }

//      // 페이지 컨트롤러에서 만든 쿠키가 있다면 응답에 포함시킨다.
//      List<Cookie> cookies = (List<Cookie>) request.getAttribute("cookies");
//      if (cookies != null) {
//        for (Cookie cookie : cookies) {
//          response.addCookie(cookie);
//        }
//      }

      // 페이지 컨트롤러가 알려준 JSP로 포워딩 한다.
//      String viewUrl = (String) request.getAttribute("viewUrl");
      if (viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.substring(9));
      } else {
        request.getRequestDispatcher(viewUrl).forward(request, response);
      }
    } catch (Exception e) {
      // 페이지 컨트롤러에서 오류가 발생했으면 오류페이지로 포워딩한다.
      request.setAttribute("message", request.getPathInfo() + " 실행 오류!");

      StringWriter stringWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(stringWriter);
      e.printStackTrace(printWriter);
      request.setAttribute("detail", stringWriter.toString());

      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
//    request.getRequestDispatcher("/home.jsp").forward(request, response);

//    servletResponse.setContentType("text/plain;charset=UTF-8");
//    response.setContentType("text/html;charset=UTF-8");
//    PrintWriter out = response.getWriter();
//
//    out.println("<!DOCTYPE html>");
////    out.println("<html lang=\"en\">");
//    out.println("<html lang='en'>");
//    out.println("<head>");
////    out.println("<meta charset=\"UTF-8\">");
//    out.println("<meta charset='UTF-8'>");
//    out.println("<title>비트캠프 데브옵스 5기</title>");
//    out.println("</head>");
//    out.println("<body>");
//
////    RequestDispatcher 요청배달자 = request.getRequestDispatcher("/header");
////    요청배달자.include(request, response);
//    request.getRequestDispatcher("/header").include(request, response);
//
//    out.println("<h1>과제 관리 시스템</h1");
//    out.println("<p>환영합니다! 교육 센터 과제 관리 시스템입니다.</p>");
//
//    request.getRequestDispatcher("/footer").include(request, response);
//
//    out.println("</body>");
//    out.println("</html>");
  }
}