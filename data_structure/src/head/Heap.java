package head;

import java.util.PriorityQueue;

/**
 * 使用大小顶堆获取流动数组的中位数
 */
public class Heap {
    /*
    为保证数据平衡
    *1.两边数据大小一样，左边堆(默认大顶堆 保存较小的元素)数据加1
    *2.两边数据大小不一样，右边堆(默认小顶堆 保存较大的元素)数据加1

    但是不可以随便添加
    *情况1.要先将元素添加到右边的堆，然后将右边堆顶元素添加到左堆（将右边最小元素添加到左边
    *情况2.同样的，要先将元素添加到左边的堆，然后将左边堆顶元素添加到右堆（将左边最大元素添加到右边
     */

    int[] array;
    int size;
    boolean max;//true 表示大顶堆；false 表示小顶堆

    public Heap(int capacity, boolean max) {
        array = new int[capacity];
        this.max = max;
    }

    public int getSize() {
        return size;
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
        if (isFull()) {
            //扩容
            expansion();
        }
        up(val);
        size++;
        return true;
    }

    private void expansion(){
        int capacity = size + (size >> 1);
        int[] newArr = new int[capacity];
        System.arraycopy(array,0,newArr,0,size);
        array = newArr;
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
        int minOrMax = index;
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        if (left < size && (max ? array[left] > array[minOrMax] : array[left] < array[minOrMax])) {
            minOrMax = left;
        }
        if (right < size && (max ? array[right] > array[minOrMax] : array[right] < array[minOrMax])) {
            minOrMax = right;
        }
        if (minOrMax != index) {
            swap(minOrMax, index);
            down(minOrMax);
        }
    }

    //新添加元素上移
    public void up(int val) {
        int child = size;
        int parent = (child - 1) / 2;
        while (child > 0 && (max ? array[parent] < val :array[parent] > val)) {
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
