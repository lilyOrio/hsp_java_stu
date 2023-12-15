package recursion;

public class Test {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static ListNode h4 = new ListNode(1, null);
    static ListNode h3 = new ListNode(2, h4);
    static ListNode h2 = new ListNode(2, h3);
    static ListNode h1 = new ListNode(1, h2);

    public static boolean isPalindrome(ListNode head) {
        ListNode m = middle(head);
        ListNode r = reversal(m);
        while (r != null) {
            if (r.val != head.val) {
                return false;
            }
            r = r.next;
            head = head.next;
        }
        return true;
    }

    private static ListNode middle(ListNode head) {//返回中间节点,偶数节点返回靠右
        ListNode p1 = head;//慢指针
        ListNode p2 = head;//快指针
        while (p2 != null && p2.next != null) {
            p2 = p2.next.next;
            p1 = p1.next;
        }
        return p1;
    }

    private static ListNode reversal(ListNode m) {//反转链表
        if (m == null || m.next == null) {
            return m;
        }
        ListNode last = reversal(m.next);//返回最后一个节点
        m.next.next = m;
        m.next = null;
        return last;
    }

    public static ListNode reverseList(ListNode o1) {
        ListNode n1 = null;
        ListNode o2 = null;
        while (o1 != null) {
            o2 = o1.next;//o2指向o1的下一个节点
            o1.next = n1;
            n1 = o1;//o1搬到新链表头部
            o1 = o2;//o1 指向o2
        }
        return n1;
    }

    public static void main(String[] args) {
        isPalindrome(h1);
    }

}

