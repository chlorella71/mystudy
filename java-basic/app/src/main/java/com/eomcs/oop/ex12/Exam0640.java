// 메서드 레퍼런스 - 활용예
package com.eomcs.oop.ex12;

import java.util.function.Predicate;

public class Exam0640 {

  static class My {
    public static boolean test1(String value) {
      return true;
    }

    public boolean test2(String value) {
      return true;
    }

    public static boolean test3() {
      return true;
    }

    public boolean test4() {
      return true;
    }
  }

  public static void main(String[] args) {

    Predicate<String> p1 = My::test1; // ok

    My obj = new My();
    Predicate<String> p2 = obj::test2; // ok

    // Predicate<My> p3 = My::test3; // 컴파일 오류!

    Predicate<My> p4 = My::test4; // ok
  }

}


