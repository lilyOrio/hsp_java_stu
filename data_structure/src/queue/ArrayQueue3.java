package queue;

import java.util.Iterator;

/**
 * 使用环形数组实现队列（tail 空出来用于判空
 */
public class ArrayQueue3<E> implements Queue<E>, Iterable<E> {
    private E[] array;
    private int head = 0;
    private int tail = 0;

    @SuppressWarnings("all")
    public ArrayQueue3(int c) {
        // 1. 抛异常
        /*if ((capacity & capacity - 1) != 0) {
            throw new IllegalArgumentException("capacity 必须是2的幂");
        }*/
        // 2. 改成 2^n    13 -> 16   22 -> 32
        /*int n = (int) (Math.log10(c-1) / Math.log10(2)) + 1;
        System.out.println(n);
        c = 1 << n;
        System.out.println(1 << n);*/
        c -= 1;
        c |= c >> 1;
        c |= c >> 2;
        c |= c >> 4;
        c |= c >> 8;
        c |= c >> 16;
        c += 1;
        array = (E[]) new Object[c];//初始化数组
    }

    public ArrayQueue3() {

    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
//        array[(int)(Integer.toUnsignedLong(tail) % array.length)] = value;
        array[tail & (array.length -1)] = value;//array.length 必现是2的n次方
        tail++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
//        E val = array[(int)(Integer.toUnsignedLong(head) % array.length)];
        E val = array[head & (array.length -1)];
        head++;
        return val;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
//        return array[(int)(Integer.toUnsignedLong(head) % array.length)];
        return array[head & (array.length -1)];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return tail - head == array.length;
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
                E val = array[p & (array.length -1)];
                p++;
                return val;
            }
        };
    }
}
