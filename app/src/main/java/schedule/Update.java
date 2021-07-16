package dbtest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Update {

  Scanner keyScan = new Scanner(System.in);
  Banner banner = new Banner();
  ShowTable showtable = new ShowTable();

  ResultSet RS;
  String msg;
  String number;
  String title;
  String contents;
  String p_date;
  boolean check;

  public void update(String id) {
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      // 일정 노출
      banner.printbanner1("일정수정");
      msg = "select rownum, a.p_date, a.title, a.p_id from (select * from seet_"+id+" order by p_date) a";
      RS = ST.executeQuery(msg);
      showtable.showtableCode(id);

      // 일정 존재 여부
      msg = "select * from seet_"+id;
      RS = ST.executeQuery(msg);
      if (RS.next() == false) {
        System.out.println("\n게시글이 존재하지 않습니다.");
      } else {

        // 일정 번호 입력
        while (true) {
          System.out.println("\n수정하실 코드를 입력하여 주세요. (0:뒤로가기)");
          System.out.print("코드 입력 : ");
          number = keyScan.nextLine();
          if (number.isEmpty()) {
            System.out.println("\n코드를 입력하여 주세요."); continue;
          }
          check = Pattern.matches("^[0-9]*$", number);
          if (check != true) {
            System.out.println("\n잘못 입력하셨습니다. 숫자만 입력하여 주세요.");
            continue;}
          msg = "select rownum, p_date, title, contents from seet_"+id+" where p_id ="+number;
          RS = ST.executeQuery(msg);
          if (number.equals("0")) { break; }
          if (RS.next() == false) {
            System.out.println("\n코드를 잘못 입력하셨습니다. 다시 입력하여 주세요."); continue; 
          }

          // 수정 필드 입력
          while (true) {
            System.out.println("\n수정하실 필드를 선택해주세요.");
            System.out.println("1. 날짜   2. 제목   3. 내용   0. 뒤로가기");
            System.out.print("번호 입력 : ");
            number = keyScan.nextLine();
            if (number.equals("0")) { break; }

            // 날짜 수정
            if (number.equals("1")) {
              System.out.println("\n변경하실 날짜를 입력해주세요.");
              while (true) {
                System.out.print("날짜 입력(YYYY-MM-DD) : ");
                p_date = keyScan.nextLine();
                check = Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", p_date);
                if (check != true) {
                  System.out.println("\n잘못 입력하셨습니다. YYYY-MM-DD 형식으로 입력해주세요.");
                  continue;}
                break;
              }
              msg = "update seet_"+id+" set p_date='"+p_date+"' where p_id = "+ number;
              ST.executeUpdate(msg);
              System.out.println("\n날짜 변경이 완료되었습니다.");
            }

            // 제목 수정
            else if (number.equals("2")) {
              System.out.println("\n변경하실 제목을 입력하여 주세요.");
              while (true) {
                System.out.print("제목 입력 : ");
                title = keyScan.nextLine();
                if (title.isEmpty()) {
                  System.out.println("\n제목이 비어있습니다. 입력하여 주세요.");
                } else {break;}
              }
              msg = "update seet_"+id+" set title='"+title+"' where p_id = "+ number;
              ST.executeUpdate(msg);
              System.out.println("\n제목 변경이 완료되었습니다.");
            }

            // 내용 수정
            else if (number.equals("3")) {
              System.out.println("\n변경하실 내용을 입력하여 주세요.");
              while (true) {
                System.out.print("내용 입력 : ");
                contents = keyScan.nextLine();
                if (contents.isEmpty()) {
                  System.out.println("\n내용이 비어있습니다. 입력하여 주세요.");
                } else {break;}
              }
              msg = "update seet_"+id+" set contents='"+contents+"' where p_id = "+ number;
              ST.executeUpdate(msg);
              System.out.println("\n내용 변경이 완료되었습니다.");
            }

            // 잘못 입력 시
            else {System.out.println("\n잘못 입력하셨습니다. 다시 입력하여 주세요.");}
          }
        }
      }
    } catch (Exception e) {}
  }
}