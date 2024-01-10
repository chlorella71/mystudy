// 클래스에 제네릭(generic) 적용하기 : 적용 후
package com.eomcs.generic.ex01;

import com.eomcs.generic.ex02.Member;

public class Exam0240 { // 바이트코드를 보면 exam0231과 같이 Object타입이다.
  // 제네릭은 컴파일러가 검사할때를 위한 문법
  // 컴파일이 끝나면 제네릭 문법은 사라진 상태이다
  // jvm은 제네릭과 관련이 없다. 컴파일러와 관련된 문법

  static class Box<T> {
    T value;

    public T get() {
      return this.value;
    }

    public void set(T value) {
      this.value = value;
    }
  }

  public static void main(String[] args) {

    // 제네릭을 사용하면 한 개의 클래스를 가지고
    // 특정 타입만을 다루는 전용 객체를 만들 수 있다.
    Box<Member> b1 = new Box<>();
    Box<String> b2 = new Box<>();
    Box<Integer> b3 = new Box<>();

    b1.set(new Member("홍길동", 20)); // 인스턴스(b1)에 따라서 set메소드에 올 수 있는 값이 정해짐
    b2.set("Hello");
    b3.set(100);

    // 제네릭을 사용하면 get() 메서드가 각 타입별로 존재하는 것처럼 사용할 수 있다.
    // => 즉 형변환 할 필요가 없다.
    Member v1 = b1.get();
    String v2 = b2.get();
    Integer v3 = b3.get();

    System.out.println(v1);
    System.out.println(v2);
    System.out.println(v3);
  }
}
