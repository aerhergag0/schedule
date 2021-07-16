package schedule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class UserQuestion {
  String function = "";
  String msg = "";
  ResultSet rs = null;
  String title = "";
  String contents = "";
  Scanner sc = new Scanner(System.in);
  String delete = "";
  String code = "";

  public void UserQuestion(String id){
    Questionviewcount(id);
    while(true) {
      System.out.println("1.문의 추가 2. 문의 삭제 3.문의 수정 4. 문의 확인 0. 뒤로가기");
      function = sc.nextLine();
      if(function.equals("1")) {QuestionAdd(id); continue;}
      if(function.equals("2")) {QuestionDelete(id); continue;}
      if(function.equals("3")) {QuestionModify(id); continue;}
      if(function.equals("4")) {Questionconfirm(id); continue;}
      if(function.isEmpty()) {System.out.println("\n잘못입력하였습니다. 다시 입력하여 주세요."); continue;}
      if(function.equals("0")) {System.out.println("문의를 종료합니다"); break;}
      else{continue;}
    }//와일문종료
  }//유저퀘스천 종료

  public void QuestionAdd(String id) {
    while(true) {
      System.out.println("문의 추가 기능입니다. 제목을 입력해주세요 0.뒤로가기");
      title = sc.nextLine();
      if(title.equals("0")) {break;}//기능종료(뒤로가기)
      if(title.isEmpty()) {System.out.println("잘못입력하였습니다. 다시 입력하여 주세요."); continue;}
      if(title.length() <= 15){} else {System.out.println("제목은 15자 이내로 작성 부탁드립니다");}
      System.out.println("내용을 입력해주세요 0.뒤로가기");
      contents = sc.nextLine();
      if(contents.equals("0")) {break;}//기능종료(뒤로가기)
      if(contents.isEmpty()) {System.out.println("잘못입력하였습니다. 다시 입력하여 주세요."); continue;}
      try{
        Boot boot = new Boot();
        Connection CN = boot.boot();
        Statement ST = CN.createStatement();
        ST = CN.createStatement();
        msg ="insert into question(id,title,contents,q_id) values('"+id+"','"+title+"','"+contents+"',seq_question.nextval)";
        ST.executeUpdate(msg);
        System.out.println(""+id+"" + "님의 질문이 생성되었습니다");
        break;
      }catch(Exception e) {System.out.println(e);}
    }
  }//퀘스쳔add종료

  public void QuestionDelete(String id) {
    try{
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();
      ST = CN.createStatement();
      while(true) {
        msg = "select * from question where id='"+id+"'";
        rs = ST.executeQuery(msg);
        if(rs.next()==false) {System.out.println("삭제하실 문의가 없습니다.");break;}
        Questionconfirm(id);
        System.out.println("문의 삭제 게시글번호를 입력해주세요 0.뒤로가기");
        delete = sc.nextLine();
        if(delete.equals("0")) {break;}//기능종료(뒤로가기)
        if(delete.isEmpty()) {System.out.println("잘못입력하였습니다. 다시 입력하여 주세요."); continue;}

        msg = "select * from question where q_id='"+delete+"'";
        rs = ST.executeQuery(msg);
        if(rs.next()==true) {
          msg = "delete from Question where q_id='"+delete+"'";
          ST.executeUpdate(msg);
          System.out.println(""+id+"님의 게시글" + ""+delete+"" + "번 문의가 삭제되었습니다.");
          break;
        }else {System.out.println("삭제할 문의가 없습니다."); break;}
      }
    }catch(Exception e) {}
  }//퀘스천델리트종료



  public void QuestionModify(String id) {
    Questionconfirm(id);
    try{
      while(true) {
        Boot boot = new Boot();
        Connection CN = boot.boot();
        Statement ST = CN.createStatement();
        ST = CN.createStatement();
        msg = "select * from question where id='"+id+"'";
        rs = ST.executeQuery(msg);
        if(rs.next()==false) {System.out.println("수정하실 문의가 없습니다."); break;}
        System.out.println("1.제목 수정 2.내용 수정 0.뒤로가기");
        String Modify = sc.nextLine();
        if(Modify.equals("0")) {break;} if(Modify.isEmpty()) {continue;} if(Modify.equals("1") || Modify.equals("2")) {}else {continue;}

        System.out.println("수정하실 게시글번호를 입력해주세요 0.뒤로가기");
        String titlecode = sc.nextLine();
        if(titlecode.equals("0")) {break;} if(titlecode.isEmpty()) {continue;}
        msg = "select * from question where q_id="+titlecode+"";
        rs = ST.executeQuery(msg);
        if(rs.next()==true) {} else {System.out.println("수정하실 게시글번호를 다시입력해주세요"); continue;}


        if(Modify.equals("1")) {
          System.out.println("수정하실 제목을 입력해주세요");
          String Modifytitle = sc.nextLine();
          msg = "update question set TITLE='"+Modifytitle+"' where q_id="+titlecode+"";
          ST = CN.createStatement();
          ST.executeUpdate(msg);
          System.out.println("제목 변경이 완료되었습니다.");
          break;
        }

        if(Modify.equals("2")) {
          System.out.println("수정하실 내용을 입력해주세요");
          String Modifycontents = sc.nextLine();
          msg = "update question set CONTENTS='"+Modifycontents+"' where q_id="+titlecode+"";
          ST = CN.createStatement();
          ST.executeUpdate(msg);
          System.out.println("내용 변경이 완료되었습니다.");
          break;
        }
        if(Modify.equals("0")) {break;}
      }
    }catch(Exception e) {}
  }




  public void Questionconfirm(String id) {
    try{
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();
      ST = CN.createStatement();
      msg ="select * from question where id='"+id+"'";
      rs = ST.executeQuery(msg);//executeQuery로 명령출력한다
      while(rs.next() == true) {
        String b = rs.getString("TITLE");//제목
        String c = rs.getString("CONTENTS");//내용
        String d = rs.getString("COMENTS");//관리자의답글
        String e = rs.getString("Q_ID");
        String f = rs.getString("viewcount");
        if(f.equals("1")) {
          msg = "update question set viewcount='0' where id='"+id+"'";
          ST.executeUpdate(msg);
        }
        System.out.printf(String.format("\n제목 : %s",b));
        System.out.printf(String.format("\n내용 : %s",c));
        while(true) {
          if(d==null){System.out.printf("\n관리자 답글 : 게시글에 답글이 달리지 않았습니다."); break;}
          else {System.out.printf(String.format("\n관리자 답글 : %s",d)); break;}
        }
        System.out.printf(String.format("\n게시글번호 : %s",e));
        System.out.println(); 
      }
      //      Boot boot = new Boot();
      //      Connection CN = boot.boot();
      //      Statement ST = CN.createStatement();
      //      ST = CN.createStatement();
      //      msg = "update question set viewcount='0' where id='"+id+"'";
      //      ST = CN.createStatement();
      //      ST.executeUpdate(msg);
    }catch(Exception e) {System.out.println(e);}
  }

  public void Questionviewcount(String id) {
    try{
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();
      ST = CN.createStatement();
      msg ="select VIEWCOUNT from question where id='"+id+"'";
      rs = ST.executeQuery(msg);
      int count = 0;
      while(rs.next() == true) {
        String a = rs.getString("viewcount");
        if(a.equals("1")) {count++;}
      }
      System.out.println("관리자가 답변한 게시글중 읽지않은 게시글이"+count+"개 있습니다.");
    }catch(Exception e) {System.out.println(e);}
  }
}

//  public void QuestionInsert(String id) { // Qustion 테이블 생성 및 시퀀스생성 메서드
//    try{
//      Boot boot = new Boot();
//      Connection CN = boot.boot();
//      Statement ST = CN.createStatement();
//      ST = CN.createStatement();
//      msg = "select * from Question_"+id+"";
//      rs = ST.executeQuery(msg);
//      if(rs.next() == true) {}
//      else {
//        msg = "create table Qustion_"+id+"("
//            + "id varchar2(100), "
//            + "title varchar2(200), "
//            + "contents varchar2(4000), "
//            + "q_id number)";
//        ST.executeUpdate(msg); //질문 테이블생성
//        msg = "create sequence seq_question_"+id;
//        ST.executeUpdate(msg); //질문 시퀀스생성
//      }
//
// 
//    }catch(Exception e) {}
//  }
//}
