package com.eomcs.reflect.ex06.a;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Exam0121 {
  public static void main(String[] args) {

    InvocationHandler invocationHandler = new InvocationHandler() {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());
        return null;
      }
    };

    // newProxyInstance()가 리턴한 객체는
    // A, B, C 인터페이스를 모두 구현한 객체(클래스의 인스턴스)이다.
    Object obj = Proxy.newProxyInstance(Exam0121.class.getClassLoader(),
        new Class<?>[] {A.class, B.class, C.class}, invocationHandler);

    ((A) obj).m1();
    ((B) obj).m2();
    ((C) obj).m3();
  }
}
