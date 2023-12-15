package recursion;

/**
 * 字符串反向打印 -- 递归
 */
public class ReversePrinting {
    public static void print1(int n, String m) {
        if (n == m.length()) {
            return;
        }
        print1(n + 1, m);
        System.out.println(m.charAt(n));
    }

    public static void print2(int n, String m) {
        if (n < 0) {
            return;
        }
        System.out.println(m.charAt(n));
        print2(n - 1, m);
    }

    public static void main(String[] args) {
        print1(1,"abcd");
        System.out.println("======================");
        print2(3,"abcd");
    }
}
