package com.eomcs.oop.ex02.test.util;

public class Calculator {
  // 다음 식을 연산자 우선 순위를 고려하지 않고 순서대로 계산하라!
  // 2 + 3 - 1 * 7 / 3 = ?

  // 계산 결과를 담을 변수를 준비한다.
  public int result = 0;

  // 메서드를 호출하여 작업을 수행하고,
  // 리턴 결과는 로컬 변수에 저장한다.
  public void plus(int value) {
    this.result += value;
  }

  public void minus(int value) {
    this.result -= value;
  }

  public void multiple(int value) {
    this.result *= value;
  }

  public void divide(int value) {
    this.result /= value;
  }

  public static int abs(int a) { // 절댓값을 만드는 메소드이다.
    //
    // if (a >= 0)
    // return a;
    // else
    // return a * -1;
    //
    return a >= 0 ? a : a * -1;
  }
}
