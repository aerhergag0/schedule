package dbtest;

import java.sql.*;
import java.util.*;
import java.util.regex.*;

public class Manager{
  Scanner sc = new Scanner(System.in);
  Banner banner = new Banner();
  ShowTable showtable = new ShowTable();


  String msg;
  ResultSet RS = null;
  boolean cheak = false;

  public void manager(){
    System.out.println("\n------------------관리자모드------------------");

    while(true) {
      UserQuestionalarm();
      System.out.println("\n메뉴를 입력하여 주세요.");
      System.out.println("\n1. 회원수정\n2. 회원삭제\n3. 회원목록\n4. 관리자 문의 \n0. 로그아웃");
      System.out.print("메뉴 입력 : ");
      String i = sc.nextLine(); // switch를 if로 바꿔주고
      if (i.equals("1")) {
        update();
      }
      else if (i.equals("2")) {
        list();
        delete();
      }
      else if (i.equals("3")) {
        list();
      }
      else if (i.equals("4")) {
        System.out.println();
        UserQuestionView();
        UserQuestionfunction();
      }
      else if (i.equals("0")) { System.out.println("\n로그아웃 되었습니다.");
      return;
      }else {
        System.out.println("\n올바른 번호를 입력하여 주세요.");
      }
    }//switch 종료
  }//manager 종료


