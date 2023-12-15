package head;

/*
    使用大小顶堆获取流动数组的中位数
    为保证数据平衡
    *1.两边数据大小一样，左边堆(默认大顶堆 保存较小的元素)数据加1
    *2.两边数据大小不一样，右边堆(默认小顶堆 保存较大的元素)数据加1

    但是不可以随便添加
    *情况1.要先将元素添加到右边的堆，然后将右边堆顶元素添加到左堆（将右边最小元素添加到左边
    *情况2.同样的，要先将元素添加到左边的堆，然后将左边堆顶元素添加到右堆（将左边最大元素添加到右边
     */
public class E04Leetcode295 {
    public E04Leetcode295() {
    }

    public void addNum(int num) {
        if (left.getSize() == right.getSize()) {
            right.offer(num);
            left.offer(right.poll());
        } else {
            left.offer(num);
            right.offer(left.poll());
        }
    }

    public double findMedian() {
        if (left.getSize() == right.getSize()) {
            return (left.peek() + right.peek()) / 2.0;
        }else {
            return left.peek();
        }
    }

    private Heap left = new Heap(10, true);
    private Heap right = new Heap(10, false);
}
