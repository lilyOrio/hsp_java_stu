package queue.priority_queue;

import queue.Queue;

/**
 * 使用无序列数组实现优先级队列 插入的时间复杂度是O(n) 删除的查询的时间复杂度O(1)
 *
 * @param <E>
 */
public class PriorityQueue1<E extends Priority> implements Queue<E> {
    Priority[] array;
    int size = 0;

    public PriorityQueue1(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[size++] = value;
        return true;
    }

    //返回数组中优先级最大的元素索引
    private int maxPriority() {
        int max = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].priority() > array[max].priority()) {
                max = i;
            }
        }
        return max;
    }

    //移除索引位置元素
    private void remove(int index) {
        if (index < size - 1) {
            System.arraycopy(array, index + 1, array, index, size - 1 - index);
        }
        array[--size] = null;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        int max = maxPriority();
        E val = (E) array[max];
        remove(max);
        return val;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        int max = maxPriority();
        return (E) array[max];
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
