package review.step02;

public class Practice01 {

  public static void main(String[] args) {
    int result = 0;
    result = Calculator.plus(2, 3);
    result = Calculator.minus(result, 1);
    result = Calculator.multiple(result, 7);
    result = Calculator.divide(result, 3);

    System.out.printf("result = %d\n", result);
  }
}
