package review.step03;

public class Calculator {

  static int result = 0;

  static void plus(int value) {
    result += value;
  }

  static void minus(int value) {
    result -= value;
  }

  static void multiple(int value) {
    result *= value;
  }

  static void divide(int value) {
    result /= value;
  }

  static int abs(int a) {
    return a >= 0 ? a : a * -1;
  }
}
