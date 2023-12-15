package recursion;

import java.util.Arrays;

/**
 * 插入排序
 */
public class E05InsertionSort {
    public static void sort(int[] a) {
        insertion(a, 1);
    }

    //low 表示未排序部分的最左边界
    private static void insertion(int[] a, int low) {
        if (low == a.length) {
            return;
        }

        int i = low - 1;//已排序部分的最右边界
        int t = a[low];//将low索引位置值暂时存在t

        while (i >= 0 && a[i] > t) {
            a[i + 1] = a[i];
            i--;
        }
        if (i + 1 != low) {
            a[i + 1] = t;
        }

        insertion(a, low + 1);
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 27, 2, 4, 8};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
