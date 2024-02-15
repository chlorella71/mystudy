package bitcamp.util;

import java.util.HashMap;
import java.util.Map;

public class Session {
//  private static final ThreadLocal<Session> sessionThreadLocal = new ThreadLocal<>();

  private Map<String, Object> attrMap = new HashMap<>();


//  public static Session getSession() {
//    Session session =  sessionThreadLocal.get();
//    if (session == null) {
//      session = new Session();
//      sessionThreadLocal.set(session);
//    }
//    return session;
//  }

  public void setAttribute(String name, Object value) {
    attrMap.put(name, value);
  }

  public Object getAttribute(String name) {
    return attrMap.get(name);
  }

//  private Session() {
//
//  }

  public void invalidate() {
    attrMap.clear();
  }
}
