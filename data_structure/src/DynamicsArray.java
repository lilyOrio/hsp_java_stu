public class DynamicsArray {
    private int size = 0;
    private int capacity = 8;
    private int[] array = new int[capacity];

    public void addLast(int element) {
//        array[size] = element;
//        size++;
        add(size, element);
    }

    public void add(int index, int element) {//index 正常范围是[0,size]
        if (index >= 0 && index < size) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = element;
        size++;
    }

}
