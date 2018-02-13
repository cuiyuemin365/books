package collection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuiyuemin on 2017/8/28.
 */
public class ArrayListCase {

    public static void main(String[] args) {
        int i = 9;
        int j = i >> 1;
        System.out.println("j:" + j);

        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        List list = list1.subList(0,2);
        list1.add("3");
        list.get(0);
        list1.clone();
        Object o = Array.newInstance(Integer.class, 4);
    }

}
