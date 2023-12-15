package deque;

import java.util.Iterator;

/**
 * 使用双向环形链表实现双端队列
 *
 * @param <E>
 */
public class LinkedListDeque<E> implements Deque<E>, Iterable<E> {
    int capacity = 10;
    int size = 0;

    public LinkedListDeque(int capacity) {
        this.capacity = capacity;
    }

    Node<E> sentinel = new Node<>(null, null, null);

    {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override //a node b 找到添加节点的前后节点
    public boolean offerFirst(E val) {
        if (isFull()) {
            return false;
        }
        Node<E> a = sentinel;//前
        Node<E> b = sentinel.next;//后
        Node<E> eNode = new Node<>(a, val, b);
        a.next = eNode;
        b.prev = eNode;
        size++;
        return true;
    }

    @Override
    public boolean offerLast(E val) {
        if (isFull()) {
            return false;
        }
        Node<E> a = sentinel.prev;
        Node<E> b = sentinel;
        Node<E> eNode = new Node<>(a, val, b);
        a.next = eNode;
        b.prev = eNode;
        size++;
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<E> a = sentinel;
        Node<E> removed = sentinel.next;
        Node<E> b = removed.next;
        a.next = b;
        b.prev = a;
        size--;
        return removed.val;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        Node<E> removed = sentinel.prev;
        Node<E> a = removed.prev;
        Node<E> b = sentinel;
        a.next = b;
        b.prev = a;
        size--;
        return removed.val;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.next.val;
    }

    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.prev.val;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = sentinel.next;

            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public E next() {
                E val = p.val;
                p = p.next;
                return val;
            }
        };
    }

    private static class Node<E> {
        Node<E> prev;
        E val;
        Node<E> next;

        public Node(Node<E> prev, E val, Node<E> next) {
            this.prev = prev;
            this.val = val;
            this.next = next;
        }
    }
}
