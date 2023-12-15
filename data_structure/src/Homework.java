public class Homework {
    public static void main(String[] args) {
        int res = oX("Ox2C");
        System.out.println(res);
    }
    private static int oX(String str) {
        int[] nums = new int[str.length() - 2];
        int res = 0;
        int idxStart = 2;
        for (int i = 0; i < nums.length; i++) {
            char c = str.charAt(idxStart + i);
            switch (c) {
                case'A':
                    nums[i] = 10;
                    break;
                case'B':
                    nums[i] = 11;
                    break;
                case'C':
                    nums[i] = 12;
                    break;
                case'D':
                    nums[i] = 13;
                    break;
                case'E':
                    nums[i] = 14;
                    break;
                case'F':
                    nums[i] = 15;
                    break;
                default:
                    nums[i] = c - '0';
            }
        }
        for(int i = 0;i<nums.length;i++){
            res = res*16 + nums[i];
        }
        return res;
    }
}
