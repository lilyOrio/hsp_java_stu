package queue.priority_queue;

import queue.Queue;

/**
 * 有序数组实现优先级队列 插入的时间复杂度是O(n) 删除的查询的时间复杂度O(1)
 */
public class PriorityQueue2<E extends Priority> implements Queue<E> {
    Priority[] array;
    int size = 0;

    public PriorityQueue2(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()){
            return false;
        }
        insert(value);
        return true;
    }

    private void insert(E val) {
        int i = size - 1;
        while (i >= 0 && array[i].priority() > val.priority()) {
            array[i + 1] = array[i];
            i--;
        }
        array[i + 1] = val;
        size ++;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E e = (E) array[size - 1];
        array[--size] = null;
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[size - 1];
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
}
