package collection;

import java.util.Random;

public class ShuffleTest {

    // Fisher-Yates 费雪耶兹洗牌算法
    public static void shuffle(int r[]) {
        Random random = new Random();
        int n = r.length;
        for (int i = n; i > 1; i--) {
            swap(r, i - 1, random.nextInt(i));
        }
    }

    private static void swap(int[] r, int i, int j) {
        int temp = r[i];
        r[i] = r[j];
        r[j] = temp;
    }

    private static void display(int[] r) {
        for (int i : r) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] r = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < 10; i++) {
            shuffle(r);
            display(r);
        }
    }
}
