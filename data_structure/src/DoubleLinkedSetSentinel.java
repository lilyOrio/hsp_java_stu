import java.util.Iterator;

/**
 * 双向链表--带头未哨兵
 */
public class DoubleLinkedSetSentinel implements Iterable<Integer> {
    private Node head = new Node(null, 888, null) ;
    private Node tail = new Node(null, 666, null);


    private static class Node {
        Node prev;
        int val;
        Node next;

        public Node(Node prev, int val, Node next) {
            this.prev = prev;
            this.val = val;
            this.next = next;
        }
    }

    public DoubleLinkedSetSentinel() {
        head.next = tail;
        tail.prev = head;
    }

    private Node findNode(int index) {//工具方法，根据索引位置插节点
        int i = -1;
        for (Node p = head; p != tail; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }

    //===================抛异常
    private IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(String.format("index[%d] 非法参数%n", index));
    }

    public void insertIndex(int index, int val) {
        Node prevNode = findNode(index - 1);
        if (prevNode == null) {
            throw illegalIndex(index);
        }
        Node nextNode = prevNode.next;
        Node insertNode = new Node(prevNode, val, nextNode);
        prevNode.next = insertNode;
        nextNode.prev = insertNode;
    }

    public void insertFirst(int val) {
        insertIndex(0, val);
    }

    public void removeIndex(int index) {
        Node prevNode = findNode(index - 1);
        if (prevNode == null) {
            throw illegalIndex(index);
        }
        Node removeNode = prevNode.next;
        if (removeNode == tail) {
            throw illegalIndex(index);
        }
        Node nextNode = removeNode.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    public void removeFirst() {
        removeIndex(0);
    }

    public void addLast(int val) {
        Node lastNode = tail.prev;
        Node insertNode = new Node(lastNode, val, tail);
        lastNode.next = insertNode;
        tail.prev = insertNode;
    }

    public void removeLast() {
        Node removeNode = tail.prev;
        Node prev = removeNode.prev;
        if (prev == head) {
            throw illegalIndex(0);
        }
        prev.next = tail;
        tail.prev = prev;
    }

    @Override
    public Iterator<Integer> iterator() {

        return new Iterator<Integer>() {
            Node p = head.next;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public Integer next() {
                int val = p.val;
                p = p.next;
                return val;
            }
        };
    }
}
