package stack;

import org.junit.jupiter.api.Test;

public class TestStack {
    @Test
    public void test1(){
        ArrayStack<Integer> integers = new ArrayStack<>(10);
        System.out.println(integers.isEmpty());
        integers.push(1);
        integers.push(2);
        integers.push(3);
        integers.push(4);
        for(Integer integer : integers){
            System.out.println(integer);
        }
        System.out.println(integers.isEmpty());
        System.out.println("================");
        System.out.println(integers.peek());
        System.out.println("================");
        System.out.println(integers.pop() + "--");
        System.out.println("=================");
        for(Integer integer : integers){
            System.out.println(integer);
        }
        System.out.println("=================");
        System.out.println(integers.isFull());
        integers.push(5);
        integers.push(6);
        integers.push(7);
        integers.push(8);
        integers.push(9);
        integers.push(10);
        integers.push(11);
        for(Integer integer : integers){
            System.out.println(integer);
        }
        System.out.println(integers.isFull());

    }
}
