package schedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//DBTablePrinter.java 필요
public class DB_Print{    

  Boot boot = new Boot();
  //db_print(쿼리문)
  public void db_print(String query) {
    Connection conn = null;
    String msg = null;
    try {
      conn = DriverManager.getConnection(boot.url,boot.id,boot.pwd);
      Statement stmt = conn.createStatement();

      msg = query;
      ResultSet rs = stmt.executeQuery(msg);
      if(rs.next() == false) {
        // 값이 없을시 출력 메시지?
        return;
      }

      DBTablePrinter.printResultSet(rs);

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
