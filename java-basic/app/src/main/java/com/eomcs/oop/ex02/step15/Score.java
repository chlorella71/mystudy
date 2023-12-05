package com.eomcs.oop.ex02.step15;

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
  static void compute(Score s) {
    s.sum = s.kor + s.eng + s.math;
    s.aver = (float) s.sum / 3;
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
