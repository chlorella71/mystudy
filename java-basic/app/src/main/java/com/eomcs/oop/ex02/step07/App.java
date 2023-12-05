package com.eomcs.oop.ex02.step07; // exam0270~0280

import com.eomcs.oop.ex02.step07.util.Calculator;
// import com.eomcs.oop.ex02.step07.util.*; <= 이것도 됨


// # 관련된 기능(메서드)을 묶어 분류하기
// 1) 분류 전
// 2) 메서드를 클래스로 묶어 분류하기
// 3) 클래스 변수 도입 (=스태틱 변수, 스태틱 필드, 클래스 필드)
// 4) 클래스 변수의 한계 확인
// 5) 인스턴스 변수 확인
// 6) 인스턴스 메소드 활용
// 7) 클래스를 역할에 따라 패키지로 분류하기
//

// Calculator 클래스를 향후 유지보수하기 쉽도록 별도의 파일로 분리한다.
// - Calculator.java 파일로 분리한다.
// - import를 이용하여 클래스의 패키지 정보를 저장한다.
public class App {

  public static void main(String[] args) {
    /*
     * // 다음 식을 연산자 우선 순위를 고려하지 않고 순서대로 계산하라! // 2 + 3 - 1 * 7 / 3 = ?
     *
     * // 계산 결과를 보관할 변수는 더이상 필요가 없다. // Calculator 내부에서 계산 결과를 관리한다. // int result = 0;
     *
     * Calculator.plus(2); Calculator.plus(3); Calculator.minus(1); Calculator.multiple(7);
     * Calculator.divide(3);
     *
     * System.out.printf("result = %d\n", Calculator.result);
     *
     * Calculator.plus(3); Calculator.multiple(2); Calculator.plus(7); Calculator.divide(4);
     * Calculator.minus(5);
     *
     * System.out.printf("result = %d\n", Calculator.result);
     */

    // 다음 두 개의 식을 분리하여 계산해 보자!
    // - 연산자 우선 순위를 고려하지 않고 순서대로 계산하라!
    // 식1) 2 + 3 - 1 * 7 / 3 = ?
    // 식2) 3 * 2 + 7 / 4 - 5 = ?

    // 클래스 변수는 오직 한 개만 존재하기 때문에
    // 여러 개의 작업을 동시에 진행할 수 없다.
    // 한 개의 식을 계산한 후에 다른 식을 계산해야 한다.

    // 두 개의 식을 동시에 계산하고 싶은가?
    // 그럴려면 계산 결과를 개별적으로 관리할 수 있어야 한다.
    // 다음과 같이 각 식의 계산 결과를 보관할 메모리를 준비한다.

    // 다른 패키지의 클래스를 사용하려면 반드시 패키지명을 클래스의 이름을 함께 지정해야한다.
    // Calculator c1 = new Calculator();=>
    // com.eomcs.oop.ex02.step07.util.Calculator c1 = new
    // com.eomcs.oop.ex02.step07.util.Calculator();
    // com.eomcs.oop.ex02.step07.util.Calculator c2 = new
    // com.eomcs.oop.ex02.step07.util.Calculator();

    // import 명령을 사용하여 클래스의 패키지 이름을 미리 알려준다면,
    // 클래스를 지정할 때 패키지 이름을 나열할 필요가 없다.
    Calculator c1 = new Calculator();
    Calculator c2 = new Calculator();

    // 식1 계산:
    // 계산을 수행할 때 어떤 result변수를 사용할 것인지
    // 메서드에 알려줘야 한다.

    // 인스턴스의 주소를 메서드 앞으로 전달한다.
    // 계산을 수행할 때 계산 결과를 보관할 메모리를 메소드 호출 앞에서 전달하라!
    // - 인스턴스 메서드를 사용하면 파라미터로 인스턴스 주소를 전달할 필요가 없다.
    // Calculator.plus(c1, 2); // c1레퍼런스에 저장된 result변수 주소값을 찾아가서 그 연산을 수행하라

    c1.plus(2);
    c2.plus(3); // =>c1레퍼런스가 가리키는 인스턴스의 result변수 레퍼런스=주소변수
    // c1이가리키는 객체의 인스턴스의 result변수
    // c1이 가리키는 객체의 result변수
    // c1객체의 result변수
    // c1의 result변수
    /*
     * Calculator.multiple(c2, 2); // + 3 * 2 Calculator.plus(c2, 7); // + 3 * 2 + 7
     * Calculator.divide(c2, 4); // + 3 * 2 + 7 / 4 Calculator.minus(c2, 5); // + 3 * 2 + 7 / 4 - 5
     * = ?
     */ // => 이제 위치를 c1계산식과 섞어도 상관없음 => 인스턴스 변수를 사용하는이유 => 개별식 사용
    c1.plus(3); // + 2 + 3
    c2.multiple(2);
    c1.minus(1); // + 2 + 3 - 1
    c2.plus(7); // + 3 * 2 + 7
    c1.multiple(7); // + 2 + 3 - 1 * 7
    c2.divide(4); // + 3 * 2 + 7 / 4
    c1.divide(3); // + 2 + 3 - 1 * 7 / 3 = ?
    c2.minus(5); // + 3 * 2 + 7 / 4 - 5 = ?

    // result 변수가 어떤 인스턴스에 있는 변수인지 지정해야한다.
    System.out.printf("result = %d\n", c1.result);// => instance는 이제 heap에 있기 때문에 더이상 클래스변수?클래스명?를
                                                  // 사용할 수 없음?
    // 이렇게 계산을 완료한 후 다음 식을 계산해야 한다.

    // 식2 계산:
    // 다른 식을 계산하기 전에 기존의 계산 결과를 갖고 있는
    // result 변수를 0으로 초기화시켜야 한다.

    // 각각의 계산식 결과는 서로 다른 result변수에 보관되기 때문에
    // 새로 초기화 시킬 필요가 없다.
    // Calculator.result = 0;
    // c2.result=0;


    // c1계산결과는 c1.result에 들어있고
    // c2계산결과는 c2.result에 들어있다.
    System.out.printf("result = %d\n", c2.result);
  }
}

