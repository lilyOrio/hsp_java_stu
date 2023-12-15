package stack;

import java.util.Iterator;

public class ArrayStack<E> implements Stack<E>, Iterable<E> {
    private final E[] array;
    private int top;

    @SuppressWarnings("all")
    public ArrayStack(int capacity) {
        array = (E[]) new Object[capacity];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = top - 1;

            @Override
            public boolean hasNext() {
                return p >= 0;
            }

            @Override
            public E next() {
                E val = array[p--];
                return val;
            }
        };
    }

    @Override
    public boolean push(E val) {
        if (isFull()) {
            return false;
        }
        array[top++] = val;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E val = array[--top];
        return val;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[top - 1];
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public boolean isFull() {
        return top == array.length;
    }
}
