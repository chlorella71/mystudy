package review.step02;

public class Calculator {

  static int plus(int a, int b) {
    return a + b;
  }

  static int minus(int a, int b) {
    return a - b;
  }

  static int multiple(int a, int b) {
    return a * b;
  }

  static int divide(int a, int b) {
    return a / b;
  }

  static int abs(int a) {
    return a >= 0 ? a : a * -1;
  }
}
