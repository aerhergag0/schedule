package schedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Boot {

  String driver = "oracle.jdbc.driver.OracleDriver";
  String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";

  String id = "system";
  String pwd = "1234";

  public Connection boot() throws ClassNotFoundException, SQLException {
    Class.forName(driver);
    Connection CN = DriverManager.getConnection(url,id,pwd);
    return CN;
  }
}