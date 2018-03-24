package pair1;

/**
 * @author Cay Horstmann
 * @version 1.00 2004-05-10
 */
public class Pair<T> {
    private T first;
    private T second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setFirst(T newValue) {
        first = newValue;
    }

    public void setSecond(T newValue) {
        second = newValue;
    }


    public static void main(String[] args) {
        Pair<String> p1 = new Pair<>("asd", "asdv");
        Pair<Integer> p2 = new Pair<>(12, 13);
        System.out.println(p1.getClass().toString());
        System.out.println(p2.getClass().toString());
//        if(p2 instanceof Pair<String>){} 编译报错
//        if(p2 instanceof Pair<T>){} 编译报错

        Object c = "";
        Pair<String> p = (Pair<String>) c;
    }
}
