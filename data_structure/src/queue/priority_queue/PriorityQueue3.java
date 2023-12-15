package queue.priority_queue;

import queue.Queue;

/**
 * 使用大顶堆实现优先级队列 插入移除的时间复杂度都是（O(logN)）
 */
public class PriorityQueue3<E extends Priority> implements Queue<E> {
    Priority[] array;
    int size = 0;

    public PriorityQueue3(int capacity) {
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
        int child = size++;
        int parent = (child - 1) / 2;
        while (child > 0 && array[parent].priority() < val.priority()){
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = val;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E e = (E) array[0];
        swap(0,size-1);
        size--;
        array[size] = null;
        //xiao
        down(0);
        return e;
    }

    private void down(int parent){
        int left = parent * 2 + 1;
        int right = parent * 2 + 2;
        int max = parent;//假设parent的优先级最高
        if (left < size && array[max].priority() < array[left].priority()){
            max = left;
        }
        if (right < size && array[max].priority() < array[right].priority()){
            max = right;
        }

        if (max != parent){//有孩子的优先级大于父亲，进行交换
            swap(max,parent);
            down(max);
        }
        //没有子节点或者子节点的优先级都比父节点小 max==parent 不会进入递归
    }

    private void swap(int i,int j){
        Priority t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[0];
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
