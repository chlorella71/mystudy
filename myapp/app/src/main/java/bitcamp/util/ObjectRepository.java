package bitcamp.util;

import java.util.Arrays;

public class ObjectRepository<E> {

  public int length = 0;
  private Object[] objects = new Object[3];

  public void add(E object) {
    if (this.length == this.objects.length) {
      //System.out.println("과제를 더이상 등록할 수 없습니다.");
      int oldSize = this.objects.length;
      int newSize = oldSize + (oldSize >> 1);

      this.objects = Arrays.copyOf(this.objects, newSize);

    }
    this.objects[this.length++] = object;
  }

  public E remove(int index) {
    if (index < 0 || index >= this.length) {
      return null;
    }

    Object deleted = this.objects[index];

    System.arraycopy(this.objects, index + 1, this.objects, index, this.length - (index + 1));

    this.objects[--this.length] = null;

    return (E) deleted;
  }

  public Object[] toArray() {
    return Arrays.copyOf(this.objects, this.length);
  }

  public E[] toArray(E[] arr) {
    if (arr.length >= this.length) {
      System.arraycopy(this.objects, 0, arr, 0, this.length);
      return arr;
    }
    return (E[]) Arrays.copyOf(this.objects, this.length, arr.getClass());
  }

  public E get(int index) {
    if (index < 0 || index >= this.length) {
      return null;
    }
    return (E) this.objects[index];
  }

  public E set(int index, E object) {
    if (index < 0 || index >= this.length) {
      return null;
    }

    Object old = this.objects[index];
    this.objects[index] = object;

    return (E) old;
  }

  public int size() {
    return this.length;
  }
}