  public void update() {
    try { 
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();


      String del;
      String a;
      String b;

      System.out.println("\n------------------회원수정------------------");

      loop:while(true) {
        System.out.println("\n1. 이름 수정\n2. 비밀번호 수정\n3. 이메일 수정\n4. 휴대전화번호 수정\n0. 뒤로가기");
        System.out.println("\n메뉴를 입력하여 주세요.");
        System.out.print("메뉴 입력 : ");
        String i = sc.nextLine();






        switch(i) {
          case "1":
            System.out.println("\n------------------이름 수정------------------");
            System.out.println("\n수정하실 회원의 아이디를 입력하여 주세요. (0:뒤로가기)");
            while(true) {
              System.out.print("아이디 입력 : ");
              a = sc.nextLine();
              if(a.isEmpty()) {
                System.out.println("\n잘못입력하였습니다. 다시 입력하여 주세요."); continue;
              }
              if(a.equals("0")) {
                break;
              }
              msg = "select * from profile where id='"+a+"'";
              RS = ST.executeQuery(msg);
              if (RS.next() == false) {
                System.out.println("\n아이디가 존재하지 않습니다."); continue;
              } else {
                msg = "select * from profile where id='"+a+"'";
                RS = ST.executeQuery(msg);
                System.out.println("\n바꾸실 이름을 입력하여 주세요.");
                while(true) {
                  System.out.print("이름 입력 : ");
                  b = sc.nextLine();
                  if(b.isEmpty()) { System.out.println("\n이름이 비었습니다. 다시 입력하여 주세요.");
                  continue;
                  }
                  msg = "update profile set Name='"+b+"'where ID ='"+a+"'";
                  ST = CN.createStatement();
                  ST.executeUpdate(msg);
                  System.out.println("\n이름 변경이 완료되었습니다.");
                  break;
                }break;
              }
            }break;







          case "2":
            System.out.println("\n------------------비밀번호 수정------------------");
            System.out.println("\n수정하실 회원의 아이디를 입력하여 주세요. (0:뒤로가기)");
            while(true) {
              System.out.print("아이디 입력 : ");
              a = sc.nextLine();
              if(a.isEmpty()) {
                System.out.println("\n잘못입력하였습니다. 다시 입력하여 주세요."); continue;
              }
              if(a.equals("0")) {
                break;
              }
              msg = "select * from profile where id='"+a+"'";
              RS = ST.executeQuery(msg);
              if (RS.next() == false) {
                System.out.println("\n아이디가 존재하지 않습니다.");
                continue;
              } else {
                msg = "select * from profile where id='"+a+"'";
                RS = ST.executeQuery(msg);
                System.out.println("\n바꾸실 비밀번호를 입력하여 주세요.");
                while(true) {
                  System.out.print("비밀번호 입력 : ");
                  b = sc.nextLine();
                  if(b.isEmpty()) {
                    System.out.println("\n비밀번호가 비었습니다. 다시 입력하여 주세요."); continue;
                  }
                  if (b.length() > 20 || b.length() < 8) {
                    System.out.println("\n변경하실 비밀번호를 최소 8자에서 최대 20자 사이로 입력해주세요.");
                    continue;
                  }
                  msg = "update profile set PW='"+b+"'where ID='"+a+"'";
                  ST = CN.createStatement();
                  ST.executeUpdate(msg);
                  System.out.println("\n비밀번호 변경이 완료되었습니다.");
                  break;                
                }
              }break;
            }break;





          case "3":
            System.out.println("\n------------------이메일 수정------------------");
            System.out.println("\n수정하실 회원의 아이디를 입력하여 주세요. (0:뒤로가기)");
            while(true) {
              System.out.print("아이디 입력 : ");
              a = sc.nextLine();
              if(a.equals("0")) {
                break;
              }
              if(a.isEmpty()) {
                System.out.println("\n잘못입력하였습니다. 다시 입력하여 주세요."); continue;
              }
              msg = "select * from profile where id='"+a+"'";
              RS = ST.executeQuery(msg);
              if (RS.next() == false) {
                System.out.println("\n아이디가 존재하지 않습니다.");
                continue;
              } else {
                msg = "select * from profile where id='"+a+"'";
                RS = ST.executeQuery(msg);
              }
              System.out.println("\n바꾸실 이메일을 입력하여 주세요.");
              while(true) {
                System.out.print("이메일 입력 : ");
                b = sc.nextLine();
                if(b.isEmpty()) {System.out.println("\n이메일이 비었습니다. 다시 입력하여 주세요.");
                continue;
                }
                cheak = Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?",b);
                if(cheak == true) {
                  msg = "update profile set EMAIL='"+b+"'where ID ='"+a+"'";
                  ST = CN.createStatement();
                  ST.executeUpdate(msg);
                  System.out.println("\n이메일 변경이 완료되었습니다.");
                  break;
                }else{System.out.println("\n유효하지 않은 이메일 주소 입니다."); continue;}
              }break;
            }break;








          case "4":
            System.out.println("\n------------------휴대전화번호 수정------------------");
            System.out.println("\n수정하실 회원의 아이디를 입력하여 주세요. (0:뒤로가기)");
            while(true) {
              System.out.print("아이디 입력 : ");
              a = sc.nextLine();
              if(a.isEmpty()) {
                System.out.println("\n잘못입력하였습니다. 다시 입력하여 주세요."); continue;
              }
              if(a.equals("0")) {
                break;
              }

              msg = "select * from profile where id='"+a+"'";
              RS = ST.executeQuery(msg);
              if (RS.next() == false) {
                System.out.println("\n아이디가 존재하지 않습니다.");
                continue;
              } else {
                msg = "select * from profile where id='"+a+"'";
                RS = ST.executeQuery(msg);
              }
              System.out.println("\n바꾸실 휴대전화번호를 입력하여 주세요.");
              while(true) {
                System.out.print("휴대전화번호 입력 : ");
                b = sc.nextLine();
                if(b.isEmpty()) {System.out.println("\n휴대전화번호가 비었습니다. 다시 입력하여 주세요.");
                continue;}
                if(b.equals("0")) {
                  break;
                }
                cheak = Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$",b);
                if(cheak == false) {
                  System.out.println("\n유효하지 않은 휴대전화번호입니다. -를 사용하여 입력하여 주세요."); 
                  continue;
                }else{
                  msg = "update profile set PHONE='"+b+"'where ID ='"+a+"'";
                  ST = CN.createStatement();
                  ST.executeUpdate(msg);

                  System.out.println("\n휴대전화번호 변경이 완료되었습니다.");
                  break;
                }
              }break;
            } break;







          case "0" : System.out.println("\n관리자 수정 기능을 취소합니다.");return; 
          default : System.out.println("\n잘못 입력하였습니다. 다시 입력하여 주세요.");
        }//switch문 종료
      }






    }catch(Exception ex) { }      
  }












  public void delete() {
    try{
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();
      String del;


      System.out.println("\n------------------회원삭제------------------");
      System.out.println("\n삭제하실 회원의 아이디를 입력하여 주세요. (0:뒤로가기)");
      while(true) {
        System.out.print("아이디 입력 : ");
        del = sc.nextLine();
        if(del.isEmpty()) {
          System.out.println("\n잘못입력하였습니다. 다시 입력하여 주세요."); continue;
        }
        if(del.equals("0")) {
          break;
        }
        msg = "select * from profile where id='"+del+"'";
        RS = ST.executeQuery(msg);
        if (RS.next() == false) {
          System.out.println("\n아이디가 존재하지 않습니다."); continue;
        } else {
          msg = "delete from profile where id='"+del+"'";
          RS = ST.executeQuery(msg);
          msg = "drop table f_seet_"+del+"";
          ST.executeUpdate(msg); //삭제한 회원의 완료 테이블 삭제
          msg = "drop table seet_"+del+"";
          ST.executeUpdate(msg); //삭제한 회원의 개인 테이블 삭제
          msg = "drop table s_seet_"+del+"";
          ST.executeUpdate(msg); //삭제한 회원의 공유 테이블 삭제
          msg = "drop sequence seq_f_seet_"+del+"";
          ST.executeUpdate(msg); // 삭제한 회원의 완료 테이블 시퀀스삭제
          msg = "drop sequence seq_seet_"+del+"";
          ST.executeUpdate(msg);// 삭제한 회원의 개인 테이블 시퀀스삭제
          msg = "drop sequence seq_s_seet_"+del+"";
          ST.executeUpdate(msg);// 삭제한 회원의 공유 테이블 시퀀스삭제
          System.out.println("\n회원님의 정보가 삭제되었습니다.");
          break;
        }
      }

    }catch(Exception e) {}


  }//수정하는 update 메서드 삭제


