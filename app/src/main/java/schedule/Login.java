package dbtest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Login {

  Scanner keyScan = new Scanner(System.in);
  Banner banner = new Banner();
  ShowTable showtable = new ShowTable();

  String msg;
  String id;
  String pw;
  ResultSet RS;

  public String login() {
    finalLoop : try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      banner.printbanner1("로그인");
      loop : while (true) {
        System.out.println("\n아이디와 비밀번호를 입력하여 주세요. (0:뒤로가기)");

        // 아이디 입력
        while (true) {
          System.out.print("아이디 : ");
          id = keyScan.nextLine();
          if (id.equals("0")) {break finalLoop;}
          if (id.isEmpty()) {
            System.out.println("\n아이디를 입력하여 주세요.");
          } else { break; }
        }

        // 비밀번호 입력
        while (true) {
          System.out.print("비밀번호 : ");
          pw = keyScan.nextLine();
          if (pw.isEmpty()) {
            System.out.println("\n비밀번호를 입력하여 주세요.");
          } else { break; }
        }

        // 관리자 확인
        if (id.equals("admin")) {
          msg = "select * from profile where id='"+id+"'";
          RS = ST.executeQuery(msg);
          while (RS.next() == true) {
            if (RS.getString("pw").equals(pw)) {
              System.out.println("관리자모드로 접속합니다."); break loop;
            }
          }
        }

        // 아이디, 비밀번호 검사
        msg = "select * from profile where id='"+id+"'";
        RS = ST.executeQuery(msg);
        if (RS.next() == false) {
          System.out.println("아이디가 존재하지 않습니다.");
        } else {
          msg = "select * from profile where id='"+id+"'";
          RS = ST.executeQuery(msg);
          while (RS.next() == true) {
            if (RS.getString("pw").equals(pw)) {
              System.out.println("로그인 되었습니다."); break loop;
            } else { System.out.println("비밀번호가 일치하지 않습니다.");}
          }
        }
      }
    } catch (Exception e) {}
    return id;
  }
}