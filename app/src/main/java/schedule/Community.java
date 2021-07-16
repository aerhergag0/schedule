package schedule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

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

  public void community (String id) {

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
        if (num.equals("1")) {write(id);}
        else if (num.equals("2")) {c_view();}
        else if (num.equals("3")) {my_view();}
        else if (num.equals("4")) {update();}
        else if (num.equals("5")) {delete();}
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
        if (title.isEmpty()) {System.out.println("\n내용이 비어있습니다. 제목을 입력하여 주세요."); continue;}
        else {break;} }

      msg = "select * from profile where id = '"+id+"'";
      RS = ST.executeQuery(msg);
      while (RS.next() == true) {writer = RS.getString("name");}

      msg = "insert into community values (sysdate, '"+title+"', '"+contents+"', '"+writer+"', seq_community.nextval, '"+id+"')";
      ST.executeUpdate(msg);
      System.out.println("게시글 작성이 완료되었습니다.");
    } catch (Exception e) {}
  }
  public void c_view () {}
  public void my_view () {}
  public void update () {}
  public void delete () {}





}
