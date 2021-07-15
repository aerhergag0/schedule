package schedule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Share {

  Scanner keyScan = new Scanner(System.in);
  Banner banner = new Banner();
  ShowTable showtable = new ShowTable();

  String msg;
  String number;
  ResultSet RS;
  String s_id;
  boolean check;
  String share;
  java.util.Date p_date;
  String title;
  String contents;
  String s_name;

  public void share (String id) {
    finalLoop : try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      // 일정 노출
      banner.printbanner1("일정공유");
      msg = "select rownum, a.p_date, a.title, a.p_id from (select * from seet_"+id+" order by p_date) a";
      RS = ST.executeQuery(msg);
      showtable.showtableCode(id);

      msg = "select * from seet_"+id;
      RS = ST.executeQuery(msg);
      if (RS.next() == false) {
        System.out.println("\n게시글이 존재하지 않습니다.");
      } else {

        // 일정 번호 입력 받기
        while (true) {
          System.out.println("\n공유하실 코드를 입력하여 주세요. (0:뒤로가기)");
          System.out.print("코드 입력 : ");
          number = keyScan.nextLine();

          if(number.equals("0")) { break finalLoop;}

          // 일정 번호 유효성 체크
          if (number.isEmpty()) {
            System.out.println("\n코드를 입력하여 주세요."); continue;
          }
          check = Pattern.matches("^[0-9]*$", number);
          if (check != true) {
            System.out.println("\n잘못 입력하셨습니다. 숫자만 입력하여 주세요.");
            continue;}
          msg = "select rownum, p_date, title, contents from seet_"+id+" where p_id ="+number;
          RS = ST.executeQuery(msg);
          if (RS.next() == false) {
            System.out.println("\n코드를 잘못 입력하셨습니다. 다시 입력하여 주세요."); continue; 
          }
          break;
        }

        // 상대방 아이디 입력 받기
        System.out.println("\n공유하실 상대방의 아이디를 입력하여 주세요.");
        while (true) {
          System.out.print("상대방 아이디 입력 : ");
          s_id = keyScan.nextLine();

          // 상대방 아이디 유효성 체크
          if (s_id.isEmpty()) {
            System.out.println("\n상대방 아이디를 입력하여 주세요."); continue;
          }
          msg = "select * from profile where id='"+s_id+"'";
          RS = ST.executeQuery(msg);
          if (RS.next() == false) {
            System.out.println("\n아이디가 존재하지 않습니다.");
          } else { break; }
        }

        // 내이름 불러오기
        msg = "select name from profile where id='"+id+"'";
        RS = ST.executeQuery(msg);
        while (RS.next() == true) {
          share = RS.getString("name");
        }

        // 공유할 일정 불러오기
        msg = "select p_date, title, contents from seet_"+id+" where p_id = "+number;
        RS = ST.executeQuery(msg);
        while (RS.next() == true) {
          p_date = RS.getDate("p_date");
          title = RS.getString("title");
          contents = RS.getString("contents");
        }

        // 상대방에게 공유 일정과 내이름 전송
        msg = "insert into s_seet_"+s_id+" values ('"+p_date+"', '"+title+"', '"+contents+"', '"+share+"', seq_s_seet_"+s_id+".nextval)";
        ST.executeUpdate(msg);

        // 내이름 불러오기
        msg = "select name from profile where id='"+s_id+"'";
        RS = ST.executeQuery(msg);
        while (RS.next() == true) {
          s_name = RS.getString("name");
        }

        System.out.println("\n"+s_name+" 님에게 공유가 완료되었습니다.");
      }
    } catch (Exception e) {System.out.println(e);}
  }
}
