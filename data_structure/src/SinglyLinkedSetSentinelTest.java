import org.junit.jupiter.api.Test;

public class SinglyLinkedSetSentinelTest {
    @Test
    public void test() {
        SinglyLinkedSetSentinel list = new SinglyLinkedSetSentinel();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        list.loop1(System.out::println);
        System.out.println("========================");
        list.addLast(0);
        list.loop2(System.out::println);
        System.out.println("========================");
        System.out.println(list.get(3));
        System.out.println("========================");
        list.insertIndex(5, 100);
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("========================");
        list.removeFirst();
        list.loop2(System.out::println);
        System.out.println("========================");
        list.removeIndex(2);
        list.loop2(System.out::println);
    }
    @Test
    public void test2() {
        DoubleLinkedSetSentinel list = new DoubleLinkedSetSentinel();
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);
        list.insertFirst(4);

        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("========================");
        list.addLast(0);
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("========================");
        list.insertIndex(5, 100);
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("========================");
        list.removeFirst();
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("========================");
        list.removeLast();
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("========================");
        list.addLast(1000);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    @Test
    public void test3() {
        DoublyLinkedSetSentinel list = new DoublyLinkedSetSentinel();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        list.addLast(100);

        list.loop1(System.out::println);
        System.out.println("========================");
        list.addLast(0);
        list.loop1(System.out::println);
        System.out.println("========================");
        list.removeFirst();
        list.loop1(System.out::println);
        System.out.println("========================");
        list.removeLast();
        list.loop1(System.out::println);
        System.out.println("========================");
        list.addLast(1000);
        list.loop2(System.out::println,System.out::println);
    }
}
