package review.step04;

public class Practice01 {

  public static void main(String[] args) {

    Calculator.plus(2);
    Calculator.plus(3);
    Calculator.minus(1);
    Calculator.multiple(7);
    Calculator.divide(3);

    System.out.printf("result = %d\n", Calculator.result);

    Calculator.plus(3);
    Calculator.multiple(2);
    Calculator.plus(7);
    Calculator.divide(4);
    Calculator.minus(5);

    System.out.printf("result = %d\n", Calculator.result);
  }
}
