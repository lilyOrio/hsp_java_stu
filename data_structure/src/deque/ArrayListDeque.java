package deque;

import java.util.Iterator;

public class ArrayListDeque<E> implements Deque<E>, Iterable<E> {
    int head = 0;
    int tail = 0;
    E[] array;
    int capacity = 10;

    @SuppressWarnings("all")
    public ArrayListDeque(int capacity) {
        this.capacity = capacity;
        array = (E[]) new Object[capacity + 1];
    }

    @Override
    public boolean offerFirst(E val) {
        if (isFull()){
            return false;
        }
        head = dec(head,array.length);
        array[head] = val;
        return true;
    }

    @Override
    public boolean offerLast(E val) {
        if (isFull()){
            return false;
        }
        array[tail] = val;
        tail = inc(tail,array.length);
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()){
            return null;
        }
        E e = array[head];
        array[head] = null;
        head = inc(head,array.length);
        return e;
    }

    @Override
    public E pollLast() {
        if (isEmpty()){
            return null;
        }
        tail = dec(tail,array.length);
        E e = array[tail];
        array[tail] = null;
        return e;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()){
            return null;
        }
        return array[head];
    }

    @Override
    public E peekLast() {
        if (isEmpty()){
            return null;
        }
        tail = dec(tail,array.length);
        return array[tail];
    }

    @Override
    public boolean isFull() {
        if (tail > head) {
            return tail - head == array.length;
        } else if (tail < head) {
            return head - tail == 1;
        } else {
            return false;
        }
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;
            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E e = array[p];
                p = inc(p,array.length);
                return e;
            }
        };
    }

    //返回有效索引
    private int inc(int i, int length) {
        if (i + 1 >= length) {
            return 0;
        }
        return i + 1;
    }

    private int dec(int i, int length) {
        if (i - 1 < 0) {
            return length - 1;
        }
        return i - 1;
    }
}
