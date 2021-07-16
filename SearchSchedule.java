package schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;

//from view.java
public class SearchSchedule {

  Scanner sc = new Scanner(System.in);
  Banner banner = new Banner();
  ShowTable showtable = new ShowTable();

  String msg="";

  public void search_menu(String id) {
    banner.printbanner1("일정 검색");
    searchLoop : while (true) {
      try {
        System.out.println("선택할 항목을 입력하여 주세요.\n");
        System.out.println("1.제목으로 검색  2.날짜로 검색  3.내용으로 검색  0.뒤로가기");
        System.out.print("번호 입력 >> ");
        int menuNum = Integer.parseInt(sc.nextLine());

        switch(menuNum) {
          case 1 : this.search_title(id); break;
          case 2 : this.search_date(id); break;
          case 3 : this.search_contents(id); break;
          case 0 : break searchLoop;
        }
      } catch(Exception e) {} // try-catch End
      System.out.println();
    } // Loop end
  } // menu method end

  public void search_title(String id) {
    banner.printbanner2("제목으로 검색");
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
      ResultSet RS;

      // 게시물 존재 여부확인
      msg = "select * from seet_"+id;
      RS = ST.executeQuery(msg);
      if (RS.next() == false) {
        System.out.println("\n게시글이 존재하지 않습니다."); }
      else {
        while (true) {
          System.out.print("검색할 제목을 입력하세요 (0: 뒤로가기) : ");
          String keyword = sc.nextLine();
          if (keyword.equals("0")) {return;}
          msg = "select * from seet_"+id+" where title='"+keyword+"'";
          RS = ST.executeQuery(msg);
          RS.last();
          int searchResultCount = RS.getRow();
          RS.beforeFirst();
          if(searchResultCount == 0) {
            System.out.printf("검색하신 %s 에 대한 결과가 없습니다.\n\n", keyword);
          }
          else {
            System.out.printf(" 총 %d 건의 검색 결과가 있습니다.\n\n", searchResultCount);
            showtable.searchResultShowTable(id, "select rownum, a.p_date, a.title, a.contents, a.p_id from (select * from seet_"+id+" order by p_date) a where title = '"+keyword+"'");
          }
        }
      }
    } catch (Exception e) {System.out.println(e);} // try-catch End
  } // search_title Method End

  public void search_date(String id) {
    banner.printbanner2("날짜로 검색");
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
      ResultSet RS;

      // 게시물 존재 여부확인
      msg = "select * from seet_"+id;
      RS = ST.executeQuery(msg);
      if (RS.next() == false) {
        System.out.println("\n게시글이 존재하지 않습니다."); }
      else {
        while (true) {
          System.out.print("검색할 날짜를 입력하세요.[YYYY-MM-DD] (0: 뒤로가기) : ");
          String keyword = sc.nextLine();

          if (keyword.equals("0")) {return;}
          else if(Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", keyword) != true){
            System.out.println("\n잘못 입력하셨습니다. YYYY-MM-DD 형식으로 입력해주세요.");
            continue;
          }
          else {
            msg = "select * from seet_"+id+" where p_date='"+keyword+"'";
            RS = ST.executeQuery(msg);
            RS.last();
            int searchResultCount = RS.getRow();
            RS.beforeFirst();
            if(searchResultCount == 0) {
              System.out.printf("검색하신 %s 에 대한 결과가 없습니다.\n\n", keyword);
            }
            else {
              System.out.printf(" 총 %d 건의 검색 결과가 있습니다.\n\n", searchResultCount);
              showtable.searchResultShowTable(id, "select rownum, a.p_date, a.title, a.contents, a.p_id from (select * from seet_"+id+" order by p_date) a where p_date = '"+keyword+"'");
            }
          }
        }
      }
    } catch (Exception e) {System.out.println(e);} // try-catch End
  } // search_date Method End

  public void search_contents(String id) {
    banner.printbanner2("내용으로 검색");
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
      ResultSet RS;
      PreparedStatement PS;

      // 게시물 존재 여부확인
      msg = "select * from seet_"+id;
      RS = ST.executeQuery(msg);
      if (RS.next() == false) {
        System.out.println("\n게시글이 존재하지 않습니다."); }
      else {
        while (true) {
          System.out.print("검색할 내용을 입력하세요 (0: 뒤로가기) : ");
          String keyword = sc.nextLine();
          if (keyword.equals("0")) {return;}
          msg = "select * from seet_"+id+" where title='"+keyword+"'";
          RS = ST.executeQuery(msg);
          RS.last();
          int searchResultCount = RS.getRow();
          RS.beforeFirst();
          if(searchResultCount == 0) {
            System.out.printf("검색하신 %s 에 대한 결과가 없습니다.\n\n", keyword);
          }
          else {
            System.out.printf(" 총 %d 건의 검색 결과가 있습니다.\n\n", searchResultCount);

            String keyword2 = "%%"+keyword+"%%";
            showtable.searchResultShowTable(id, "select rownum, a.p_date, a.title, a.contents, a.p_id from (select * from seet_"+id+" order by p_date) a where contents Like '"+keyword2+"'");
          }
        } // search While end
      } // RS check End
    } catch (Exception e) {System.out.println(e);} // try-catch End
  } // search_contents Method End
} // Class End


