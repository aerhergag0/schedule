package dbtest;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Insert {

  Scanner keyScan = new Scanner(System.in);
  Banner banner = new Banner();
  ShowTable showtable = new ShowTable();

  String msg;
  String p_date;
  String title;
  String contents;
  boolean check;

  public void insert(String id) {
    try {
      Boot boot = new Boot();
      Connection CN = boot.boot();
      Statement ST = CN.createStatement();

      // 날짜 입력
      banner.printbanner1("일정 추가");
      System.out.println("\n날짜를 입력해주세요.");
      while (true) {
        System.out.print("날짜 입력(YYYY-MM-DD) : ");
        p_date = keyScan.nextLine();
        check = Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", p_date);
        if (check != true) {
          System.out.println("\n잘못 입력하셨습니다. YYYY-MM-DD 형식으로 입력해주세요.");
          continue;}
        break;
      }

      // 제목 입력
      System.out.println("\n제목을 입력하여 주세요.");
      while (true) {
        System.out.print("제목 입력 : ");
        title = keyScan.nextLine();
        if (title.isEmpty()) {
          System.out.println("\n제목이 비어있습니다. 제목을 입력하여 주세요.");
        } else {break;}
      }

      // 내용 입력
      System.out.println("\n내용을 입력하여 주세요.");
      while (true) {
        System.out.print("내용 입력 : ");
        contents = keyScan.nextLine();
        if (contents.isEmpty()) {
          System.out.println("\n내용이 비어있습니다. 내용을 입력하여 주세요.");
        } else {break;}
      }

      // 내용 등록
      msg = "insert into seet_"+id+" values ('"+p_date+"', '"+title+"', '"+contents+"', seq_seet_"+id+".nextval)";
      ST.executeUpdate(msg);

      System.out.println("\n일정이 등록되었습니다.");
    } catch (Exception e) {}
  }
}