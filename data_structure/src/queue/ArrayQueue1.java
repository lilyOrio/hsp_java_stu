package queue;

import java.util.Iterator;

/**
 * 使用环形数组实现队列（tail 空出来用于判空
 */
public class ArrayQueue1<E> implements Queue<E>, Iterable<E> {
    private E[] array;
    private int head = 0;
    private int tail = 0;

    @SuppressWarnings("all")
    public ArrayQueue1(int capacity) {
        array = (E[]) new Object[capacity + 1];//初始化数组
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail] = value;
        tail = (tail + 1) % array.length;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E val = array[head];
        head = (head + 1) % array.length;
        return val;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        E val = array[head];
        return val;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return (tail + 1) % array.length == head;
    }

    @Override
    public int size() {
        if (isEmpty()){
            return 0;
        }
        return tail - head;
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
                E val = array[p];
                p = (p + 1) % array.length;
                return val;
            }
        };
    }
}
