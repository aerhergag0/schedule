package schedule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Notice {

  ResultSet RS;
  String msg;
  String title;
  long diffDay;
  String shares;
  java.sql.Date n_date;
  java.sql.Date c_date;

  public void view_count(String id) {
    try{
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      msg = "select sysdate from dual";
      RS = ST.executeQuery(msg);
      RS.next();
      n_date = RS.getDate("sysdate");

      System.out.println("\n[개인일정]");

      msg = "select * from seet_"+id;
      RS = ST.executeQuery(msg);
      while (RS.next() == false) {
        System.out.println("다가오는 일정이 없습니다."); break;
      }

      msg = "select * from seet_"+id;
      RS = ST.executeQuery(msg);
      while (RS.next() == true) {
        c_date = RS.getDate("p_date");
        title = RS.getString("title");
        diffDay = (c_date.getTime() - n_date.getTime()) / (24*60*60*1000);

        if(diffDay < 8 && diffDay > 0) {
          System.out.println("* 현재 "+diffDay+"일 남은 "+title+" 일정이 있습니다.");   
        }
      }
      System.out.println();
    }catch(Exception e) {}
  }

  public void s_view_count(String id) {
    try{
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      msg = "select sysdate from dual";
      RS = ST.executeQuery(msg);
      RS.next();
      n_date = RS.getDate("sysdate");

      System.out.println("[공유일정]");

      msg = "select * from s_seet_"+id;
      RS = ST.executeQuery(msg);
      while (RS.next() == false) {
        System.out.println("다가오는 공유일정이 없습니다."); break;
      }

      msg = "select * from s_seet_"+id;
      RS = ST.executeQuery(msg);
      while (RS.next() == true) {
        c_date = RS.getDate("s_date");
        title = RS.getString("title");
        shares = RS.getString("Shares");
        diffDay = (c_date.getTime() - n_date.getTime()) / (24*60*60*1000);

        if(diffDay < 8 && diffDay > 0) {
          System.out.println("* 현재 "+shares+"님이 공유한 "+diffDay+"일 남은 "+title+" 공유일정이 있습니다.");   
        }
      }
    }catch(Exception e) {}
  }

}