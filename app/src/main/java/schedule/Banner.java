package dbtest;

public class Banner {
  final int bannerLength = 75;
  final char bannerChar = '-';

  public void printbanner1(String str) {

    System.out.println();
    for(int i=0;i<bannerLength;i++) {
      System.out.print(bannerChar);
    }
    System.out.println();
    for(int i=0; i<(bannerLength-str.length())/2 - ((str.length())*0.6); i++) {
      System.out.print(" ");
    }
    System.out.println(str);
    for(int i=0;i<bannerLength;i++) {
      System.out.print(bannerChar);
    }
    System.out.println();
    System.out.println();
  }

  public void printbanner2(String str) {

    System.out.println();
    for(int i=0;i<((bannerLength-str.length())/2);i++) {
      System.out.print(bannerChar);
    }
    System.out.print(str);
    for(int i=0;i<((bannerLength-str.length())/2);i++) {
      System.out.print(bannerChar);
    }
    System.out.println();
    System.out.println();
  }
}
