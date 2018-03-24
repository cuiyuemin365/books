package my;

public class Utils {

    public static <T> int indexOf(T[] arr, T elm) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(elm)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 上界为某个接口
     *
     * @param arr
     * @param <T>
     * @return
     */
    public static <T extends Comparable> T max(T[] arr) {
        T max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(max) > 0) {
                max = arr[i];
            }
        }
        return max;
    }

    public static <T extends Comparable<T>> T max(DynamicArray<T> arr) {
        T max = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).compareTo(max) > 0) {
                max = arr.get(i);
            }
        }
        return max;
    }
}
