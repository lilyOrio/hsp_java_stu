package queue;

import java.util.Iterator;

/**
 * 使用环形数组实现队列(tail 不需要空出来
 */
public class ArrayQueue2<E> implements Queue<E>, Iterable<E> {
    private E[] array;
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    @SuppressWarnings("all")
    public ArrayQueue2(int capacity) {
        array = (E[]) new Object[capacity];//初始化数组
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail] = value;
        tail = (tail + 1) % array.length;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E val = array[head];
        head = (head + 1) % array.length;
        size--;
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
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;
            int count = 0;
            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() {
                E val = array[p];
                p = (p + 1) % array.length;
                count++;
                return val;
            }
        };
    }
}
