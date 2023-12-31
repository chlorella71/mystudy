// 스태틱 초기화 블록(static initializer) - 활용 II : 클래스 로딩과 스태틱 블록 실행
package com.eomcs.oop.ex03;

public class Exam0691 {
  static class A {
    static int a = 7;// => static int a; => 스태틱변수 생성
    static {
      // a = 7; =스태틱변수에 값 입력
      System.out.println("A.static{}"); // =>1.실행
      a += B.b; // => A클래스 멈추고 B클래스 로딩 => B클래스로딩끝나면 돌아와서 4.실행
    }
  }
  static class B {
    static int b = 22; // => static int b; => 스태틱 변수 생성
    static {
      // b= 22; => 값입력
      System.out.println("B.static{}"); // => 2.실행
      b += A.a;// => 3.실행 후 B클래스로딩을 끝내고 B클래스로딩전 작업중이던 곳으로 돌아감
    }
  }

  public static void main(String[] args) {
    System.out.println(A.a); // ? => 7+=29 => 36
    System.out.println(B.b); // ? => 22+=7 => 29

    // 클래스 로딩 절차
    // 1) 클래스를 Method Area에 로딩한다.
    // 2) 스태틱 변수를 만든다.
    // 3) 스태틱 블록을 실행한다.
    //

    // 클래스 로딩
    // => 클래스 멤버(변수, 메서드)를 사용할 때
    // => Class.forName("클래스명") 을 통해 임의로 로딩할 때
    // => 단 한 번 로딩된 클래스는 다시 로딩하지 않는다.
    //

    // 스태틱 블록의 목적
    // => 클래스 멤버(스태틱 변수, 스태틱 메서드)를 사용하기 전에 유효한 값으로 초기화 시키는 것이다.
  }
}


