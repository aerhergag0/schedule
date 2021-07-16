package schedule;

import java.util.Scanner;

public class LoginFinish extends DB_Print{

  Scanner keyScan = new Scanner(System.in);
  Login login = new Login();
  View view = new View();
  View_F view_f = new View_F();
  View_S view_s = new View_S();
  Edit edit = new Edit();
  Leave leave = new Leave();
  Manager manager = new Manager();
  Notice notice = new Notice();
  Banner banner = new Banner();
  ShowTable showtable = new ShowTable();
  Community community = new Community();

  public void loginfinish() {
    loop : while (true) {
      login.login();
      String id = login.id;
      if (id.equals("0")) { break; }
      if (id.equals("admin")) { manager.manager(); break;}

      notice.view_count(id);
      notice.s_view_count(id);

      while (true) {
        banner.printbanner1("메뉴");
        System.out.println("[날짜별 일정 개수]");
        System.out.println();
        db_print("SELECT to_char(a.p_date,'YY-MM-DD') AS Schedule , to_char(COUNT(a.p_date),'999') AS Count FROM (SELECT * FROM seet_"+id+" order by p_date) a GROUP BY a.p_date");

        System.out.println("\n메뉴를 선택하여 주세요.");
        System.out.println("\n1.개인일정  2.완료일정  3.공유일정  4.회원정보수정  5.회원탈퇴  6. 커뮤니티  0.로그아웃\n");
        System.out.print("메뉴 입력 : ");
        String num = keyScan.nextLine();
        if (num.equals("1")) {view.view(id);}
        else if(num.equals("2")) {view_f.view_f(id);}
        else if(num.equals("3")) {view_s.view_s(id);}
        else if(num.equals("4")) {edit.edit(id);}
        else if(num.equals("5")) {leave.leave(id); break loop;}
        else if(num.equals("6")) {community.community(id);}
        else if(num.equals("0")) {break loop;}
        else {System.out.println("\n잘못 입력하셨습니다. 다시 입력하여 주세요.");}
      }
    }
  }
}