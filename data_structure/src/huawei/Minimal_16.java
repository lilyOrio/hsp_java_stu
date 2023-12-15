package huawei;

import java.util.Scanner;
import java.util.Stack;

/**
 * 第16题 - 找最小数
 *
 * 给一个正整数 NUM1，计算出新正整数 NUM2，NUM2 为 NUM1 中移除 N 位数字后的结果，需要使得 NUM2 的值最小
 *
 * 输入
 * 输入的第一行为一个字符串，字符串由 0-9 字符组成，记录正整数 NUM1，NUM1 长度小于 32。
 * 输入的第二行为需要移除的数字的个数，小于 NUM1 长度。
 * 如：
 * 2615371
 * 4
 *
 * 输出一个数字字符串，记录最小值 NUM2。
 * 如：131
 */
public class Minimal_16 {
    public static void main(String[] arg){
        Scanner scanner = new Scanner(System.in);
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        String nums = scanner.nextLine();
        int n = scanner.nextInt();


        for (int i = 0; i < nums.length(); i++) {
            while(n > 0 && !stack.isEmpty() && stack.peek() > nums.charAt(i)){
                stack.pop();
                n --;
            }
            stack.push(nums.charAt(i));
        }

        for (char ch : stack) {
            sb.append(ch);
        }

        System.out.println(sb.toString());
    }
}
