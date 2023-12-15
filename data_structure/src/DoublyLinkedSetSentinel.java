import java.util.function.Consumer;

/**
 * 双向循环链表
 */
public class DoublyLinkedSetSentinel {
    Node sentinel = new Node(null, -1, null);

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

    public DoublyLinkedSetSentinel() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(int val) {
        Node prev = sentinel;
        Node next = sentinel.next;
        Node addNode = new Node(prev, val, next);
        prev.next = addNode;
        next.prev = addNode;
    }

    public void addLast(int val) {
        Node prev = sentinel.prev;
        Node next = sentinel;
        Node addNode = new Node(prev, val, next);
        prev.next = addNode;
        next.prev = addNode;
    }

    public void removeFirst() {
        Node removed = sentinel.next;
        if (removed == sentinel) {
            return;
        }
        Node next = removed.next;
        sentinel.next = next;
        next.prev = sentinel;
    }

    public void removeLast() {
        Node removed = sentinel.prev;
        if (removed == sentinel) {
            return;
        }
        Node prev = removed.prev;
        prev.next = sentinel;
        sentinel.prev = prev;
    }

    //根据值删除
    private void removeByValue(int val) {
        Node remove = findByValue(val);
        if (remove == null) {
            return;
        }
        Node prev = remove.prev;
        Node next = remove.next;
        prev.next = next;
        next.prev = prev;
    }

    private Node findByValue(int val) {
        Node p = sentinel.next;
        while (p != sentinel) {
            if (p.val == val) {
                return p;
            }
        }
        return null;
    }

    //遍历
    public void loop1(Consumer<Integer> consumer) {
        Node p = sentinel.next;
        while (p != sentinel) {
            consumer.accept(p.val);
            p = p.next;
        }
    }

    public void loop2(Consumer<Integer> p, Consumer<Integer> n) {
        recursion(sentinel.next, p, n);
    }

    //递归方法
    private void recursion(Node curr, Consumer<Integer> p, Consumer<Integer> n) {
        if (curr == sentinel) {
            return;
        }
        p.accept(curr.val);
        recursion(curr.next, p, n);
        n.accept(curr.val);
    }


    private Node findNode(int index) {
        int i = 0;
        Node p = sentinel.next;
        while (p != sentinel) {
            i++;
            p = p.next;
            if (i == index) {
                return p;
            }
        }
        return null;
    }
}
