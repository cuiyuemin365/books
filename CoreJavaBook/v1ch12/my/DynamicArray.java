package my;

import java.util.Arrays;

public class DynamicArray<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public DynamicArray() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity >= minCapacity) {
            return;
        }
        int newCapacity = oldCapacity * 2;
        if (newCapacity < minCapacity)
            newCapacity = minCapacity;
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    public void add(E e) {
        ensureCapacity(size + 1);
        elementData[size++] = e;
    }

    public E get(int index) {
        return (E) elementData[index];
    }

    public int size() {
        return size;
    }

    public E set(int index, E element) {
        E oldValue = get(index);
        elementData[index] = element;
        return oldValue;
    }

    /**
     * 类型参数的限定：上界是其他类型参数
     *
     * @param c
     * @param <T>
     */
    public <T extends E> void addAll(DynamicArray<T> c) {
        for (int i = 0; i < c.size; i++) {
            add(c.get(i));
        }
    }

    public static int indexOf(DynamicArray<?> arr, Object elm) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).equals(elm)) {
                return i;
            }
        }
        return -1;
    }

    public void copyTo(DynamicArray<E> dest) {
        for (int i = 0; i < size; i++) {
            dest.add(get(i));
        }
    }

}
