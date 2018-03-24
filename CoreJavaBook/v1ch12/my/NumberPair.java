package my;

/**
 * 上界是类
 * 指定边界后，类型擦除时就不会转换为Object了，而是会转换为它的边界类型，这也是容易理解的。
 * @param <T>
 */
public class NumberPair<T extends Number> extends Pair<T> {

    public NumberPair(T first, T second) {
        super(first, second);
    }
}