package schedule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Find {

  Scanner keyScan = new Scanner(System.in);
  Banner banner = new Banner();
  ShowTable showtable = new ShowTable();

  ResultSet RS;
  String msg;
  String num;
  String name;
  String email;
  String id;
  String question;
  String answer;
  String pw;

  public void find() {
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      banner.printbanner1("아이디/비밀번호 찾기");
      while (true) {
        System.out.println("\n찾으실 항목을 선택하여 주세요.");
        System.out.println("1. 아이디   2. 비밀번호   0. 뒤로가기");
        System.out.print("\n항목선택 : ");
        num = keyScan.nextLine();

        if (num.equals("1")) {
          System.out.println("\n회원님의 이름을 입력하여 주세요.");
          while (true) {
            System.out.print("이름 입력 : ");
            name = keyScan.nextLine();
            if (name.isEmpty()) {
              System.out.println("\n이름을 입력하여 주세요.");
            } else { break; }
          }

          System.out.println("\n가입당시 입력한 이메일 주소를 입력하여 주세요.");
          while (true) {
            System.out.print("이메일 입력 : ");
            email = keyScan.nextLine();
            if (email.isEmpty()) {
              System.out.println("\n이메일을 입력하여 주세요.");
            } else { break; }
          }

          msg = "select * from profile where email='"+email+"'";
          RS = ST.executeQuery(msg);
          if (RS.next() == false) {
            System.out.println("\n아이디가 존재하지 않습니다.");
          } else {
            msg = "select * from profile where email='"+email+"'";
            RS = ST.executeQuery(msg);
            while (RS.next() == true) {
              if (RS.getString("name").equals(name)) {
                msg = "select * from profile where email='"+email+"'";
                RS = ST.executeQuery(msg);
                while (RS.next() == true) {
                  id = RS.getString("id");
                  System.out.println("\n회원님의 아이디는 "+id+" 입니다."); 
                } break;
              } else { System.out.println("\n이름이 일치하지 않습니다.");}
            }
          }
        }

        else if (num.equals("2")) {
          System.out.println("\n회원님의 아이디를 입력하여 주세요.");
          while (true) {
            System.out.print("아이디 입력 : ");
            id = keyScan.nextLine();
            if (id.isEmpty()) {
              System.out.println("\n아이디를 입력하여 주세요.");
            } else { break; }
          }

          msg = "select * from profile where id='"+id+"'";
          RS = ST.executeQuery(msg);
          if (RS.next() == false) {
            System.out.println("\n아이디가 존재하지 않습니다.");
          } else {
            msg = "select * from profile where id='"+id+"'";
            RS = ST.executeQuery(msg);
            while (RS.next() == true) {
              question = RS.getString("question");
              System.out.println("\n"+question); 
            } 
            while (true) {
              System.out.print("답변 입력 : ");
              answer = keyScan.nextLine();
              if (answer.isEmpty()) {
                System.out.println("\n답변을 입력하여 주세요.");
              } else { break; }
            }

            msg = "select * from profile where id='"+id+"'";
            RS = ST.executeQuery(msg);
            while (RS.next() == true) {
              if (RS.getString("answer").equals(answer)) {
                msg = "select * from profile where id='"+id+"'";
                RS = ST.executeQuery(msg);
                while (RS.next() == true) {
                  pw = RS.getString("pw");
                  System.out.println("\n회원님의 비밀번호는 "+pw+" 입니다."); 
                } break;
              } else { System.out.println("\n답변이 틀렸습니다.");}
            }
          }
        }

        else if (num.equals("0")) {break;}
        else {System.out.println("\n잘못 입력하셨습니다. 다시 입력하여 주세요.");}
      }
    } catch (Exception e) {}
  }
}