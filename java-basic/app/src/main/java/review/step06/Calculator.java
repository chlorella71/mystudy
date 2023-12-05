package review.step06;

public class Calculator {

  int result = 0;

  void plus(int value) {
    this.result += value;
  }

  void minus(int value) {
    this.result -= value;
  }

  void multiple(int value) {
    this.result *= value;
  }

  void divide(int value) {
    this.result /= value;
  }

  static int abs(int a) {
    return a >= 0 ? a : a * -1;
  }
}