  public void UserQuestionView() {
    try{
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();
      ST = CN.createStatement();
      msg ="select * from question";
      RS = ST.executeQuery(msg);//executeQuery로 명령출력한다
      while(RS.next() == true) {
        String a = RS.getString("id"); //아이디
        String b = RS.getString("TITLE");//제목
        String c = RS.getString("CONTENTS");//내용
        String d = RS.getString("COMENTS");//관리자의답글
        String e = RS.getString("Q_ID");
        System.out.printf(String.format("ID  : %s", a));
        System.out.printf(String.format("\n제목 : %s",b));
        System.out.printf(String.format("\n내용 : %s",c));
        while(true) {
          if(d==null){System.out.printf("\n관리자 답글 : 게시글에 답글이 달리지 않았습니다."); break;}
          else {System.out.printf(String.format("\n관리자 답글 : %s",d)); break;}
        }
        System.out.printf(String.format("\n게시글번호 : %s",e));
        System.out.println();
        System.out.println();
      }
    }catch(Exception e) {}
  }




  public void UserQuestionfunction() {
    try {
      while(true) {
        Boot boot = new Boot();
        Connection CN = boot.boot();
        Statement ST = CN.createStatement();
        ST = CN.createStatement();
        System.out.println("1.답글달기 0. 뒤로가기");
        String funtion = sc.nextLine();
        if(funtion.equals("1")) {
          System.out.println("답글을 달아주실 게시글 번호를 입력해주세요");
          String updatecode = sc.nextLine();
          if(updatecode.equals("0")) {break;}
          msg = "select * from question where q_id='"+updatecode+"'";
          RS = ST.executeQuery(msg);
          if(RS.next()==true) {
            System.out.println("답글 내용을 입력해주세요");
            String updatetitle = sc.nextLine();
            if(updatetitle.equals("0")) {break;}
            msg = "update question set coments='"+updatetitle+"', viewcount='1' where q_id="+updatecode+"";
            ST.executeUpdate(msg);
            break;
          }else {System.out.println("올바른 게시글 번호를 입력해주세요");continue;}
        }else {break;}
      }
    }catch(Exception e) {System.out.println(e);}
  }


  public void UserQuestionalarm() {
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();
      ST = CN.createStatement();
      msg ="select coments from question";
      RS = ST.executeQuery(msg);
      int count = 0;
      while(RS.next() == true) {
        String d = RS.getString("COMENTS");
        if(d==null){count++;}
      }
      System.out.println("관리자님의 답글을 기다리는 게시글이 "+count+"개 있습니다.");
    }catch(Exception e) {}
  }



  public void list() { 
    System.out.println("회원 목록을 출력합니다.");
    try{
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();
      ST = CN.createStatement();
      msg ="select * from profile";
      ResultSet rs = ST.executeQuery(msg);//executeQuery로 명령출력한다
      while(rs.next() == true) {
        String a = rs.getString("NAME");//이름

        String b = rs.getString("ID");//아디
        if(a.equals("관리자")||b.equals("admin")) {
          break;
        }
        String c = rs.getString("PW");//비번
        String d = rs.getString("EMAIL");//이메일
        String e = rs.getString("PHONE");//폰
        System.out.printf(String.format("%-10s",a));
        System.out.printf(String.format("%15s",b));
        System.out.printf(String.format("%20s",c));
        System.out.printf(String.format("%25s",d));
        System.out.printf(String.format("%20s",e));
        System.out.println();
      } //회원목록 출력해오기 이중 회원을 삭제 수정할수있음 수정은 특정회원의 이름 ,비밀번호 연락처 등 while 종료

    }catch(Exception e) {}
  }

}//manager 종료


