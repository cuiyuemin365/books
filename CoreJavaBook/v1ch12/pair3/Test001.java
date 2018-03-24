package pair3;

public class Test001 {

    public static void main(String[] args)throws Exception {
        //Error 创建泛型数组
//        Pair<String>[] pairs = new Pair<String>[10];
        Class<String> stringClass = String.class;
        String str = stringClass.newInstance();

    }

}
