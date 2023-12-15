package deque;

/**
 * 双端队列接口定义
 */
public interface Deque<E> {

    boolean offerFirst(E val);
    boolean offerLast(E val);

    E pollFirst();
    E pollLast();

    E peekFirst();
    E peekLast();

    boolean isFull();
    boolean isEmpty();
}
