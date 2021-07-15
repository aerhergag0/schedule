package schedule;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SignUp {

  Banner banner = new Banner();

  Scanner keyScan = new Scanner(System.in);
  String msg;
  String name;
  String id;
  String pw;
  String question;
  String answer;
  String email;
  String phone;
  boolean check;

  public void signup() {
    finalLoop : try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      banner.printbanner1("회원 가입");

      // 이름 작성
      System.out.println("\n이름을 입력하여 주세요. (0:뒤로가기)");
      while (true) {
        System.out.print("이름 : ");
        name = keyScan.nextLine();
        if (name.equals("0")) {break finalLoop;}
        if (name.isEmpty()) {
          System.out.println("\n이름이 비었습니다. 이름을 입력하여 주세요.");
        } else {break;}
      }

      // 아이디 작성
      System.out.println("\n아이디를 입력하여 주세요.");
      while (true) {
        try {
          while (true) {
            System.out.print("아이디 : ");
            id = keyScan.nextLine();
            check = Pattern.matches("^[0-9a-zA-Z]*$", id);
            int a = id.length();
            if (check != true) {
              System.out.println("\n영어와 숫자만 적어주세요.");
              continue;}
            if (a > 20 || a < 8) {
              System.out.println("\n아이디는 최소 8자에서 최대 20자 사이 입니다.");
              continue;}
            break;
          }
          msg = "insert into profile (id) values ('"+id+"')";
          ST.executeUpdate(msg); break;
        } catch (Exception e) {System.out.println("\n동일한 아이디가 존재합니다. 다른 아이디를 입력하여 주세요."); continue;}
      }

      // 비밀번호 작성
      System.out.println("\n비밀번호를 입력하여 주세요.");
      while(true) {
        System.out.print("비밀번호 : ");
        pw = keyScan.nextLine();
        check = Pattern.matches("^[0-9a-zA-Z]*$", pw);
        int a = pw.length();
        if (check != true) {
          System.out.println("\n영어와 숫자만 적어주세요.");
          continue;}
        if (a > 20 || a < 8) {
          System.out.println("\n비밀번호는 최소 8자에서 최대 20자 사이 입니다.");
          continue;}
        System.out.print("비밀번호 확인 : ");
        String pw_check = keyScan.nextLine();
        if (pw.equals(pw_check)) {
          break; } else {
            System.out.println("\n비밀번호가 일치하지 않습니다. 비밀번호를 다시 입력하여 주세요.");
          }
      }

      // 비밀번호 질답 작성
      System.out.println("\n비밀번호 찾기에 대한 질문을 골라주세요.");
      while (true) {
        System.out.println("\n1. 내가 태어난 곳은?");
        System.out.println("2. 우리 아버지 이름은?");
        System.out.println("3. 내가 다닌 초등학교 이름은?");
        System.out.println("4. 나의 별명은?");
        System.out.println("5. 내 신체만의 비밀은?");
        System.out.print("질문 선택 : ");
        question = keyScan.nextLine();
        if (question.equals("1")) { question = "내가 태어난 곳은?"; break;}
        else if (question.equals("2")) { question = "우리 아버지 이름은?"; break;}
        else if (question.equals("3")) { question = "내가 다닌 초등학교 이름은?"; break;}
        else if (question.equals("4")) { question = "나의 별명은?"; break;}
        else if (question.equals("5")) { question = "내 신체만의 비밀은?"; break;}
        else {System.out.println("\n잘못 입력하셨습니다. 다시 입력하여 주세요.");
        }
      }

      System.out.println("\n질문에 대한 답변을 적어주세요.");
      while (true) {
        System.out.print("답변 작성 : ");
        answer = keyScan.nextLine();
        if (name.isEmpty()) {
          System.out.println("\n이름이 비었습니다. 이름을 입력하여 주세요.");
        } else {break;}
      }

      // 이메일 작성
      System.out.println("\n이메일을 입력하여 주세요.");
      while(true) {
        System.out.print("이메일 : ");
        email = keyScan.nextLine();
        check = Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", email);
        if (check == true) {
          break;
        } else {System.out.println("\n이메일 형식이 올바르지 않습니다. 다시 입력하여 주세요.");
        }
      }

      // 휴대전화번호 작성
      System.out.println("\n휴대전화번호를 입력하여 주세요.");
      while (true) {
        System.out.print("휴대전화번호 : ");
        phone = keyScan.nextLine();
        check = Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", phone);
        if(check != true) {
          System.out.println("\n휴대전화번호 형식이 올바르지 않습니다. -를 이용하여 다시 입력하여 주세요.");
          continue;
        }
        break;
      }

      // 회원정보 업데이트
      msg = "update profile set "
          + "name='"+name+"', "
          + "pw='"+pw+"', "
          + "question='"+question+"', "
          + "answer='"+answer+"', "
          + "email='"+email+"', "
          + "phone='"+phone+"' "
          + "where id='"+id+"'";
      ST.executeUpdate(msg);

      // 회원전용 개인일정 생성
      msg = "create table seet_"+id+"("
          + "p_date date, "
          + "title varchar2(100), "
          + "contents varchar2(4000), "
          + "p_id number)";
      ST.executeUpdate(msg);

      // 회원전용 완료일정 생성
      msg = "create table f_seet_"+id+"("
          + "f_date date, "
          + "title varchar2(100), "
          + "contents varchar2(4000), "
          + "f_id number)";
      ST.executeUpdate(msg);

      // 회원전용 공유일정 생성
      msg = "create table s_seet_"+id+"("
          + "s_date date, "
          + "title varchar2(100), "
          + "contents varchar2(4000), "
          + "shares varchar2(20), "
          + "s_id number)";
      ST.executeUpdate(msg);

      // 개인일정 시퀀스 생성
      msg = "create sequence seq_seet_"+id;
      ST.executeUpdate(msg);

      // 완료일정 시퀀스 생성
      msg = "create sequence seq_f_seet_"+id;
      ST.executeUpdate(msg);

      // 공유일정 시퀀스 생성
      msg = "create sequence seq_s_seet_"+id;
      ST.executeUpdate(msg);

      System.out.println("\n"+name+" 회원님의 아이디는 "+id+" 입니다.");
      System.out.println("회원가입이 완료되었습니다.\n");
    } catch (Exception e) {System.out.println(e);}
  }
}