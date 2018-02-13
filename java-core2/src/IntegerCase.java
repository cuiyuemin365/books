public class IntegerCase {

    private volatile int x;
    private int y;
    private final int z = 0;
    private final Integer z1 = null;
    private final Integer z2;
    private IntegerCase obj;

    public IntegerCase(int x, int y) {
        z2 = null;
    }

    public IntegerCase() {
        z2 = null;
        obj = this;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void add() {
        x++;
    }


    public static void main(String[] args) {

        int a = 1;
        int b = 2;
        int c = a | b;
        System.out.println(c);
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    /**
     *
     */

}

