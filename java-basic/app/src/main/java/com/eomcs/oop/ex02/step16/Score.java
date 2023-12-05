package com.eomcs.oop.ex02.step16;

// ## 사용자 정의 데이터 타입 만들기
// - 학생의 성적 데이터를 담을 전용 메모리(변수)를 설계한다.
// - 학생 데이터를 묶음으로 다룰 수 있도록 변수 그룹을 정의한다.
public class Score {

  // 인스턴스 변수(instance variable; instanc field)
  // - new 명령으로 생성되는 변수이다.
  // - 성적 데이터를 개별적으로 다루고 싶을 때 인스턴스 변수로 선언한다.(데이터 타입으로서)
  String name; // => 인스턴스 변수로서 new 명령으로 생성되는 변수
  int kor;
  int eng;
  int math;
  int sum;
  float aver;


  // Score 데이터 값을 다루는 메소드는 Score 데이터를 선언한 클래스에 가까이 두는 것이
  // 유지보수에 좋다
  // GRASP패턴의 'Information Expert'설계 기법을 적용한 예이다.
  // 합계, 평균은 별도의 메서드에서 처리하게 한다.
  // static void compute(Score s) { => Score class에서 인스턴스 주소를 파라미터로 주지않고 앞에 주기때문에 아래처럼 바뀐다. 이때 내장변수
  // this를 사용한다.
  void compute() {
    this.sum = this.kor + this.eng + this.math; // => 변수앞에는 레퍼런스주소가 있어야 하지만 해당 메소드 안에? 있을때는 생략할수 있다.
                                                // 그렇다고 this를 사용하지 않는것이 아니다. 컴파일러가 this를 붙이냐 사용자가
                                                // 붙이냐의 차이일 뿐이다.
    this.aver = (float) this.sum / 3;
    // static method를 instance method로 바꾸는 이유:
    // - 클래스 메서드로 연산자를 정의하면,
    // - 계산을 수행할 때마다 인스턴스의 주소를 파라미터로 받아야 한다.
    // - 매우 번거롭다.
    //
    // public static void calculate(Score score) {
    // score.sum = score.kor + score.eng + score.math;
    // score.average = score.sum / 3f;
    // }
    // - 그래서 자바는 "인스턴스 메서드"라는 것을 제공한다.
    //

    // 인스턴스 메서드 = non-static 메서드
    // - 메서드를 호출할 때 인스턴스의 주소를 파라미터로 넘기지 않는다.
    // - 메서드를 호출할 때(연산자를 사용할 때), 메서드 앞에 인스턴스 주소를 적는다.
    // - 이렇게 전달된 인스턴스 주소는 메서드에 내장된 this라는 변수에 자동 저장된다.
    // - 그래서 파라미터 대신 this를 사용하면 된다.
    // - 인스턴스 메서드는 static을 붙이지 않는다.



    // => App에서 메소드를 이동했다(Move Method)
    // compute() 메서드는 Score 데이터를 다루는 메서드이다.
    // 메서드를 이용하여 이 타입의 데이터를 다룰 수 있는 '연산자'라 할 수 있다.
    // - 사용자 정의 데이터 타입 입장에서는 그 데이터를 다루는 메서드가 연산자인 것이다.
    // - 즉 사용자 정의 데이터 타입에 메서드를 정의하는 것은
    // 그 데이터를 다룰 연산자를 정의하는 것과 같다. => 즉compute 메소드를 Score Data의 연산자라고 할 수 있다.
    // 데이터타입마다 다룰 수 있는 연산자가 다르다

  }

}
// OOAD : 객체지향 메소드
// 객체지향에서는 모든 메소드가 연산자(오퍼레이터: 데이터를 다루는 연산자)라고 할 수 있음
// 선언된 변수를 다루는 형식일때는 사용자가 데이터를 다루는 연산자를 추가시킨 것이라고 볼 수 있다.
