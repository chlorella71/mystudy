package bitcamp.util;

public class LinkedList<E> {

  private Node<E> first;
  private Node<E> last;
  private int size;

  public int size() {
    return size;
  }

  public void add(E value) {
    Node<E> node = new Node<>();
    node.value = value;

    if (last == null) {
      //노드 객체가 없을 때,
      first = last = node;
    } else {
      // 기존에 노드 객체가 있을 때,
      // 마지막 노드의 다음 노드로 새로 만든 노드를 가리키게 한다.
      this.last.next = node;
      this.last = node;
    }
    this.size++;
  }

  public Object[] toArray() {
    Object[] arr = new Object[this.size];
    int index = 0;
    Node<E> node = first;
    while (node != null) {
      arr[index++] = node.value;
      //int temp = index;
      //index = index + 1;  // index++;
      //arr[temp] = node.value;
      node = node.next;
    }
    return arr;
  }

  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("무효한 인덱스입니다.");
    }

    int cursor = 0;
    Node<E> node = first;
    while (cursor++ < index) {  //++cursor <= index
      node = node.next;
    }

    return node.value;
  }

  public E set(int index, E value) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("무효한 인덱스입니다.");
    }

    int cursor = 0;
    Node<E> node = first;
    while (cursor++ < index) {  //++cursor <= index
      node = node.next;
    }

    E old = node.value;
    node.value = value;
    return old;
  }

  public void add(int index, E value) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("무효한 인덱스입니다.");
    }

    Node<E> node = new Node<>(); //E생략가능
    node.value = value;

    if (first == null) {
      first = last = node;

    } else if (index == 0) {
      node.next = first;
      first = node;

    } else if (index == size) {
      //index가 size일 때,
      last.next = node;
      last = node;

    } else {
      int cursor = 0;
      Node<E> currNode = first;
      while (++cursor < index) {
        currNode = currNode.next;
      }
      node.next = currNode.next;
      currNode.next = node;
    }

    size++;
  }

  public E remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("무효한 인덱스입니다.");
    }

    E old = null;

    if (size == 1) {
      old = first.value;
      first = last = null;

    } else if (index == 0) {
      old = first.value;
      first = first.next;

    } else {
      int cursor = 0;
      Node<E> currNode = first;
      while (++cursor < index) {
        currNode = currNode.next;
      }
      old = currNode.next.value;
      currNode.next = currNode.next.next;

      if (index == (size - 1)) {
        last = currNode;
      }
    }

    size--;
    return old;
  }

  public boolean remove(E value) {
    Node prevNode = null;
    Node node = first;

    while (node != null) {
      if (node.value.equals(value)) {
        break;
      }
      prevNode = node;
      node = node.next;

    }

    if (node == null) {
      return false;
    }

    if (node == first) {
      first = first.next;
      if (first == null) {
        last = null;
      }

    } else {
      prevNode.next = node.next;
    }

    size--;
    return true;
  }

  /*
      E old = null;

      if (size == 1) {
        old = first.value;
        first = last = null;

      } else if (index == 0) {
        old = first.value;
        first = first.next;

      } else if (index == (size - 1)) {
        int cursor = 0;
        Node<E> currNode = first;
        while (++cursor < index) {
          currNode = currNode.next;
        }
        old = currNode.next;
        currNode.next = null;
        last = currNode;

      } else {
        int cursor = 0;
        Node currNode = first;
        while (++cursor < index) {
          currNode = currNode.next;
        }
        old = currNode.next;
        currNode.next = currNode.next.next;

      }
      size--;
      return old;
    }
  */
  private static class Node<E> {

    E value;
    Node<E> next;

  }

}



    /*
    // index == 0 (맨 앞)
    else if (index == 0) {
      first = null;
    }
    // index == size (맨 뒤)
    else if (index == size) {
      last = null;
    }
    // 기타 ( 1개 일 때, 중간)
    else if (first == last) {
      first = null;
    }
    else if (index > 0 || index < size) {
      int cursor = 0;
      Node currNode = first;
      while(++cursor < index) {
        currNode = currNode.next;
      }
      cursor = 0;
      Node currNode2 = last;
      while(cursor++ < index) {
       currNode2 = currNode.next;
      }
      currNode.next = currNode2.next;
    }
    return;
     */

    /*} else if (index != 0 || index < size) {  //내가 만든거
      int cursor = index;*/


    /*Node old = last;  // 내가 만든 인덱스가 사이즈일떄
    size++;

    old.next = node;
    last = node;
    if (last == null) {
      first = last;
    }*/

    /*if (first != null) {  //내가 만들어본 삽입 알고리즘 인덱스가 0 일떄
      Node node = new Node();
      Node old = first.next;
      first.next = node;
      node.next = old;
    } else if (first == null) {
      Node node = new Node();
      first = node;
    }*/