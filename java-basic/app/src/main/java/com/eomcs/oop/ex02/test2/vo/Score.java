package com.eomcs.oop.ex02.test2.vo;

public class Score {

  public String name;
  public int kor;
  public int eng;
  public int math;
  public int sum;
  public float aver;

  public void compute() {
    this.sum = this.kor + this.eng + this.math;
    this.aver = (float) this.sum / 3;
  }

  public Score(String name, int kor, int eng, int math) {
    this.name = name;
    this.kor = kor;
    this.eng = eng;
    this.math = math;

    this.compute();
    // 주소없이는 호출할 수 없음
    // compute();로 한다면 컴파일할때 컴파일러가 compute() 앞에 생성자가 받은 인스턴스 주소를 넘긴다.
  }
}
