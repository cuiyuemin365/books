package my.bridge;

public class Child extends Parent<String> {

    @Override
    public String getValue() {
        System.out.println("getValue");
        return "";
    }

    //名称冲突:
    // my.bridge.Child中的sayHello(java.lang.Object)
    // 和my.bridge.Parent中的sayHello(T)具有相同疑符, 但两者均不覆盖对方
//    public void sayHello(Object value) {
//        System.out.println("This is Child class, value is " + value);
//    }

    @Override
    public void sayHello(String value) {
        System.out.println("This is Child class, value is " + value);
    }

    public static void main(String[] args) {
        Child child = new Child();
        Parent<String> parent = child;
        parent.sayHello("This is a string");
    }
}
