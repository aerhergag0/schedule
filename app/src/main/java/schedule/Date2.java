package schedule;

import java.sql.*;

public class Date2 {
  public void date2(String id) {
    try{
      ResultSet rs = null;
      String msg = "";


      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      msg = "select sysdate from dual";
      rs = ST.executeQuery(msg);
      rs.next();
      java.sql.Date n_date = rs.getDate("sysdate");


      msg = "select * from seet_"+id+"";
      rs = ST.executeQuery(msg);
      while (rs.next() == true ) {
        java.sql.Date c_date = rs.getDate("p_date");
        String title = rs.getString("title");

        long diffDay = (n_date.getTime() - c_date.getTime()) / (24*60*60*1000);

        if(diffDay < 8 && diffDay > 0) {
          System.out.println("현재 " +diffDay+"일"+"남은 "+title+" 일정이 있습니다.");   
        }
      }

    }catch(Exception e) {}
  }


  public void s_date2(String id) {
    try{
      ResultSet rs = null;
      String msg = "";


      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      msg = "select sysdate from dual";
      rs = ST.executeQuery(msg);
      rs.next();
      java.sql.Date n_date = rs.getDate("sysdate");


      msg = "select * from s_seet_"+id+" ";
      rs = ST.executeQuery(msg);
      while (rs.next() == true ) {
        java.sql.Date c_date = rs.getDate("s_date");
        String title = rs.getString("title");

        String Shares = rs.getString("Shares");
        long diffDay = (n_date.getTime()-c_date.getTime()) / (24*60*60*1000);

        if(diffDay < 8 && diffDay > 0) {
          System.out.println("현재 "+Shares+" 님이 공유한 " +diffDay+"일"+"남은 "+title+" 공유 일정이 있습니다.");   
        }
      }
    }catch(Exception e) {}
  }// s_date2 end
}//class end