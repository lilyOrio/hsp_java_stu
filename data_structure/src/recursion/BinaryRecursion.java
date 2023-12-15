package recursion;

/**
 * 二分法查找--递归
 */
public class BinaryRecursion {
    private static int binarySearch(int[] a, int target, int i, int j) {
        if (i > j) {
            return -1;
        }
        int m = (i + j) >>> 1;
        if (target < a[m]) {
            return binarySearch(a, target, i, m - 1);
        } else if (a[m] < target) {
            return binarySearch(a, target, m + 1, j);
        } else {
            return m;
        }
    }

    public static int search(int[] a, int target) {
        return binarySearch(a, target, 0, a.length - 1);
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{1, 2, 3, 4, 5}, 5));
        System.out.println(search(new int[]{1, 2, 3, 4, 5}, 1));
        System.out.println(search(new int[]{1, 2, 3, 4}, 5));
    }
}
