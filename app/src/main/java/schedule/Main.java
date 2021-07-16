package dbtest;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    Scanner keyScan = new Scanner(System.in);
    LoginFinish loginfinish = new LoginFinish();
    SignUp signup = new SignUp();
    Find find = new Find();

    Banner banner = new Banner();
    ShowTable showtable = new ShowTable();

    while (true) {
      banner.printbanner1("일정관리 시스템");
      System.out.println("\n1. 로그인   2. 회원가입   3. 아이디/비번 찾기   0. 종료");
      System.out.print("번호 입력 : ");
      String num = keyScan.nextLine();
      if (num.equals("1")) {loginfinish.loginfinish();}
      else if (num.equals("2")) {signup.signup();}
      else if (num.equals("3")) {find.find();}
      else if (num.equals("0")) {System.out.println("\n시스템을 종료합니다."); break;}
      else {System.out.println("\n잘못 입력하셨습니다. 다시 입력하여 주세요.");}
    }
  }
}