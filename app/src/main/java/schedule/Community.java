package schedule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Community {

  Scanner keyScan = new Scanner(System.in);
  Banner banner = new Banner();
  ShowTable showtable = new ShowTable();

  ResultSet RS;
  String num;
  String title;
  String contents;
  String msg;
  String writer;
  String name;
  String check;
  boolean check2;
  String comments;

  public void community (String id) {

    String user_id = id;

    banner.printbanner1("커뮤니티");

    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      showtable.showCommunity(id);

      while (true) {
        System.out.println("\n메뉴를 선택하여 주세요.");
        System.out.println("\n1.게시글 작성\n2.게시글 보기\n3.내가 쓴 게시글 보기\n4.게시글 수정\n5.게시글 삭제\n0.뒤로가기\n");
        System.out.print("메뉴 입력 : ");
        num = keyScan.nextLine();
        if (num.equals("1")) {write(user_id);}
        else if (num.equals("2")) {c_view(user_id);}
        else if (num.equals("3")) {my_view(user_id);}
        else if (num.equals("4")) {update(user_id);}
        else if (num.equals("5")) {delete(user_id);}
        else if (num.equals("0")) {break;}
        else {System.out.println("\n잘못 입력하셨습니다. 다시 입력하여 주세요.");}
      }
    } catch (Exception e) {}
  }

  public void write (String id) {

    banner.printbanner2("게시글 작성");

    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      System.out.println("제목을 입력하여 주세요.");
      while (true) {
        System.out.print("제목 입력 : ");
        title = keyScan.nextLine();
        if (title.isEmpty()) {System.out.println("\n제목이 비어있습니다. 제목을 입력하여 주세요."); continue;}
        else {break;} }

      System.out.println("\n내용을 입력하여 주세요.");
      while (true) {
        System.out.print("내용 입력 : ");
        contents = keyScan.nextLine();
        if (title.isEmpty()) {System.out.println("\n내용이 비어있습니다. 내용을 입력하여 주세요."); continue;}
        else {break;} }

      msg = "select * from profile where id = '"+id+"'";
      RS = ST.executeQuery(msg);
      while (RS.next() == true) {writer = RS.getString("name");}

      msg = "insert into community values (sysdate, '"+title+"', '"+contents+"', '"+writer+"', seq_community.nextval, '"+id+"')";
      ST.executeUpdate(msg);
      System.out.println("\n게시글 작성이 완료되었습니다.");
    } catch (Exception e) {}
  }

  public void c_view (String id) {

    String user_id = id;
    banner.printbanner2("게시글 보기");

    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      System.out.println("\n게시글 번호를 입력하여 주세요.");
      loop : while (true) {
        System.out.print("게시글 번호 입력 : ");
        num = keyScan.nextLine();
        String sequence = num;
        if (num.isEmpty()) {System.out.println("\n번호가 비어있습니다. 번호를 입력하여 주세요."); continue;}

        check2 = Pattern.matches("^[0-9]*$", num);
        if (check2 != true) {System.out.println("\n잘못 입력하셨습니다. 숫자만 입력하여 주세요."); continue;}

        msg = "select * from community where c_id="+num;
        RS = ST.executeQuery(msg);
        if (RS.next() == false) {
          System.out.println("\n게시글이 존재하지 않습니다. 다시 입력하여 주세요."); continue;
        } else {
          msg = "select * from community where c_id="+num;
          RS = ST.executeQuery(msg);
          while (RS.next() == true) {
            String title = RS.getString("title");
            String writer = RS.getString("writer");;
            String contents = RS.getString("contents");;
            java.util.Date c_date = RS.getDate("c_date");;

            System.out.println();
            System.out.printf("   제  목 : %s\n   ", title);
            System.out.printf("작성자 : %s\n   ", writer);
            System.out.printf("내  용 : %s\n   ", contents);
            System.out.printf("작성일 : %s\n   ", c_date);
            System.out.println();
          }

          msg = "select * from comments where c_id="+num;
          RS = ST.executeQuery(msg);
          while (RS.next() == true) {
            System.out.println("---댓글-----------------------");
          }

          msg = "select * from comments where c_id="+num;
          RS = ST.executeQuery(msg);
          while (RS.next() == true) {
            comments = RS.getString("comments");
            writer = RS.getString("writer");
            System.out.println("이름 : " + writer + "\t댓글 : " + comments);
          }

          System.out.println("\n1. 댓글달기  0. 뒤로가기");
          while (true) {
            System.out.print("번호 입력 : ");
            String c_num = keyScan.nextLine();

            if (c_num.equals("1")) {comments(user_id, sequence); break loop;}
            else if (c_num.equals("0")) {break loop;}
            else {System.out.println("\n잘못 입력하셨습니다. 다시 입력하여 주세요."); continue;}
          }
        }
      }
    } catch (Exception e) {}
  }

  public void my_view (String id) {
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      msg = "select * from profile where id = '"+id+"'";
      RS = ST.executeQuery(msg);
      while (RS.next() == true) {
        name = RS.getString("name");
      }

      System.out.println("\n"+name+" 회원님이 작성하신 게시물 입니다.");

      msg = "select * from community where user_id = '"+id+"'";
      RS = ST.executeQuery(msg);
      while (RS.next() == true) {
        String title = RS.getString("title");
        String writer = RS.getString("writer");;
        String contents = RS.getString("contents");;
        java.util.Date c_date = RS.getDate("c_date");;

        System.out.println();
        System.out.printf("   제  목 : %s\n   ", title);
        System.out.printf("작성자 : %s\n   ", writer);
        System.out.printf("내  용 : %s\n   ", contents);
        System.out.printf("작성일 : %s\n   ", c_date);
        System.out.println();
      }

    } catch (Exception e) {System.out.println(e);}
  }

  public void update (String id) {
    banner.printbanner2("게시글 수정");
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      System.out.println("\n수정하실 게시글 번호를 입력하여 주세요.");
      while (true) {
        System.out.print("게시글 번호 입력 : ");
        num = keyScan.nextLine();
        if (num.isEmpty()) {System.out.println("\n번호가 비어있습니다. 번호를 입력하여 주세요."); continue;}

        check2 = Pattern.matches("^[0-9]*$", num);
        if (check2 != true) {System.out.println("\n잘못 입력하셨습니다. 숫자만 입력하여 주세요."); continue;}

        msg = "select * from community where c_id="+num;
        RS = ST.executeQuery(msg);
        if (RS.next() == false) {System.out.println("\n게시글이 존재하지 않습니다. 다시 입력하여 주세요."); continue;}

        msg = "select * from community where c_id="+num;
        RS = ST.executeQuery(msg);
        while (RS.next() == true) {check = RS.getString("user_id");}
        if (!check.equals(id)) {System.out.println("\n회원님이 작성한 게시글이 아닙니다."); continue;}

        System.out.println("\n수정하실 제목을 입력하여 주세요.");
        while (true) {
          System.out.print("제목 입력 : ");
          title = keyScan.nextLine();
          if (title.isEmpty()) {System.out.println("\n제목이 비어있습니다. 제목을 입력하여 주세요."); continue;}
          else {break;} }

        System.out.println("\n수정하실 내용을 입력하여 주세요.");
        while (true) {
          System.out.print("내용 입력 : ");
          contents = keyScan.nextLine();
          if (contents.isEmpty()) {System.out.println("\n내용이 비어있습니다. 내용을 입력하여 주세요."); continue;}
          else {break;} }

        msg = "update community set title='"+title+"', contents = '"+contents+"' where c_id = "+num;
        ST.executeUpdate(msg);
        System.out.println("\n게시글 수정이 완료되었습니다.");
        break;
      } 
    } catch (Exception e) {System.out.println(e);}
  }

  public void delete (String id) {
    banner.printbanner2("게시글 삭제");
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      System.out.println("\n삭제하실 게시글 번호를 입력하여 주세요.");
      while (true) {
        System.out.print("게시글 번호 입력 : ");
        num = keyScan.nextLine();
        if (num.isEmpty()) {System.out.println("\n번호가 비어있습니다. 번호를 입력하여 주세요."); continue;}

        check2 = Pattern.matches("^[0-9]*$", num);
        if (check2 != true) {System.out.println("\n잘못 입력하셨습니다. 숫자만 입력하여 주세요."); continue;}

        msg = "select * from community where c_id="+num;
        RS = ST.executeQuery(msg);
        if (RS.next() == false) {System.out.println("\n게시글이 존재하지 않습니다. 다시 입력하여 주세요."); continue;}

        msg = "select * from community where c_id="+num;
        RS = ST.executeQuery(msg);
        while (RS.next() == true) {check = RS.getString("user_id");}
        if (!check.equals(id)) {System.out.println("\n회원님이 작성한 게시글이 아닙니다."); continue;}

        msg = "delete from community where c_id = "+num;
        ST.executeUpdate(msg);

        msg = "delete from comments where c_id = "+num;
        ST.executeUpdate(msg);
        System.out.println("\n게시글이 삭제되었습니다.");
        break;
      } 
    } catch (Exception e) {System.out.println(e);}
  }

  public void comments (String id, String sequence) {
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      System.out.println("\n댓글을 입력하여 주세요.");
      while (true) {
        System.out.print("댓글 입력 : ");
        comments = keyScan.nextLine();
        if (comments.isEmpty()) {System.out.println("\n내용이 비어있습니다. 내용를 입력하여 주세요."); continue;}
        else {break;}
      }

      msg = "select * from profile where id = '"+id+"'";
      RS = ST.executeQuery(msg);
      while (RS.next() == true) {name = RS.getString("name");}

      msg = "insert into comments values ('"+comments+"', '"+name+"', '"+sequence+"', '"+id+"')";
      ST.executeUpdate(msg);
      System.out.println("\n댓글을 달았습니다.");
    } catch (Exception e) {}
  }
}