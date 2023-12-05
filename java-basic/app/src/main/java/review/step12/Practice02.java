package review.step12;

public class Practice02 {
  public static void main(String[] args) {

    Score s1 = new Score();
    Score s2 = new Score();
    Score s3 = new Score();
    s1.name = "홍길동";
    s1.kor = 100;
    s1.eng = 90;
    s1.math = 85;
    s1.sum = s1.kor + s1.eng + s1.math;
    s1.aver = (float) s1.sum / 3;

    System.out.printf("%s: %d, %d, %d,%d, %.1f\n", s1.name, s1.kor, s1.eng, s1.math, s1.sum,
        s1.aver);

    s2.name = "임꺽정";
    s2.kor = 90;
    s2.eng = 80;
    s2.math = 75;
    s2.sum = s2.kor + s2.eng + s2.math;
    s2.aver = (float) s2.sum / 3;

    System.out.printf("%s: %d, %d, %d,%d, %.1f\n", s2.name, s2.kor, s2.eng, s2.math, s2.sum,
        s2.aver);

    s3.name = "유관순";
    s3.kor = 80;
    s3.eng = 70;
    s3.math = 65;
    s3.sum = s3.kor + s3.eng + s3.math;
    s3.aver = (float) s3.sum / 3;

    System.out.printf("%s: %d, %d, %d,%d, %.1f\n", s3.name, s3.kor, s3.eng, s3.math, s3.sum,
        s3.aver);
  }

}
