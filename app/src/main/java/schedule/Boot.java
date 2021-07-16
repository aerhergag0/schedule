package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Boot {
  public Connection boot() throws ClassNotFoundException, SQLException {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection CN = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","system","1234");
    return CN;
  }
}