package review.step05;

public class Practice01 {

  public static void main(String[] args) {

    Calculator c1 = new Calculator();
    Calculator c2 = new Calculator();

    Calculator.plus(c1, 2);
    Calculator.plus(c1, 3);
    Calculator.minus(c1, 1);
    Calculator.multiple(c1, 7);
    Calculator.divide(c1, 3);

    System.out.printf("result = %d\n", c1.result);

    Calculator.plus(c2, 3);
    Calculator.multiple(c2, 2);
    Calculator.plus(c2, 7);
    Calculator.divide(c2, 4);
    Calculator.minus(c2, 5);

    System.out.printf("result = %d\n", c2.result);
  }
}
