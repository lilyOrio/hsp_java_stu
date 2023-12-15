package deque;

import org.junit.jupiter.api.Test;
import queue.LinkedListQueue;

public class TestDeque {
    @Test
    public void test1() {
        ArrayListDeque<Integer> queue = new ArrayListDeque<>(5);
        System.out.println(queue.isEmpty());
        queue.offerFirst(1);
        queue.offerFirst(2);
        queue.offerLast(3);
        queue.offerLast(4);
        for (Integer integer : queue) {//2 1 3 4
            System.out.print(integer + " ");
        }
        System.out.println();
        System.out.println(queue.pollFirst());//2
        System.out.println(queue.pollLast());//4
        for (Integer integer : queue) {// 1 3
            System.out.print(integer + " ");
        }
        System.out.println();
        queue.offerFirst(1);
        queue.offerFirst(2);
        queue.offerLast(3);
        queue.offerLast(4);
        System.out.println(queue.isFull());
    }
}
