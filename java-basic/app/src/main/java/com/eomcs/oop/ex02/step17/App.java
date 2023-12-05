package com.eomcs.oop.ex02.step17; // exam0150

import com.eomcs.oop.ex02.step17.vo.Score;

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
// 3) 리팩토링:
// - 메서드 추출(extract method) : 한개의 메서드는 한개의 기능을 수행해야한다.
// 4) GRASP 패턴
// -Information Expert : 데이터를 다룰 때는 그 데이터를 갖고있는 객체에게 묻는다.
// -리팩토링 : 메서드이동(Move Method)
// -메서드를 관련된 클래스로 이동시킨다. => 코드를 이해하기가 쉽다.
// 5)인스턴스 메서드 :
// -인스턴스 주소를 더 쉽게 받는 문법
// 6) 패키지 멤버 클래스(생략 이전과정에서 수행하여 굳이 할필요가없어짐)
// 7) 클래스를 역할에 따라 패키지로 분류
// => 클래스가 많을 경우 유지보수하기 쉽도록 적절한 패키지로 분산 배치한다.
// => 데이터 타입의 역할을 하는 클래스의 경우
// 보통 domain, vo(value object), dto(data transfer object) 라는 이름을 가진
// 패키지에 분류한다.
// => 패키지가 다르면 modifier 옵션에 따라 접근 범위가 달라진다.
// 멤버의 접근 범위 설정
// => public: 모두 공개
// => protected: 서브 클래스와 같은 패키지의 멤버는 접근 가능
// => (default): 같은 패키지의 멤버는 접근 가능
// => private: 접근 불가! 그 멤버가 속한 클래스의 내부에서만 접근 가능
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
    /* Score.compute(s1); //=> 인스턴스 주소를 파라미터로 보내고 있다. */
    s1.compute(); // => 피연산자(인스턴스 주소)를 파라미터로 주지말고 앞쪽으로 보내고있다. //s1.compute(); => 마치 변수 뒤에 연산자를 놓는
                  // i++ 의 예와 비슷하다.
    // 다음은 Score의 값을 다루기 위해 스태틱 메서드를 호출하는 예이다.
    // => static 메서드 = 클래스 메서드
    //
    // Score.compute(score);
    //

    // 클래스 메서드를 사용할 때 마다 매번 인스턴스의 주소를 파라미터로 넘겨줘야 했다.
    // 그러나 인스턴스 메서드를 사용하면 인스턴스 주소를 넘기기가 더 편하다.
    // 메서드 호출 앞에다 둔다.
    // 소스 코드의 목적을 이해하는데 더 직관적이다.



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
    s2.compute();
    // s2.compute(s2);도 오류없이 실행가능하지만 이렇게하면 스태틱변수인데 인스턴스변수로 오해하기 쉽다. 그래서 저렇게 하지 않는다.
    // 레퍼런스로 호출할 수 있다하더라도 스태틱메소드는 스태틱메소드호출방식으로 할것.


    s3.name = "유관순";
    s3.kor = 80;
    s3.eng = 70;
    s3.math = 65;
    // s3.sum = s3.kor + s3.eng + s3.math;
    // s3.aver = (float) s3.sum / 3;
    s3.compute();

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

    /*
     * s.sum = s.kor + s.eng + s.math; // => kor eng math 값을 인스턴스에서 가져와서? sum 메모리에 저장? s.aver =
     * (float) s.sum / 3; // (float) s.sum / (float) 3; => (내부적?)암시적 형변환
     */
    // s.sum / 3f; int를 float으로 바꿔서 연산함. sum에 있는 값을 가져와서 float메모리에 저장해서 연산하는 것임.
    System.out.printf("%s: %d, %d, %d, %d, %.1f\n", s.name, s.kor, s.eng, s.math, s.sum, s.aver);
  }

  /*
   * // 합계, 평균은 별도의 메서드에서 처리하게 한다. static void compute(Score s) { s.sum = s.kor + s.eng + s.math;
   * s.aver = (float) s.sum / 3;
   */ // =>class Score로 이동(Move Method, Information Expert)

}

// 클래스 문법의 용도?
// 1) 사용자 정의 데이터 타입 만들 때
// 즉 새로운 구조의 메모리를 설계할 때 사용한다.
// 2) 메서드를 묶을 때
// 서로 관련된 기능을 관리하기 쉽게 묶고 싶을 때 사용한다.
