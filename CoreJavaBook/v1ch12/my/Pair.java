package my;

public class Pair<T> {
    private T first;
    private T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public static void main(String[] args) {
        Pair<Integer> genericMinMax = new Pair<>(1, 100);
        Integer min1 = genericMinMax.getFirst();
        Integer max1 = genericMinMax.getSecond();

        NormalPair normalMinMax = new NormalPair(1, 100);
        Integer min2 = (Integer) normalMinMax.getFirst();
        Integer max2 = (Integer) normalMinMax.getSecond();
    }
}
