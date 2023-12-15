package deque;

public class E023Leecode {
    public ListNode mergeKLists(ListNode[] lists) {//使用小顶堆
        MinHeap minHeap = new MinHeap(lists.length);
        //1.将链表的头节点加入小顶堆
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null){
                minHeap.offer(lists[i]);
            }
        }
        //2.不断从顶部将元素移除,移入新链表尾部
        ListNode s = new ListNode(-1,null);
        ListNode n = s;
        while(!minHeap.isEmpty()){
            ListNode min = minHeap.poll();
            n.next = min;
            n=min;

            if (min.next != null){
                minHeap.offer(min.next);
            }
        }
        return s.next;
    }
}
