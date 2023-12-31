package review.step07.util;

public class Calculator {

  public int result = 0;

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

  public static int abs(int a) {
    return a >= 0 ? a : a * -1;
  }
}
