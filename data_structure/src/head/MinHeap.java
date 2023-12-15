package head;

//小顶堆
public class MinHeap {
    int[] array;
    int size;

    public MinHeap(int capacity) {
        array = new int[capacity];
    }

    public MinHeap(int[] array) {
        this.array = array;
        size = array.length;
    }

    //键堆
    public void heapify() {
        //从最后一个非叶子节点开始 size/2 -1
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(i);
        }

    }

    public int peek() {
        return array[0];
    }


    public int poll() {
        int val = array[0];
        swap(0, size - 1);
        size--;
        down(0);
        return val;
    }

    public boolean offer(int val) {
        if (isFull()){
            return false;
        }
        up(val);
        size ++;
        return true;
    }

    //替换堆顶元素
    public void replace(int val) {
        array[0] = val;
        down(0);
    }

    //删除索引位置元素
    public void delete(int index) {
        swap(index, size - 1);
        size--;
        down(index);
    }

    //替换索引位置元素
    public void swap(int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    //元素下潜
    public void down(int index) {
        int min = index;
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        if (left < size && array[left] < array[min]) {
            min = left;
        }
        if (right < size && array[right] < array[min]) {
            min = right;
        }
        if (min != index) {
            swap(min, index);
            down(min);
        }
    }

    //新添加元素上移
    public void up(int val) {
        int child = size;
        int parent = (child - 1) / 2;
        while (child > 0 && array[parent] > val) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = val;
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == array.length;
    }
}
