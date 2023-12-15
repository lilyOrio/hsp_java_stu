package recursion;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 冒泡排序--递归
 */
public class E04BubbleSort {

    //    普通方法
    public static void sort01(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                }
            }
        }
    }

    //    递归方法
    //j表示未排序的右边界
    private static void binary(int[] a, int j) {
        if (j == 0) {
            return;
        }
        for (int i = 0; i < j; i++) {
            if (a[i] > a[i + 1]) {
                int t = a[i];
                a[i] = a[i + 1];
                a[i + 1] = t;
            }
        }
        binary(a, j - 1);
    }

    //    递归方法 冒泡排序优化
    //j表示未排序的右边界
    private static void binary02(int[] a, int j) {
        if (j == 0) {
            return;
        }
        int x = 0;
        for (int i = 0; i < j; i++) {
            if (a[i] > a[i + 1]) {
                int t = a[i];
                a[i] = a[i + 1];
                a[i + 1] = t;
                x = i;
            }
        }
        binary(a, x);
    }

    public static void sort02(int[] a) {
        binary(a, a.length - 1);
    }

    public static void sort03(int[] a) {
        binary02(a, a.length - 1);
    }

    public static void main(String[] args) {
        int[] a1 = {0, 1, 5, 2, 4, 5, 0};
        sort01(a1);
        System.out.println(Arrays.toString(a1));
        int[] a2 = {0, 1, 5, 2, 4, 5, 0};
        sort02(a2);
        System.out.println(Arrays.toString(a2));

        int[] a3 = {1, 2, 3, 4, 8, 5};
        sort03(a3);
        System.out.println(Arrays.toString(a3));
    }


    //合并两个有序数组
    void mergeTow(int[] num,int i,int iEND,int j,int jEnd,int[] a,int k){

        if(i > iEND){
            System.arraycopy(num,j,a,k,jEnd-j+1);
            return;
        }

        if(j > jEnd){
            System.arraycopy(num,i,a,k,iEND-i+1);
            return;
        }

        if(num[i] < num[j]){
            a[k] = num[i];
            mergeTow(num,i+1,iEND,j,jEnd,a,k+1);
        }else{
            a[k] = num[j];
            mergeTow(num,i,iEND,j+1,jEnd,a,k+1);
        }
    }

    void mergeTow2(int[] num,int i,int iEND,int j,int jEnd,int[] a){
        int k = 0;
        while (i <= iEND && j <= jEnd){
            if(num[i] < num[j]){
                a[k] = num[i];
                k++;
                i++;
            }else{
                a[k] = num[j];
                k++;
                j++;
            }
        }
        if(i > iEND){
            System.arraycopy(num,j,a,k,jEnd-j+1);
            return;
        }

        if(j > jEnd){
            System.arraycopy(num,i,a,k,iEND-i+1);
            return;
        }
    }


    @Test
    public void test(){
        int[] nums1 = {1,2,3,2,5,6};
        int[] a = new int[nums1.length];
        int[] a2 = new int[nums1.length];
        mergeTow(nums1,0,2,3,5,a,0);
        mergeTow2(nums1,0,2,3,5,a2);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(a2));
    }
}
