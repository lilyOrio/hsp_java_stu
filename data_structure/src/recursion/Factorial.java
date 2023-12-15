package recursion;

/**
 * 递归--阶乘
 * 1*2*3*4*...*n
 */
public class Factorial {
    public static int getResult(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        return n * getResult(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(getResult(0));
    }
}
