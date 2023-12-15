package queue;

import java.util.Iterator;

/**
 * 使用带哨兵的单向环形链表实现队列
 */
public class LinkedListQueue<E> implements Queue<E>,Iterable<E>{
    private final Node<E> head = new Node<>(null,null);
    private Node<E> tail = head;//一开始 head 和 tail 都指向哨兵节点
    private int size;//节点数
    private int capacity = Integer.MAX_VALUE;//容量

    {//在代码块中初始化对象
        tail.next = head;//哨兵节点头尾相接
    }

    public LinkedListQueue(int capacity) {
        this.capacity = capacity;
    }

    public LinkedListQueue(){
    }

    private static class Node<E>{
        E val;
        Node<E> next;

        public Node(E val, Node<E> next) {
            this.val = val;
            this.next = next;
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;
            @Override
            public boolean hasNext() {
                return p != head;
            }

            @Override
            public E next() {
                E val = p.val;
                p = p.next;
                return val;
            }
        };
    }

    @Override
    public boolean offer(E value) {
        if (isFull()){
            return false;
        }
        Node<E> newNode = new Node<>(value,head);
        tail.next = newNode;
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()){
            return null;
        }
        Node<E> first= head.next;
        head.next = first.next;
        if (first == tail){
            tail = head;
        }
        size--;
        return first.val;
    }

    @Override
    public E peek() {
        if (isEmpty()){
            return null;
        }
        return head.next.val;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public int size() {
        return size;
    }
}
