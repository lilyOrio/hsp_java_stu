import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class BinarySearch {
    public static void main(String[] args) {
        int[] a = {1, 5, 7, 9};
        int target = 4;
        int insertIndex;
        int i = Arrays.binarySearch(a, target);
        if (i < 0) {
            int[] b = new int[a.length + 1];
            insertIndex = -(i + 1);
            System.arraycopy(a, 0, b, 0, insertIndex);
            b[insertIndex] = target;
            System.arraycopy(a, insertIndex, b, insertIndex + 1, a.length - insertIndex);
            System.out.println(Arrays.toString(b));
        }
    }

    //基础版
    public int myBinarySearch(int[] a, int target) {
        int i = 0;
        int j = a.length - 1;//j = a.length
        while (i <= j) {//i<j
            int m = (j + i) >>> 1;
            if (target < a[m]) {
                j = m - 1;//j=m
            } else if (a[m] < target) {
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    //    左右平衡版
    public int myBinarySearch02(int[] a, int target) {
        int i = 0;
        int j = a.length;
        while (1 < j - i) {
            int m = (j + i) >>> 1;
            if (target < a[m]) {
                j = m;
            } else {
                i = m;
            }
        }
        if (target == a[i]) {
            return i;
        }
        return -1;
    }

    //存在重复元素 1,2,3,4,4,4,6,7 返回重复元素的最左边索引值 Leftmost
    public int myBinarySearch03(int[] a, int target) {
        int i = 0;
        int j = a.length - 1;
        int candidate = -1;
        while (i <= j) {
            int m = (j + i) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if (a[m] < target) {
                i = m + 1;
            } else {
                candidate = m;
                j = m - 1;
//                i = m + 1;//Rightmost
            }
        }
        return candidate;
    }

    //存在重复元素 获取更有意义的返回值
    public int myBinarySearch03_1(int[] a, int target) {
        int i = 0;
        int j = a.length - 1;
        while (i <= j) {
            int m = (j + i) >>> 1;
            if (target <= a[m]) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return i;//找到=》与目标相等的最左侧的索引位置；未找到=》大于目标值的最左侧索引位置
                    //总结：返回>=目标值的最左侧索引位置
                    //场景；求排名
    }

    public int myBinarySearch03_2(int[] a, int target) {
        int i = 0;
        int j = a.length - 1;
        while (i <= j) {
            int m = (j + i) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return i -1;
        //总结：返回<=目标值的最右侧索引位置
    }

    @Test
    public void test() {
        int[] a = {1, 3, 5, 7, 13, 56, 89};
        int target = 89;
        int i = myBinarySearch(a, target);
        System.out.println(i);
    }

    @Test
    public void test02() {
        int[] a = {1, 3, 5, 7, 13, 56, 89};
        int target = 4;
        int i = myBinarySearch02(a, target);
        System.out.println(i);
    }

    @Test
    public void test03() {
        int[] a = {1, 3, 6, 6, 6, 56, 89};
        int target = 6;
        int i = myBinarySearch03(a, target);
        System.out.println(i);
    }

    @Test
    public void test03_2() {
        int[] a = {1, 3, 6, 6, 6, 56, 89};
        int target = 6;
        int i = myBinarySearch03_2(a, target);
        System.out.println(i);
    }


}
