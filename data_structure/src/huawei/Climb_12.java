package huawei;

import java.util.Scanner;

/**
 * 12题-猴子爬山
 * <p>
 * 一天一只顽猴想去从山脚爬到山顶，途中经过一个有个 N 个台阶的阶梯，但是这猴子有一个习惯：
 * 每一次只能跳 1 步或跳 3 步，试问猴子通过这个阶梯有多少种不同的跳跃方式？
 */
public class Climb_12 {
    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(climbMountain(n));
    }

    public static int climbMountain(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        if (n == 3) {
            return 2;
        }

        //只剩一个台阶
//        if (n < 3){
//            return 1;
//        }

        return climbMountain(n - 1) + climbMountain(n - 3);
    }
}
