package schedule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;

public class View_F {
  Scanner sc = new Scanner(System.in);
  Banner banner = new Banner();
  ShowTable showtable = new ShowTable();

  ResultSet RS;
  String msg;
  String number;
  String title;
  String contents;
  String p_date;
  boolean check;

  /* 이테이블은 로그인을 하게되면  // 최종메뉴 System.out.println("1.개인일정 2.완료일정 3.공유일정 4.회원정보관리 0.로그아웃");
  여기서 완료일정을 누르면 보여지는 것임 

  들어오면 먼저 리스트를 보여주고 삭제할수있고 뒤로가기할수있고 ( 삭제하기, 뒤로가기(0) )*/

  public void view_f(String id){
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();
      banner.printbanner1("완료일정");
      f_view(id);//f_view 값을 불러옴 login id 값을줌

      // 일정 존재 여부
      msg = "select * from f_seet_"+id;
      RS = ST.executeQuery(msg);
      if (RS.next() == false) {
        System.out.println("\n게시글이 존재하지 않습니다.");
      } else {

        while(true) {
          System.out.println("\n메뉴를 선택하여 주세요.");
          System.out.println("\n1. 완료일정 삭제\n0. 뒤로가기\n");
          System.out.print("메뉴 입력 : ");
          String i = sc.nextLine();
          if (i.equals("1")) {f_delete(id);break;}
          else if (i.equals("0")) {
            return;
          }

          else {
            System.out.println("\n올바르지 않은 번호입니다. 다시 입력하여 주세요."); 
          }
        }
      }
    }catch(Exception e) {}
  }






  public void f_delete(String id){
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      // 일정 노출
      banner.printbanner1("완료일정삭제");



      // 일정 번호 입력
      while (true) {
        System.out.println("\n삭제하실 코드를 입력하여 주세요. (0:뒤로가기)");
        System.out.print("코드 입력 : ");
        number = sc.nextLine();
        if (number.isEmpty()) {
          System.out.println("\n코드를 입력하여 주세요."); continue;
        }
        check = Pattern.matches("^[0-9]*$", number);
        if (check != true) {
          System.out.println("\n잘못 입력하셨습니다. 숫자만 입력하여 주세요.");
          continue;}
        msg = "select * from f_seet_"+id+" where f_id ="+number;
        RS = ST.executeQuery(msg);
        if (number.equals("0")) { break; }
        if (RS.next() == false) {
          System.out.println("\n코드를 잘못 입력하셨습니다. 다시 입력하여 주세요."); continue; 
        }

        msg = "delete from f_seet_"+id+" where f_id = "+number;
        ST.executeUpdate(msg);
        System.out.println("\n삭제가 완료되었습니다.");

      } // 일정 삭제 반복문 end



    }catch (Exception e) {System.out.println(e);} // try-catch end
  }








  public void f_view(String id) {
    System.out.println("완료 일정 목록을 출력합니다.");
    try{
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();
      ST = CN.createStatement();
      String msg ="select * from f_seet_"+id+" ";
      RS = ST.executeQuery(msg);
      showtable.showFtable(id);

    }catch(Exception e) {System.out.println("완료일정이 없습니다.");}
  }
}



















