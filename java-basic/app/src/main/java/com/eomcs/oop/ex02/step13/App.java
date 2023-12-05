package com.eomcs.oop.ex02.step13; // exam0120

// 데이터 타입 정의
// 0) 클래스 사용전 :
// - 낱개 변수 사용
// 1) 성적 데이터를 저장할 메모리를 새로 정의 :
// - '사용자(개발자) 정의 데이터 타입(user-defined data type)'이라 부른다.
// - 여러 개로 이루어진 데이터를 한 묶음으로 관리하면 다루면 관리하기가 쉽다.
// (특정 데이터를 저장할 전용 메모리를 만들면 관리하기 쉽다.)=> 성적데이터를 저장할 우리만의 전용 멤리
//
// => 클래스를 사용하는 용도. 낱개로 이루어진 것을 합치는 것?
// 2) 리팩토링 :
// - 메서드 추출(extract method)
// - (인스턴스에?)중복되는 코드가 있으면 별도의 메소드로 분리한다.
public class App {
  public static void main(String[] args) {
    /*
     * String name; int kor; int eng; int math; int sum; float aver;
     */
    // =>Score 클래스로 옮겨 인스턴스 변수 선언


    /*
     * name = "홍길동"; kor = 100; eng = 90; math = 85; sum = kor + eng + math; aver = (float) sum / 3;
     */
    // =>Score s1 = new Score();

    // 사용자 정의 데이터 타입을 사용하는 방법
    // - new 명령을 사용하여 설계도에 기술된 대로 메모리(변수)를 준비한다.
    // - 변수는 Heap 영역에 생성된다.
    // - 변수들이 생성된 메모리의 주소를 레퍼런스(주소 변수)에 저장한다.
    Score s1 = new Score(); // => heap이라는 메모리에 생성, 인스턴스 3개(홍길동, 유관순, 임꺽정) 레퍼런스 3개 만들어짐
    Score s2 = new Score();
    Score s3 = new Score();

    // - Heap 영역에 생성된 인스턴스의 변수는 레퍼런스를 통해 접근한다.
    s1.name = "홍길동"; // => s1에 들어있는 인스턴스주소로 찾아가서 name,kor 변수 등을 찾는 것임. =>개발자는 간결하게 표현, s1에 들어있는~
    s1.kor = 100;
    s1.eng = 90;
    s1.math = 85;
    // s1.sum = s1.kor + s1.eng + s1.math; => static void printScore로 합침.
    // s1.aver = (float) s1.sum / 3;

    /*
     * System.out.printf("%s: %d, %d, %d, %d, %.1f\n", name, kor, eng, math, sum, aver);
     * =>System.out.printf("%s: %d, %d, %d, %d, %.1f\n", s1.name, s1.kor, s1.eng, s1.math, s1.sum,
     * s1.aver);
     */

    s2.name = "임꺽정";
    s2.kor = 90;
    s2.eng = 80;
    s2.math = 75;
    // s2.sum = s2.kor + s2.eng + s2.math;
    // s2.aver = (float) s2.sum / 3;


    s3.name = "유관순";
    s3.kor = 80;
    s3.eng = 70;
    s3.math = 65;
    // s3.sum = s3.kor + s3.eng + s3.math;
    // s3.aver = (float) s3.sum / 3;

    /*
     * System.out.printf("%s: %d, %d, %d, %d, %.1f\n", s1.name, s1.kor, s1.eng, s1.math, s1.sum,
     * s1.aver); System.out.printf("%s: %d, %d, %d, %d, %.1f\n", s2.name, s2.kor, s2.eng, s2.math,
     * s2.sum, s2.aver); System.out.printf("%s: %d, %d, %d, %d, %.1f\n", s3.name, s3.kor, s3.eng,
     * s3.math, s3.sum, s3.aver);
     */
    // =>
    printScore(s1);
    printScore(s2);
    printScore(s3);
  }

  // 합계와 평균, 출력 코드를 분리하여 별도의 메서드로 정의한다.
  // 즉 증복 코드 제거하여 메서드로 정의해두면 재사용하기가 쉽다.
  //
  static void printScore(Score s) { // => Score s : Score 인스턴스를 준것임.

    s.sum = s.kor + s.eng + s.math; // => kor eng math 값을 인스턴스에서 가져와서? sum 메모리에 저장?
    s.aver = (float) s.sum / 3; // (float) s.sum / (float) 3; => (내부적?)암시적 형변환
    // s.sum / 3f; int를 float으로 바꿔서 연산함. sum에 있는 값을 가져와서 float메모리에 저장해서 연산하는 것임.
    System.out.printf("%s: %d, %d, %d, %d, %.1f\n", s.name, s.kor, s.eng, s.math, s.sum, s.aver);
  }
}

// 클래스 문법의 용도?
// 1) 사용자 정의 데이터 타입 만들 때
// 즉 새로운 구조의 메모리를 설계할 때 사용한다.
// 2) 메서드를 묶을 때
// 서로 관련된 기능을 관리하기 쉽게 묶고 싶을 때 사용한다.
