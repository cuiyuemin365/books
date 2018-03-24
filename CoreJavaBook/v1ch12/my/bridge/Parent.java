package my.bridge;

public class Parent<T> {

    private T value;

    public T getValue() {
        return value;
    }

    public void sayHello(T value) {
        System.out.println("This is Parent Class, value is " + value);
    }
}