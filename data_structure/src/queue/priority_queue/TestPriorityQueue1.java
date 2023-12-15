package queue.priority_queue;

import org.junit.jupiter.api.Test;

public class TestPriorityQueue1 {
    @Test
    public void test1(){
        PriorityQueue3<Entry<String>> priorityQueue1 = new PriorityQueue3<>(5);
        priorityQueue1.offer(new Entry<>("贝贝",3));
        priorityQueue1.offer(new Entry<>("jj",4));
        priorityQueue1.offer(new Entry<>("hh",1));
        priorityQueue1.offer(new Entry<>("yy",5));
        priorityQueue1.offer(new Entry<>("nn",2));

        System.out.println("peek:"+ priorityQueue1.peek());
        System.out.println("poll:"+ priorityQueue1.poll());
        System.out.println("poll:"+ priorityQueue1.poll());
        System.out.println("poll:"+ priorityQueue1.poll());
        System.out.println("poll:"+ priorityQueue1.poll());
        System.out.println("poll:"+ priorityQueue1.poll());
        System.out.println("poll:"+ priorityQueue1.poll());
    }
}
