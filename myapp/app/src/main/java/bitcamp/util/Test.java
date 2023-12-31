package bitcamp.util;

public class Test {

  public static void main(String[] args) {
    LinkedList<String> list = new LinkedList<>();
    list.add("aaa"); // aaa,
    list.add("bbb"); // aaa, bbb,
    list.add("ccc"); // aaa, bbb, ccc,
    list.add("ddd"); // aaa, bbb, ccc, ddd,

    System.out.println(list.remove("xxx")); // aaa, bbb, ccc, ddd,
    System.out.println(list.remove("ccc")); // aaa, bbb, ccc,
    System.out.println(list.remove("ddd")); // aaa, bbb,
    System.out.println(list.remove("aaa")); // bbb,
    System.out.println(list.remove("bbb")); //
    list.add("xxx"); // xxx,
    list.add("yyy"); // xxx, yyy,
    list.add("zzz"); // xxx, yyy, zzz,

    //list.add(Integer.valueOf(100));

    //list.remove(2); // aaa, bbb, ddd,
    //list.remove(2); // aaa, bbb,
    //list.remove(0); // bbb,
    //list.remove(0); //

    //list.remove(0); // bbb, ccc, ddd,
    //list.remove(0); // ccc, ddd,
    //list.remove(0); // ddd,
    //list.remove(0); //

    //list.add(0, new String("xxx")); // xxx, aaa, bbb, ccc, ddd,
    //list.add(5, new String("yyy")); // xxx, aaa, bbb, ccc, ddd, yyy,
    //list.add(1, new String("mmm")); // xxx, mmm, aaa, bbb, ccc, ddd, yyy,
    //list.add(3, new String("ttt")); // xxx, mmm, aaa, ttt, bbb, ccc, ddd, yyy,
    //list.add(7, new String("ppp")); // xxx, mmm, aaa, ttt, bbb, ccc, ddd, ppp, yyy,

    //System.out.println(list.first.value);
    //System.out.println(list.first.next.value);
    //System.out.println(list.first.next.next.value);
    //System.out.println(list.last.value);

    /*Node node = list.first;
    System.out.println(node.value);

    node = node.next;
    System.out.println(node.value);

    node = node.next;
    System.out.println(node.value);

    node = node.next;
    System.out.println(node.value);*/

    String[] arr = list.toArray(new String[0]);
    for (String value : arr) {
      System.out.printf("%s, ", value);
    }
    System.out.println();

    //System.out.println(list.get(0));
    //System.out.println(list.get(1));
    //System.out.println(list.get(2));
    //System.out.println(list.get(3));
    //System.out.println(list.get(4));
  }

}