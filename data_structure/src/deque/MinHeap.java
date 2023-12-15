package deque;


import queue.priority_queue.Priority;

public class MinHeap {
    ListNode[] array;
    int size = 0;

    public MinHeap(int capacity) {
        array = new ListNode[capacity];
    }

    public boolean offer(ListNode value) {
        if (isFull()){
            return false;
        }
        insert(value);
        return true;
    }

    private void insert(ListNode val) {
        int child = size++;
        int parent = (child - 1) / 2;
        while (child > 0 && array[parent].val > val.val){
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = val;
    }

    public ListNode poll() {
        if (isEmpty()) {
            return null;
        }
        ListNode e =  array[0];
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
        int min = parent;//假设parent的优先级最高
        if (left < size && array[min].val > array[left].val){
            min = left;
        }
        if (right < size && array[min].val > array[right].val){
            min = right;
        }

        if (min != parent){//有孩子的优先级大于父亲，进行交换
            swap(min,parent);
            down(min);
        }
        //没有子节点或者子节点的优先级都比父节点小 max==parent 不会进入递归
    }

    private void swap(int i,int j){
        ListNode t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public ListNode peek() {
        if (isEmpty()) {
            return null;
        }
        return array[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public int size() {
        return size;
    }
}
