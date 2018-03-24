package my;

public class Base implements Comparable<Base> {
    private int sortOrder;

    public Base(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public int compareTo(Base o) {
        if (sortOrder < o.sortOrder) {
            return -1;
        } else if (sortOrder > o.sortOrder) {
            return 1;
        } else {
            return 0;
        }
    }


    public static void main(String[] args) {
        DynamicArray<Child> childs = new DynamicArray<Child>();
        childs.add(new Child(20));
        childs.add(new Child(80));
//        Child maxChild = Utils.max(childs);//ERROR
    }
}

class Child extends Base {
    public Child(int sortOrder) {
        super(sortOrder);
    }
}