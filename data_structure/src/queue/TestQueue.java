package queue;
import org.junit.jupiter.api.Test;

public class TestQueue {
    @Test
    public void test1(){
        LinkedListQueue<Integer> integers = new LinkedListQueue<>();
        System.out.println(integers.isEmpty());
        integers.offer(1);
        integers.offer(2);
        integers.offer(3);
        integers.offer(4);
        System.out.println(integers.peek());
        System.out.println(integers.isEmpty());
        for (Integer t: integers) {
            System.out.println(t);
        }
        System.out.println("============");
        System.out.println(integers.poll());
        for (Integer t: integers) {
            System.out.println(t);
        }
        System.out.println("============");
        System.out.println(integers.poll());
        System.out.println(integers.poll());
        System.out.println(integers.poll());
        System.out.println(integers.poll());
        System.out.println(integers.poll());
        for (Integer t: integers) {
            System.out.println(t + "============");
        }
        System.out.println(integers.isEmpty());

        System.out.println("======================");
        LinkedListQueue<Integer> integers1 = new LinkedListQueue<>(8);
        integers1.offer(1);
        integers1.offer(2);
        integers1.offer(3);
        integers1.offer(4);
        integers1.offer(5);
        integers1.offer(6);
        integers1.offer(7);
        integers1.offer(8);
        integers1.offer(9);
        System.out.println(integers1.offer(10));
        System.out.println(integers1.isFull());
        for (Integer t: integers1) {
            System.out.println(t);
        }
    }

    @Test
    public void test2(){
        ArrayQueue3<Integer> integers = new ArrayQueue3<>(8);
        System.out.println(integers.isEmpty());
        integers.offer(1);
        integers.offer(2);
        integers.offer(3);
        integers.offer(4);
        System.out.println(integers.peek());
        System.out.println(integers.isEmpty());
        for (Integer t: integers) {
            System.out.println(t);
        }
        System.out.println("============");
        System.out.println(integers.poll() + "--");
        for (Integer t: integers) {
            System.out.println(t);
        }
        System.out.println("============");
        System.out.println(integers.poll());
        System.out.println(integers.poll());
        System.out.println(integers.poll());
        System.out.println(integers.poll());
        System.out.println(integers.poll());
        for (Integer t: integers) {
            System.out.println(t + "============");
        }
        System.out.println(integers.isEmpty());

        System.out.println("======================");
        ArrayQueue3<Integer> integers1 = new ArrayQueue3<>(8);
        integers1.offer(1);
        integers1.offer(2);
        integers1.offer(3);
        integers1.offer(4);
        integers1.offer(5);
        integers1.offer(6);
        integers1.offer(7);
        integers1.offer(8);
        integers1.offer(9);
        System.out.println(integers1.offer(10));
        System.out.println(integers1.isFull());
        for (Integer t: integers1) {
            System.out.println(t);
        }
    }
}
