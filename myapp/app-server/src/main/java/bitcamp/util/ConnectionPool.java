package bitcamp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public interface ConnectionPool {

  Connection getConnection() throws Exception;

  void returnConnection(Connection con);
}
