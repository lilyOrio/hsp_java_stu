import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 单向链表--带哨兵
 */
public class SinglyLinkedSetSentinel implements Iterable<Integer> {
    private Node head = new Node(666, null);//头指针


    /*
    节点类
     */
    private static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    //=====================首部添加
    public void addFirst(int value) {
//        head.next = new Node(value, head.next);
        insertIndex(0,value);
    }


    //=====================遍历节点
    public void loop1(Consumer<Integer> consumer) {
        Node p = head.next;
        while (p != null) {
            consumer.accept(p.value);
            p = p.next;
        }
    }

    public void loop2(Consumer<Integer> consumer) {
        for (Node p = head.next; p != null; p = p.next) {
            consumer.accept(p.value);
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = head.next;

            @Override
            public boolean hasNext() {//是否有下一个节点
                return p != null;
            }

            @Override
            public Integer next() {//返回当前节点的值并且指向下一个节点
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    //====================查找最后一个节点 返回值的Node的都改为私有属性，外部只关心存的值
    private Node findLast() {
        Node p;
        for (p = head; p.next != null; p = p.next) {
        }
        return p;
    }

    //=====================尾部添加
    public void addLast(int value) {
        Node last = findLast();
        last.next = new Node(value, null);
    }

    //====================索引查找
    private Node findIndex(int index) {
        int i = -1;
        for (Node p = head; p != null; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }

    public int get(int index) {
        Node node = findIndex(index);
        if (node == null) {
            throw illegalIndex(index);
        }
        return node.value;
    }

    //===================抛异常
    private IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(String.format("index[%d] 非法参数%n", index));
    }

    //===================向索引位置插入
    public void insertIndex(int index, int value) {
        Node prev = findIndex(index - 1);
        if (prev == null) {
            throw illegalIndex(index);
        }
        prev.next = new Node(value, prev.next);
    }

    //====================删除首节点
    public void removeFirst() {
        if (head.next == null) {
            System.out.println("链表为空无法删除！");
            return;
        }
        head.next = head.next.next;
    }

    //====================删除索引节点
    public void removeIndex(int index) {
        Node prev = findIndex(index - 1);
        if (prev == null) {
            throw illegalIndex(index);
        }
        Node remove = prev.next;
        if (remove == null) {
            throw illegalIndex(index);
        }
        prev.next = remove.next;
    }
}
