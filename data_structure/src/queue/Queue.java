package queue;

public interface Queue<E> {
    //向队列尾部插入值
    boolean offer(E value);

    //从队列头部获取数值，并移除
    E poll();

    //从队列头部获取值，不移除
    E peek();

    //检查队列是否为空
    boolean isEmpty();

    //检查队列是否已经满了
    boolean isFull();

    //返回队列的元素个数
    int size();
}
