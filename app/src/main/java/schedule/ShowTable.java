package schedule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShowTable {

  ResultSet RS;
  String msg;

  //번호 날짜 제목 내용
  public void showtableContents(String id) {
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      msg = "select rownum, a.p_date, a.title, a.contents from (select * from seet_"+id+" order by p_date) a";
      RS = ST.executeQuery(msg);
      while (RS.next() == true) {
        int rownum = RS.getInt("rownum");
        java.util.Date p_date = RS.getDate("p_date");
        String title = RS.getString("title");
        String contents = RS.getString("contents");

        System.out.printf("   번호\t %s\n   ", rownum);
        System.out.printf("날짜 %s\n   ", p_date);
        System.out.printf("제목 %s\n   ", title);
        System.out.printf("내용 %s\n   ", contents);
        System.out.println();

      }
      CN.close(); ST.close(); RS.close();
    } catch(Exception e) {}
  } //Method End

  //번호 날짜 제목 내용
  public void showCommunity(String id) {
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      System.out.println("번호" +"\t"+ "제목" +"\t\t\t"+ "작성자" +"\t"+ "날짜");
      msg = "select c_id, title, writer, c_date from community order by c_id desc";
      RS = ST.executeQuery(msg);
      while (RS.next() == true) {
        int c_id = RS.getInt("c_id");
        String title = RS.getString("title");
        String writer = RS.getString("writer");
        java.util.Date c_date = RS.getDate("c_date");
        System.out.println(c_id +"\t"+ title +"\t\t"+ writer +"\t"+ c_date);
      }
      CN.close();
    } catch(Exception e) {}
  } //Method End

  //번호 날짜 제목 코드
  public void showtableCode(String id) {
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      msg = "select rownum, a.p_date, a.title,a.contents, a.p_id from (select * from seet_"+id+" order by p_date) a";
      RS = ST.executeQuery(msg);
      while (RS.next() == true) {
        int rownum = RS.getInt("rownum");
        java.util.Date p_date = RS.getDate("p_date");
        String title = RS.getString("title");
        String p_id = RS.getString("p_id");

        System.out.printf("   번호\t %s\n   ", rownum);
        System.out.printf("날짜 %s\n   ", p_date);
        System.out.printf("제목 %s\n   ", title);
        System.out.printf("코드 %s\n   ", p_id);
        System.out.println();

      }
      CN.close(); ST.close(); RS.close();
    } catch(Exception e) {}

  } //Method End

  //완료테이블 번호 날짜 제목 내용 코드
  public void showFtable(String id) {
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      msg = "select rownum, a.f_date, a.title, a.contents, a.f_id from (select * from f_seet_"+id+" order by f_date) a";
      RS = ST.executeQuery(msg);
      while (RS.next() == true) {
        int rownum = RS.getInt("rownum");
        java.util.Date f_date = RS.getDate("f_date");
        String title = RS.getString("title");
        String contents = RS.getString("contents");
        String f_id = RS.getString("f_id");

        System.out.printf("   번호\t %s\n   ", rownum);
        System.out.printf("날짜 %s\n   ", f_date);
        System.out.printf("제목 %s\n   ", title);
        System.out.printf("내용 %s\n   ", contents);
        System.out.printf("코드 %s\n   ", f_id);
        System.out.println();

      }
      CN.close();
    } catch(Exception e) {}
  } // Method End

  //공유테이블 번호 날짜 제목 내용 공유자 코드
  public void showStable(String id) {
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      msg = "select rownum, a.s_date, a.title, a.contents, a.shares, a.s_id from (select * from s_seet_"+id+" order by s_date) a";
      RS = ST.executeQuery(msg);
      while (RS.next() == true) {
        int rownum = RS.getInt("rownum");
        java.util.Date s_date = RS.getDate("s_date");
        String title = RS.getString("title");
        String contents = RS.getString("contents");
        String s_id = RS.getString("s_id");
        String shares = RS.getString("shares");

        System.out.printf("   번호\t %s\n   ", rownum);
        System.out.printf("날짜 %s\n   ", s_date);
        System.out.printf("제목 %s\n   ", title);
        System.out.printf("내용 %s\n   ", contents);
        System.out.printf("공유자 %s\n   ", shares);
        System.out.printf("코드 %s\n   ", s_id);
        System.out.println();

      }
      CN.close(); ST.close(); RS.close();
    } catch(Exception e) {}
  } // Method End

  public void searchResultShowTable(String id, String Query) {
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      msg = Query;
      RS = ST.executeQuery(msg);
      while (RS.next() == true) {
        int rownum = RS.getInt("rownum");
        java.util.Date p_date = RS.getDate("p_date");
        String title = RS.getString("title");
        String contents = RS.getString("contents");
        //String p_id = RS.getString("p_id");

        System.out.printf("   번호\t %s\n   ", rownum);
        System.out.printf("날짜 %s\n   ", p_date);
        System.out.printf("제목 %s\n   ", title);
        System.out.printf("내용 %s\n   ", contents);
        //System.out.printf("코드 %s\n   ", p_id);
        System.out.println();
      }
      CN.close(); ST.close(); RS.close();
    } catch(Exception e) {}
  }//Method End
  public void searchResultShowTable_F(String id, String Query) {
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      msg = Query;
      RS = ST.executeQuery(msg);
      while (RS.next() == true) {
        int rownum = RS.getInt("rownum");
        java.util.Date f_date = RS.getDate("f_date");
        String title = RS.getString("title");
        String contents = RS.getString("contents");
        //String p_id = RS.getString("p_id");

        System.out.printf("   번호\t %s\n   ", rownum);
        System.out.printf("날짜 %s\n   ", f_date);
        System.out.printf("제목 %s\n   ", title);
        System.out.printf("내용 %s\n   ", contents);
        //System.out.printf("코드 %s\n   ", p_id);
        System.out.println();
      }
      CN.close(); ST.close(); RS.close();
    } catch(Exception e) {}
  }//Method End

  public void searchResultShowTable_S(String id, String Query) {
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      msg = Query;
      RS = ST.executeQuery(msg);
      while (RS.next() == true) {
        int rownum = RS.getInt("rownum");
        java.util.Date s_date = RS.getDate("s_date");
        String title = RS.getString("title");
        String contents = RS.getString("contents");
        String shares = RS.getString("shares");

        System.out.printf("   번호\t %s\n   ", rownum);
        System.out.printf("날짜 %s\n   ", s_date);
        System.out.printf("제목 %s\n   ", title);
        System.out.printf("내용 %s\n   ", contents);
        System.out.printf("공유자 %s\n   ", shares);
        System.out.println();
      }
      CN.close(); ST.close(); RS.close();
    } catch(Exception e) {}
  }//Method End

} //class End
