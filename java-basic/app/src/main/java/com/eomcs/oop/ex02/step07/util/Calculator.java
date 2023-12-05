package com.eomcs.oop.ex02.step07.util;

// 다른 패키지에서 이 클래스와 멤버(필드 및 메서드)를 접근할 수 있도록
// public으로 공개해야 한다. -> public으로 공개하는 방법은 메소드 리턴타입 앞에 public을 붙이면 된다.
public class Calculator {


  // ## 클래스 변수 사용
  // - 메서드들의 작업 결과를 보관할 때 사용할 변수이다.
  // - 변수 선언에 static을 붙이다.
  // - "스태틱 변수"라고도 부른다.
  // - 클래스 변수는 new 명령으로 생성하지 않는다.
  // - 클래스가 메모리에 로딩될 때 자동으로 "Method Area" 영역에 생성된다.
  // static int result = 0;
  public int result = 0; // <= 인스턴스변수는 static을 지우면 됨
  // 정확하게는 인스턴스 변수를 만들라는 명령임 => instance변수는 heap메모리에 저장됨 변수이름은 result
  // 인스턴스 변수(= non-static 변수)
  // - 작업 결과를 개별적으로 관리하고 싶을 때 인스턴스 변수로 선언한다.
  // - 인스턴스 변수는 클래스가 로딩 될 때 만들어지지 않는다.
  // - new 명령을 사용해서 만들어야 한다.
  // - 변수 선언 앞에 static이 붙지 않는다.
  // => 인스턴스 변수를 개별적으로 저장하기위해 선언(연산 결과를)

  // 메서드 작업 결과는 클래스 변수에 보관한다.

  // 계산 기능과 관련된 메서드를 별도의 블록으로 분리할 때 사용하는 문법이 "클래스"이다.
  // 메서드를 분류해 놓으면 좋은 점?
  // - 관련된 메서드가 한 클래스에 묶여 있기 때문에 소스 코드를 유지보수하기 쉬워진다.
  // - 다른 프로젝트에서 메서드를 재사용 하기가 쉽다.
  //

  // 메서드 작업 결과는 인스턴스 변수에 보관한다.
  // 그러기 위해 메서드가 호출될 때 보관할 메모리를 준비한다.


  // 인스턴스 변수를 다룰 때는 인스턴스 메서드를 사용하는 것이 편하다!
  // 왜?
  // - 인스턴스 주소를 파라미터로 받을 필요가 없기 때문이다.
  // - 메서드를 호출할 때 앞쪽에 인스턴스 주소를 지정한다.
  //
  // static void plus(int value) {
  // => static을 빼게되면 메소드를 호출할때 앞에서 전달한 인스턴스는 주소를 받을 수 있도록 내장된
  // this라는 이름의 내장 변수에 자동 저장된다.

  // 공개하는 법 메소드 리턴타입 앞에 public을 붙인다
  public void plus(int value) {
    // 인스턴스 변수를 다루는 메서드는 작업을 수행할 때 그 인스턴스 주소를 받아야 한다.
    // result 는 더이상 클래스 변수가 아니기 때문에 직접 접근할 수 없다.
    // 오직 인스턴스 주소를 통해서만 접근 할 수 있다.
    // result = result + value;
    this.result += value;
  }

  public void minus(int value) {
    this.result -= value;
  }

  public void multiple(int value) {
    this.result *= value;
  }

  // intstance 주소를 이제 파라미터로 넘길 필요가 없어짐
  /*
   * void divide(Calculator instance, int value) { instance.result /= value; }
   */
  public void divide(int value) {
    this.result /= value;
  }


  // 다음 메서드는 계산 결과를 사용하지 않는다.
  // 파라미터 값에 대해 절대값을 리턴하는 일을 한다. => 결이다름=> 결이다르다 : 문법이 바뀔수있음

  // 다음 메서드는 result 변수를 사용하지 않기때문에
  // Calculator의 인스턴스 주소를 받을 필요가 없다.
  public static int abs(int a) { // 절댓값을 만드는 메소드이다. //기존 결과를 바꾸는 것이 아닌 값을 받아서 절댓값으로 바꿔는 역할
    // 스태틱 메서드는 this라는 내장 변수가 없다.


    //
    // if (a >= 0)
    // return a;
    // else
    // return a * -1;
    //
    return a >= 0 ? a : a * -1;
  }

}
