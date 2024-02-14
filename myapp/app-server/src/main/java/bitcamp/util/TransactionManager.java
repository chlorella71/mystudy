package bitcamp.util;

import java.sql.Connection;

public class TransactionManager {

  ConnectionPool connectionPool;

  public TransactionManager(ConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  public void startTransaction() throws Exception {
    connectionPool.getConnection().setAutoCommit(false);
    System.out.printf("[%s] 트랜잭션 시작\n", Thread.currentThread().getName());
  }

  public void commit() throws Exception {
connectionPool.getConnection().commit();
complete();

////    connectionPool.getConnection().commit();
////    connectionPool.getConnection().setAutoCommit(true);
////    connectionPool.getConnection().close();
//    Connection con = connectionPool.getConnection(); //여러번 호출할때는 레퍼런스에 담아놓고 쓰면 편리하다.
//    con.commit();
//    con.setAutoCommit(true);
//    con.close();
  }

  public void rollback() throws Exception {
    connectionPool.getConnection().rollback();
    complete();
//    Connection con = connectionPool.getConnection();
//    con.rollback();;
//    con.setAutoCommit(true);
//    con.close();
  }

  private void complete() throws Exception {
    Connection con = connectionPool.getConnection();
    con.setAutoCommit(true);
    con.close();
    System.out.printf("[%s] 트랜잭션 종료\n", Thread.currentThread().getName());

  }
}
