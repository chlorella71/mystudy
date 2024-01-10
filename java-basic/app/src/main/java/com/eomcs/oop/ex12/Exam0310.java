// 아규먼트에 람다(lambda) 활용
package com.eomcs.oop.ex12;

public class Exam0310 {

  static interface Player {
    void play();
  }

  static void testPlayer(Player p) {
    p.play();
  }

  public static void main(String[] args) {
    // 로컬 클래스
    class MyPlayer implements Player {
      @Override
      public void play() {
        System.out.println("실행!");
      }
    }

    testPlayer(new MyPlayer());
    // 1. 로컬클래스를 익명클래스만들기
    // Player = new Player() {
    // public void play() {
    // System.out.println("실행!");
    // }
    // }
    // 2. 람다문법
    // 3. 합치기
    // testPlayer(() -> System.out.println("실행!"));
  }
}


