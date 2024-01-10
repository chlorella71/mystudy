// 메서드 레퍼런스 - 활용예
package com.eomcs.oop.ex12;

import java.util.function.Predicate;

public class Exam0630b {

  // static class My {
  // public static boolean test1(String value) {
  // return true;
  // }
  //
  // public boolean test2(String value) {
  // return true;
  // }
  //
  // public static boolean test3() {
  // return true;
  // }
  //
  // public boolean test4() {
  // return true;
  // }
  // }

  public static void main(String[] args) {

    // Predicate<String> 인터페이스 구현체 준비하기

    // 1) 로컬 클래스로 인터페이스 구현체 만들기
    class MyPredicate<T> implements Predicate<T> {
      @Override
      public boolean test(T value) {
        return ((String) value).isEmpty();
      }
    }
    Predicate<String> p1 = new MyPredicate<>();
    p1.test("");
    p1.test("Hello!");
    System.out.println("--------------------------------");


    // 2) 익명 클래스로 인터페이스 구현체 만들기
    Predicate<String> p2 = new Predicate<>() {
      @Override
      public boolean test(String value) {
        return value.isEmpty();
      }
    };

    // 3) 람다로 인터페이스 구현체 만들기
    Predicate<String> p3 = value -> value.isEmpty();

    // 4) 메서드 레퍼런스를 사용하여 기존 클래스의 메서드를 인터페이스 구현체로 사용하기
    String s1 = "";

    // => 의미: "Predicate 인터페이스 구현체로서 String의 isEmpty()를 사용하겠다"
    Predicate<String> p4 = String::isEmpty;
    System.out.println(p4.test("hello"));


  }

}


