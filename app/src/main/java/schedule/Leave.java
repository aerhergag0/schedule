package schedule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Leave {

  Scanner keyScan = new Scanner(System.in);
  Banner banner = new Banner();
  ShowTable showtable = new ShowTable();

  ResultSet RS;
  String msg;
  String pw;

  public void leave (String id) {
    finalLoop : try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      banner.printbanner1("회원탈퇴");

      // 비밀번호 입력
      System.out.println("\n개인정보 보호를 위해 비밀번호를 입력해주세요. (0:뒤로가기)");
      loop : while (true) {
        while (true) {
          System.out.print("비밀번호 : ");
          pw = keyScan.nextLine();
          if (pw.equals("0")) { break finalLoop;}
          if (pw.isEmpty()) {
            System.out.println("\n비밀번호를 입력하여 주세요.");
          } else { break; }
        }

        // 비밀번호 검사
        msg = "select * from profile where id='"+id+"'";
        RS = ST.executeQuery(msg);
        while (RS.next() == true) {
          if (RS.getString("pw").equals(pw)) {
            break loop;
          } else { System.out.println("\n비밀번호가 일치하지 않습니다.");}
        }
      }

      // 탈퇴여부 질문
      while (true) {
        System.out.println("\n!!!!! 정말로 회원탈퇴 하시겠습니까? (Y/N) !!!!!");
        System.out.print("Y/N 입력 : ");
        String yn = keyScan.nextLine();
        if (yn.equals("y")||yn.equals("Y")) {
          msg = "drop table seet_"+id;
          ST.executeUpdate(msg);
          msg = "drop table s_seet_"+id;
          ST.executeUpdate(msg);
          msg = "drop table f_seet_"+id;
          ST.executeUpdate(msg);
          msg = "drop sequence seq_seet_"+id;
          ST.executeUpdate(msg);
          msg = "drop sequence seq_s_seet_"+id;
          ST.executeUpdate(msg);
          msg = "drop sequence seq_f_seet_"+id;
          ST.executeUpdate(msg);
          msg = "delete from profile where id = '"+id+"'";
          ST.executeUpdate(msg);
          System.out.println("\n회원탈퇴가 완료되었습니다.");
          break;
        } else if (yn.equals("n")||yn.equals("N")) {
          System.out.println("\n회원탈퇴가 취소되었습니다.");
          break;
        } else {
          System.out.println("\n잘못 입력하셨습니다. 다시 입력하여 주세요.");
        }
      }
    } catch (Exception e) {System.out.println(e);}
  }
}
